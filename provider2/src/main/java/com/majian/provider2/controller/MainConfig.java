package com.majian.provider2.controller;

import com.majian.provider2.ConfigServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


@Configuration

//排除
@ComponentScan(basePackages = {"com.majian.provider2"},excludeFilters = {
        //扫描目录下的类中，排除有这个注解的类
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class, Service.class}),
        //扫描目录下的类中，排除自定义的类
        //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {ConfigServiceImpl.class})
        //常用的两个排除类型 ：
        //              一个就是FilterType.ANNOTATION，这个方式，排除注解，
        //              另一个是FilterType.ASSIGNABLE_TYPE，排除自定义类

})

//包含


public class MainConfig {
}
