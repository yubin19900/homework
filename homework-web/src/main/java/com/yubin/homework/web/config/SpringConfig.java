package com.yubin.homework.web.config;

import com.yubin.homework.web.bean.SpringBeanServiceImpl4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-06 16:49
 **/
@Configuration
public class SpringConfig {

    @Bean(name = "springBeanService4")
    public SpringBeanServiceImpl4 getSpringBeanService(){
        return new SpringBeanServiceImpl4();
    }
}
