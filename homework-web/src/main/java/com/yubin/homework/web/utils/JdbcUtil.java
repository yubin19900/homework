package com.yubin.homework.web.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

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
    //创建一些静态成员变量，用来存储数据库的连接信息
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/geek";
    private static String user = "root";
    private static String password = "123456";

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

    /**
     * 查询
     *
     * @param sql
     * @return
     */
    public static List<Map<String, Object>> selectList(String sql) {
        //获取连接
        Connection conn = getConnection();
        //获取执行sql语句的执行者对象Statement
        Statement stat = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = Lists.newArrayList();
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

    public static int insert(String insertSql) {
        return update(insertSql);
    }

    public static int update(String updateSql) {
        //获取连接
        Connection conn = getConnection();
        //获取执行sql语句的执行者对象Statement
        Statement stat = null;
        int row = 0;
        try {
            stat = conn.createStatement();
            row = stat.executeUpdate(updateSql);

        } catch (SQLException e) {
            log.error("update SQLException error", e);
        } finally {
            close(conn, stat, null);
        }
        return row;
    }

    public static int delete(String deleteSql) {
        return update(deleteSql);
    }

    public static List<Map<String, Object>> selectListByPreparedStatement(String sql, long primaryKey, Object... args) {
        //获取连接
        Connection conn = getConnection();
        //获取执行sql语句的执行者对象Statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = Lists.newArrayList();
        try {
            pst = conn.prepareStatement(sql);
            pst.setLong(1, primaryKey);
            if (args != null) {
                int i = 2;
                for (Object arg : args) {
                    pst.setObject(i, arg);
                    i++;
                }
            }
            rs = pst.executeQuery();
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
            log.error("selectListByPreparedStatement SQLException error", e);
        } finally {
            close(conn, pst, rs);
        }
        return list;
    }

    public static int[] updateByPreparedStatement(String updateSql1, String updateSql2, long primaryKey1, long primaryKey2, String name1, String name2) {
        //获取连接
        Connection conn = getConnection();
        //获取执行sql语句的执行者对象Statement
        PreparedStatement pstat = null;
        int[] row = {};
        try {
            //关闭自动提交事务
            conn.setAutoCommit(false);
            pstat = conn.prepareStatement(updateSql1);
            pstat.addBatch(updateSql2);
            pstat.setLong(1, primaryKey1);
            pstat.setString(2, name1);
            pstat.setLong(3, primaryKey2);
            pstat.setString(4, name2);
            pstat.addBatch();
            row = pstat.executeBatch();
            if (row != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            log.error("updateByPreparedStatement SQLException error", e);
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                log.error("updateByPreparedStatement rollback Exeption", e);
            }
        } finally {
            close(conn, pstat, null);
        }
        return row;
    }

    public static void main(String[] args) {
        List<Map<String, Object>> list = selectList("select * from sys.sys_config");
        System.out.println(JSON.toJSONString(list));
    }
}
