package com.majian.rabbitmq.publisher;

import com.majian.rabbitmq.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

/*
* 消息生产者
* */
@Component
@Slf4j
public class BasicPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //直连交换机
    String exchange = "springBootExchange";
    String routingKey = "MQ";
    String routingKey2 = "MQ2";
    String routingKey3 = "MQ3";
    //广播交换机
    String fanoutExchange = "fanoutExchange";

    //topic交换机
    String topicExchange = "topicExchange";


    /*
    * 直连交换机,
    * 直连交换机发送一条信息只能发送到一个queue里面,配置时需要指定交换机和交换机所对应的routingKey
    *
    * */
    public void sendMessage(String message){
        if (!"".equals(message)&&!message.equals("")){

            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(exchange);
            rabbitTemplate.setRoutingKey(routingKey);

            try {
                //发送
                rabbitTemplate.convertAndSend(message.getBytes(StandardCharsets.UTF_8));
                log.info("消息发送成功-生产者-发送的消息为: {}",message);
            }catch (Exception e){
                log.error("消息发送失败-生产者-消息为信息为: {}",message,e);
            }
        }else {
            log.info("消息为空,不发送");
        }
    }

    public void getStock(String message){
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(routingKey3);
        try {
            rabbitTemplate.convertAndSend(message.getBytes(StandardCharsets.UTF_8));
            log.info("消息发送成功-生产者-发送的消息为: {}",message);
        }catch (Exception e){
            log.error("消息发送失败-生产者-消息为信息为: {}",message,e);
        }
    }

    public void sendMessageForObject(Person p){
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(routingKey2);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        try {
            rabbitTemplate.convertAndSend(p, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //获取消息属性
                    MessageProperties messageProperties = message.getMessageProperties();
                    //设置消息的持久化模式
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //设置好消息的类型
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,Person.class);
                    return message;
                }
            });
            log.info("消息发送成功-生产者-发送的对象消息为: {}",p);
        }catch (Exception e){
            log.error("消息发送失败-生产者-消息为对象信息为: {}",p,e);
        }
    }
    /*
    * 广播交换机
    * 广播交换机不需要指定队列,只需要指定交换机,就会向交换机所绑定的所有队列中发送消息
    *
    * */
    public void setFanoutExchangeMessage(Person person){

        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(person, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //获取消息属性
                MessageProperties messageProperties = message.getMessageProperties();
                //设置消息的持久化模式
                messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                //设置好消息的类型
                messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,Person.class);
                return message;
            }
        });
        log.info("生产者发送信息,信息为 :{}",person);
    }


    /*
    * topic交换机,通过#,*对binding进行匹配发送信息,*代表一个单词,#代表0个或多个单词
    * */

    public void sendTopicMessage(String message,String binding){

        if (!"".equals(message)&&!message.equals(null)){
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(topicExchange);
            rabbitTemplate.setRoutingKey(binding);
            rabbitTemplate.convertAndSend(message.getBytes(StandardCharsets.UTF_8));
            log.info("消息发送成功,message: {},,,,,bingdingKey: {}",message,binding);

        }
    }

}
