package com.asurplus.onlyfortest.repository;

import com.asurplus.onlyfortest.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 实体类型：Device
 * 主键类型：String
 */
public interface DeviceRepository extends JpaRepository<Device,String> {

}
