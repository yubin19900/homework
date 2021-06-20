package com.yubin.homework.database.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-06-20 16:29
 **/
@Configuration
public class DataSourceConfig {

    @Bean(name = "datasourceMasterProperties")
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSourceProperties datasourceMasterProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "datasourceSlaveProperties")
    @ConfigurationProperties(prefix = "datasource.slave")
    public DataSourceProperties datasourceSlaveProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "master")
    public DataSource masterDataSource(@Qualifier("datasourceMasterProperties") DataSourceProperties dataSourceProperties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUserName());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;

    }

    @Bean(name = "slave")
    public DataSource slave1DataSource(@Qualifier("datasourceSlaveProperties") DataSourceProperties dataSourceProperties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUserName());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }
}
