package com.majian.kafka.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.majian.kafka.entity.SsKafka;
import com.majian.kafka.service.SsKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class SimpleListener {

    @Autowired
    private SsKafkaService ssKafkaService;

    @KafkaListener(topics = {"S_ZZ_MCP_Out"})
    public void listen2(String s) {
        log.info("检测到发送信息!!!");
        log.info(s);

    }
}
