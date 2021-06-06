package com.yubin.homework.web.bean;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: homework
 * @description: spring bean装配
 * @author: Yu Bin
 * @create: 2021-06-06 15:56
 **/
@Slf4j
public class SpringBeanServiceImpl1 implements SpringBeanService{

    @Override
    public void saySomething() {
        System.out.println("SpringBeanServiceImpl1 say hello");
    }
}
