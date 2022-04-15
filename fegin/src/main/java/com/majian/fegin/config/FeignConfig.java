package com.majian.fegin.config;


import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignConfig {

    //fegin 日志配置,在配置文件中在指定单个 feginClient 的日志级别

    @Bean
    Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }

    //feginclient单独禁用hystrix,全局禁用直接在application中修改,引用此配置才会关闭hystrix
    //@Bean
    @Scope("prototype")
    public Feign.Builder feginBuilder(){
        return Feign.builder();
    }


}


