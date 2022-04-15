package com.majian.fegin.controller;

import com.majian.fegin.config.CommonResult;
import com.majian.fegin.service.ThreadPoolExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/fegin")
@Slf4j
public class ThreadPoolExecutorController {

    @Autowired
    private ThreadPoolExecutorService threadPoolExecutorService;

    @GetMapping("/threadExecutor/go")
    public ResponseEntity go(){
        long timeStart = new Date().getTime();
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutorService.go();
        }
        long timeEnd = new Date().getTime();
        log.info("本次请求消耗时间为 ： ----> "+(timeEnd-timeStart));
        return  ResponseEntity.ok(new CommonResult()
                .setCode("2000")
                .setMessage("执行成功"));
    }

}
