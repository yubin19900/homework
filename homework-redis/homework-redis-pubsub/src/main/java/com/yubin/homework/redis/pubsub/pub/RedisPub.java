package com.yubin.homework.redis.pubsub.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-18 20:43
 **/
@Slf4j
@Component
public class RedisPub {
    @Autowired
    private RedisTemplate redisTemplate;

    public void sendMsg(String channel, String msg) {
        log.info("sendMag,channel:{},msg:{}", channel, msg);
        redisTemplate.convertAndSend(channel, msg);
    }
}
