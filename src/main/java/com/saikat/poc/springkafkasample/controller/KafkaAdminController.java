package com.saikat.poc.springkafkasample.controller;

import com.saikat.poc.springkafkasample.adminclient.KafkaAdminClient;
import com.saikat.poc.springkafkasample.model.NewTopicRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Saikat
 */

@Slf4j
@RestController
@RequestMapping("/proxy/kafka/admin")
public class KafkaAdminController {

    @Autowired
    private KafkaAdminClient kafkaAdminClient;

    @PostMapping(value = "/topic" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addTopic(@RequestBody NewTopicRequest request){
        log.info("Recieved request to create topic : [ {} ] ---- Owner : [ {} ]",request.getTopicName(),request.getOwner());
        kafkaAdminClient.addTopic(request.getTopicName());
        return ResponseEntity.ok("New Topic Successfully added");
    }
}
