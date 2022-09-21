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
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.asurplus.App.socks;

/**
 * 电参量信息表 前端控制器
 *
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
     * 查询当前电参量
     *
     * @return one
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("A01-01")
    public DevEParaInfo getNow() {
        DevEParaInfo info = new DevEParaInfo();
        //构造查询条件
        QueryWrapper<DevEParaInfo> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        info = devEParaInfoService.getOne(wrapper);
        System.out.println("返回当前电参量：" + info);
        return info;
    }

    /**
     * 查询历史电参量
     *
     * @return all
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("A01-01-all")
    public List<DevEParaInfo> getAll() {
        List<DevEParaInfo> list = new ArrayList<>();
        //构造查询条件
        QueryWrapper<DevEParaInfo> wrapper = new QueryWrapper<>();
//        按照时间正序
        wrapper.orderByAsc("create_time");
//        wrapper.last("limit 1");
        list = devEParaInfoService.list(wrapper);
        System.out.println("返回历史电参量：" + list);
        return list;
    }

    /**测试用
     * 插入一条记录
     * 电压0-300
     * 电流0-100
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("A01-01-add")
    public String saveOne() {
//        System.out.println("++++++++++++++++++测试+++++++++++++++++++++");
        DevEParaInfo info = new DevEParaInfo();
        Random rand = new Random();
        float voltA = 220+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*10;
        float voltB = 220+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*10;
        float voltC = 220+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*10;
        float currA = 50+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*5;
        float currB = 50+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*5;
        float currC = 50+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*5;

        info.setVoltA(voltA);
        info.setVoltB(voltB);
        info.setVoltC(voltC);
        info.setCurrA(currA);
        info.setCurrB(currB);
        info.setCurrC(currC);

        info.setDevId("A01-01");
        info.setDevName("第一台测试设备，自动生成数据");
        info.setCreateTime(new Date());
        boolean save = devEParaInfoService.save(info);
        System.out.println(save ? "插入成功！" : "插入失败！");
        return (save ? "插入成功！" : "插入失败！");
    }

    /**
     * 发送指令
     */
    @SaCheckPermission("system:role:list")
    @PostMapping("A01-01/0x03")
    public String sendCommand(@RequestBody String command) {
//        取第一个socket
        Socket sock = socks[0];
        String sendStr = "0808 0000 0006 01 03 9c41 0001";
        System.out.println("[0x03] " + command);
//        System.out.println(sock);
        TcpConnection.send(sock, sendStr);
        return sock + sendStr + "命令帧发送成功！";

    }


}
