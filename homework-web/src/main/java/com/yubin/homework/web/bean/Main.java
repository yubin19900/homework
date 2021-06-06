package com.yubin.homework.web.bean;

import com.yubin.homework.web.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-06 16:23
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-bean.xml");
        SpringBeanServiceImpl1 springBean1 = (SpringBeanServiceImpl1) context.getBean("springBeanService1");
        springBean1.saySomething();

        SpringBeanServiceImpl2 springBean2 = (SpringBeanServiceImpl2) context.getBean("springBeanService2");
        springBean2.saySomething();

        SpringBeanServiceImpl3 springBean3 = (SpringBeanServiceImpl3) context.getBean("springBeanServiceImpl3");
        springBean3.saySomething();

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        SpringBeanServiceImpl4 springBean4 = (SpringBeanServiceImpl4)ctx.getBean("springBeanService4");
        springBean4.saySomething();
    }
}
