CREATE DATABASE  IF NOT EXISTS `billingsoftware`;
USE `billingsoftware`;

DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bankAccNo` varchar(255) DEFAULT NULL,
  `bankIfsc` varchar(255) DEFAULT NULL,
  `bankName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `gstNo` varchar(255) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `tinNo` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `bank_acc_no` varchar(255) DEFAULT NULL,
  `bank_ifsc` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `gst_no` varchar(255) DEFAULT NULL,
  `tin_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `BRAND_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `BRAND_ID_FK` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product_items`;
CREATE TABLE `product_items` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `BILL_DATE` datetime DEFAULT NULL,
  `BILL_NO` varchar(255) DEFAULT NULL,
  `BILL_TOTAL` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `DISCOUNT_TYPE` varchar(255) DEFAULT NULL,
  `ENTRY_DATE` datetime DEFAULT NULL,
  `GST` int(11) DEFAULT NULL,
  `NET_TOTAL` double DEFAULT NULL,
  `VENDOR_ID` int(11) DEFAULT NULL,
  `DISCOUNTED_AMOUNT` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_vendor_id` FOREIGN KEY (`VENDOR_ID`) REFERENCES `vendor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT  CHARSET=utf8;


DROP TABLE IF EXISTS `purchase_item`;
CREATE TABLE `purchase_item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_CODE` varchar(255) DEFAULT NULL,
  `MARGIN` double DEFAULT NULL,
  `MARGIN_TYPE` varchar(255) DEFAULT NULL,
  `MODEL` varchar(255) DEFAULT NULL,
  `PRICE_PER_PC` double DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `SR_NO` varchar(255) DEFAULT NULL,
  `TOTAL_PRICE` double DEFAULT NULL,
  `PRODUCT_ITEM_ID` int(20) DEFAULT NULL,
  `PURCHASE_ID` int(20) DEFAULT NULL,
  `PRODUCT_TYPE_TEXT` varchar(15) DEFAULT NULL,
  `SIZE` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_product_item_id` FOREIGN KEY (`PRODUCT_ITEM_ID`) REFERENCES `product_items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_purchase_id` FOREIGN KEY (`PURCHASE_ID`) REFERENCES `purchase` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `discount` double DEFAULT NULL,
  `discount_type` varchar(255) DEFAULT NULL,
  `discounted_amount` double DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  `gst` int(11) DEFAULT NULL,
  `invoice_date` datetime DEFAULT NULL,
  `invoice_no` varchar(255) DEFAULT NULL,
  `invoice_total` double DEFAULT NULL,
  `net_total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ITEM_CODE` varchar(255) DEFAULT NULL,
  `MODEL` varchar(255) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `SALE_PRICE_PER_PC` double DEFAULT NULL,
  `PRODUCT_TYPE_ID` int(11) DEFAULT NULL,
  `ITEM_ADDED_TS` datetime DEFAULT NULL,
  `ITEM_UPDATED_TS` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `MOBILE` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


