package com.majian.rabbitmq.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMqConfig {
    //rabbitmq的链接工厂实例
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    //自动装配消息监听器所在的容器工厂配置类实例
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
    /*
    * 下面为单一消费者实例配置
    * */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factory.setConnectionFactory(cachingConnectionFactory);
        //设置消息在传输中的格式,json传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者的初始数量
        factory.setConcurrentConsumers(1);
        //设置并发消费者的最大数量
        factory.setMaxConcurrentConsumers(1);
        //设置并发消费者实例中每个实例拉取的消息数量
        factory.setPrefetchCount(1);
        return factory;
    }
    //自定义rabbitTemlate
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //设置发送消息后进行确认
        cachingConnectionFactory.setPublisherConfirms(true);
        //设置发送消息后确认返回信息
        cachingConnectionFactory.setPublisherReturns(true);
        //构建发送消息的组件实例对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);
        //发送信息后,如果发送成功,则输出消息发送成功的反馈信息
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        //发送消息后,如果发送失败,则输出'消息发送失败-消息丢失'的反馈消息
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("消息丢失:s1({}),s2({}),i({}),message({}),messages: {}",s1,s2,i,s,message);
            }
        });
        //返回实例B
        return rabbitTemplate;
    }
    //1.声明一个队列
    @Bean
    public Queue springBootQueue(){
        //设置队列的 名称 同时也可以设置 队列的其他属性 默认只给了名字
        return new Queue("springBootQueue",true);
    }

    //2.声明交换机 可以通过不同类型的交换机进行声明配置

    //直连交换机

    //durable :此属性为是否持久化的意思,设置为true使消息持久化防治消息因为各种原因没有被消费而丢失
    @Bean
    public DirectExchange springBootExchange(){
        return new DirectExchange("springBootExchange");
    }

    //3.将队列与交换机进行绑定
    @Bean
    public Binding queueExchangeBind(){
        //创建 队列与交换机之间的绑定关系  设置一个绑定的key 为MQ
        return BindingBuilder.bind(springBootQueue()).to(springBootExchange()).with("MQ");
    }

    @Bean
    public Queue redisStockQueue(){
        return new Queue("redisStockQueue1",true);
    }
    @Bean
    public Queue springBootQueue2(){
        //设置队列的 名称 同时也可以设置 队列的其他属性 默认只给了名字
        return new Queue("springBootQueue2",true);
    }
    //3.将队列与交换机进行绑定
    @Bean
    public Binding queueExchangeBind2(){
        //创建 队列与交换机之间的绑定关系  设置一个绑定的key 为MQ
        return BindingBuilder.bind(springBootQueue2()).to(springBootExchange()).with("MQ2");
    }
    @Bean
    public Binding queueExchangeBind3(){
        //创建 队列与交换机之间的绑定关系  设置一个绑定的key 为MQ
        return BindingBuilder.bind(redisStockQueue()).to(springBootExchange()).with("MQ3");
    }
    /*
    * 广播交换机配置
    * */
    //广播交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange",true,false);
    }
    //广播交换机的三个队列
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanoutQueue1",true);
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanoutQueue2",true);
    }

    @Bean
    public Queue fanoutQueue3(){
        return new Queue("fanoutQueue3",true);
    }
    //三个绑定,绑定广播交换机和它三个队列
    @Bean
    public Binding queueFanoutExchangeBind1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding queueFanoutExchangeBind2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public Binding queueFanoutExchangeBind3(){
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }

    /*
    *
    *  topic主题交换机,分为*和#两种bangingKey
    *  *代表一个单词,#代表0个或多个单词
    *   当*和#两种绑定同时存在时,#总会被使用,*只有符合是一个单词才会被使用
    * */

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange",true,false);
    }

    @Bean
    public Queue topicQueue1(){
        return new Queue("topicQueue1",true);
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue("topicQueue2",true);
    }

    @Bean
    public Binding queueTopicExchangeBing1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("com.topic.*.binding");
    }

    @Bean
    public Binding queueTopicExchangeBing2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("com.topic.#.binding");
    }

}
