package com.majian.provider2.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //包扫描-排除
        //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        //包扫描-包含
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigComponentScan.class);
        System.out.println(ac.getBean("configServiceImpl"));

    }
}
