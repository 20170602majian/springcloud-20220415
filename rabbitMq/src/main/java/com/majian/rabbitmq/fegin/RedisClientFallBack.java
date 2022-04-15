package com.majian.rabbitmq.fegin;

public class RedisClientFallBack implements RedisCilentFegin{
    @Override
    public String getStock() {
        return "调用失败";
    }
}
