package com.asurplus.gateway.service;

import com.asurplus.common.utils.RES;
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Luyao
 * @since 2022-9-15
 */

public interface GatewayInfoService extends IService<GatewayInfo> {
    /**
     * @return List
     * 返回不分页的网关信息
     */
    RES listGateway();
    /**
     * @param gatewayInfo 查询的参数
     * @return 网关信息表
     * 根据参数查询网关信息
     */

    TableInfo list(GatewayInfo gatewayInfo);
    /**
     * 根据id查询
     */
    RES getById(Integer id);

//    /**
//     * 连接网关
//     * @param id
//     * @return
//     */
//    RES connect(Integer id);

    RES updateStatus(GatewayInfo gatewayInfo);

    /**
     * 将所有网关状态设为未连接
     * @param status 网关状态
     * @return RES
     */
    RES setAllStatus(int status);
    /**
     * 新增
     */
    RES add(GatewayInfo gatewayInfo);
    /**
     * 删除
     */
    RES delete(Integer[] ids);

    /**
     * 修改
     */
    RES update(GatewayInfo gatewayInfo);
}

