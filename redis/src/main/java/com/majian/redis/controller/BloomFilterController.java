package com.majian.redis.controller;

import com.majian.redis.service.BloomFilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redisson")
@Slf4j
public class BloomFilterController {

    @Autowired
    private BloomFilterService bloomFilterService;

    @GetMapping("/bloomContainsInteger")
    @ResponseBody
    public Boolean bloomContainsInteger(Integer number){
        return bloomFilterService.containsInteger(number);
    }

}
