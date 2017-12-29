-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: indorama_poly
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `bu`
--

DROP TABLE IF EXISTS `bu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bu_name` varchar(32) NOT NULL DEFAULT '',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bu`
--

LOCK TABLES `bu` WRITE;
/*!40000 ALTER TABLE `bu` DISABLE KEYS */;
INSERT INTO `bu` VALUES (1,'bu1','2017-09-19 04:56:37'),(2,'bu2','2017-09-19 04:56:37'),(3,'bu3','2017-09-19 04:56:37');
/*!40000 ALTER TABLE `bu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailylog`
--

DROP TABLE IF EXISTS `dailylog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailylog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dates` varchar(64) NOT NULL DEFAULT '',
  `shift` varchar(64) NOT NULL DEFAULT '',
  `substation` varchar(64) NOT NULL DEFAULT '',
  `loadmax` double NOT NULL DEFAULT '0',
  `loadmin` double NOT NULL DEFAULT '0',
  `voltmax` double NOT NULL DEFAULT '0',
  `voltmin` double NOT NULL DEFAULT '0',
  `frequencymax` double NOT NULL DEFAULT '0',
  `frequencymin` double NOT NULL DEFAULT '0',
  `pfmax` double NOT NULL DEFAULT '0',
  `pfmin` double NOT NULL DEFAULT '0',
  `powerdip` double NOT NULL DEFAULT '0',
  `remark` varchar(64) NOT NULL DEFAULT '',
  `machine` varchar(64) NOT NULL DEFAULT '',
  `description` varchar(255) DEFAULT NULL,
  `timefrom` time(6) NOT NULL,
  `timeto` time(6) NOT NULL,
  `spareparts` varchar(64) NOT NULL DEFAULT '',
  `attendby` varchar(64) NOT NULL DEFAULT '',
  `jobtype` varchar(64) NOT NULL DEFAULT '',
  `recordtype` varchar(64) NOT NULL DEFAULT '',
  `status` varchar(64) NOT NULL DEFAULT '',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `dailylog_jobtype` (`jobtype`),
  KEY `dailylog_status` (`status`),
  KEY `dailylog_attendby` (`attendby`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailylog`
--

LOCK TABLES `dailylog` WRITE;
/*!40000 ALTER TABLE `dailylog` DISABLE KEYS */;
INSERT INTO `dailylog` VALUES (7,'','Morning','MS1',0,0,0,0,0,0,0,0,0,'','','','00:00:00.000000','00:00:00.000000','','','NONE','NONE','NONE','2017-09-19 06:51:01');
/*!40000 ALTER TABLE `dailylog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailylog_bu`
--

DROP TABLE IF EXISTS `dailylog_bu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailylog_bu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bu_id` int(11) NOT NULL,
  `dailylog_id` int(11) NOT NULL DEFAULT '-1',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_dailylog_bu` (`dailylog_id`),
  UNIQUE KEY `constr_dailylogid` (`dailylog_id`),
  KEY `dailylog_id` (`dailylog_id`),
  KEY `bu_id` (`bu_id`),
  CONSTRAINT `dailylog_bu_ibfk_1` FOREIGN KEY (`dailylog_id`) REFERENCES `dailylog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dailylog_bu_ibfk_2` FOREIGN KEY (`bu_id`) REFERENCES `bu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailylog_bu`
--

LOCK TABLES `dailylog_bu` WRITE;
/*!40000 ALTER TABLE `dailylog_bu` DISABLE KEYS */;
INSERT INTO `dailylog_bu` VALUES (7,1,7,'2017-09-19 06:51:01');
/*!40000 ALTER TABLE `dailylog_bu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailylog_users`
--

DROP TABLE IF EXISTS `dailylog_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailylog_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `dailylog_id` int(11) NOT NULL DEFAULT '-1',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_dailylog_users` (`dailylog_id`),
  UNIQUE KEY `constr_dailylogid` (`dailylog_id`),
  KEY `dailylog_id` (`dailylog_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `dailylog_users_ibfk_1` FOREIGN KEY (`dailylog_id`) REFERENCES `dailylog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dailylog_users_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailylog_users`
--

LOCK TABLES `dailylog_users` WRITE;
/*!40000 ALTER TABLE `dailylog_users` DISABLE KEYS */;
INSERT INTO `dailylog_users` VALUES (1,3,7,'2017-09-19 06:51:01');
/*!40000 ALTER TABLE `dailylog_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(32) NOT NULL DEFAULT '',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__department` (`department_name`),
  KEY `departments_name_idx` (`department_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'A1','2017-09-19 04:58:19'),(2,'A2','2017-09-19 04:58:19'),(3,'A3','2017-09-19 04:58:19');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(32) NOT NULL DEFAULT '',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN','2017-09-19 04:58:42'),(2,'GROUP_USER','2017-09-19 04:58:42');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '',
  `realname` varchar(64) NOT NULL DEFAULT '',
  `phone` varchar(32) NOT NULL DEFAULT '',
  `address` varchar(255) NOT NULL DEFAULT '',
  `state` varchar(32) NOT NULL DEFAULT '',
  `city` varchar(32) NOT NULL DEFAULT '',
  `zipcode` varchar(8) NOT NULL DEFAULT '',
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `notification` tinyint(4) NOT NULL DEFAULT '1',
  `birthdate` datetime DEFAULT NULL,
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`,`phone`),
  KEY `users_username_idx` (`username`),
  KEY `users_realname_idx` (`realname`(16)),
  KEY `users_birthdate_idx` (`birthdate`),
  KEY `users_city_idx` (`city`),
  KEY `users_address_idx` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'doshi.priyank@gmail.com','Priyank Doshi','4086673759','40058 Kelly Street','California','fremont','400064',1,1,'2017-09-19 12:12:48','2017-09-19 06:42:48');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_bu`
--

DROP TABLE IF EXISTS `users_bu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_bu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bu_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL DEFAULT '-1',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_users_bu` (`users_id`),
  UNIQUE KEY `constr_usersid` (`users_id`),
  KEY `users_id` (`users_id`),
  KEY `bu_id` (`bu_id`),
  CONSTRAINT `users_bu_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_bu_ibfk_2` FOREIGN KEY (`bu_id`) REFERENCES `bu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_bu`
--

LOCK TABLES `users_bu` WRITE;
/*!40000 ALTER TABLE `users_bu` DISABLE KEYS */;
INSERT INTO `users_bu` VALUES (3,2,3,'2017-09-19 06:42:48');
/*!40000 ALTER TABLE `users_bu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_departments`
--

DROP TABLE IF EXISTS `users_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departments_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL DEFAULT '-1',
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_users_departments` (`users_id`),
  UNIQUE KEY `constr_usersid` (`users_id`),
  KEY `users_id` (`users_id`),
  KEY `departments_id` (`departments_id`),
  CONSTRAINT `users_departments_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_departments_ibfk_2` FOREIGN KEY (`departments_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_departments`
--

LOCK TABLES `users_departments` WRITE;
/*!40000 ALTER TABLE `users_departments` DISABLE KEYS */;
INSERT INTO `users_departments` VALUES (3,1,3,'2017-09-19 06:42:48');
/*!40000 ALTER TABLE `users_departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_users_roles` (`users_id`),
  KEY `users_id` (`users_id`),
  KEY `roles_id` (`roles_id`),
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,3,2,'2017-09-19 06:42:48');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-19 10:14:56
