package com.majian.fegin.service;


import com.majian.fegin.config.FeignConfig;
import com.majian.fegin.entity.Person;
import com.majian.fegin.hystrix.ProviderFallBackFactory;
import com.majian.fegin.hystrix.SendMesageFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient( name = "cloud-rabbitmq" ,configuration = FeignConfig.class, fallbackFactory = SendMesageFallBackFactory.class)
public interface SendMessageFegin {

    @ResponseBody
    @GetMapping("/send/message")
    String sendMessage(@RequestParam("message") String message);


    @ResponseBody
    @GetMapping("/send/object")
    String sendMessageForObject(@RequestParam Map map);

    @ResponseBody
    @GetMapping("/send/fanoutMessage")
    String sendFanoutMessage(@RequestParam Map map);
    @ResponseBody
    @GetMapping("/send/topicMessage")
    String sendTopicMessage(@RequestParam("message") String message, @RequestParam("binding") String binding);
    }
