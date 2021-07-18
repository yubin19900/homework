package com.yubin.homework.redis.pubsub.controller;

import com.yubin.homework.redis.pubsub.pub.RedisPub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-18 21:02
 **/
@RestController
public class TestController {
    @Autowired
    private RedisPub redisPub;

    @GetMapping("/test/send")
    public Object sendMsg(String data) {
        redisPub.sendMsg("test", data);
        return "success";
    }
}
