package com.yubin.homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@ImportResource("classpath*:spring/*.xml")
@SpringBootApplication
public class HomeworkTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkTransactionApplication.class, args);
    }
}
