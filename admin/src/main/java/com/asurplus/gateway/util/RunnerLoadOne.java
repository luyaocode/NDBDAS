package com.asurplus.gateway.util;

import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.service.GatewayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目启动时查询网关信息
 * @author Luyao
 * @since 2022-9-15
 */
@Component
public class RunnerLoadOne implements CommandLineRunner {
    private static List<String> addrList=new ArrayList<>();

    @Autowired
    private GatewayInfoService gatewayInfoService;

    @Override
    public void run(String... args) throws Exception {
        List<GatewayInfo> list = gatewayInfoService.list();
        for(GatewayInfo info:list){
            addrList.add(info.getIp()+":"+info.getPort());
        }
    }

    public static List<String> getAddrList(){
        return addrList;
    }

}
