package com.asurplus.common.onlyfortest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestReceive {

    private static final Logger log = LoggerFactory.getLogger(TestReceive.class);

    @Async
    public void receive(){
        while (true) {
            log.info("***************receive*******************");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
