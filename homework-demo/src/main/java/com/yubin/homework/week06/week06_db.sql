drop table if exists `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nick_name` varchar(64) NOT NULL DEFAULT '' COMMENT '昵称',
  `real_name` varchar(64) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(32) NOT NULL DEFAULT '' COMMENT '加密盐',
  `email` varchar(32) NOT NULL DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '状态 0 停用 1 启用',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

drop table if exists `user_info_ext`;
CREATE TABLE `user_info_ext` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `sex` smallint(6) DEFAULT '0' COMMENT '性别 0默认 1男 2女',
  `photo` varchar(128) DEFAULT '' COMMENT '头像',
  `address` varchar(128) DEFAULT '' COMMENT '联系地址',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展表';

drop table if exists `goods_info`;
CREATE TABLE `goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_name` varchar(128) NOT NULL DEFAULT '' COMMENT '商品名称',
  `purchase_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品进价 单位:元',
  `sale_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品售价 单位:元',
  `discount_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品折扣价 单位:元',
  `goods_brand_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品品牌ID',
  `first_category` int(8) NOT NULL DEFAULT '0' COMMENT '一级分类',
  `second_category` int(8) NOT NULL DEFAULT '0' COMMENT '二级分类',
  `third_category` int(8) NOT NULL DEFAULT '0' COMMENT '三级分类',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '0 下架 1上架',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='商品表';


drop table if exists `goods_info_ext`;
CREATE TABLE `goods_info_ext` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `goods_product` varchar(128) NOT NULL DEFAULT '' COMMENT '商品产地',
  `goods_model` varchar(128) NOT NULL DEFAULT '' COMMENT '商品型号',
  `goods_desc` varchar(512) NOT NULL DEFAULT '' COMMENT '商品描述',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='商品扩展表';

drop table if exists `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

drop table if exists `order_pay`;
CREATE TABLE `order_pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单ID',
  `pay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `pay_type_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '支付方式ID',
  `total_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价 单位:元',
  `reduced_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '优惠 单位:元',
  `freight_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '运费 单位:元',
  `red_packet_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '红包 单位:元',
  `actual_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '订单实际支付价格 单位:元',
  `pay_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '0未支付 2已支付',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='订单支付表';


drop table if exists `order_goods_info`;
CREATE TABLE `order_goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单ID',
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `purchase_number` int(8) NOT NULL DEFAULT '0' COMMENT '购买数量',
  `sale_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品售价 单位:元',
  `total_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品总价 单位:元',
  `reduced_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '优惠 单位:元',
  `actual_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '实际支付价格 单位:元',
  `gift` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否赠品 0否 1是',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='订单商品表';

drop table if exists `pay_type`;
CREATE TABLE `pay_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_name` varchar(128) NOT NULL DEFAULT '' COMMENT '支付方式',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='支付类型表';


drop table if exists `goods_brand`;
CREATE TABLE `goods_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `brand_name` varchar(128) NOT NULL DEFAULT '' COMMENT '品牌名称',
  `brand_company` varchar(128) NOT NULL DEFAULT '' COMMENT '品牌公司',
  `brand_logo_url` varchar(128) NOT NULL DEFAULT '' COMMENT '品牌logo',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='商品品牌表';

drop table if exists `role_info`;
CREATE TABLE `role_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(64) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_type` smallint(6) DEFAULT '1' COMMENT '角色类型 1普通用户 2管理员',
  `deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除 1 是 0 否',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `add_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

drop table if exists `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='用户角色中间表';