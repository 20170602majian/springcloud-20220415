package com.majian.rabbitmq.controller;

import com.majian.rabbitmq.consumer.BasicConsumer;
import com.majian.rabbitmq.entity.Person;
import com.majian.rabbitmq.publisher.BasicPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class SendStringMessageController {

    @Autowired
    private BasicPublisher publisher;

    @ResponseBody
    @GetMapping("/send/message")
    public String sendMessage(@RequestParam("message") String message){
        try {
            publisher.sendMessage(message);
            return message;
        }catch (Exception e){
            log.error("消息发送失败: {}",e);
            return "消息发送失败";
        }
    }

    @ResponseBody
    @GetMapping("/send/object")
    public String sendMessageForObject( Person p){
        try {
            publisher.sendMessageForObject(p);
            return p.toString();
        }catch (Exception e){
            log.error("消息发送失败: {}",e);
            return "消息发送失败";
        }
    }

    @ResponseBody
    @GetMapping("/send/fanoutMessage")
    public String sendFanoutMessage(Person person){
        log.info("rabbitMq模块接收到请求,开始送消息");
        try {
            publisher.setFanoutExchangeMessage(person);
            log.info("消息发送成功!");
            return person.toString();
        }catch (Exception e){
            return "消息发送失败!";
        }
    }

    @ResponseBody
    @GetMapping("/send/topicMessage")
    public String sendTopicMessage(@RequestParam("message") String message , @RequestParam("binding") String binding){
        try {
            publisher.sendTopicMessage(message,binding);
            return message;
        }catch (Exception e){
            log.error("发送信息失败,失败信息为: {}",e);
            return "消息发送异常,异常信息为: {}"+e;
        }
    }

    @ResponseBody
    @GetMapping("/send/redisStockMessage")
    public String redisStockMessage(@RequestParam("message") String message ){
        try {
            publisher.getStock(message);
            return "mq调用redis购买商品消息发送成功";
        }catch (Exception e){
            log.error("发送信息失败,失败信息为: {}",e);
            return "消息发送异常,异常信息为: {}"+e;
        }
    }


}
