package com.yubin.homework.provider.service;

import com.google.common.collect.Maps;
import com.yubin.homework.api.OrderService;

import java.util.Map;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 13:35
 **/
public class OrderServiceImpl implements OrderService {

    @Override
    public Map<String, Object> selectOrderById(long orderId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("orderId", orderId);
        map.put("userName", "张三");
        map.put("orderNumber", System.currentTimeMillis());
        return map;
    }
}
