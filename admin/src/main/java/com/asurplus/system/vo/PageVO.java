package com.asurplus.system.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * PageVO是一个包含6个属性的对象
 * 1、pageNum-页序号, Integer
 * 2、pageSize-每页记录条数, Integer
 * 3、field-排序字段, String
 * 4、isAsc-排序方式（是否正序）, boolean
 * 5、beginTime-开始时间, String
 * 6、endTime-结束时间, String
 * 剩下的就是一些get和set方法，还有toString方法
 */
public class PageVO {

    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 条数
     */
    private Integer pageSize;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序方式(ASC,DESC)
     */
    private boolean isAsc;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        if (StringUtils.isBlank(isAsc)) {
            this.isAsc = true;
            return;
        }
        // 兼容前端排序类型
        if ("ascending".equals(isAsc)) {
            this.isAsc = true;
            return;
        }
        if ("descending".equals(isAsc)) {
            this.isAsc = false;
            return;
        }
        this.isAsc = true;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", field='" + field + '\'' +
                ", isAsc=" + isAsc +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
