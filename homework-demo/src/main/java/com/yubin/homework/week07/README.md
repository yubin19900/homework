作业第二题 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
利用TestJdbcUtil测试工具，针对100万订单模拟数据,每次执行前保证表中无数据状态
1、普通插入方式，花1082s
2、事务插入方式，花355s
3、批量插入方式，花452s
4、事务批量插入方式，花357s
批量插入和事务插入方式执行比较快，最慢的是单个插入
代码地址:https://github.com/yubin19900/homework/blob/main/homework-database/src/main/java/com/yubin/homework/utils/TestJdbcUtil.java

作业第九题 读写分离 - 动态切换数据源版本 1.0
代码地址:https://github.com/yubin19900/homework/tree/main/homework-database/src/main/java/com/yubin/homework/database
测试地址:https://github.com/yubin19900/homework/blob/main/homework-database/src/test/java/com/yubin/homework/OrderInfoServiceImplTest.java

作业第十题 读写分离 - 数据库框架版本 2.0
代码地址:https://github.com/yubin19900/homework/blob/main/homework-database/src/main/java/com/yubin/homework/shardingsphere/ShardingsphereDynamicDataSource.java
测试地址:https://github.com/yubin19900/homework/blob/main/homework-database/src/test/java/com/yubin/homework/ShardingsphereDynamicDataSourceTest.java
