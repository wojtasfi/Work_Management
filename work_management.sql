-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: work_management
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `category` varchar(15) DEFAULT NULL,
  `analyst` tinyint(1) DEFAULT NULL,
  `senior` tinyint(1) DEFAULT NULL,
  `manager` tinyint(1) DEFAULT NULL,
  `supervisor` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES ('Help',1,1,1,1),('Ad-hoc',1,1,1,1),('Management',0,0,1,1);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_tasks`
--

DROP TABLE IF EXISTS `fixed_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fixed_tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(30) DEFAULT NULL,
  `minutes` int(11) DEFAULT NULL,
  `task_type` varchar(15) DEFAULT NULL,
  `frequency` varchar(15) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `attachment` blob,
  `review_required` tinyint(1) DEFAULT NULL,
  `category_1` varchar(15) DEFAULT NULL,
  `team` varchar(30) DEFAULT NULL,
  `deadline_hour` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_tasks`
--

LOCK TABLES `fixed_tasks` WRITE;
/*!40000 ALTER TABLE `fixed_tasks` DISABLE KEYS */;
INSERT INTO `fixed_tasks` VALUES (1,'Report',1,'Fixed','Weekly','',NULL,1,'Help','Bob',NULL),(3,'Finance',1,'Fixed','Daily','',NULL,1,'Help','Bob','12:00:00');
/*!40000 ALTER TABLE `fixed_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequencies`
--

DROP TABLE IF EXISTS `frequencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `frequency` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequencies`
--

LOCK TABLES `frequencies` WRITE;
/*!40000 ALTER TABLE `frequencies` DISABLE KEYS */;
INSERT INTO `frequencies` VALUES (1,'Daily'),(2,'Weekly'),(3,'Monthly');
/*!40000 ALTER TABLE `frequencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(30) DEFAULT NULL,
  `preparer` varchar(7) DEFAULT NULL,
  `reviewer` varchar(7) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `deadline_hour` time DEFAULT NULL,
  `minutes` int(11) DEFAULT NULL,
  `task_type` varchar(15) DEFAULT NULL,
  `frequency` varchar(15) DEFAULT NULL,
  `comment` varchar(10000) DEFAULT NULL,
  `attachment` blob,
  `prc_status` float DEFAULT NULL,
  `review_required` tinyint(1) DEFAULT NULL,
  `done` tinyint(1) DEFAULT NULL,
  `orderer` varchar(7) DEFAULT NULL,
  `category_1` varchar(15) DEFAULT NULL,
  `reviewed` tinyint(1) DEFAULT NULL,
  `team` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'Nowe zadanie','knowak',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(2,'Nowe zadanie','knowak','jkowal','2016-10-09','00:00:20',100,'Variable','V','Do it!',NULL,0.34,1,0,'jkowal','Simple tasks',NULL,NULL),
(3,'Nowe zadanie','knowak','jkowal','2016-10-09','20:20:00',100,'Variable','V','Do it!',NULL,0.34,1,0,'jkowal','Simple tasks',NULL,NULL),
(4,'test','knowak',NULL,'2016-11-01','12:12:00',1,NULL,'V',NULL,NULL,0,NULL,0,NULL,'Help',NULL,NULL),
(5,'test','knowak',NULL,'2016-11-02','12:12:00',1,NULL,'V',NULL,NULL,0,NULL,0,NULL,'Help',NULL,NULL),
(6,'Zadanie testowe','knowak',NULL,'2016-11-02','23:59:00',10,NULL,'V',NULL,NULL,0,NULL,0,NULL,'Ad-hoc',NULL,NULL),
(7,'Testing','knowak',NULL,'2016-11-09','23:23:00',1,NULL,'V',NULL,NULL,40,NULL,0,NULL,'Help',NULL,NULL),
(8,'gui','knowak',NULL,'2016-11-03','12:00:00',1,NULL,'V',NULL,NULL,0,NULL,0,NULL,'Help',NULL,NULL),
(9,'Cook dinner','knowak',NULL,'2016-11-02','12:00:00',1,NULL,'V',NULL,NULL,0,NULL,0,NULL,'Help',NULL,NULL),
(10,'Prepare report','knowak',NULL,'2016-11-02','10:00:00',1,NULL,'V',NULL,NULL,0,NULL,0,NULL,'Help',NULL,NULL),
(11,'Check report','knowak',NULL,'2016-11-02','09:00:00',1,NULL,'V',NULL,NULL,0,NULL,0,NULL,'Help',NULL,NULL),
(12,'Hello','knowak',NULL,'2016-11-04','23:12:00',5,NULL,'V',NULL,NULL,100,NULL,1,NULL,'Help',NULL,NULL),
(13,'Zadanie z komentarzem','knowak',NULL,'2016-11-04','14:00:00',10,'V','Variable','This is new task.',NULL,0,NULL,0,NULL,'Help',NULL,NULL),
(14,'Long comment','knowak',NULL,'2016-11-05','00:34:00',1,'V','Variable','Bla bla bla bla bal bal bal bla bal bal b\nwofjiowfiowefifewj\nwfiowjefoijweifjwieofj',NULL,48,NULL,0,NULL,'Ad-hoc',NULL,NULL),
(15,'Finance','knowak',NULL,'2016-11-11','12:00:00',9,'V','Variable','kjhfiwahegfiuhwaipeughwuipareg',NULL,23,NULL,0,'knowak','Help',0,NULL),
(16,'What is up?','knowak',NULL,'2016-11-13','12:00:00',8,'V','Variable','???',NULL,0,NULL,0,'knowak','Help',0,NULL),
(17,'First task','wfigas',NULL,'2016-11-13','12:00:00',3,'V','Variable','My first task',NULL,0,NULL,0,'wfigas','Ad-hoc',0,NULL),
(18,'k','knowak','wfigas','2016-11-13','13:00:00',1,'V','Variable','rgerg',NULL,100,NULL,1,'wfigas','Help',1,NULL),
(19,'Bobby task','knowak','null','2016-11-15','12:00:00',1,'V','Variable','Bla',NULL,0,NULL,0,'jkowal','Management',0,'Bob'),
(20,'Second task','knowak','null','2016-11-18','12:30:00',1,'V','Variable','This is another task.',NULL,0,NULL,0,'jkowal','Ad-hoc',0,'Bob'),
(21,'Bob Again','knowak','null','2016-11-18','13:00:00',1,'V','Variable','efwef',NULL,0,NULL,0,'jkowal','Help',0,'Bob'),
(22,'Another...',NULL,NULL,'2016-11-18','14:00:00',1,'V','Variable','woeifjwiojef',NULL,0,NULL,0,'jkowal','Help',0,'Bob'),
(23,'32r23r','bjohns',NULL,'2016-11-18','12:34:00',1,'V','Variable','',NULL,0,NULL,0,'bjohns','Ad-hoc',0,'null'),
(24,'qw',NULL,NULL,'2016-11-18','11:11:00',1,'V','Variable','',NULL,0,NULL,0,'jkowal','Help',0,'Bob'),
(25,'1','knowak','null','2016-11-18','11:11:00',1,'V','Variable','',NULL,0,1,0,'jkowal','Help',0,'Bob'),
(26,'Bobby','wfigas','wfigas','2016-11-18','12:33:00',1,'V','Variable','',NULL,0,1,0,'jkowal','Ad-hoc',0,'Bob'),
(27,'zadanie','jkowal',NULL,'2016-11-20','13:00:00',1,'V','Variable','bla bla bla',NULL,24,NULL,0,'jkowal','Help',0,'null'),
(28,'Report',NULL,NULL,NULL,NULL,1,'Fixed','Daily','A',NULL,NULL,1,NULL,NULL,'Ad-hoc',NULL,'Bob'),
(29,'efwe','jkowal',NULL,'2016-11-23','12:12:00',1,'V','Variable','',NULL,0,NULL,0,'jkowal','Help',0,'null');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(7) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `team` varchar(15) DEFAULT NULL,
  `hours_per_day` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'knowak','345','Karolina','Nowak','Analyst','Bob',8,'2015-09-01'),(2,'jkowal','1234','Jan','Kowalski','Manager','Management',8,'2014-09-01'),(3,'wfigas','1234','Wojciech','Figas','Senior','Bob',8,'2013-09-01'),(4,'bjohns','1234','Bob','Johnson','Supervisor','Bob',8,'2014-10-01');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-18 11:36:34
