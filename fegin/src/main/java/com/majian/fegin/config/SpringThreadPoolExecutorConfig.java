package com.majian.fegin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
//配置线程池需要加的注解
@EnableAsync
public class SpringThreadPoolExecutorConfig {


    //核心线程数
    private Integer coorPoolSize = 8;

    //最大线程数
    private Integer maxMunPoolSize = 50;

    //阻塞队列长度
    private Integer queueCapacity = 5000;

    //存活时间
    private Integer keepAliveTime = 3*60;

    //线程池前缀名称
    private String threadNamePrefix = "自定义线程池执行操作 ---->";


    @Bean("threadExecutor")
    public Executor threadExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(coorPoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxMunPoolSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveTime);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        //设置拒绝策略，一般为主线程
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }


}
