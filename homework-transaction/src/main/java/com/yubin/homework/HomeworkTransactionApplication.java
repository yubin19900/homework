package com.yubin.homework;

import com.yubin.homework.config.TransactionConfig;
import com.yubin.homework.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
@Import(TransactionConfig.class)
public class HomeworkTransactionApplication implements CommandLineRunner {

    @Autowired
    private TransactionService transactionService;

    public static void main(String[] args) {
        SpringApplication.run(HomeworkTransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        transactionService.insert(31);
    }
}
