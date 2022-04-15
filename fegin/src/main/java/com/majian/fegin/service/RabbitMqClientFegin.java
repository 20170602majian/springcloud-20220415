package com.majian.fegin.service;

import com.majian.fegin.hystrix.RabbitMqClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "cloud-rabbitmq",fallback = RabbitMqClientFallback.class)
public interface RabbitMqClientFegin {

    @ResponseBody
    @GetMapping("/send/redisStockMessage")
    public String redisStockMessage(@RequestParam("message") String message );

}
