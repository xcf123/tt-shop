package com.qianfeng.provider.main;

import com.qianfeng.provider.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/spring-dubbo-consumer.xml");
        context.start();
        // obtain proxy object for remote invocation
        DemoService demoService = (DemoService) context.getBean("demoService");
        // execute remote invocation
        String str= demoService.demo("world");
        // show the result
        System.out.println(str);
    }
}
