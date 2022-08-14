package com.majian.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdminClient kafkaAdminClient(){
        Properties prpo = new Properties();
        prpo.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.130:9092,192.168.1.131:9092,192.168.1.132:9092");
        KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(prpo);
        return kafkaAdminClient;
    }
}
