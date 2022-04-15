package com.majian.javabasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
//包扫描
//简单方式
@ComponentScan(basePackages = {"com.majian"})
public class JavabasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavabasicApplication.class, args);
    }

}
