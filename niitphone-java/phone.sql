create database NIIT;
use NIIT;

CREATE TABLE `user`  (
  `user_id` int NOT NULL COMMENT '顾客ID',
  `password` varchar(200) NULL COMMENT '顾客密码',
  `user_name` varchar(20) NOT NULL COMMENT '顾客姓名',
  `user_sex` varchar(2) NULL COMMENT '顾客性别',
  `user_phone` varchar(12) NULL COMMENT '顾客电话',
  `user_age` int NULL COMMENT '顾客年龄',
  `user_address` varchar(100) NULL COMMENT '顾客地址',
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `order`  (
  `order_id` int NOT NULL COMMENT '订单ID',
  `user_id` int NOT NULL COMMENT '顾客ID',
  `product_id` int NOT NULL COMMENT '产品ID',
  `order_quantity` int NULL COMMENT '数量',
  `create_time` datetime NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
);

 CREATE TABLE `product`  (
  `product_id` int NOT NULL COMMENT '产品ID',
  `product_name` varchar(20) NOT NULL COMMENT '产品名称',
  `product_img` varchar(100) default '' COMMENT '图片路径',
  `product_price` float(10, 2) NULL COMMENT '产品单价',
  `product_class` varchar(20) COMMENT '产品类别',
  PRIMARY KEY (`product_id`)
);


ALTER TABLE `order` ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
ALTER TABLE `order` ADD CONSTRAINT `fk_product_id1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);