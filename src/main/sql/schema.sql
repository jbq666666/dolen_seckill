--- 数据库初始化脚本
-- 创建数据库
create database seckill;
-- 使用数据库
user seckill;
-- 创建秒杀库存表
drop table seckill;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '库存名称',
  `number` int(11) NOT NULL COMMENT '库存名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀开启时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';
-- 秒杀成功明细表
-- 用户登录相关表
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品id',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态表示：-1:无效，0：成功 1：',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `indx_seckillId_phone` (`seckill_id`,`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

