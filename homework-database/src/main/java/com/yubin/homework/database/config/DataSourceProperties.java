package com.yubin.homework.database.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-20 16:33
 **/
@Data
public class DataSourceProperties {
    private String url;
    private String userName;
    private String password;
    private String driverClassName;
}
