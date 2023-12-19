/*
 Navicat Premium Data Transfer

 Source Server         : localDocker
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : db_pj

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 19/12/2023 18:18:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus
-- ----------------------------
DROP TABLE IF EXISTS `bus`;
CREATE TABLE `bus`  (
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '位置',
  `price` int NOT NULL COMMENT '价格',
  `numBus` int NOT NULL COMMENT '总坐',
  `numAvail` int NOT NULL COMMENT '可坐',
  PRIMARY KEY (`location`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '大巴表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus
-- ----------------------------
INSERT INTO `bus` VALUES ('A', 43, 20, 19);
INSERT INTO `bus` VALUES ('B', 5, 20, 20);
INSERT INTO `bus` VALUES ('C', 5, 20, 20);
INSERT INTO `bus` VALUES ('D', 7, 20, 20);
INSERT INTO `bus` VALUES ('E', 121, 111, 111);
INSERT INTO `bus` VALUES ('W', 121, 111, 111);

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `custName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户名',
  `custID` int NOT NULL COMMENT '客户ID',
  PRIMARY KEY (`custName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('test', 14);
INSERT INTO `customers` VALUES ('刘俊驰', 7);
INSERT INTO `customers` VALUES ('吕语堂', 5);
INSERT INTO `customers` VALUES ('吴博文', 1);
INSERT INTO `customers` VALUES ('孔伟祺', 2);
INSERT INTO `customers` VALUES ('宋鸿煊', 9);
INSERT INTO `customers` VALUES ('方熠彤', 10);
INSERT INTO `customers` VALUES ('石晓博', 6);
INSERT INTO `customers` VALUES ('谭皓轩', 11);
INSERT INTO `customers` VALUES ('邵潇然', 8);
INSERT INTO `customers` VALUES ('金峻熙', 4);
INSERT INTO `customers` VALUES ('韩越泽', 3);

-- ----------------------------
-- Table structure for flights
-- ----------------------------
DROP TABLE IF EXISTS `flights`;
CREATE TABLE `flights`  (
  `flightNum` int NOT NULL AUTO_INCREMENT COMMENT '号',
  `price` int NOT NULL COMMENT '价',
  `numSeats` int NOT NULL COMMENT '总坐',
  `numAvail` int NOT NULL COMMENT '可坐',
  `FromCity` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'from',
  `ArivCity` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '目的地',
  PRIMARY KEY (`flightNum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '航班信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flights
-- ----------------------------
INSERT INTO `flights` VALUES (1000, 284, 20, 18, 'A', 'B');
INSERT INTO `flights` VALUES (1001, 371, 20, 20, 'A', 'C');
INSERT INTO `flights` VALUES (1002, 228, 20, 20, 'A', 'D');
INSERT INTO `flights` VALUES (1003, 166, 20, 20, 'B', 'D');
INSERT INTO `flights` VALUES (1004, 246, 20, 20, 'B', 'C');
INSERT INTO `flights` VALUES (1005, 209, 20, 20, 'D', 'A');
INSERT INTO `flights` VALUES (1006, 223, 20, 20, 'C', 'A');
INSERT INTO `flights` VALUES (1007, 123, 1, 0, 'G', 'F');

-- ----------------------------
-- Table structure for hotels
-- ----------------------------
DROP TABLE IF EXISTS `hotels`;
CREATE TABLE `hotels`  (
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '位置',
  `price` int NOT NULL COMMENT '价格',
  `numRooms` int NOT NULL COMMENT '房间号',
  `numAvail` int NOT NULL COMMENT '剩余房间',
  PRIMARY KEY (`location`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'DB_PJ.`HOTELS`' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hotels
-- ----------------------------
INSERT INTO `hotels` VALUES ('A', 132, 100, 17);
INSERT INTO `hotels` VALUES ('B', 118, 101, 20);
INSERT INTO `hotels` VALUES ('C', 127, 102, 20);
INSERT INTO `hotels` VALUES ('D', 274, 103, 20);

-- ----------------------------
-- Table structure for reservations
-- ----------------------------
DROP TABLE IF EXISTS `reservations`;
CREATE TABLE `reservations`  (
  `resvKey` int NOT NULL AUTO_INCREMENT,
  `custName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户名',
  `resvType` int NOT NULL COMMENT '预定类型',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预订的型号',
  PRIMARY KEY (`resvKey`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '预定表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservations
-- ----------------------------
INSERT INTO `reservations` VALUES (4, 'test', 2, 'A');
INSERT INTO `reservations` VALUES (5, 'test', 1, '1000');
INSERT INTO `reservations` VALUES (6, 'test', 3, 'A');
INSERT INTO `reservations` VALUES (7, 'test', 1, '1007');

-- ----------------------------
-- Triggers structure for table customers
-- ----------------------------
DROP TRIGGER IF EXISTS `trg_customers_insert`;
delimiter ;;
CREATE TRIGGER `trg_customers_insert` BEFORE INSERT ON `customers` FOR EACH ROW BEGIN
    DECLARE maxID INT;
    SET maxID = (SELECT IFNULL(MAX(`custID`), 0) FROM `customers`);
    SET NEW.`custID` = maxID + 1;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
