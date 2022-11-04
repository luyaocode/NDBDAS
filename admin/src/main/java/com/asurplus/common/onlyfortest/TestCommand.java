package com.asurplus.common.onlyfortest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
/**
 * @Scope("prototype")
 * springboot默认单例，这个注解将这个类改为多例模式
 */
@Scope("prototype")
public class TestCommand {
    private static final Logger log = LoggerFactory.getLogger(TestCommand.class);
    private Object obj=new Object();

    @Async
    public void setObj(){
        obj=new Object();
        System.out.println("[set]:"+obj);
    }

    @Async
    public void getObj(){
        System.out.println("[get]:"+obj);

    }

}
