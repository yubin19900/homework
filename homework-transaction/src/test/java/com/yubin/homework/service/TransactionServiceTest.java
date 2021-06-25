package com.yubin.homework.service;

import com.yubin.homework.HomeworkTransactionApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomeworkTransactionApplication.class)
@Slf4j
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;


    @Test
    public void insert() {
        transactionService.insert(31);
    }
}