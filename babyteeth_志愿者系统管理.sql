-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: babyteeth
-- ------------------------------------------------------
-- Server version	5.7.38-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `儿童口腔健康基本信息`
--

DROP TABLE IF EXISTS `儿童口腔健康基本信息`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `儿童口腔健康基本信息` (
  `儿童编号` int(11) NOT NULL AUTO_INCREMENT,
  `儿童姓名` tinytext,
  `儿童性别` enum('男','女') NOT NULL COMMENT '男',
  `儿童年龄` tinyint(4) DEFAULT NULL,
  `家庭住址` varchar(255) DEFAULT NULL,
  `监护人` varchar(255) DEFAULT NULL,
  `监护人联系方式` char(11) DEFAULT NULL,
  `口腔健康情况` enum('健康','一般','不健康') NOT NULL,
  `是否有龋坏` enum('是','否') NOT NULL,
  `是否有唇腭裂情况` enum('是','否') NOT NULL,
  PRIMARY KEY (`儿童编号`),
  UNIQUE KEY `儿童基本信息_儿童编号_uindex` (`儿童编号`),
  UNIQUE KEY `儿童基本信息_pk` (`儿童年龄`,`儿童编号`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `儿童口腔健康基本信息`
--

LOCK TABLES `儿童口腔健康基本信息` WRITE;
/*!40000 ALTER TABLE `儿童口腔健康基本信息` DISABLE KEYS */;
INSERT INTO `儿童口腔健康基本信息` VALUES (1,'李一','男',6,'重庆市忠县','刘大志','17826352653','健康','是','是'),(2,'李妍可','女',6,'四川省成都市天府新区','苏悦','19978982343','健康','是','是'),(3,'王智','男',8,'四川省成都市武侯区','王非','18767662345','健康','是','是'),(4,'范德萨','女',12,'的撒','的撒','12332112341','健康','是','是'),(5,'范某','男',12,'四川大学','范刘庆','19980813507','健康','否','否');
/*!40000 ALTER TABLE `儿童口腔健康基本信息` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `儿童用户系统管理`
--

DROP TABLE IF EXISTS `儿童用户系统管理`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `儿童用户系统管理` (
  `儿童用户账号` varchar(100) NOT NULL,
  `儿童登陆密码` int(11) NOT NULL,
  PRIMARY KEY (`儿童用户账号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `儿童用户系统管理`
--

LOCK TABLES `儿童用户系统管理` WRITE;
/*!40000 ALTER TABLE `儿童用户系统管理` DISABLE KEYS */;
INSERT INTO `儿童用户系统管理` VALUES ('李一',123456);
/*!40000 ALTER TABLE `儿童用户系统管理` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `医生信息`
--

DROP TABLE IF EXISTS `医生信息`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `医生信息` (
  `医生姓名` varchar(11) NOT NULL,
  `联系方式` varchar(11) NOT NULL,
  `医生职称` enum('主任医师','副主任医师','主治医师') DEFAULT NULL,
  `所在科室` varchar(100) NOT NULL,
  PRIMARY KEY (`医生姓名`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `医生信息`
--

LOCK TABLES `医生信息` WRITE;
/*!40000 ALTER TABLE `医生信息` DISABLE KEYS */;
INSERT INTO `医生信息` VALUES ('范刘庆','19988886666','主治医师','口腔修复科');
/*!40000 ALTER TABLE `医生信息` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `志愿信息`
--

DROP TABLE IF EXISTS `志愿信息`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `志愿信息` (
  `志愿活动名称` varchar(255) NOT NULL,
  `志愿时间` datetime DEFAULT NULL COMMENT '2022-1-1 0:0:0',
  `志愿活动所需人数` int(11) NOT NULL,
  `志愿地点` varchar(255) DEFAULT NULL,
  `志愿时长` int(11) NOT NULL,
  PRIMARY KEY (`志愿活动名称`),
  UNIQUE KEY `志愿信息_志愿活动名称_uindex` (`志愿活动名称`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `志愿信息`
--

LOCK TABLES `志愿信息` WRITE;
/*!40000 ALTER TABLE `志愿信息` DISABLE KEYS */;
INSERT INTO `志愿信息` VALUES ('四川大学附属第一幼儿园知识宣讲活动','2022-06-01 09:30:00',14,'四川大学附属第一幼儿园',4),('四川省成都市双流区儿童涂氟志愿活动','2022-05-30 10:00:00',10,'四川省成都市双流区阳光小区',2),('涂氟活动','2022-06-06 11:45:33',13,'四川大学',2);
/*!40000 ALTER TABLE `志愿信息` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `志愿者信息`
--

DROP TABLE IF EXISTS `志愿者信息`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `志愿者信息` (
  `志愿者编号` tinyint(4) NOT NULL AUTO_INCREMENT,
  `志愿者姓名` varchar(255) NOT NULL,
  `参与的志愿活动名称` varchar(255) DEFAULT NULL,
  `志愿时长` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`志愿者编号`),
  UNIQUE KEY `志愿者信息_志愿者编号_uindex` (`志愿者编号`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `志愿者信息`
--

LOCK TABLES `志愿者信息` WRITE;
/*!40000 ALTER TABLE `志愿者信息` DISABLE KEYS */;
INSERT INTO `志愿者信息` VALUES (1,'范刘庆','四川省成都市双流区儿童涂氟志愿活动',2),(2,'gf','gds',4),(3,'范刘庆','四川大学附属第一幼儿园知识宣讲活动',4),(4,'范刘庆','涂氟活动',2),(5,'范刘庆','涂氟活动',2),(6,'范刘庆','涂氟活动',2),(7,'范刘庆','涂氟活动',2);
/*!40000 ALTER TABLE `志愿者信息` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `志愿者系统管理`
--

DROP TABLE IF EXISTS `志愿者系统管理`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `志愿者系统管理` (
  `志愿者账号` varchar(100) NOT NULL,
  `志愿者系统密码` int(11) NOT NULL,
  PRIMARY KEY (`志愿者账号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `志愿者系统管理`
--

LOCK TABLES `志愿者系统管理` WRITE;
/*!40000 ALTER TABLE `志愿者系统管理` DISABLE KEYS */;
INSERT INTO `志愿者系统管理` VALUES ('刘小华',123321),('梁文菁',123456),('范刘庆',111111);
/*!40000 ALTER TABLE `志愿者系统管理` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `智慧诊疗`
--

DROP TABLE IF EXISTS `智慧诊疗`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `智慧诊疗` (
  `口腔不健康情况` varchar(1000) NOT NULL,
  `解决方案` varchar(500) DEFAULT NULL,
  `预防方案` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`口腔不健康情况`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `智慧诊疗`
--

LOCK TABLES `智慧诊疗` WRITE;
/*!40000 ALTER TABLE `智慧诊疗` DISABLE KEYS */;
INSERT INTO `智慧诊疗` VALUES ('楔状缺损','一般情况下，轻度缺损患者，牙齿刺激症状（酸痛）不明显的患者无需治疗，以纠正不当刷牙（如横刷）习惯为主；若患者有一过性的刺激症状，可进行脱敏治疗；中、深度缺损患者需根据实际情况进行充填修复或根管治疗。','采用巴氏刷牙法，不要横刷'),('牙脱位','。部分性脱位的牙齿，应在局麻下复位，再结扎固定4周。在术后的3、6和12个月进行复查。如果发现牙髓已经坏死，应该及时做根管治疗。嵌入性脱位，应该在复位后的2周做根管治疗。如果是嵌入性脱位的年轻恒牙，不可以进行强行的拉出复位。完全性脱位的牙齿，应该在半个小时内进行种植，一般90%的牙根可以避免吸收。如果脱位时间大于2个小时才就诊，牙髓和牙周膜细胞已经出现坏死，在体外做完根管治疗，并经根面和牙槽窝刮治后，将患牙直接固定在牙槽骨内即可。','在运动时做好防护'),('磨牙龋坏','去龋坏，若没有损坏牙髓则补牙；若伤害了牙髓则做根管治疗再补牙','做窝沟封闭或者涂氟；采用巴氏刷牙法早晚刷牙；少喝可乐等');
/*!40000 ALTER TABLE `智慧诊疗` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-13 10:22:57
