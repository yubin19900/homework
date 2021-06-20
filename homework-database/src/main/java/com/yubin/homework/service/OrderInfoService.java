package com.yubin.homework.service;


import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-20 17:38
 **/
public interface OrderInfoService {

    /**
     * 保存订单
     *
     * @param dataSource
     * @return
     */
    int insertOrder(DataSource dataSource);

    /**
     * 查询订单
     *
     * @param dataSource
     * @return
     */
    List<Map<String, Object>> selectOrderList(DataSource dataSource);
}
