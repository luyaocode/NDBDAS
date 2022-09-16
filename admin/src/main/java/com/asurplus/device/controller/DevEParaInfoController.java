package com.asurplus.device.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.device.entity.DevEParaInfo;
import com.asurplus.device.service.DevEParaInfoService;
import com.asurplus.mytcp.TcpConnection;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.asurplus.App.socks;

/**
 * 电参量信息表 前端控制器
 * @author luyao
 * @since 2022/9/13
 */
@RestController
@RequestMapping("/device/dev-epara-info")
public class DevEParaInfoController {

    @Autowired
    private DevEParaInfoService devEParaInfoService;

//    /**
//     * 查询所有记录，按照时间逆序
//     * @param devEParaInfo
//     * @return eParaList
//     */
//    @SaCheckPermission("system:role:list")
//    @GetMapping("A01-01")
//    public List<DevEParaInfo> list(DevEParaInfo devEParaInfo) {
////        System.out.println("运行了");
//        QueryWrapper<DevEParaInfo> wrapper=new QueryWrapper<>();
//        wrapper.orderByDesc("create_time");
//        List<DevEParaInfo> eParaList = devEParaInfoService.list(wrapper);
//        System.out.println("返回数据："+eParaList);
//        return eParaList;
//    }

    /**
     * 仅查询时间最近一条的记录
     * @return one
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("A01-01")
    public List<DevEParaInfo> getNowTime() {
        List<DevEParaInfo> list=new ArrayList<>();
        //构造查询条件
        QueryWrapper<DevEParaInfo> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        list.add(devEParaInfoService.getOne(wrapper));
        System.out.println("返回数据："+list);
        return list;
    }

    /**
     * 发送指令
     */
    @SaCheckPermission("system:role:list")
    @PostMapping("A01-01/0x03")
    public String sendCommand(@RequestBody String command){
//        取第一个socket
        Socket sock=socks[0];
        String sendStr="0808 0000 0006 01 03 9c41 0001";
        System.out.println("[0x03] "+command);
//        System.out.println(sock);
        TcpConnection.send(sock,sendStr);
        return sock+sendStr+"命令帧发送成功！";

    }


}
