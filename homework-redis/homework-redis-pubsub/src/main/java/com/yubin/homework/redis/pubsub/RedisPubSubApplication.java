package com.yubin.homework.redis.pubsub;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.yubin.homework"})
public class RedisPubSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisPubSubApplication.class, args);
    }
}
