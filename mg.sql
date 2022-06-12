/*
Navicat MySQL Data Transfer

Source Server         : mg
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : mgrenou

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2022-06-12 20:07:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_token`
-- ----------------------------
DROP TABLE IF EXISTS `account_token`;
CREATE TABLE `account_token` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `user_flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `token` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_app_user_doll`
-- ----------------------------
DROP TABLE IF EXISTS `mg_app_user_doll`;
CREATE TABLE `mg_app_user_doll` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `doll_id` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `suit_id` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `color_id` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_app_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `mg_app_user_info`;
CREATE TABLE `mg_app_user_info` (
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_mobile` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `user_password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_pet_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_category`
-- ----------------------------
DROP TABLE IF EXISTS `mg_category`;
CREATE TABLE `mg_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `category_type` int DEFAULT NULL,
  `sale_type` int DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `part_id` int DEFAULT NULL,
  `color_id` int DEFAULT NULL,
  `suit_id` int DEFAULT NULL,
  `owner_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_color`
-- ----------------------------
DROP TABLE IF EXISTS `mg_color`;
CREATE TABLE `mg_color` (
  `color_id` int NOT NULL AUTO_INCREMENT,
  `color_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `color_type` int DEFAULT NULL,
  `sale_type` int DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  `owner_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`color_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_color_group`
-- ----------------------------
DROP TABLE IF EXISTS `mg_color_group`;
CREATE TABLE `mg_color_group` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_config_data`
-- ----------------------------
DROP TABLE IF EXISTS `mg_config_data`;
CREATE TABLE `mg_config_data` (
  `config_id` int NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `config_type` int DEFAULT NULL,
  `config_value` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_doll`
-- ----------------------------
DROP TABLE IF EXISTS `mg_doll`;
CREATE TABLE `mg_doll` (
  `doll_id` int NOT NULL AUTO_INCREMENT,
  `doll_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `doll_type` int DEFAULT NULL COMMENT '1-normal,2-sale',
  `sale_type` int DEFAULT NULL COMMENT '1-money,2-point',
  `price` decimal(9,2) DEFAULT NULL,
  `owner_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`doll_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_manage_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `mg_manage_user_info`;
CREATE TABLE `mg_manage_user_info` (
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_pet_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_material`
-- ----------------------------
DROP TABLE IF EXISTS `mg_material`;
CREATE TABLE `mg_material` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `material_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `layer_level` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `color_id` int DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_note_info`
-- ----------------------------
DROP TABLE IF EXISTS `mg_note_info`;
CREATE TABLE `mg_note_info` (
  `note_id` int NOT NULL AUTO_INCREMENT,
  `note_value` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_mobile` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `send_status` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `res_contents` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_part`
-- ----------------------------
DROP TABLE IF EXISTS `mg_part`;
CREATE TABLE `mg_part` (
  `part_id` int NOT NULL AUTO_INCREMENT,
  `part_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `part_type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `doll_id` int DEFAULT NULL,
  `color_group_id` int DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `status` int unsigned DEFAULT '1',
  PRIMARY KEY (`part_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_sale_log`
-- ----------------------------
DROP TABLE IF EXISTS `mg_sale_log`;
CREATE TABLE `mg_sale_log` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `product_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '1-doll,2-material,3-color,4-suit',
  `product_id` int DEFAULT NULL,
  `owner_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `sale_type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `mg_suit`
-- ----------------------------
DROP TABLE IF EXISTS `mg_suit`;
CREATE TABLE `mg_suit` (
  `suit_id` int NOT NULL AUTO_INCREMENT,
  `suit_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `suit_type` int DEFAULT NULL COMMENT '1-normal,2-sale',
  `sale_type` int DEFAULT NULL COMMENT '1-money,2-point',
  `price` decimal(9,2) DEFAULT NULL,
  `owner_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `insert_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`suit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

