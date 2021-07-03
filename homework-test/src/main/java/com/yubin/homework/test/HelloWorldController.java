package com.yubin.homework.test;

import com.yubin.homework.web.bean.ISchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-06 18:38
 **/
@RestController
public class HelloWorldController {

    @Autowired
    private ISchool school;

    @RequestMapping("/test")
    public String sendMsg(){
        school.ding();
        return "";
    }
}
