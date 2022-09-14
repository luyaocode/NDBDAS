package com.asurplus.onlyfortest.controller;

import com.asurplus.onlyfortest.entity.Device;
import com.asurplus.onlyfortest.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository; //注入
    @GetMapping("/findAll")  //get请求
    public List<Device> findall(){  //返回一个集合（Book需引用自己写的）
        return deviceRepository.findAll();
    }
}
