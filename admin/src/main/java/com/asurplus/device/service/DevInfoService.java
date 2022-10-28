package com.asurplus.device.service;

import com.asurplus.common.utils.RES;
import com.asurplus.device.entity.DevInfo;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (DevInfo)表服务接口
 *
 * @author makejava
 * @since 2022-10-19 14:27:18
 */
public interface DevInfoService extends IService<DevInfo> {
//    回收站外设备列表
    TableInfo list(DevInfo devInfo,boolean isExport);
//    回收站设备列表
    TableInfo binList();

    RES add(DevInfo devInfo);

    RES getById(Integer id);

    RES update(DevInfo devInfo);

    RES update(Integer[] ids);
//    假删除
    RES delete(Integer[] ids);

//    彻底删除
    RES remove(Integer[] ids);


}
