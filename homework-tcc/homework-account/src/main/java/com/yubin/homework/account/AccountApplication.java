package com.yubin.homework.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 14:33
 **/
@SpringBootApplication
@ImportResource({"classpath:spring/account-provider.xml"})
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
