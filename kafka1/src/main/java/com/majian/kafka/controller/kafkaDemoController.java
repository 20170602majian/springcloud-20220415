package com.majian.kafka.controller;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/kafkademo")
/*
* topic的基础操作 ,dom管理(创建,删除,查看详细信息)
* */
public class kafkaDemoController {

    @Autowired
    public KafkaTemplate<Object, Object> kafkaTemplate;

    @Autowired
    private KafkaAdminClient kafkaAdminClient;

    @GetMapping("/addTopicList")
    public void addTopicList () throws ExecutionException, InterruptedException {
        //创建topic(异步的,不一定马上就能输出出来)
                        //topic名称               分区数       一个分区存储多少份
        NewTopic newTopicNewDemo1  = new NewTopic("TOPIC_20220714_DEMO1", 3, (short) 3);
        NewTopic newTopicNewDemo2  = new NewTopic("TOPIC_20220714_DEMO2", 3, (short) 3);
        NewTopic newTopicNewDemo3  = new NewTopic("TOPIC_20220714_DEMO3", 3, (short) 3);
        Set<NewTopic> setNewTopic = new HashSet();
        setNewTopic.add(newTopicNewDemo1);
        setNewTopic.add(newTopicNewDemo2);
        setNewTopic.add(newTopicNewDemo3);
        //创建topic
        CreateTopicsResult topicsCreate = kafkaAdminClient.createTopics(setNewTopic);
        //如果我们想同步的创建topic,需要加此配置
        topicsCreate.all().get();

        //获取kafka上面所有topic集合
        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
        Set<String> topicSet = listTopicsResult.names().get();
        topicSet.forEach(key -> {
            System.out.println(key);
        });




    }
    @GetMapping("/deleteTopicList")
    public void deleteTopicList () throws ExecutionException, InterruptedException {
        //删除topic
        DeleteTopicsResult deleteTopicsResult = kafkaAdminClient.deleteTopics(Arrays.asList("TOPIC_20220714_DEMO1"));
        //同步删除
        deleteTopicsResult.all().get();

        //获取kafka上面所有topic集合
        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
        Set<String> topicSet = listTopicsResult.names().get();
        topicSet.forEach(key -> {
            System.out.println(key);
        });

    }

    @GetMapping("/topicList")
    public void topicList () throws ExecutionException, InterruptedException {
       DescribeTopicsResult describeTopicsResult = kafkaAdminClient.describeTopics(Arrays.asList("TOPIC_20220714_DEMO2"));
        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
        //System.out.println(stringTopicDescriptionMap.toString());
        stringTopicDescriptionMap.entrySet().forEach(key -> {
            System.out.println(key+"\n"+stringTopicDescriptionMap.get(key));

        });

    }

    @GetMapping("/shengchan")
    public void shengchan () throws ExecutionException, InterruptedException {


    }
    

}
