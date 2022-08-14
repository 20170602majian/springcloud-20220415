package com.majian.kafka.controller;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/kafka")
public class SimpleController {

    public KafkaTemplate<Object, Object> kafkaTemplate;

    @GetMapping("/send")
    public String send() {

        String s1 = "xiaoxi";
        kafkaTemplate.send("S_ZZ_MCP_Out",  s1);
        return " 发送成功！！ "+s1;


    }



}
