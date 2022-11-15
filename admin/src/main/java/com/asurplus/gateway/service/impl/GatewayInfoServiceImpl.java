package com.asurplus.gateway.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.mapper.GatewayInfoMapper;
import com.asurplus.gateway.service.GatewayInfoService;
import com.asurplus.mytcp.TcpConnection;
import com.asurplus.mytcp.ThreadReceive;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.Arrays;

import static com.asurplus.App.socketMap;

/**
 * @author chenluyao
 */
@Service
public class GatewayInfoServiceImpl extends ServiceImpl<GatewayInfoMapper, GatewayInfo> implements GatewayInfoService {

    private static final Logger log = LoggerFactory.getLogger(GatewayInfoServiceImpl.class);

    @Override
    public TableInfo list(GatewayInfo gatewayInfo) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<GatewayInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(gatewayInfo.getName())) {
            queryWrapper.lambda().like(GatewayInfo::getName, gatewayInfo.getName());
        }
        if (StringUtils.isNotBlank(gatewayInfo.getIp())) {
            queryWrapper.lambda().like(GatewayInfo::getIp, gatewayInfo.getIp());
        }
        if (null != gatewayInfo.getPort()) {
            queryWrapper.lambda().like(GatewayInfo::getPort, gatewayInfo.getPort());
        }
        if (null != gatewayInfo.getType()) {
            queryWrapper.lambda().eq(GatewayInfo::getType, gatewayInfo.getType());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(GatewayInfo::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(GatewayInfo::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(GatewayInfo::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES getById(Integer id) {
        //        入参检验
        if (null == id) {
            return RES.no("输入数据错误！");
        }
        return RES.ok(this.baseMapper.selectById(id));
    }

    @SneakyThrows
    @Override
    public RES connect(Integer id) {
//        log.info("id==" + id);
        GatewayInfo gatewayInfo = this.baseMapper.selectById(id);
        String ip = gatewayInfo.getIp();
        Integer port = gatewayInfo.getPort();
        String addr = ip + ":" + port;
        Socket socket = null;
        if (null != socketMap.get(addr)) {
            socket = socketMap.get(addr);
        }
        log.info("==map==");
        for (String k : socketMap.keySet()) {
            log.info(k + socketMap.get(k));
        }
        log.info("==map==");
        if (socket != null) {
            if (socketMap.containsKey(addr) && !socket.isClosed() && socket.isConnected() && !TcpConnection.isServerClose(socket)) {
                log.info("别点了，已经连接上了" + addr);
                return RES.ok("别点了，已经连接上了" + addr);
            } else if (TcpConnection.isServerClose(socket)) {
                log.info("网关断开连接" + addr);
//                return RES.no("网关断开连接，请检查后再试"+addr);
            } else {
                log.info("本地断开连接，正在准备重连");
            }

        }
//        重新new一个socket
        TcpConnection tcpConnection = new TcpConnection();
//        异步方法
        socket = tcpConnection.connect(ip, port).get();
        if (socket == null) {
            log.info("连接失败了" + addr);
//          修改网关状态为未连接
//            GatewayInfo gateway = new GatewayInfo();
//            gateway.setStatus(1);
//            gateway.setId(id);
//            this.baseMapper.updateById(gateway);
//            log.info("网关状态修改为未连接");
            return RES.no("连接失败了" + addr);
        }
        socketMap.put(addr, socket);

        log.info("==map==");
        for (String k : socketMap.keySet()) {
            log.info(k + socketMap.get(k));
        }
        log.info("==map==");
        log.info("连接成功了" + addr);

        //开启接收线程
        new ThreadReceive(socket).start();
        return RES.ok("连接成功了" + addr);
    }

    @Override
    public RES updateStatus(GatewayInfo gatewayInfo) {
        log.info("id==" + gatewayInfo.getId() + ",status==" + gatewayInfo.getStatus());
        this.baseMapper.updateById(gatewayInfo);
        return RES.ok();
    }

    @Override
    public RES add(GatewayInfo gatewayInfo) {
        if (null == gatewayInfo) {
            return RES.no("提交错误");
        }
        if (null == gatewayInfo.getName()) {
            return RES.no("请输入网关名称");
        }
        if (null == gatewayInfo.getIp()) {
            return RES.no("请输入IP地址");
        }
        if (null == gatewayInfo.getPort()) {
            return RES.no("请输入端口号");
        }

        //查重
        LambdaQueryWrapper<GatewayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GatewayInfo::getPort, gatewayInfo.getPort());
        int count = this.baseMapper.selectCount(queryWrapper);
        if (0 < count) {
            return RES.no("端口已存在");
        }
        gatewayInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.insert(gatewayInfo);
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }

        LambdaQueryWrapper<GatewayInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(GatewayInfo::getId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper2);
        return RES.ok();
    }

    @Override
    public RES update(GatewayInfo gatewayInfo) {
        if (null == gatewayInfo || null == gatewayInfo.getId() || 0 == gatewayInfo.getId()) {
            return RES.no("数据错误");
        }
        if (StringUtils.isBlank(gatewayInfo.getIp())) {
            return RES.no("请输入网关IP地址");
        }
        if (null == gatewayInfo.getPort()) {
            return RES.no("请输入端口号");
        }
        LambdaQueryWrapper<GatewayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(GatewayInfo::getId, gatewayInfo.getId());
        queryWrapper.eq(GatewayInfo::getPort, gatewayInfo.getPort());
        int rows = this.baseMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("端口号已存在");
        }
        gatewayInfo.setCreateUser(StpUtil.getLoginIdAsInt());
        this.baseMapper.updateById(gatewayInfo);
        return RES.ok();
    }

}
