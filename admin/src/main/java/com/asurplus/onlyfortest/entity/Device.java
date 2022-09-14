package com.asurplus.onlyfortest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Device {
    @Id
    private String id;
    private String name;
    private String info;
}
