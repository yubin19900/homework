package com.yubin.homework.activemq;

import com.yubin.homework.activemq.consumer.ActiveConsumer;
import com.yubin.homework.activemq.producer.ActiveProducer;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-24 23:12
 **/
public class Test {
    public static void main(String[] args) {
        ActiveConsumer consumer = new ActiveConsumer();
        consumer.consumerMessage(true);
        ActiveProducer producer = new ActiveProducer();
        producer.sendMessage(true, "hello,I'm topicProducer");
        producer.sendMessage(false, "hello,I'm queueProducer");
        consumer.consumerMessage(false);
    }
}
