-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: 1412363_database
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `tblaccount`
--

DROP TABLE IF EXISTS `tblaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblaccount` (
  `UserName` varchar(10) NOT NULL,
  `Pass` varchar(500) DEFAULT NULL,
  `IsTheFisrtTime` int(2) DEFAULT NULL,
  `IsSV` int(2) DEFAULT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblaccount`
--

LOCK TABLES `tblaccount` WRITE;
/*!40000 ALTER TABLE `tblaccount` DISABLE KEYS */;
INSERT INTO `tblaccount` VALUES ('1412101','1412101',1,1),('1412197','d856125d827ac297307baf299a8ee1f1',0,1),('1412206','1412206',1,1),('1412356','1412356',1,1),('1412363','3eaf6c984c18d143e1c0a797779afea8',0,1),('1412477','bd129b81598dbe63b0cc4f4167f416df',0,1),('ducvo','123456',1,0),('thanhho','123456',1,0),('tuyenho','123456',1,0);
/*!40000 ALTER TABLE `tblaccount` ENABLE KEYS */;
UNLOCK TABLES;

CREATE DATABASE 1412363_database;

USE mydatabase;

--
-- Table structure for table `tblattendance`
--

DROP TABLE IF EXISTS `tblattendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblattendance` (
  `MaSV` varchar(10) NOT NULL,
  `MaMH` varchar(10) NOT NULL,
  `NgayDD` date NOT NULL,
  `DaDD` bit(1) DEFAULT NULL,
  PRIMARY KEY (`MaSV`,`MaMH`,`NgayDD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblattendance`
--

LOCK TABLES `tblattendance` WRITE;
/*!40000 ALTER TABLE `tblattendance` DISABLE KEYS */;
INSERT INTO `tblattendance` VALUES ('1412197','10','2017-04-07','\0'),('1412363','1','2017-04-12',''),('1412363','10','2017-04-07','\0'),('1412363','10','2017-04-14',''),('1412363','11','2017-04-14',''),('1412363','12','2017-04-14',''),('1412363','5','2017-04-06',''),('1412363','5','2017-04-13','\0');
/*!40000 ALTER TABLE `tblattendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblestudent_subject`
--

DROP TABLE IF EXISTS `tblestudent_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblestudent_subject` (
  `MaSV` varchar(10) NOT NULL,
  `MaMH` varchar(10) NOT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MaSV`,`MaMH`),
  KEY `MaMH` (`MaMH`),
  CONSTRAINT `tblestudent_subject_ibfk_1` FOREIGN KEY (`MaSV`) REFERENCES `tblstudent` (`MaSV`),
  CONSTRAINT `tblestudent_subject_ibfk_2` FOREIGN KEY (`MaMH`) REFERENCES `tblsubject` (`MaMH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblestudent_subject`
--

LOCK TABLES `tblestudent_subject` WRITE;
/*!40000 ALTER TABLE `tblestudent_subject` DISABLE KEYS */;
INSERT INTO `tblestudent_subject` VALUES ('1412197','10','nothing'),('1412197','2','nothing'),('1412197','4','nothing'),('1412198','1','nothing'),('1412198','12','nothing'),('1412199','1','nothing'),('1412205','1','nothing'),('1412205','12','nothing'),('1412206','1','nothing'),('1412306','1','nothing'),('1412350','1','nothing'),('1412357','1','nothing'),('1412363','1','nothing'),('1412363','10','nothing'),('1412363','13','nothing'),('1412363','4','nothing'),('1412363','5','nothing'),('1412434','1','nothing'),('1412890','1','nothing'),('1512344','1','nothing');
/*!40000 ALTER TABLE `tblestudent_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblministry`
--

DROP TABLE IF EXISTS `tblministry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblministry` (
  `MaGV` varchar(10) NOT NULL,
  `TenGV` varchar(50) DEFAULT NULL,
  `UserName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`MaGV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblministry`
--

LOCK TABLES `tblministry` WRITE;
/*!40000 ALTER TABLE `tblministry` DISABLE KEYS */;
INSERT INTO `tblministry` VALUES ('1','Võ Minh Đức','ducvo'),('2','Hồ Tuấn Thanh','thanhho'),('3','Hồ Thị Thanh Tuyến','tuyenho'),('4','Trần Duy Quang','quangtran'),('5','Ngô Chánh Đức','ducngo');
/*!40000 ALTER TABLE `tblministry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblstudent`
--

DROP TABLE IF EXISTS `tblstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblstudent` (
  `MaSV` varchar(10) NOT NULL,
  `TenSV` varchar(50) DEFAULT NULL,
  `UserName` varchar(30) DEFAULT NULL,
  `TenLop` varchar(30) DEFAULT NULL,
  `GioiTinh` varchar(10) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`MaSV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblstudent`
--

LOCK TABLES `tblstudent` WRITE;
/*!40000 ALTER TABLE `tblstudent` DISABLE KEYS */;
INSERT INTO `tblstudent` VALUES ('1412101','Nguyễn Đình T','1412101','14CTT3','Nam','1996-06-12','Quảng Ngãi'),('1412197','Đoàn Thị Phương Huyền','1412197','14CTT2','Nữ','1996-06-25','Sài Gòn'),('1412198','Hà Đức Huy','1412198','16CTT3','Nam',NULL,'Sài Gòn'),('1412199','Trần Thị B','1412199','15CTT3','Nữ',NULL,'Sài Gòn'),('1412205','Nguyễn Đức Huy','1412205','16CTT3','Nam',NULL,'kjkk'),('1412206','Nguyễn Tấn Huy','1412206','16CNTN','Nam',NULL,'Sài Gòn'),('1412306','Nguyễn văn A','1412306','15CTT2','Nam',NULL,'Sài Gòn'),('1412350','NguyễnVăn C','1412350','14CTT2','Nam',NULL,'Sài Gòn'),('1412356','Lê Thị T','1412356','14CTT2','Nữ','1996-07-04','Lâm Đồng'),('1412357','Nguyễn Văn A','1412357','14CTT1','Nam',NULL,'Sài Gòn'),('1412363','Trần Thị Nhã','1412363','14CTT2','Nữ','1996-02-16','Sài Gòn'),('1412434','Trần Thị S','1412434','15CTT2','Nữ',NULL,'SG'),('1412477','Đoàn Hiếu Tâm','1412477','14CTT3','Nam','1996-02-16','Sài Gòn'),('1412890','Trương Văn D','1412890','16CTT1','Nam',NULL,'Sài Gòn'),('1512344','Lê Văn B','1512344','15CNTN','Nam',NULL,'knn');
/*!40000 ALTER TABLE `tblstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsubject`
--

DROP TABLE IF EXISTS `tblsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblsubject` (
  `MaMH` varchar(10) NOT NULL,
  `TenMH` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MaMH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsubject`
--

LOCK TABLES `tblsubject` WRITE;
/*!40000 ALTER TABLE `tblsubject` DISABLE KEYS */;
INSERT INTO `tblsubject` VALUES ('1','Hướng đối tượng'),('10','Thiết kế phần mềm'),('11','Phân tích yêu cầu phần mềm'),('12','Lập trình web'),('13','Kiến trúc máy tính và hợp ngữ'),('14','Cấu trúc dữ liệu và giải thuật'),('2','Lập trình window'),('3','Khoa học web'),('4','Lập trình ứng dụng java'),('5','web');
/*!40000 ALTER TABLE `tblsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltimetable`
--

DROP TABLE IF EXISTS `tbltimetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltimetable` (
  `MaTKB` varchar(10) NOT NULL,
  `MonHoc` varchar(10) DEFAULT NULL,
  `NgayBD` date DEFAULT NULL,
  `NgayKT` date DEFAULT NULL,
  `SoTuan` int(11) DEFAULT NULL,
  `Thu` varchar(10) DEFAULT NULL,
  `GioBD` time DEFAULT NULL,
  `GioKT` time DEFAULT NULL,
  `PhongHoc` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MaTKB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltimetable`
--

LOCK TABLES `tbltimetable` WRITE;
/*!40000 ALTER TABLE `tbltimetable` DISABLE KEYS */;
INSERT INTO `tbltimetable` VALUES ('1','1','2017-04-11','2017-08-21',15,'Thứ tư','16:00:00','20:00:00','F203'),('2','4','2017-03-28','2017-09-12',15,'Thứ tư','17:00:00','23:59:00','C22'),('3','5','2017-04-05','2017-07-20',15,'Thứ năm','00:00:00','01:00:00','C22'),('4','10','2017-04-05','2017-09-29',15,'Thứ sáu','16:00:00','21:00:00','C22'),('tkb1','11','2017-04-05','2017-08-24',15,'Thứ sáu','16:00:00','20:00:00','C22'),('tkb2','12','2017-04-04','2017-08-17',15,'Thứ sáu','16:00:00','21:00:00','C22'),('tkb3','13','2017-03-29','2017-08-03',15,'Thứ bảy','00:00:00','03:00:00','C22');
/*!40000 ALTER TABLE `tbltimetable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-15 10:34:58
