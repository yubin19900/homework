第十二周作业
第一题
配置 redis 的主从复制，sentinel 高可用，Cluster 集群
1、redis主从复制实现核心步骤
redis的主从复制实现非常的简单，极简的风格, 从节点执行:
> SLAVEOF 127.0.0.1 6379

2、sentinel 高可用
设置哨兵 修改sentinel.conf文件,注意修改myid和端口,
分别启动哨兵redis-sentinel sentinel0.conf/redis-sentinel sentinel1.conf
主节点redis-cli -h 192.168.0.1 -p 6379 shutdown 关闭后哨兵在10s后进行选举发现主节点发生变化
3、Cluster 集群
Redis集群最少需要三台主服务器，三台从服务器 端口号分别为：6379~6384
修改配置文件redis.conf 如下内容，重复其他两台从服务器
> cluster-enabled yes

创建redis集群如下:
./redis-cli --cluster create 192.168.0.1:6379 192.168.0.1:6380 192.168.0.1:6381 192.168.0.1:6382 192.168.0.1:6383 192.168.0.1:6384 --cluster-replicas 1

客户端连接 redis-cli –h 192.168.0.1 –p 6379 –c
查看集群状态
192.168.0.1:6379> cluster info
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:3
cluster_stats_messages_sent:926
cluster_stats_messages_received:926
第六题
搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github
代码地址:https://github.com/yubin19900/homework/tree/main/homework-activemq