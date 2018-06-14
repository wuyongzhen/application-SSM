/*
Navicat MySQL Data Transfer

Source Server         : 10.10.10.191_3306
Source Server Version : 50721
Source Host           : 10.10.10.191:3306
Source Database       : application

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-06-14 15:44:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '使用人',
  `user` varchar(255) NOT NULL COMMENT '登录账号',
  `password` varchar(255) NOT NULL COMMENT '登录密码：使用MD5加密，非本人不可知',
  `role` tinyint(4) NOT NULL COMMENT '角色权限字段：1为查看合作商，2为查看合伙人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `content` varchar(255) DEFAULT NULL COMMENT '公告内容',
  `upload_time` date DEFAULT NULL COMMENT '上传时间',
  `neeq_time` date DEFAULT NULL COMMENT '新三板发布时间',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `del` tinyint(4) DEFAULT NULL COMMENT '0为正常，1为删除（假删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `cooperative_partner`
-- ----------------------------
DROP TABLE IF EXISTS `cooperative_partner`;
CREATE TABLE `cooperative_partner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司全称',
  `established_time` date DEFAULT NULL COMMENT '成立时间',
  `company_address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `legal_personality` varchar(30) DEFAULT NULL COMMENT '法人代表',
  `registered_capital` varchar(30) DEFAULT NULL COMMENT '注册资本',
  `nature` tinyint(4) DEFAULT NULL COMMENT '公司性质：1个体，2独资，3股份制',
  `principal` varchar(30) DEFAULT NULL COMMENT '负责人',
  `duty` varchar(20) DEFAULT NULL COMMENT '职务',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `company_water` varchar(30) DEFAULT NULL COMMENT '公司年流水',
  `company_scale` varchar(30) DEFAULT NULL COMMENT '公司人数',
  `business` varchar(255) DEFAULT NULL COMMENT '主营业务',
  `client_subject` varchar(50) DEFAULT NULL COMMENT '客户主体',
  `advantage` varchar(255) DEFAULT NULL COMMENT '公司核心优势',
  `inspect` tinyint(4) DEFAULT NULL COMMENT '是否申请北京总部考察',
  `intention` varchar(4) DEFAULT NULL COMMENT '意向产品项目    1:网路宝（盛世云+无线WF监管）项目\r\n2:网路神警上网行为审计项目（大型场所）\r\n3:特征码采集（卡扣）项目\r\n4:食品安全快检技术项目',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creation_time` datetime DEFAULT NULL COMMENT '合作商申请时间',
  `del_state` tinyint(4) unsigned zerofill DEFAULT NULL COMMENT '删除状态:1为删除，为1时不删除数据，仅显示隐藏，防止误操作',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `customer_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `customer_feedback`;
CREATE TABLE `customer_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '反馈人',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `qq` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `content` varchar(255) DEFAULT NULL COMMENT '反馈内容',
  `type` tinyint(4) DEFAULT NULL COMMENT '1:对产品的意见和建议，2:对服务的意见和建议,3:对官网的意见和建议，4:其他',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creation_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `partner`
-- ----------------------------
DROP TABLE IF EXISTS `partner`;
CREATE TABLE `partner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '合伙人姓名',
  `age` tinyint(4) DEFAULT NULL COMMENT '合伙人年龄',
  `phone` varchar(255) DEFAULT NULL COMMENT '合伙人手机号',
  `native_place` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `mailbox` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `company_name` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `duty` varchar(255) DEFAULT NULL COMMENT '担任职务',
  `site` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '合伙人申请时间',
  `del` tinyint(4) DEFAULT '0' COMMENT '假删除：1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
