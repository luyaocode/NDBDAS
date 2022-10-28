package com.asurplus.device.service;

import com.asurplus.common.utils.RES;
import com.asurplus.device.entity.DevLocInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (DevLocInfo)表服务接口
 *
 * @author makejava
 * @since 2022-10-19 15:52:30
 */
public interface DevLocInfoService extends IService<DevLocInfo> {
    List<DevLocInfo> list(DevLocInfo devLocInfo);

    RES treeSelect(DevLocInfo devLocInfo);

}
