package com.saikat.poc.springkafkasample.model;

import lombok.*;

/**
 * @author Saikat
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NewTopicRequest {

    String topicName;
    String owner;
}
