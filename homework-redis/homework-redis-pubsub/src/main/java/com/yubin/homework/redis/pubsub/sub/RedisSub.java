package com.yubin.homework.redis.pubsub.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-18 20:43
 **/
@Slf4j
@Component
public class RedisSub extends MessageListenerAdapter {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String msg = (String) redisTemplate.getStringSerializer().deserialize(body);
        String topic = (String) redisTemplate.getStringSerializer().deserialize(channel);
        log.info("监听到topic为:{},消息:{}", topic, msg);
    }
}
