package com.asurplus.common.quartz;

import cn.hutool.core.collection.CollectionUtil;
import com.asurplus.common.exception.CustomException;
import com.asurplus.monitor.entity.SysQuartzInfo;
import com.asurplus.monitor.mapper.SysQuartzInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * quartz工具类
 */
@Slf4j
@Component
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SysQuartzInfoMapper sysQuartzInfoMapper;

    /**
     * 项目启动时，初始化定时任务
     */
    @PostConstruct
    public void init() {
        // 查询定时任务
        List<SysQuartzInfo> list = sysQuartzInfoMapper.selectList(null);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        // 主要是防止手动修改数据库导致未同步到定时任务处理
        for (SysQuartzInfo item : list) {
            add(item.getClassName(), item.getCronExpression(), item.getParam(), item.getStatus());
        }
    }

    /**
     * 添加定时任务
     *
     * @param className
     * @param cronExpression
     * @param param
     * @param status
     */
    public void add(String className, String cronExpression, String param, Integer status) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(className).getClass()).withIdentity(className).usingJobData("param", param).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(className).withSchedule(scheduleBuilder).build();
            // 创建定时任务
            scheduler.scheduleJob(jobDetail, trigger);
            // 停止
            if (1 == status) {
                stop(className);
            }
        } catch (Exception e) {
            throw new CustomException("添加定时任务失败");
        }
    }

    /**
     * @param className
     * @param cronExpression
     * @param param
     * @param status
     */
    public void update(String className, String cronExpression, String param, Integer status) {
        try {
            // 判断是否存在，存在先删除
            if (scheduler.checkExists(JobKey.jobKey(className))) {
                scheduler.deleteJob(JobKey.jobKey(className));
            }
            // 再创建
            add(className, cronExpression, param, status);
        } catch (Exception e) {
            throw new CustomException("添加定时任务失败");
        }
    }

    /**
     * 暂停任务
     *
     * @param className
     */
    public void stop(String className) {
        try {
            scheduler.pauseJob(JobKey.jobKey(className));
        } catch (SchedulerException e) {
            // 暂停定时任务失败
            throw new CustomException("暂停定时任务失败");
        }
    }

    /**
     * 恢复任务
     *
     * @param className
     */
    public void start(String className) {
        try {
            scheduler.resumeJob(JobKey.jobKey(className));
        } catch (SchedulerException e) {
            // 暂停定时任务失败
            throw new CustomException("暂停定时任务失败");
        }
    }

    /**
     * 立即执行一次
     *
     * @param className
     */
    public void run(String className) {
        try {
            scheduler.triggerJob(JobKey.jobKey(className));
        } catch (SchedulerException e) {
            // 暂停定时任务失败
            throw new CustomException("执行定时任务失败");
        }
    }

    /**
     * 删除定时任务
     *
     * @param className
     */
    public void delete(String className) {
        try {
            // 停止触发器
            scheduler.pauseTrigger(TriggerKey.triggerKey(className));
            // 移除触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(className));
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(className));
        } catch (Exception e) {
            throw new CustomException("删除定时任务失败");
        }
    }

    /**
     * 根据类名获取类
     *
     * @param className
     * @return
     * @throws Exception
     */
    private static Job getClass(String className) throws Exception {
        Class<?> class1 = Class.forName(className);
        return (Job) class1.newInstance();
    }
}
