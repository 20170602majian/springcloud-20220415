package com.majian.fegin.service.impl;

import com.majian.fegin.service.RabbitMqClientFegin;
import com.majian.fegin.service.RedisClientFegin;
import com.majian.fegin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisClientFegin redisClientFegin;
    @Autowired
    private RabbitMqClientFegin rabbitMqClientFegin;

    @Override
    @Async("threadExecutor")
    public CompletableFuture toRedisCilean() {

        String stock = redisClientFegin.getStock();
        rabbitMqClientFegin.redisStockMessage("开始购买商品");

        return new CompletableFuture().completedFuture(stock) ;
    }

    @Override
    @Async("threadExecutor")
    public void toRabbitMqCilean() {
        //String stock = redisClientFegin.getStock();
        rabbitMqClientFegin.redisStockMessage("开始购买商品");
    }

    @Override
    public Integer getNumber() {
        return redisClientFegin.getNumberByStock();
    }


}
