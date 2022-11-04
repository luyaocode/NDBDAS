package com.asurplus.gateway.service;

<<<<<<< HEAD
=======
import com.asurplus.common.utils.RES;
import com.asurplus.config.entity.SysParam;
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Luyao
 * @since 2022-9-15
 */

public interface GatewayService extends IService<GatewayInfo> {
    /**
     * 分页查询
     */
    TableInfo list(GatewayInfo gatewayInfo);
<<<<<<< HEAD
}
=======
    /**
     * 根据id查询
     */
    RES getById(Integer id);
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

>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
