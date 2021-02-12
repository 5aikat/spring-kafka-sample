package com.saikat.poc.springkafkasample.producer;

import com.saikat.poc.springkafkasample.model.ProduceMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.Future;

/**
 * @author Saikat
 */

@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,ProduceMessage> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String,String> stringKafkaTemplate;

    public Future sendMessage(ProduceMessage message, String topic_name){

        ListenableFuture<SendResult<String, ProduceMessage>> sendResultListenableFuture = kafkaTemplate.send(topic_name, message);

        sendResultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, ProduceMessage>>() {

            @Override
            public void onFailure(Throwable throwable) {
                log.info("Failure while sending the message to kafka");
                log.warn(throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, ProduceMessage> stringStringSendResult) {
                log.info("Sent message=[ " + message + " ] with offset=[ " + stringStringSendResult.getRecordMetadata().offset() + " ]");
            }
        });

        return sendResultListenableFuture;
    }

    public void sendStringMessage(String message, String topic_name){
        ListenableFuture<SendResult<String, String>> listenableFuture = stringKafkaTemplate.send(topic_name, message);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("Failure while sending the message to kafka");
                log.warn(throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("Sent message=[ " + message + " ] with offset=[ " + stringStringSendResult.getRecordMetadata().offset() + " ]");
            }
        });
    }

}
