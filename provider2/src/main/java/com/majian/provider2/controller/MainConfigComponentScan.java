package com.majian.provider2.controller;

import com.majian.provider2.ConfigServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = {"com.majian.provider2"},includeFilters = {
        //@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class,Service.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {ConfigServiceImpl.class})
},useDefaultFilters = false)//false : 不全部扫描包
public class MainConfigComponentScan {
}
