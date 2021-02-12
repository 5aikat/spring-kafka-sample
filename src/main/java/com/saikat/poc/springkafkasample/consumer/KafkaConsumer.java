package com.saikat.poc.springkafkasample.consumer;

import com.saikat.poc.springkafkasample.model.ProduceMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author Saikat
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "saikat-test-1", groupId = "saikat-consumer-group-2", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload ProduceMessage message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partitionId){

        log.info("Recieved message on topic : saikat-test on Partiion ID : [ {} ]",partitionId);
        log.info("Message : {}",message.toString());
    }
}
