package com.yubin.homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
public class HomeworkDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkDatabaseApplication.class, args);
	}

}
