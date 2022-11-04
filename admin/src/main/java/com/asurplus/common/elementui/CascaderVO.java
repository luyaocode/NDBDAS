package com.asurplus.common.elementui;

import lombok.Data;

import java.util.List;

/**
 * 级联选择
 */
@Data
public class CascaderVO {

    /**
     * id
     */
    private Integer value;

    /**
     * name
     */
    private String label;

    /**
     * 子级
     */
    private List<CascaderVO> children;
}
