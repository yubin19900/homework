package com.yubin.homework.trade.service.impl;

import com.yubin.homework.api.model.Account;
import com.yubin.homework.api.service.AccountService;
import com.yubin.homework.trade.service.TradeService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 16:51
 **/
@Service("tradeService")
public class TradeServiceImpl implements TradeService {
    @Autowired
    private AccountService accountService;

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void trade() {
        transactionA();
        transactionB();
    }

    private void transactionA() {
        Account account = new Account();
        account.setId(1L);
        account.setUsMoney(-1d);
        account.setCnyMoney(7d);
        accountService.pay(account);
    }

    private void transactionB() {
        Account account = new Account();
        account.setId(2L);
        account.setUsMoney(1d);
        account.setCnyMoney(-7d);
        accountService.pay(account);
    }

    public void confirmOrderStatus() {
        //TODO 确认操作
    }

    public void cancelOrderStatus() {
        //TODO 取消操作
    }
}
