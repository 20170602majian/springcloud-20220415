package com.majian.redis.controller;

import com.majian.redis.service.CURDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redis")
@Slf4j
public class CURDController {



    @Autowired
    private CURDService curdService;

    @GetMapping("/add")
    @ResponseBody
    public Boolean add(){
        Boolean flag = curdService.putData();
        return  flag;
    }


    @GetMapping("/getStock")
    @ResponseBody
    public String getStock(){
        log.info("被调用成功,开始交易");
        String responseResulf = curdService.getStock();
        log.info("调用成功,进行返回");
        return responseResulf;
    }


    @GetMapping("/getNumberByStock")
    @ResponseBody
    public Integer getNumberByStock(){
        log.info("被调用成功,开始交易");
        Integer number = curdService.getNumberByStock();
        log.info("调用成功,进行返回");
        return number;
    }



}
