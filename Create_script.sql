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

ALTER TABLE `billingsoftware`.`purchase_item` 
ADD COLUMN `added_ts` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `total_price`,
ADD COLUMN `updated_ts` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `added_ts`;


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




CREATE TABLE `billingsoftware`.`sale_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sale_id` INT NOT NULL,
  `item_code` VARCHAR(45) NOT NULL,
  `item_desc` VARCHAR(50) NULL,
  `item_rate` DOUBLE NULL,
  `item_qty` INT NULL,
  `item_total` DOUBLE NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `billingsoftware`.`sale_items` 
ADD COLUMN `item_added_ts` DATETIME NULL AFTER `item_total`,
ADD COLUMN `item_updated_ts` DATETIME NULL AFTER `item_added_ts`;

create table role (
	id int(11), 
    role varchar(20), 
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `billingsoftware`.`role` (`id`, `role`) VALUES ('1', 'ADMIN');
INSERT INTO `billingsoftware`.`role` (`id`, `role`) VALUES ('2', 'USER');

CREATE TABLE `user_role` (
  `user_id` bigint(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `billingsoftware`.`users` (`id`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `MOBILE`, `PASSWORD`, `USER_NAME`) VALUES ('2', 'user@gmail.com', 'User', 'Rol', '9875465452', '$2y$12$JmvJp43hi4rsylmfKfB01eG6amMQ1rMBMkAxCpiLNrDcBYCsu8f8a', 'user@gmail.com');


CREATE TABLE `billingsoftware`.`permissions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `link` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `link_UNIQUE` (`link` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));
 INSERT INTO `billingsoftware`.`permissions` (`id`, `name`, `link`) VALUES ('1', 'Product', 'product/productList');
 
 CREATE TABLE `billingsoftware`.`permission_group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `permission_groupcol_UNIQUE` (`permission_groupcol` ASC));
  
  ALTER TABLE `billingsoftware`.`permissions` 
ADD COLUMN `permission_group_id` INT NULL AFTER `link`;

ALTER TABLE `billingsoftware`.`permissions` 
ADD INDEX `permission_group_id_idx` (`permission_group_id` ASC);
ALTER TABLE `billingsoftware`.`permissions` 
ADD CONSTRAINT `permission_group_id`
  FOREIGN KEY (`permission_group_id`)
  REFERENCES `billingsoftware`.`permission_group` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  DROP TABLE IF EXISTS `supplier`;
 CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_line_1` varchar(150) DEFAULT NULL,
  `address_line_2` varchar(150) DEFAULT NULL,
  `bank_acc_no` varchar(20) DEFAULT NULL,
  `bank_branch` varchar(50) DEFAULT NULL,
  `bank_city` varchar(50) DEFAULT NULL,
  `bank_ifsc` varchar(50) DEFAULT NULL,
  `bank_name` varchar(50) DEFAULT NULL,
  `bank_state` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `gst_no` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `tin_no` varchar(20) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `billingsoftware`.`role_permission` (
  `role_id` INT NOT NULL,
  `permission_id` INT NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`));