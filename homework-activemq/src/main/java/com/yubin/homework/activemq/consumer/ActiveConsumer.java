package com.yubin.homework.activemq.consumer;

import com.yubin.homework.activemq.service.AbstractActiveMq;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-24 22:52
 **/
@Slf4j
public class ActiveConsumer extends AbstractActiveMq {
    private MessageConsumer getMessageConsumer(boolean topicConsumer) {
        init();
        Destination destination;
        if (topicConsumer) {
            destination = new ActiveMQTopic("yubin.topic");

        } else {
            destination = new ActiveMQQueue("yubin.queue");
        }
        try {
            return session.createConsumer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void consumerMessage(boolean topicConsumer) {
        MessageConsumer messageConsumer = getMessageConsumer(topicConsumer);
        try {
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        log.info("consumerMessage msg:{}", textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
//            close();
        }
    }
}
