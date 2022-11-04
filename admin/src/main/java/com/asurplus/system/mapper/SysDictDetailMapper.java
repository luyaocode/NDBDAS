package com.asurplus.system.mapper;

import com.asurplus.system.entity.SysDictDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典配置 Mapper 接口
 * </p>
 *
 * @author lizhou
 * @since 2021-07-18
 */
public interface SysDictDetailMapper extends BaseMapper<SysDictDetail> {

    String getDictDataByTypeAndValue(@Param("dictCode") String dictCode, @Param("code") String code);

    List<SysDictDetail> listSysDictDetailByDictCode(String dictCode);
}
