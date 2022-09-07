package com.asurplus.system.service;

import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysDictDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典配置 服务类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
public interface SysDictDetailService extends IService<SysDictDetail> {

    TableInfo list(SysDictDetail sysDictDetail);

    RES getSysDictDetail(Integer id);

    RES add(SysDictDetail sysDictDetail);

    RES update(SysDictDetail sysDictDetail);

    RES delete(Integer[] dictCodes);

    /**
     * 根据字典编码，和字典值查询字典数据
     */
    String getDictDataByTypeAndValue(String dictCode, String code);

    /**
     * 根据字典编码查询字典配置信息
     * 提供给下拉框使用
     */
    List<SysDictDetail> listSysDictDetailByDictCode(String dictCode);
}
