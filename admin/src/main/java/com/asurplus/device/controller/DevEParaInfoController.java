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

import static com.asurplus.App.socketMap;


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
    @GetMapping("/{devId}")//{}:获取接口url中的字段devId
    public DevEParaInfo getNow(@PathVariable String devId) {
        DevEParaInfo info;
        //构造查询条件
        QueryWrapper<DevEParaInfo> wrapper = new QueryWrapper<>();
//        System.out.println("从request获取到的devId="+devId);
        wrapper.eq("dev_id",devId);//指定查询字段
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        info = devEParaInfoService.getOne(wrapper);
        System.out.println("["+devId+"]返回当前电参量：" + info);
        return info;
    }

    /**
     * 查询历史电参量
     *
     * @return all
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("/A01-01-all")
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
     * 非均衡负载情况下
     * 总有功P=P1+P2+P3=U1*I1*cosφ1+U2*I2*cosφ2+U3*I3*cosφ3
     * 总无功Q=Q1+Q2+Q3=U1*I1*sinφ1+U2*I2*sinφ2+U3*I3*sinφ3
     * 总视在S=P1+P2+P3=U1*I1+U2*I2+U3*I3
     * 电压220±10
     * 电流5±1
     * 功率因数0.9±0.1
     *
     * @RequestParam 接收axios传来的参数devId
     */
    @SaCheckPermission("system:role:list")
//    规范来讲，@RequestMapping和@GetMapping最前面都要加斜杠
    @GetMapping("/testAdd/{devId}")
    public String saveOne(@PathVariable String devId) {
//        System.out.println("++++++++++++++++++测试+++++++++++++++++++++");
        System.out.println("devId=="+devId);
        DevEParaInfo info = new DevEParaInfo();
        Random rand = new Random();
        float voltA = 220+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*10;
        float voltB = 220+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*10;
        float voltC = 220+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*10;
        float currA = 5+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*1;
        float currB = 5+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*1;
        float currC = 5+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*1;
        float powFacA= (float) (0.9+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*0.1);
        float powFacB= (float) (0.9+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*0.1);
        float powFacC= (float) (0.9+rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)*0.1);
        float apprPowA=voltA*currA;
        float apprPowB=voltA*currA;
        float apprPowC=voltA*currA;
        float apprPowTotal=apprPowA+apprPowB+apprPowC;
        float actiPowA=apprPowA*powFacA;
        float actiPowB=apprPowA*powFacB;
        float actiPowC=apprPowA*powFacC;
        float actiPowTotal=actiPowA+actiPowB+actiPowC;
        float wattPowA= (float) (apprPowA*powFacA*Math.pow(1-powFacA*powFacA,0.5));
        float wattPowB= (float) (apprPowB*powFacB*Math.pow(1-powFacB*powFacB,0.5));
        float wattPowC= (float) (apprPowC*powFacC*Math.pow(1-powFacC*powFacC,0.5));
        float wattPowTotal=wattPowA+wattPowB+wattPowC;

        info.setDevId(devId);
        info.setDevName("测试设备");
        info.setRemark("");
        info.setCreateTime(new Date());
        info.setFreq(50);
        info.setVoltA(voltA);
        info.setVoltB(voltB);
        info.setVoltC(voltC);
        info.setCurrA(currA);
        info.setCurrB(currB);
        info.setCurrC(currC);
        info.setActiPowA(actiPowA);
        info.setActiPowB(actiPowB);
        info.setActiPowC(actiPowC);
        info.setActiPowTotal(actiPowTotal);
        info.setWattPowA(wattPowA);
        info.setWattPowB(wattPowB);
        info.setWattPowC(wattPowC);
        info.setWattPowTotal(wattPowTotal);
        info.setApprPowA(apprPowA);
        info.setApprPowB(apprPowB);
        info.setApprPowC(apprPowC);
        info.setApprPowTotal(apprPowTotal);
        info.setPowFacA(powFacA);
        info.setPowFacB(powFacB);
        info.setPowFacC(powFacC);

        boolean save = devEParaInfoService.save(info);
        System.out.println(save ? devId+"记录增加成功！" : devId+"记录增加失败！");
        return (save ? "记录增加成功！" : "记录增加失败！");
    }

    /**
     * 发送指令
     */
    @SaCheckPermission("system:role:list")
    @PostMapping("A01-01/0x03")
    public String sendCommand(@RequestBody String command) {
//        取第一个socket
        Socket sock = socketMap.get("localhost:9090");
        String sendStr = "0808 0000 0006 01 03 9c41 0001";
        System.out.println("[0x03] " + command);
//        System.out.println(sock);
        TcpConnection.send(sock, sendStr);
        return sock + sendStr + "命令帧发送成功！";

    }


}
