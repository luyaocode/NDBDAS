package com.asurplus.onlyfortest.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author chenluyao
 */
@Data
@EqualsAndHashCode
public class Message {
    public String msg;
    public Date date;

    public Message(String msg, Date date) {
        this.msg=msg;
        this.date=date;
    }
}
