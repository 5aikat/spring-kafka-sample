package com.saikat.poc.springkafkasample.controller;

import com.saikat.poc.springkafkasample.model.ProduceMessage;
import com.saikat.poc.springkafkasample.model.ProduceRequestPayload;
import com.saikat.poc.springkafkasample.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author Saikat
 */

@Slf4j
@RestController
@RequestMapping("proxy/kafka/produce")
public class KafkaProducerController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postToKafka(@RequestBody ProduceRequestPayload message){
        log.info("Recieved message to post to kafka");
        log.info("Recieved Message : [ {} ]",message.toString());
        //kafkaProducer.sendStringMessage(message.getMessage(),"saikat-test");

        Future sendMessage = kafkaProducer.sendMessage(message.getMessage(), message.getTopicName());
        return ResponseEntity.ok("Successfully posted message to Kafka");
    }
}
