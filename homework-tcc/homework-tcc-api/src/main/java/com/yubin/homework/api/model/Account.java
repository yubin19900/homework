package com.yubin.homework.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-03 14:58
 **/
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 3409783718015895688L;

    private long id;

    private String userName;

    private double cnyMoney;

    private double usMoney;
}
