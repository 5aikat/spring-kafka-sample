package com.saikat.poc.springkafkasample.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author Saikat
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProduceMessage implements Serializable {

    String Id;
    String name;
    String message;
    String timestamp;
}
