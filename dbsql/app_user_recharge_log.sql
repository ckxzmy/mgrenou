/*
 Navicat Premium Data Transfer

 Source Server         : mg
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : mgrenou

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 04/12/2022 17:28:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_user_recharge_log
-- ----------------------------
DROP TABLE IF EXISTS `app_user_recharge_log`;
CREATE TABLE `app_user_recharge_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `finance_id` int(0) NULL DEFAULT NULL,
  `amount` decimal(19, 2) NULL DEFAULT 0.00,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
