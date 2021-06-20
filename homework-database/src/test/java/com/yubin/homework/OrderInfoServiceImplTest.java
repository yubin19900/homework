package com.yubin.homework;

import com.alibaba.fastjson.JSON;
import com.yubin.homework.database.factory.DataSourceFactory;
import com.yubin.homework.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomeworkDatabaseApplication.class)
@Slf4j
public class OrderInfoServiceImplTest {

    @Autowired
    private DataSourceFactory dataSourceFactory;
    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    public void insertOrder() {
        DataSource dataSource = dataSourceFactory.getDefaultDataSource();
        orderInfoService.insertOrder(dataSource);
    }

    @Test
    public void selectOrderList() {
        DataSource dataSource = dataSourceFactory.getDefaultDataSource();
        List<Map<String, Object>> list = orderInfoService.selectOrderList(dataSource);
        log.info("list:{}", JSON.toJSONString(list));
    }
}