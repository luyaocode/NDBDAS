package com.asurplus.system.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 后台返回给数据表格的数据格式
 *
 * @author lizhou
 */
@Data
public class TableInfo {

    /**
     * 接口状态
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 接口数据长度
     */
    private Long total;

    /**
     * 接口数据
     */
    private List<?> rows;

    private static TableInfo resultData(Integer code, String msg, Long count, List<?> data) {
        TableInfo res = new TableInfo();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(count);
        res.setRows(data);
        return res;
    }

    /**
     * 返回数据给表格
     */
    public static TableInfo ok(IPage iPage) {
        return resultData(200, "查询成功", iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 返回数据给表格
     */
    public static TableInfo ok(Long count, List<?> data) {
        return resultData(200, "查询成功", count, data);
    }

    /**
     * 返回数据给表格
     */
    public static TableInfo ok(Integer count, List<?> data) {
        return resultData(200, "查询成功", count.longValue(), data);
    }

    /**
     * 返回数据给表格
     */
    public static TableInfo no() {
        return resultData(500, "查询失败", 0L, null);
    }
}
