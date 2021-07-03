package com.yubin.homework.account.dao;

import com.yubin.homework.api.model.Account;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 17:27
 **/
@Repository
public interface AccountDAO {

    @Update("update account_info set cny_money = cny_money+#{cnyMoney},us_money = us_money+#{usMoney},update_time= now() where id = #{id}")
    boolean updateAccount(Account account);
}
