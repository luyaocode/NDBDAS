package com.asurplus.onlyfortest.controller;

import com.asurplus.onlyfortest.entity.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author chenluyao
 */
@RestController
public class Chat {

    @GetMapping("/tool/chat")
    public Message request() {

        return new Message("OK",new Date());

    }
}
