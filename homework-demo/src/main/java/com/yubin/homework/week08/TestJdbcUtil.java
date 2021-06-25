package com.yubin.homework.week08;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * @program: homework
 * @description:测试100万数据不同方式插入效率
 * @author: Yu Bin
 * @create: 2021-06-20 13:35
 **/
@Slf4j
public class TestJdbcUtil {
    //创建一些静态成员变量，用来存储数据库的连接信息
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:13306/sharding_db?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true";
    private static String user = "sharding";
    private static String password = "sharding";

    static {
        //1.注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("load mysql driver error", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接异常" + e);
        }
        return conn;
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param stat
     * @param rs
     */
    public static void close(Connection conn, Statement stat, ResultSet rs) {
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

    /**
     * 释放资源
     *
     * @param conn
     * @param pstat
     * @param rs
     */
    public static void close(Connection conn, PreparedStatement pstat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("ResultSet close error", e);
            }
        }

        if (pstat != null) {
            try {
                pstat.close();
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

    public static void normalInsert() {
        //获取连接
        Connection conn = getConnection();
        //获取执行sql语句的执行者对象Statement
        PreparedStatement pstm = null;
        try {
            String sql = "INSERT INTO t_order_info" +
                    "(order_id,user_id, order_number, order_time, receive_name, receive_mobile, receive_address, order_status, deleted, add_time, add_user_id, update_user_id, update_time)" +
                    "VALUES(?,?, ?, ?, '', '', '', 0, 0, now(), ?, ?, now());";
            pstm = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();
            for (int i = 1; i <= 30; i++) {
                pstm.setLong(1, i);
                pstm.setLong(2, i % 10000);
                pstm.setString(3, "202106" + String.format("%07d", i));
                pstm.setString(4, DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                pstm.setLong(5, i % 10000);
                pstm.setLong(6, i % 10000);
                pstm.executeUpdate();
            }
            log.info("cost:{}", (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            log.error("normalInsert SQLException error", e);
        } finally {
            close(conn, pstm, null);
        }
    }

    public static void main(String[] args) {
        normalInsert();
    }
}
