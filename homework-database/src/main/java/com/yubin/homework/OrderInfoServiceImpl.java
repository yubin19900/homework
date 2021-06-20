package com.yubin.homework;

import com.yubin.homework.annotation.ReadDataSource;
import com.yubin.homework.service.OrderInfoService;
import com.yubin.homework.utils.JdbcUtil;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-20 17:39
 **/
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Override
    public int insertOrder(DataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (connection != null) {
            JdbcUtil.transactionInsert(connection);
            return 1;
        }
        return 0;
    }

    @Override
    @ReadDataSource
    public List<Map<String, Object>> selectOrderList(DataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (connection != null) {
            return JdbcUtil.selectList(connection);
        }
        return Collections.emptyList();
    }
}
