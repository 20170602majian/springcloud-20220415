package com.majian.fegin.hystrix;

import com.majian.fegin.service.RabbitMqClientFegin;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqClientFallback implements RabbitMqClientFegin {
    @Override
    public String redisStockMessage(String message) {
        return "调用失败!!";
    }
}
