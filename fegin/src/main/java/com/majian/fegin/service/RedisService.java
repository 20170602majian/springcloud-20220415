package com.majian.fegin.service;

import java.util.concurrent.CompletableFuture;

public interface RedisService {
    public CompletableFuture toRedisCilean();
    public void toRabbitMqCilean() ;
    public Integer getNumber();
}
