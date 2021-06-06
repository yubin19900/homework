package com.yubin.homework.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
@ComponentScan(value = "com.yubin.homework.web.bean")
public class HomeworkWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkWebApplication.class, args);
	}

}
