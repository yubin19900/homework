package com.yubin.homework.shardingsphere;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @program: homework
 * @description: 数据库框架版本
 * @author: Yu Bin
 * @create: 2021-06-20 19:03
 **/
@Slf4j
@Component
public class ShardingsphereDynamicDataSource {
    @Autowired
    private Environment environment;

    public DataSource createDataSource() {
        String[] dbs = environment.getProperty("spring.shardingsphere.datasource.names").split(",");
        // 设置打印SQL语句，查看主从配置和切换是否成功
        Properties properties = new Properties();
        properties.setProperty("sql-show", "true");
        DataSource dataSource = null;
        try {
            ReadwriteSplittingDataSourceRuleConfiguration dataSourceConfig = new ReadwriteSplittingDataSourceRuleConfiguration(
                    "slave-query", "", dbs[0], Arrays.asList(dbs[1]), null);
            ReadwriteSplittingRuleConfiguration ruleConfig = new ReadwriteSplittingRuleConfiguration(Collections.singleton(dataSourceConfig), Collections.emptyMap());
            dataSource = ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(dbs), Collections.singleton(ruleConfig), properties);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 返回DataSource列表
     */
    private Map<String, DataSource> createDataSourceMap(String[] dbs) {
        Map<String, DataSource> result = new HashMap<>(dbs.length);
        for (String db : dbs) {
            result.put(db, createDataSource("spring.shardingsphere.datasource." + db));
        }
        return result;
    }

    private DataSource createDataSource(String prefix) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty(prefix + ".driverClassName"));
        dataSource.setJdbcUrl(environment.getProperty(prefix + ".jdbcUrl"));
        dataSource.setUsername(environment.getProperty(prefix + ".username"));
        dataSource.setPassword(environment.getProperty(prefix + ".password"));
        return dataSource;
    }
}
