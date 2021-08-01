package com.yubin.homework.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-08-01 22:13
 **/
@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = {"unknownyu"})
    public void listen(ConsumerRecord record) {
        log.info("listen topic:{},message:{}", record.topic(), record.value());
    }
}
