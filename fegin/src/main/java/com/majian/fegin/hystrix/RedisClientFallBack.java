package com.majian.fegin.hystrix;

import com.majian.fegin.service.RedisClientFegin;
import org.springframework.stereotype.Component;

@Component
public class RedisClientFallBack implements RedisClientFegin {
    @Override
    public String getStock() {
        return "调用失败???????";
    }

    @Override
    public Integer getNumberByStock() {
        return -1;
    }

    @Override
    public Boolean bloomContainsInteger(Integer number) {
        return false;
    }

}
