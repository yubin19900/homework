package com.yubin.homework.activemq.service;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-24 22:53
 **/
public abstract class AbstractActiveMq {
    protected Connection connection;
    protected Session session;
    private static final String URL = "tcp://127.0.0.1:61616";

    protected void init() {
        try {
            //1.创建ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
            //2.创建连接
            connection = connectionFactory.createConnection();
            //3.启动连接
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    protected void close() {
        if (session != null) {
            session.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
