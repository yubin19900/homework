第八周作业
作业二：设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作
1、下载shardingsphere-proxy,版本为apache-shardingsphere-5.0.0-beta-shardingsphere-proxy,下载后解压并配置两个文件：server.yaml、config-sharding.yaml,示例如下
server.yaml配置如下:
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

######################################################################################################
# 
# If you want to configure governance, authorization and proxy properties, please refer to this file.
# 
######################################################################################################

#governance:
#  name: governance_ds
#  registryCenter:
#    type: ZooKeeper
#    serverLists: localhost:2181
#    props:
#      retryIntervalMilliseconds: 500
#      timeToLiveSeconds: 60
#      maxRetries: 3
#      operationTimeoutMilliseconds: 500
#  overwrite: false

#scaling:
#  blockQueueSize: 10000
#  workerThread: 40

rules:
 - !AUTHORITY
   users:
     - root@localhost:root
     - sharding@:sharding
   provider:
     type: NATIVE

props:
  max-connections-size-per-query: 1
  acceptor-size: 16  # The default value is available processors count * 2.
  executor-size: 16  # Infinite by default.
  proxy-frontend-flush-threshold: 128  # The default value is 128.
    # LOCAL: Proxy will run with LOCAL transaction.
    # XA: Proxy will run with XA transaction.
    # BASE: Proxy will run with B.A.S.E transaction.
  proxy-transaction-type: LOCAL
  proxy-opentracing-enabled: false
  proxy-hint-enabled: false
  query-with-cipher-column: true
  sql-show: true
  check-table-metadata-enabled: false
  
  config-sharding.yaml配置如下
  #
  # Licensed to the Apache Software Foundation (ASF) under one or more
  # contributor license agreements.  See the NOTICE file distributed with
  # this work for additional information regarding copyright ownership.
  # The ASF licenses this file to You under the Apache License, Version 2.0
  # (the "License"); you may not use this file except in compliance with
  # the License.  You may obtain a copy of the License at
  #
  #     http://www.apache.org/licenses/LICENSE-2.0
  #
  # Unless required by applicable law or agreed to in writing, software
  # distributed under the License is distributed on an "AS IS" BASIS,
  # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  # See the License for the specific language governing permissions and
  # limitations under the License.
  #
  
  ######################################################################################################
  # 
  # Here you can configure the rules for the proxy.
  # This example is configuration of sharding rule.
  # 
  ######################################################################################################
 
  schemaName: sharding_db
  dataSources:
   ds_0:
     url: jdbc:mysql://127.0.0.1:3316/demo_ds_0?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true
     username: root
     password: 
     connectionTimeoutMilliseconds: 30000
     idleTimeoutMilliseconds: 60000
     maxLifetimeMilliseconds: 1800000
     maxPoolSize: 50
     minPoolSize: 1
     maintenanceIntervalMilliseconds: 30000
   ds_1:
     url: jdbc:mysql://127.0.0.1:3326/demo_ds_1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true
     username: root
     password: 
     connectionTimeoutMilliseconds: 30000
     idleTimeoutMilliseconds: 60000
     maxLifetimeMilliseconds: 1800000
     maxPoolSize: 50
     minPoolSize: 1
     maintenanceIntervalMilliseconds: 30000
  
  rules:
  - !SHARDING
   tables:
     t_order_info:
       actualDataNodes: ds_${0..1}.t_order_info_${0..15}
       tableStrategy:
         standard:
           shardingColumn: order_id
           shardingAlgorithmName: t_order_info_inline
   defaultDatabaseStrategy:
     standard:
       shardingColumn: user_id
       shardingAlgorithmName: database_inline
   
   shardingAlgorithms:
     database_inline:
       type: INLINE
       props:
         algorithm-expression: ds_${user_id % 2}
     t_order_info_inline:
       type: INLINE
       props:
         algorithm-expression: t_order_info_${order_id % 16}
2、直接进入sharding的根目录下的bin目录中运行：./start.sh 13306(指定端口号)，查看启动日志,'ShardingSphere-Proxy start success' 说明启动成功
3、使用数据库客户端连接shardingsphere-proxy,查看ShardingSphere-Proxy logs/stdout.log日志有多个创建语句，创建表脚本如下：
CREATE TABLE `t_order_info` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `order_number` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `deliver_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'  COMMENT '发货时间',
  `expect_distribution_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'   COMMENT '期望配送时间',
  `receive_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'   COMMENT '收货时间',
  `receive_name` varchar(32) NOT NULL DEFAULT '' COMMENT '收货人',
  `receive_mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '收货人电话',
  `receive_address` varchar(128) NOT NULL DEFAULT '' COMMENT '收货地址',
  `order_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '0未支付 2已支付 3已发货 4已收货 5已评价 6退货 7换货 8退货完成 9换货完成',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='订单表';
同时执行drop table也能成功
4、使用jdbc工具类进行测试，代码见同目录下:TestJdbcUtil


