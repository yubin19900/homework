package com.yubin.homework.account.service.impl;

import com.yubin.homework.account.dao.AccountDAO;
import com.yubin.homework.api.model.Account;
import com.yubin.homework.api.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 14:52
 **/
@Slf4j
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean pay(Account account) {
        return accountDAO.updateAccount(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(Account account) {
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(Account account) {
        return true;
    }
}
