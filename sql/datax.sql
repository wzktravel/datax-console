SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` varchar(128) NOT NULL,
  `name` varchar(255) NOT NULL,
  `registered_date` varchar(255) DEFAULT NULL,
  `registered_capital` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `main_category` varchar(255) DEFAULT NULL,
  `big_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `prediction_model`
-- ----------------------------
DROP TABLE IF EXISTS `prediction_model`;
CREATE TABLE `prediction_model` (
  `id` varchar(128) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `prediction_result`
-- ----------------------------
DROP TABLE IF EXISTS `prediction_result`;
CREATE TABLE `prediction_result` (
  `id` varchar(255) NOT NULL,
  `model_id` varchar(255) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `score` double DEFAULT NULL,
  `prediction` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
