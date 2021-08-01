package com.yubin.homework.kafka;

import com.yubin.homework.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-08-01 22:14
 **/
@RestController
public class TestController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/sendMsg")
    public String sendMsg(String topic, String message) {
        kafkaProducer.sendMsg(topic, message);
        return "success";
    }
}
