package com.asurplus.system.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典管理 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
public interface SysDictService extends IService<SysDict> {

    TableInfo list(SysDict sysDict);

    RES listSelect();

    RES getSysDict(Integer id);

    RES add(SysDict sysDict);

    RES update(SysDict sysDict);

    RES delete(Integer[] dictCodes);

    RES optionselect();
}
