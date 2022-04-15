package com.majian.fegin.controller;


import com.majian.fegin.config.CommonResult;
import com.majian.fegin.service.RedisClientFegin;
import com.majian.fegin.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/fegin/redis")
@Slf4j
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Qualifier("com.majian.fegin.service.RedisClientFegin")
    @Autowired
    private RedisClientFegin redisClientFegin;

    @GetMapping("/getStock")
    public ResponseEntity<?> getStock(){
        log.info("开始调用取库存");
        List<String> list = new ArrayList<>();
        List<CompletableFuture> completableFutureList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<Map<String,Object>> completableFuture = redisService.toRedisCilean();
            completableFutureList.add(completableFuture);
        }
        CompletableFuture[] completableFutures = completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]);
        CompletableFuture.allOf(completableFutures).join();
        completableFutureList.forEach((po) -> {
            try {
                String message =String.valueOf(po.get());
                list.add(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        log.info("调用成功");
        return ResponseEntity.ok(new CommonResult()
                .setMessage("请求完成")
                .setCode("2000")
                .setData(list));
    }

    @GetMapping("/getStockByRabbitMq")
    public ResponseEntity<?> getStockByRabbitMq() throws InterruptedException {
        log.info("开始调用取库存");

        for (int i = 0; i < 3005; i++) {
            redisService.toRabbitMqCilean();
            log.info("调用一次!!");
        }
        Thread.sleep(4000);
        Integer number = redisService.getNumber();
        log.info("调用成功");
        return ResponseEntity.ok(new CommonResult()
                .setMessage("请求完成")
                .setCode("2000")
                .setData("剩余商品数量为 ->"+number)
               );
    }

    @GetMapping("/bloomFilterInteger")
    public ResponseEntity<?> bloomFilterInteger (Integer number){
        Boolean flag = redisClientFegin.bloomContainsInteger(number);
        return ResponseEntity.ok(new CommonResult()
                .setMessage("查询成功")
                .setCode("2000")
                .setData(flag)
        );
    }

}
