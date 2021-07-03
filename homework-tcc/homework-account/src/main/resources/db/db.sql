CREATE TABLE `account_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `cny_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价 单位:元',
  `us_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价 单位:元',
  `deleted` smallint NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='交易表';

INSERT INTO geek.account_info
(id, user_name, cny_money, us_money, deleted, add_time, update_time)
VALUES(1, '张三', 100.00, 10.00, 0, '2021-07-03 15:28:15', '2021-07-03 15:28:15');
INSERT INTO geek.account_info
(id, user_name, cny_money, us_money, deleted, add_time, update_time)
VALUES(2, '李四', 10.00, 100.00, 0, '2021-07-03 15:28:15', '2021-07-03 15:28:15');
