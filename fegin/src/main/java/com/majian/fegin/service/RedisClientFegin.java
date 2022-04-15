package com.majian.fegin.service;


import com.majian.fegin.hystrix.RedisClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cloud-redis")//,fallback = RedisClientFallBack.class
public interface RedisClientFegin {

    @GetMapping("/redis/getStock")
    @ResponseBody
    public String getStock();

    @GetMapping("/redis/getNumberByStock")
    @ResponseBody
    public Integer getNumberByStock();



    @RequestMapping(method = RequestMethod.GET,value = "/redisson/bloomContainsInteger")
    @ResponseBody
    public Boolean bloomContainsInteger(@RequestParam Integer number);
}
