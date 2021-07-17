package com.yubin.homework.redis.counter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-17 21:32
 **/
@Component
@Slf4j
public class RedisCounter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void incr(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (StringUtils.isEmpty(value)) {
            stringRedisTemplate.boundValueOps(key).set("1");
        } else {
            stringRedisTemplate.boundValueOps(key).increment();
        }
    }

    public String get(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }
}
