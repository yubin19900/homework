package com.yubin.homework.web.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @program: homework
 * @description: spring bean装配
 * @author: Yu Bin
 * @create: 2021-06-06 15:56
 **/
@Slf4j
@Component
public class SpringBeanServiceImpl3 implements SpringBeanService{

    @Override
    public void saySomething() {
        System.out.println("SpringBeanServiceImpl3 say Bonjour");
    }
}
