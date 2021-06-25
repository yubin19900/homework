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