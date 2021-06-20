package com.yubin.homework.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @program: homework
 * @description:jdbc工具类
 * @author: Yu Bin
 * @create: 2021-06-06 20:01
 **/
@Slf4j
public class JdbcUtil {
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

    /**
     * 查询
     *
     * @param conn
     * @return
     */
    public static List<Map<String, Object>> selectList(Connection conn) {
        //获取执行sql语句的执行者对象Statement
        Statement stat = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = Lists.newArrayList();
        String sql = "select id,user_id,order_number,order_time from order_info";
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = Maps.newHashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            log.error("selectList SQLException error", e);
        } finally {
            close(conn, stat, rs);
        }
        return list;
    }

    /**
     * 插入
     * @param conn
     */
    public static void transactionInsert(Connection conn) {
        //获取执行sql语句的执行者对象Statement
        PreparedStatement pstm = null;
        try {
            String sql = "INSERT INTO geek.order_info" +
                    "(user_id, order_number, order_time, deliver_time, expect_distribution_time, receive_time, receive_name, receive_mobile, receive_address, order_status, deleted, add_time, add_user_id, update_user_id, update_time)" +
                    "VALUES(?, ?, ?, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', '', 0, 0, now(), ?, ?, now());";
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            long start = System.currentTimeMillis();
            for (int i = 5; i <= 6; i++) {
                pstm.setLong(1, i % 10000);
                pstm.setString(2, "202106" + String.format("%07d", i));
                pstm.setString(3, DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                pstm.setLong(4, i % 10000);
                pstm.setLong(5, i % 10000);
                pstm.addBatch();
            }
            pstm.executeBatch();
            conn.commit();
            log.info("cost:{}", (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            log.error("normalInsert SQLException error", e);
        } finally {
            close(conn, pstm, null);
        }
    }
}
