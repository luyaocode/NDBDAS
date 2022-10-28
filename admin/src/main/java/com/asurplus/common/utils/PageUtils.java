package com.asurplus.common.utils;

import com.asurplus.system.vo.PageVO;

/**
 * PageUtils工具类
 * PAGE_NUM="pageNum", String
 * PAGE_SIZE="pageSize", String
 * FIELD="orderByColumn", String
 * ISASC = "isAsc"
 * BEGINTIME = "beginTime"
 * ENDTIME = "endTime"
 */
public class PageUtils {

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String FIELD = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String ISASC = "isAsc";

    /**
     * 开始时间
     */
    private static final String BEGINTIME = "beginTime";

    /**
     * 结束时间
     */
    private static final String ENDTIME = "endTime";

    /**
     * 获取分页对象参数
     *
     * @return
     */
    public static PageVO getPageVO() {
        PageVO pageVO = new PageVO();
        pageVO.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageVO.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageVO.setField(StringUtils.isNotBlank(ServletUtils.getParameter(FIELD)) ? StringUtils.toUnderScoreCase(ServletUtils.getParameter(FIELD)) : null);
        pageVO.setIsAsc(ServletUtils.getParameter(ISASC));
        pageVO.setBeginTime(ServletUtils.getParams(BEGINTIME));
        pageVO.setEndTime(ServletUtils.getParams(ENDTIME));
        return pageVO;
    }
}
