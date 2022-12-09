package com.asurplus.common.onlyfortest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DogThread {
    @SneakyThrows
    @Async("taskExecutor")
    public void say(){
        log.info("汪汪");
        Thread.sleep(1000000000);
    }
}
