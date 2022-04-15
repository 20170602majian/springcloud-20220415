package com.majian.rabbitmq.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "cloud-redis",fallback = RedisClientFallBack.class)
public interface RedisCilentFegin {

    @GetMapping("/redis/getStock")
    @ResponseBody
    public String getStock();
}
