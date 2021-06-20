package com.yubin.homework;

import com.yubin.homework.shardingsphere.ShardingsphereDynamicDataSource;
import com.yubin.homework.utils.JdbcUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomeworkDatabaseApplication.class)
@Slf4j
public class ShardingsphereDynamicDataSourceTest {
    @Autowired
    private ShardingsphereDynamicDataSource shardingsphereDynamicDataSource;

    @Test
    public void test() {
        DataSource dataSource = shardingsphereDynamicDataSource.createDataSource();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (conn != null) {
//            JdbcUtil.transactionInsert(conn);
            List<Map<String, Object>> list = JdbcUtil.selectList(conn);
        }
    }
}