package com.yubin.homework.provider.config;

import com.yubin.homework.api.OrderService;
import com.yubin.homework.provider.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置接口的实现类
 *
 * @author lw
 */
@Configuration
public class BeanConfig {

    @Bean("com.yubin.homework.api.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
