package com.yubin.homework.database.factory;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-20 16:26
 **/
@Component
public class DataSourceFactory {
    @Resource(name = "master")
    DataSource masterDataSource;

    @Resource(name = "slave")
    DataSource slaveDataSource;

    public DataSource getDefaultDataSource() {
        return masterDataSource;
    }

    public DataSource getSlaveDataSource() {
        return slaveDataSource;
    }
}
