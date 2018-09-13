-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: billingsoftware
-- ------------------------------------------------------
-- Server version	5.6.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_role`
--

DROP TABLE IF EXISTS `app_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_role` (
  `ROLE_ID` bigint(20) NOT NULL,
  `ROLE_NAME` varchar(30) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `APP_ROLE_UK` (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_role`
--

LOCK TABLES `app_role` WRITE;
/*!40000 ALTER TABLE `app_role` DISABLE KEYS */;
INSERT INTO `app_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `app_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `USER_ID` bigint(20) NOT NULL,
  `USER_NAME` varchar(36) NOT NULL,
  `ENCRYTED_PASSWORD` varchar(128) NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `APP_USER_UK` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'dbadmin1','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',''),(2,'dbuser1','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu','');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Other'),(2,'Other2'),(3,'Other3'),(4,'Other4'),(5,'Other5'),(6,'Other6'),(12,'ABCDE'),(13,'HIJKLAAA');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Hyderabad','v@gmail.com','2018-09-14 00:38:38','9878547854','Test2');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_group`
--

DROP TABLE IF EXISTS `permission_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_group`
--

LOCK TABLES `permission_group` WRITE;
/*!40000 ALTER TABLE `permission_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `link` varchar(100) NOT NULL,
  `permission_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `link_UNIQUE` (`link`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `permission_group_id_idx` (`permission_group_id`),
  CONSTRAINT `permission_group_id` FOREIGN KEY (`permission_group_id`) REFERENCES `permission_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (2,'Supplier-Edit','${pageContext.request.contextPath}/ajax/suppliers',NULL),(3,'Supplier-List','${pageContext.request.contextPath}/ajax/permissions/all',NULL),(6,'Brand-Save','${pageContext.request.contextPath}/ajax/brand/save',NULL),(8,'Supplier-Save','${pageContext.request.contextPath}/ajax/permissions/save',NULL),(9,'Supplier-Delete','${pageContext.request.contextPath}/ajax/permissions/delete',NULL);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'Jeans'),(2,1,'Shirts');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_items`
--

DROP TABLE IF EXISTS `product_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_items`
--

LOCK TABLES `product_items` WRITE;
/*!40000 ALTER TABLE `product_items` DISABLE KEYS */;
INSERT INTO `product_items` VALUES (1,'28 to 32 Size','28 - 30 - 32',1),(2,'34 to 36','34-36',1),(3,'L size','L',2),(4,'M size','M',2);
/*!40000 ALTER TABLE `product_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL,
  `bill_no` varchar(255) DEFAULT NULL,
  `bill_total` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `discount_type` varchar(255) DEFAULT NULL,
  `discounted_amount` double DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  `gst` int(11) DEFAULT NULL,
  `net_total` double DEFAULT NULL,
  `vendor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjl17pcx27nu0uul784vqla9f` (`vendor_id`),
  CONSTRAINT `FKjl17pcx27nu0uul784vqla9f` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,'2018-07-06 00:00:00','9874',11610,0,NULL,0,'2018-06-07 22:00:04',NULL,11610,1),(2,'2018-01-07 00:00:00','456',137850,0,NULL,0,'2018-07-01 02:24:58',NULL,137850,1);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_item`
--

DROP TABLE IF EXISTS `purchase_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(255) DEFAULT NULL,
  `margin` double DEFAULT NULL,
  `margin_type` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `price_per_pc` double DEFAULT NULL,
  `product_item_id` bigint(20) DEFAULT NULL,
  `purchase_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `sr_no` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `added_ts` datetime NOT NULL,
  `updated_ts` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_item`
--

LOCK TABLES `purchase_item` WRITE;
/*!40000 ALTER TABLE `purchase_item` DISABLE KEYS */;
INSERT INTO `purchase_item` VALUES (6,'JA0096',20,'%',NULL,800,1,1,11,NULL,'4',8800,'2018-06-07 23:45:21','2018-06-07 23:45:41'),(7,'JB0070',25,'%',NULL,562,2,1,5,NULL,'2',2810,'2018-06-14 23:56:37','2018-06-14 23:56:37'),(8,'SC0063',20,'%',NULL,521,3,2,50,NULL,'1',26050,'2018-07-01 02:24:58','2018-07-01 02:24:58'),(9,'SD0082',25,'%',NULL,652,4,2,65,NULL,'2',42380,'2018-07-01 02:25:18','2018-07-01 02:25:18'),(10,'JA0065',25,'%',NULL,520,1,2,52,NULL,'',27040,'2018-07-01 02:25:52','2018-07-01 02:25:52'),(11,'JB0082',25,'%',NULL,652,2,2,65,NULL,'',42380,'2018-07-01 02:26:05','2018-07-01 02:26:05');
/*!40000 ALTER TABLE `purchase_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_item_yet_to_save`
--

DROP TABLE IF EXISTS `purchase_item_yet_to_save`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_item_yet_to_save` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `item_code` varchar(255) DEFAULT NULL,
  `margin` double DEFAULT NULL,
  `margin_type` varchar(255) DEFAULT NULL,
  `price_per_pc` double DEFAULT NULL,
  `product_size_id` bigint(20) DEFAULT NULL,
  `product_type_id` bigint(20) DEFAULT NULL,
  `purchase_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sr_no` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_item_yet_to_save`
--

LOCK TABLES `purchase_item_yet_to_save` WRITE;
/*!40000 ALTER TABLE `purchase_item_yet_to_save` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_item_yet_to_save` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id_FK_idx` (`permission_id`),
  CONSTRAINT `FK2xn8qv4vw30i04xdxrpvn3bdi` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `permission_id_FK` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_id_FK` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,2),(2,2),(1,3),(2,3),(1,6),(1,9);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (1,NULL,'RS',0,'2018-06-20 00:09:02',NULL,'2018-06-20 00:07:14','201806200',3069,3069),(2,NULL,'RS',2366,'2018-06-20 00:15:09',NULL,'2018-06-20 00:15:01','201806200',2366,2366),(3,NULL,'RS',0,'2018-06-20 00:26:41',NULL,'2018-06-20 00:26:40','201806200',960,960),(4,NULL,'%',0,'2018-06-30 23:27:43',NULL,'2018-06-30 23:27:42','201806300',703,703),(5,NULL,NULL,0,'2018-07-01 00:33:28',NULL,'2018-07-01 00:33:28','201807010',1920,1920),(6,NULL,'%',0,'2018-07-01 01:35:10',NULL,'2018-07-01 01:35:10','201807011',960,960),(7,NULL,'%',0,'2018-07-01 01:38:06',NULL,'2018-07-01 01:38:06','201807012',960,960),(8,NULL,'RS',0,'2018-07-01 02:02:50',NULL,'2018-07-01 02:02:50','201807013',2623,2623),(9,NULL,'RS',0,'2018-07-01 02:09:21',NULL,'2018-07-01 02:09:21','201807014',960,960),(10,NULL,'%',0,'2018-07-01 02:13:47',NULL,'2018-07-01 02:13:47','201807015',960,960),(11,NULL,NULL,0,'2018-07-01 02:15:32',NULL,'2018-07-01 02:15:32','201807016',960,960),(12,NULL,'%',0,'2018-07-01 02:35:30',NULL,'2018-07-01 02:35:30','201807017',2905,2905),(13,NULL,'%',0,'2018-07-01 02:37:37',NULL,'2018-07-01 02:37:37','201807018',2905,2905),(14,NULL,NULL,0,'2018-07-01 02:38:00',NULL,'2018-07-01 02:38:00','201807019',815,815),(15,NULL,NULL,0,'2018-07-01 02:39:15',NULL,'2018-07-01 02:39:15','201807020',625,625),(16,NULL,NULL,0,'2018-07-01 02:41:52',NULL,'2018-07-01 02:41:52','201807021',625,625),(17,NULL,NULL,0,'2018-07-01 02:43:35',NULL,'2018-07-01 02:43:35','201807022',625,625),(18,NULL,NULL,0,'2018-07-01 02:45:41',NULL,'2018-07-01 02:45:41','201807023',625,625),(19,NULL,NULL,0,'2018-07-01 02:45:52',NULL,'2018-07-01 02:45:52','201807024',625,625),(20,NULL,NULL,0,'2018-07-01 02:46:03',NULL,'2018-07-01 02:46:03','201807025',625,625),(21,NULL,NULL,0,'2018-07-01 02:47:09',NULL,'2018-07-01 02:47:09','201807026',625,625),(22,NULL,NULL,0,'2018-07-01 02:51:51',NULL,'2018-07-01 02:51:51','201807027',625,625),(23,NULL,NULL,0,'2018-07-01 02:56:50',NULL,'2018-07-01 02:56:50','201807028',650,650);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_items`
--

DROP TABLE IF EXISTS `sale_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_id` int(11) NOT NULL,
  `item_code` varchar(45) NOT NULL,
  `item_desc` varchar(50) DEFAULT NULL,
  `item_rate` double DEFAULT NULL,
  `item_qty` int(11) DEFAULT NULL,
  `item_total` double DEFAULT NULL,
  `item_added_ts` datetime DEFAULT NULL,
  `item_updated_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_items`
--

LOCK TABLES `sale_items` WRITE;
/*!40000 ALTER TABLE `sale_items` DISABLE KEYS */;
INSERT INTO `sale_items` VALUES (1,1,'JA0096',NULL,960,1,960,'2018-06-20 00:09:49','2018-06-20 00:09:49'),(2,2,'JA0096','Jeans 28 - 30 - 32',960,1,960,'2018-06-20 00:15:55','2018-06-20 00:15:55'),(3,2,'JB0070','Jeans 34-36',703,2,1406,'2018-06-20 00:16:40','2018-06-20 00:16:40'),(4,3,'JA0096','Jeans 28 - 30 - 32',960,1,960,'2018-06-20 00:26:41','2018-06-20 00:26:41'),(5,4,'JB0070','Jeans 34-36',703,1,703,'2018-06-30 23:27:43','2018-06-30 23:27:43'),(6,5,'JA0096','Jeans 28 - 30 - 32',960,2,1920,'2018-07-01 00:33:28','2018-07-01 00:33:28'),(7,6,'JA0096','Jeans 28 - 30 - 32',960,1,960,'2018-07-01 01:35:11','2018-07-01 01:35:11'),(8,7,'JA0096','Jeans 28 - 30 - 32',960,1,960,'2018-07-01 01:38:06','2018-07-01 01:38:06'),(9,8,'JA0096','Jeans 28 - 30 - 32',960,2,1920,'2018-07-01 02:02:50','2018-07-01 02:02:50'),(10,8,'JB0070','Jeans 34-36',703,1,703,'2018-07-01 02:02:50','2018-07-01 02:02:50'),(11,9,'JA0096','Jeans 28 - 30 - 32',960,1,960,'2018-07-01 02:09:21','2018-07-01 02:09:21'),(12,10,'JA0096','Jeans 28 - 30 - 32',960,1,960,'2018-07-01 02:13:47','2018-07-01 02:13:47'),(13,11,'JA0096','Jeans 28 - 30 - 32',960,1,960,'2018-07-01 02:15:32','2018-07-01 02:15:32'),(14,12,'JB0082','Jeans 34-36',815,1,815,'2018-07-01 02:35:30','2018-07-01 02:35:30'),(15,12,'JA0065','Jeans 28 - 30 - 32',650,1,650,'2018-07-01 02:35:30','2018-07-01 02:35:30'),(16,12,'SC0063','Shirts L',625,1,625,'2018-07-01 02:35:30','2018-07-01 02:35:30'),(17,12,'SD0082','Shirts M',815,1,815,'2018-07-01 02:35:30','2018-07-01 02:35:30'),(18,13,'JB0082','Jeans 34-36',815,1,815,'2018-07-01 02:37:37','2018-07-01 02:37:37'),(19,13,'JA0065','Jeans 28 - 30 - 32',650,1,650,'2018-07-01 02:37:37','2018-07-01 02:37:37'),(20,13,'SC0063','Shirts L',625,1,625,'2018-07-01 02:37:37','2018-07-01 02:37:37'),(21,13,'SD0082','Shirts M',815,1,815,'2018-07-01 02:37:38','2018-07-01 02:37:38'),(22,14,'SD0082','Shirts M',815,1,815,'2018-07-01 02:38:00','2018-07-01 02:38:00'),(23,15,'SC0063','Shirts L',625,1,625,'2018-07-01 02:39:15','2018-07-01 02:39:15'),(24,16,'SC0063','Shirts L',625,1,625,'2018-07-01 02:41:52','2018-07-01 02:41:52'),(25,17,'SC0063','Shirts L',625,1,625,'2018-07-01 02:43:35','2018-07-01 02:43:35'),(26,18,'SC0063','Shirts L',625,1,625,'2018-07-01 02:45:41','2018-07-01 02:45:41'),(27,19,'SC0063','Shirts L',625,1,625,'2018-07-01 02:45:52','2018-07-01 02:45:52'),(28,20,'SC0063','Shirts L',625,1,625,'2018-07-01 02:46:03','2018-07-01 02:46:03'),(29,21,'SC0063','Shirts L',625,1,625,'2018-07-01 02:47:09','2018-07-01 02:47:09'),(30,22,'SC0063','Shirts L',625,1,625,'2018-07-01 02:51:51','2018-07-01 02:51:51'),(31,23,'JA0065','Jeans 28 - 30 - 32',650,1,650,'2018-07-01 02:56:50','2018-07-01 02:56:50');
/*!40000 ALTER TABLE `sale_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_added_ts` datetime NOT NULL,
  `item_code` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sale_price_per_pc` double DEFAULT NULL,
  `item_updated_ts` datetime NOT NULL,
  `product_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaakda27vtakdtnjxxvuq28153` (`product_type_id`),
  CONSTRAINT `FKaakda27vtakdtnjxxvuq28153` FOREIGN KEY (`product_type_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (4,'2018-06-07 23:45:21','JA0096',NULL,0,960,'2018-07-01 02:15:32',NULL),(5,'2018-06-14 23:56:37','JB0070',NULL,5,703,'2018-07-29 18:23:15',NULL),(6,'2018-07-01 02:24:58','SC0063',NULL,40,625,'2018-07-01 02:51:51',NULL),(7,'2018-07-01 02:25:18','SD0082',NULL,62,815,'2018-07-01 02:38:00',NULL),(8,'2018-07-01 02:25:52','JA0065',NULL,49,650,'2018-07-01 02:56:50',NULL),(9,'2018-07-01 02:26:05','JB0082',NULL,63,815,'2018-07-01 02:37:37',NULL);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Gandhi Nagar','Kavadiguda','25648759544','Gandhi Nagar','Hyderabad','HDFC0000811','HDFC Bank','TS','Hyderabad','XYZ Company','vade@gmail.com','8975483289','ASDF2548FG21F','9875642589','Rajesh V','040-5879654','TS','8597458962','500080');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,1),(5,1),(6,1),(3,2),(6,2),(7,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin1@gmail.com','admin','Rol','9875465452','$2y$12$NKiv8H4D.oMU6z9s2y2CAe1tnwR3aumPgfa.NbK7gjg93jpWzw/IO','admin@gmail.com'),(3,'vadeghar@gmail.com','lakshman','Vadeghar','9705599921','$2a$10$Xyd7bg6KCPaxA3GADWJ/veVGH8G4Nlo6/FxP2nnZiUouze1XU52fm','vadeghar@gmail.com'),(5,'vadeghar1@gmail.com','lakshman','Vadeghar','9705599921','$2a$10$Fi9BQOpeMTSV06t0lhRvOuvOG2arA.ELnx4ctluvgdgQKMDL.dKO6','vadeghar1@gmail.com'),(6,'eade@gmail.com','Test','Enter','8974565452','456854587','Enter'),(7,'email.com','Test2','Last','9875465458','$2a$10$DznLydJ4KGotex.Zvtrl6eGlpSwDHfTFZKHEaiCLlrjsDoD9tVg3y','tttt@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bank_acc_no` varchar(255) DEFAULT NULL,
  `bank_ifsc` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `gst_no` varchar(255) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `tin_no` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (1,'002555656565','SBIN021254','SBI Bank','Hyderabad','XYZ ind','vadeghar@gmail.com','8989989','9898989898','1st Street','','9705599922','Sahiti Kandati','9898989898','Andra Pradesh','98989898','www.aafc.com','500062');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-14  0:53:38
