package com.yubin.homework.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-08-01 22:12
 **/
@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate template;

    public void sendMsg(String topic, String message) {
        log.info("sendMsg topic:{},message:{}", topic, message);
        template.send(topic, message);
    }
}
