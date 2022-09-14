package com.asurplus.onlyfortest.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeviceRepositoryTest {
    @Autowired //自动装载
    private DeviceRepository deviceRepository;//自动注入BookRepository
    @Test
    void findAll(){
        System.out.println(deviceRepository.findAll()); //直接调方法
    }
}