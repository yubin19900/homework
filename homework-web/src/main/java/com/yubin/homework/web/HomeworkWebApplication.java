package com.yubin.homework.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
@ComponentScan(value = "com.yubin.homework.web.bean")
public class HomeworkWebApplication {

    public static void main(String[] args) {
        ApplicationContext ctc = SpringApplication.run(HomeworkWebApplication.class, args);
        DataSource dataSource = ctc.getBean(DataSource.class);
        //检查数据库是否是hikar数据库连接池
        if (dataSource instanceof HikariDataSource) {
            //获取执行sql语句的执行者对象Statement
            Statement stat = null;
            ResultSet rs = null;
            Connection conn = null;
            List<Map<String, Object>> list = Lists.newArrayList();
            try {
                conn = dataSource.getConnection();
                stat = conn.createStatement();
                rs = stat.executeQuery("select* from sys.sys_config");
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> rowData = Maps.newHashMap();
                    for (int i = 1; i <= columnCount; i++) {
                        rowData.put(md.getColumnName(i), rs.getObject(i));
                    }
                    list.add(rowData);
                }
                System.out.println(JSON.toJSONString(list));
            } catch (SQLException e) {
                log.error("selectList SQLException error", e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        log.error("ResultSet close error", e);
                    }
                }

                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException e) {
                        log.error("Statement close error", e);
                    }
                }

                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        log.error("Connection close error", e);
                    }
                }
            }
        }
    }
}
