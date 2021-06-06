package com.yubin.homework.web.config;

import com.google.common.collect.Lists;
import com.yubin.homework.web.bean.Klass;
import com.yubin.homework.web.bean.School;
import com.yubin.homework.web.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @program: homework
 * @description:自定义启动类配置
 * @author: Yu Bin
 * @create: 2021-06-06 17:16
 **/
@Configuration
@EnableConfigurationProperties(MsgCustomProperties.class)
public class MyCustomConfiguration {

    @Autowired
    private MsgCustomProperties msgCustomProperties;

    @Bean(name = "student100")
    @ConditionalOnProperty(prefix = "my.stduent", value = "enabled", havingValue = "true")
    public Student getStudent100() {
        Student student = new Student();
        student.setId(msgCustomProperties.getId());
        student.setName(msgCustomProperties.getName());
        return student;
    }

    @Bean
    @ConditionalOnProperty(prefix = "my.stduent", value = "enabled", havingValue = "true")
    public Klass getKlass() {
        Klass klass = new Klass();
        Student student = new Student();
        student.setId(msgCustomProperties.getId());
        student.setName(msgCustomProperties.getName());
        klass.setStudents(Lists.newArrayList(student));
        return klass;
    }

    @Bean
    public School getSchool() {
        return new School();
    }
}
