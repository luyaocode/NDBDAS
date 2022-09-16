package com.asurplus.gateway.util;

import com.asurplus.gateway.entity.GatewayInfo;
import com.asurplus.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目启动时查询网关信息
 * @author Luyao
 * @since 2022-9-15
 */
@Component
public class RunnerLoadOne implements CommandLineRunner {
    static Map<String,Integer> urlMap=new HashMap<>();

    @Autowired
    private GatewayService gatewayService;

    @Override
    public void run(String... args) throws Exception {
        List<GatewayInfo> list = gatewayService.list();
        for(GatewayInfo info:list){
            urlMap.put(info.getIp(),info.getPort());
        }
    }

    public static Map<String,Integer> getUrlMap(){
        return urlMap;
    }

}
