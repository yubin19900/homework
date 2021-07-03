package com.yubin.homework.trade;

import com.yubin.homework.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 14:35
 **/
@SpringBootApplication
@ImportResource({"classpath:spring/account-consumer.xml"})
public class TradeApplication implements ApplicationRunner {

    @Autowired
    private TradeService tradeService;


    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        tradeService.trade();
    }
}
