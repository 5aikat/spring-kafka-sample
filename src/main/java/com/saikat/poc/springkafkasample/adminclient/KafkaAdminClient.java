package com.saikat.poc.springkafkasample.adminclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Saikat
 */

@Slf4j
@Component
public class KafkaAdminClient {

    @Autowired
    private KafkaAdmin admin;

    public void addTopic(String topicName){
        log.info("Creating new topic using adminClient . Topic name : [ {} ]",topicName);
        AdminClient adminClient = AdminClient.create(admin.getConfigurationProperties());

        NewTopic newTopic = TopicBuilder.name(topicName)
                .partitions(1)
                .replicas(1)
                .build();

        CreateTopicsResult topics = adminClient.createTopics(Collections.singletonList(newTopic));
        topics.values().forEach((k,v) ->{
            log.info("Key : {}",k);
            v.thenApply(x -> {
                log.info("Completed");
                return x;
            });
        });
    }
}
