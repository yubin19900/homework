package com.yubin.homework.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-08-01 22:05
 **/
@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.yubin.homework"})
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}
