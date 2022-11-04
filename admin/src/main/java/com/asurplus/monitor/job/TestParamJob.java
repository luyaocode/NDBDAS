package com.asurplus.monitor.job;

import com.asurplus.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName TestJob
 * 测试带参定时任务
 * @Author Lizhou
 * @Date 2020-07-21 10:58:58
 **/
@Slf4j
public class TestParamJob implements Job {

    /**
     * 根据 SysQuartzInfo 实体类中的字段名改变
     */
    private String param;

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务启动：" + DateUtils.getYmdHms() + "，参数：" + this.param);
    }
}
