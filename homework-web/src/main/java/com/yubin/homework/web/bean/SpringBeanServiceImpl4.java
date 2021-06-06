package com.yubin.homework.web.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: homework
 * @description: spring bean装配
 * @author: Yu Bin
 * @create: 2021-06-06 15:56
 **/
@Slf4j
@Component
public class SpringBeanServiceImpl4 implements SpringBeanService{

    @Override
    public void saySomething() {
        System.out.println("SpringBeanServiceImpl4 say Hallo");
    }
}
