package com.asurplus.device.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.asurplus.device.entity.DevInfo;
import lombok.Data;

@Data
public class DevInfoVO extends DevInfo {

    @Excel(name = "所属楼层", width = 15)
    private String locName;

    @Excel(name = "创建者", width = 15)
    private String createUserName;

    @Excel(name = "修改者", width = 15)
    private String updateUserName;

}
