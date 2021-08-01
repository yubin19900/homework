第十三周作业
1、第一题
1)搭建一个 3 节点 Kafka 集群，测试功能和性能；
执行如下命令性能测试
命令:
bin/kafka-producer-perf-test.sh --topic test32 --num-records 100000 --record-size 1000 --throughput 200000 --producer-props bootstrap.servers=localhost:9002
结果:
100000 records sent, 24503.798089 records/sec (23.37 MB/sec), 933.24 ms avg latency, 1262.00 ms max latency, 955 ms 50th, 1182 ms 95th, 1237 ms 99th, 1256 ms 99.9th
命令:
bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9002 --topic test32 --fetch-size 1048576 --messages 100000 --threads 1
结果:
2021-08-01 22:22:45:710, 2021-08-01 22:24:25:833, 95.367, 125.5154, 100000, 139414.8368, 0.0000, 0, 100123, 0.0000, 0.0000
2)实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github
代码地址:https://github.com/yubin19900/homework/tree/main/homework-kafka
2、第六题
代码地址：https://github.com/yubin19900/homework/tree/main/homework-mymq
