package com.yubin.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-25 15:09
 **/
@Service
@Slf4j
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = SQLException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void insert(final int beginIndex) {
        jdbcTemplate.execute("INSERT INTO t_order_info" +
                        "(order_id,user_id, order_number, order_time, receive_name, receive_mobile, receive_address, order_status, deleted, add_time, add_user_id, update_user_id, update_time)" +
                        "VALUES(?,?, ?, ?, '', '', '', 0, 0, now(), ?, ?, now())",
                (PreparedStatementCallback<TransactionType>) preparedStatement -> {
                    doInsert(beginIndex, preparedStatement);
                    return TransactionTypeHolder.get();
                });
    }

    private void doInsert(int beginIndex, PreparedStatement pstm) throws SQLException {
        for (int i = beginIndex; i <= (beginIndex + 30); i++) {
            pstm.setLong(1, i);
            pstm.setLong(2, i % 10000);
            pstm.setString(3, "202106" + String.format("%07d", i));
            pstm.setString(4, DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            pstm.setLong(5, i % 10000);
            pstm.setLong(6, i % 10000);
            pstm.executeUpdate();
        }
    }
}
