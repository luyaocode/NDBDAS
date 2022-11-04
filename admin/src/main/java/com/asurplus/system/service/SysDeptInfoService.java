package com.asurplus.system.service;

import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDeptInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
public interface SysDeptInfoService extends IService<SysDeptInfo> {

    List<SysDeptInfo> list(SysDeptInfo sysDeptInfo);

    RES getSysDeptInfo(Integer id);

    RES add(SysDeptInfo sysDeptInfo);

    RES update(SysDeptInfo sysDeptInfo);

    RES delete(Integer[] ids);

    RES treeSelect(SysDeptInfo sysDeptInfo);
}
