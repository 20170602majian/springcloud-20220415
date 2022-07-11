package com.majian.kafka;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringCloudApplication
@ComponentScan("com.majian")
@MapperScan("com.majian.kafka.mapper")
@EnableFeignClients
@EnableKafka
public class kafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(kafkaApplication.class, args);
    }
}
