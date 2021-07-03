package com.yubin.homework.api.service;

import com.yubin.homework.api.model.Account;
import org.dromara.hmily.annotation.Hmily;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 14:51
 **/
public interface AccountService {
    @Hmily
    boolean pay(Account account);
}
