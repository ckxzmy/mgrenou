/*
Navicat MySQL Data Transfer

Source Server         : mg
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : mgrenou

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2022-06-19 23:03:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_token`
-- ----------------------------
DROP TABLE IF EXISTS `account_token`;
CREATE TABLE `account_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `user_flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `token` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
