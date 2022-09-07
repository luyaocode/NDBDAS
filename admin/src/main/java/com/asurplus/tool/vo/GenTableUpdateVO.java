package com.asurplus.tool.vo;

import com.asurplus.tool.entity.GenTable;
import com.asurplus.tool.entity.GenTableColumn;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GenTableUpdateVO extends GenTable {

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 列
     */
    @TableField(exist = false)
    private List<GenTableColumn> columns;

}
