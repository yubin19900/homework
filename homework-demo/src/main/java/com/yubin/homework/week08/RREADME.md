第八周作业

作业二：设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作

1、下载shardingsphere-proxy,版本为apache-shardingsphere-5.0.0-beta-shardingsphere-proxy,下载后解压并配置两个文件：server.yaml、config-sharding.yaml示例在该目录下

2、直接进入sharding的根目录下的bin目录中运行：./start.sh 13306(指定端口号)，查看启动日志,'ShardingSphere-Proxy start success' 说明启动成功

3、使用数据库客户端连接shardingsphere-proxy,查看ShardingSphere-Proxy logs/stdout.log日志有多个创建语句，创建表脚本在该目录下，同时执行drop table也能成功

4、使用jdbc工具类进行测试，代码见同目录下:TestJdbcUtil

作业六：基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），选择了基于Atomikos XA事务实现

代码地址：https://github.com/yubin19900/homework/tree/main/homework-transaction


