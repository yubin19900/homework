package com.yubin.homework.api;

import java.util.Map;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 13:41
 **/
public interface OrderService {
    Map<String, Object> selectOrderById(long orderId);
}
