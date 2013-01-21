-- MySQL dump 10.13  Distrib 5.5.25, for osx10.6 (i386)
--
-- Host: localhost    Database: ring-project
-- ------------------------------------------------------
-- Server version	5.5.25

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
-- Table structure for table `Friendship`
--

DROP TABLE IF EXISTS `Friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Friendship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `following` bigint(20) NOT NULL,
  `follower` bigint(20) NOT NULL,
  `creationTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Friendship`
--

LOCK TABLES `Friendship` WRITE;
/*!40000 ALTER TABLE `Friendship` DISABLE KEYS */;
/*!40000 ALTER TABLE `Friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL,
  `fullName` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(255) NOT NULL,
  `about` varchar(200) NOT NULL,
  `avatarImage` varchar(255) NOT NULL,
  `avatarThumbnail` varchar(255) NOT NULL,
  `credentialsNonExpired` tinyint(1) NOT NULL,
  `accountNonLocked` tinyint(1) NOT NULL,
  `activated` tinyint(1) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `birthDate` bigint(10) NOT NULL,
  `ringFriendCount` bigint(20) NOT NULL,
  `creationTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'serkan','Serkan Özer','d033e22ae348aeb5660fc2140aec35850c4da997','serkan@t2.com.tr','everything about serkan','http://d1dgt2apkkbq37.cloudfront.net/avatar/1358342975940_2.png','http://d1dgt2apkkbq37.cloudfront.net/avatar-thumb/1358342984168_2.png',1,1,1,1,1357099374268,123,1357600028348),(2,'erkan','Erkan Kerti','d033e22ae348aeb5660fc2140aec35850c4da997','erkan@t2.com.tr','everything about erkan','http://d1dgt2apkkbq37.cloudfront.net/avatar/1358342975940_2.png','http://d1dgt2apkkbq37.cloudfront.net/avatar-thumb/1358342984168_2.png',1,1,1,1,1357099374268,223,1357600028348),(3,'ibo','Ibo Güntaş','d033e22ae348aeb5660fc2140aec35850c4da997','ibrahim@t2.com.tr','everything about ibo','http://d1dgt2apkkbq37.cloudfront.net/avatar/1358342975940_2.png','http://d1dgt2apkkbq37.cloudfront.net/avatar-thumb/1358342984168_2.png',1,1,1,1,1357099374268,23,1357600028348),(4,'soner','Soner ALTIN','d033e22ae348aeb5660fc2140aec35850c4da997','soner@t2.com.tr','everything about soner','http://d1dgt2apkkbq37.cloudfront.net/avatar/1358342975940_2.png','http://d1dgt2apkkbq37.cloudfront.net/avatar-thumb/1358342984168_2.png',1,1,1,1,1357099374268,323,1357600028348);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendship_requests`
--

DROP TABLE IF EXISTS `friendship_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendship_requests` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `requesterID` bigint(20) NOT NULL,
  `requestedID` bigint(20) NOT NULL,
  `creationTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendship_requests`
--

LOCK TABLES `friendship_requests` WRITE;
/*!40000 ALTER TABLE `friendship_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `friendship_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gcm_users`
--

DROP TABLE IF EXISTS `gcm_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gcm_users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `gcmRegID` text NOT NULL,
  `userID` bigint(50) NOT NULL,
  `creationTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gcm_users`
--

LOCK TABLES `gcm_users` WRITE;
/*!40000 ALTER TABLE `gcm_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `gcm_users` ENABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-21 17:18:57