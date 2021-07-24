package com.yubin.homework.activemq.producer;

import com.yubin.homework.activemq.service.AbstractActiveMq;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-24 22:34
 **/
@Slf4j
public class ActiveProducer extends AbstractActiveMq {
    private MessageProducer getMessageProducer(boolean topicProducer) {
        init();
        Destination destination;
        if (topicProducer) {
            destination = new ActiveMQTopic("yubin.topic");
        } else {
            destination = new ActiveMQQueue("yubin.queue");
        }

        try {
            return session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMessage(boolean topicProducer, String message) {
        MessageProducer messageProducer = getMessageProducer(topicProducer);
        TextMessage textMessage;
        try {
            log.info("sendMessage msg:{}", message);
            textMessage = session.createTextMessage(message);
            messageProducer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
