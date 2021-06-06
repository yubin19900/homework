package com.yubin.homework.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-06 17:42
 **/
@Data
@ConfigurationProperties(prefix = "my.stduent")
public class MsgCustomProperties {
    private int id;
    private String name;
}
