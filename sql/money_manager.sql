/*
 Navicat MySQL Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : money2

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 07/04/2019 20:27:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acc_account
-- ----------------------------
DROP TABLE IF EXISTS `acc_account`;
CREATE TABLE `acc_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `account_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `note` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_have_acount`(`user_id`) USING BTREE,
  CONSTRAINT `FK_have_acount` FOREIGN KEY (`user_id`) REFERENCES `acc_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_assets
-- ----------------------------
DROP TABLE IF EXISTS `acc_assets`;
CREATE TABLE `acc_assets`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `assets_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `moner` decimal(10, 2) NOT NULL,
  `one_way` binary(1) NOT NULL,
  `note` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_have_assets`(`user_id`) USING BTREE,
  CONSTRAINT `FK_have_assets` FOREIGN KEY (`user_id`) REFERENCES `acc_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_family
-- ----------------------------
DROP TABLE IF EXISTS `acc_family`;
CREATE TABLE `acc_family`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `family_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `acc_transaction_record`;
CREATE TABLE `acc_transaction_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assets_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `other_user` bigint(20) NULL DEFAULT NULL,
  `other_assets` bigint(20) NULL DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `treasury_id` bigint(20) NULL DEFAULT NULL,
  `amount` decimal(10, 2) NOT NULL,
  `note` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_rel_acount`(`account_id`) USING BTREE,
  INDEX `FK_rel_assets`(`assets_id`) USING BTREE,
  INDEX `FK_rel_treasury`(`treasury_id`) USING BTREE,
  INDEX `FK_rel_type`(`type_id`) USING BTREE,
  INDEX `FK_rel_user`(`user_id`) USING BTREE,
  INDEX `FK_?Է??û?`(`other_user`) USING BTREE,
  INDEX `FK_rel_other_ssets`(`other_assets`) USING BTREE,
  CONSTRAINT `FK_?Է??û?` FOREIGN KEY (`other_user`) REFERENCES `acc_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rel_acount` FOREIGN KEY (`account_id`) REFERENCES `acc_account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rel_assets` FOREIGN KEY (`assets_id`) REFERENCES `acc_assets` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rel_other_ssets` FOREIGN KEY (`other_assets`) REFERENCES `acc_assets` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rel_treasury` FOREIGN KEY (`treasury_id`) REFERENCES `acc_treasury` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rel_type` FOREIGN KEY (`type_id`) REFERENCES `acc_transaction_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rel_user` FOREIGN KEY (`user_id`) REFERENCES `acc_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_transaction_type
-- ----------------------------
DROP TABLE IF EXISTS `acc_transaction_type`;
CREATE TABLE `acc_transaction_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `income` binary(1) NOT NULL,
  `note` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_have_type`(`user_id`) USING BTREE,
  INDEX `FK_parent`(`parent_id`) USING BTREE,
  CONSTRAINT `FK_have_type` FOREIGN KEY (`user_id`) REFERENCES `acc_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_parent` FOREIGN KEY (`parent_id`) REFERENCES `acc_transaction_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_treasury
-- ----------------------------
DROP TABLE IF EXISTS `acc_treasury`;
CREATE TABLE `acc_treasury`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `treasury_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `moner` decimal(10, 2) NOT NULL,
  `note` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_heve_treasury`(`user_id`) USING BTREE,
  CONSTRAINT `FK_heve_treasury` FOREIGN KEY (`user_id`) REFERENCES `acc_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_user
-- ----------------------------
DROP TABLE IF EXISTS `acc_user`;
CREATE TABLE `acc_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` int(1) NULL DEFAULT NULL,
  `authority` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `neck_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`user_name`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for family_mapping
-- ----------------------------
DROP TABLE IF EXISTS `family_mapping`;
CREATE TABLE `family_mapping`  (
  `family_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`family_id`, `user_id`) USING BTREE,
  INDEX `FK_family_mapping2`(`user_id`) USING BTREE,
  CONSTRAINT `FK_family_mapping` FOREIGN KEY (`family_id`) REFERENCES `acc_family` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_family_mapping2` FOREIGN KEY (`user_id`) REFERENCES `acc_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
