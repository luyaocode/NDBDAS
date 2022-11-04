package com.asurplus.common.aspect;

import com.alibaba.fastjson.JSON;
import com.asurplus.system.service.LoginService;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessStatus;
import com.asurplus.common.ip.IpUtils;
import com.asurplus.common.utils.ServletUtils;
import com.asurplus.common.utils.StringUtils;
import com.asurplus.monitor.entity.SysOperLog;
import com.asurplus.monitor.service.SysOperLogService;
import com.asurplus.system.entity.SysUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author Lizhou
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysOperLogService sysOperLogService;
    @Autowired
    private LoginService loginService;

    /**
     * 开始时间
     */
    private long startTime = 0L;
    /**
     * 结束时间
     */
    private long endTime = 0L;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.asurplus.common.annotation.SysLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBeforeInServiceLayer() {
        startTime = System.currentTimeMillis();
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            SysLog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            // 获取当前的用户
            SysUserInfo sysUserInfo = loginService.getUserInfoFromSession();
            // *========操作日志=========*//
            SysOperLog operLog = new SysOperLog();
            // 操作状态
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // IP地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            operLog.setIp(ip);
            // 返回参数
            operLog.setResult(JSON.toJSONString(jsonResult));
            // 请求url
            operLog.setUrl(ServletUtils.getRequest().getRequestURI());
            if (null != sysUserInfo) {
                operLog.setOperName(sysUserInfo.getAccount());
            }
            // 有异常
            if (null != e) {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setFun(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog);
            //执行结束时间
            endTime = System.currentTimeMillis();
            operLog.setSpendTime(endTime - startTime);
            // 保存数据库
            sysOperLogService.saveSysOperLog(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, SysLog log, SysOperLog operLog) throws Exception {
        // 设置action动作
        operLog.setType(log.type().ordinal());
        // 设置标题
        operLog.setTitle(log.title());
        // 是否需要保存request，参数和值
        if (log.saveParam()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog) throws Exception {
        String requestMethod = operLog.getFun();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private SysLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(SysLog.class);
        }
        return null;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (StringUtils.isNotNull(paramsArray[i]) && !isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
                return iter.next() instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry entry = (Map.Entry) iter.next();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof BindingResult;
    }
}
