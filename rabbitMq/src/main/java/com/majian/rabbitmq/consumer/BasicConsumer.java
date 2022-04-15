package com.majian.rabbitmq.consumer;
import com.majian.rabbitmq.entity.Person;
import com.majian.rabbitmq.fegin.RedisCilentFegin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


@Component
@Slf4j
public class BasicConsumer {

    @Autowired
    private RedisCilentFegin redisCilentFegin;

    final String queue2 = "springBootQueue2";
    final String queue = "springBootQueue";
    final String redisStockQueue = "redisStockQueue1";
    final String fanoutQueue1 = "fanoutQueue1";
    final String fanoutQueue2 = "fanoutQueue2";
    final String fanoutQueue3 = "fanoutQueue3";
    final String topicQueue1 = "topicQueue1";
    final String topicQueue2 = "topicQueue2";
    @RabbitListener(queues = queue,containerFactory = "singleListenerContainer")
    //@RabbitHandler
    public void cousumerMessage(byte[] bytes) throws UnsupportedEncodingException {
        String message = null;
        message = new String(bytes, StandardCharsets.UTF_8);
        log.info("消息接收成功,消息为: {} ",message);
    }

    @RabbitListener(queues = redisStockQueue ,containerFactory = "singleListenerContainer" )
    public void redisStock(byte[] bytes){
        String message = null;
        message = new String(bytes, StandardCharsets.UTF_8);
        log.info("消息接收成功,消息为: {} ",message);
        String stock = redisCilentFegin.getStock();
        log.info(stock);
    }

    @RabbitListener(queues = queue2,containerFactory = "singleListenerContainer")
    public void objectMessage(Person p){
        log.info("对象消息接收成功,消息为: {} ",p);
    }



    @RabbitListener(queues = fanoutQueue1,containerFactory = "singleListenerContainer")
    public void fanoutQueue1(Person p){
        log.info("消费者1收到消息,对象消息接收成功,消息为: {} ",p);
    }

    @RabbitListener(queues = fanoutQueue2,containerFactory = "singleListenerContainer")
    public void fanoutQueue2(Person p){
        log.info("消费者2收到消息,对象消息接收成功,消息为: {} ",p);
    }

    @RabbitListener(queues = fanoutQueue3,containerFactory = "singleListenerContainer")
    public void fanoutQueue3(Person p){
        log.info("消费者3收到消息,对象消息接收成功,消息为: {} ",p);
    }


    @RabbitListener(queues =topicQueue1 , containerFactory ="singleListenerContainer")
    public void topicQueue1GetMessage(byte[] bytes) throws UnsupportedEncodingException {
        String message = new String(bytes, StandardCharsets.UTF_8);
        log.info("消息消费成功---******--- 消息为: {}",message);
    }

    @RabbitListener(queues =topicQueue2 , containerFactory ="singleListenerContainer")
    public void topicQueue2GetMessage(byte[] bytes) throws UnsupportedEncodingException {
        String message = new String(bytes, StandardCharsets.UTF_8);
        log.info("消息消费成功---######--- 消息为: {}",message);
    }
}
