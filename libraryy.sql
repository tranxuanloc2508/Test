-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `masach` text NOT NULL,
  `tensach` text NOT NULL,
  `tacgia` text NOT NULL,
  `motasach` text NOT NULL,
  `namxuatban` text NOT NULL,
  `ngaynhapsach` text NOT NULL,
  `vitri` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'b1','Thất Tịch Không Mưa','Lâu Vũ Tình','Ngôn tình','2019','29/10/2019','s1'),(2,'b2','Nắng','Nguyễn Bảo Trung','Truyện ngắn','2018','23/02/2019','s2'),(3,'b3','Một Mảnh Trăng','Ha Huyn','Tản văn','2020','01/11/2020','s3'),(4,'b4','Hỏa Ngục','Dan Brown','Trinh thám','2018','13/10/2020','s4'),(5,'b5','Thú Tội','Minato Kanae','Trinh thám','2017','12/09/2018','s5'),(6,'b6','Người Lạ Trong Nhà','Leila Slimani','Kinh dị','2017','01/03/2018','s6');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookdocgia`
--

DROP TABLE IF EXISTS `bookdocgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookdocgia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idbook` int NOT NULL,
  `iddocgia` int NOT NULL,
  `ngaymuon` varchar(45) DEFAULT NULL,
  `ngaytra` varchar(45) DEFAULT NULL,
  `tienphat` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bookdocgia_idbook_idx` (`idbook`),
  KEY `fk_bookdocgia_idmember_idx` (`iddocgia`),
  CONSTRAINT `fk_bookdocgia_idbook` FOREIGN KEY (`idbook`) REFERENCES `book` (`id`),
  CONSTRAINT `fk_bookdocgia_iddocgia` FOREIGN KEY (`iddocgia`) REFERENCES `thedocgia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookdocgia`
--

LOCK TABLES `bookdocgia` WRITE;
/*!40000 ALTER TABLE `bookdocgia` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookdocgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gioitinh`
--

DROP TABLE IF EXISTS `gioitinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gioitinh` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gioitinh`
--

LOCK TABLES `gioitinh` WRITE;
/*!40000 ALTER TABLE `gioitinh` DISABLE KEYS */;
INSERT INTO `gioitinh` VALUES ('Man'),('Other'),('Woman');
/*!40000 ALTER TABLE `gioitinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thedocgia`
--

DROP TABLE IF EXISTS `thedocgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thedocgia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `madocgia` text,
  `hoten` text,
  `gioitinh` varchar(45) DEFAULT NULL,
  `ngaysinh` text,
  `doituong` text,
  `bophan` text,
  `email` text,
  `diachi` text,
  `sdt` text,
  `hanthe` text,
  PRIMARY KEY (`id`),
  KEY `fk_thedocgia_gioitinh_idx` (`gioitinh`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thedocgia`
--

LOCK TABLES `thedocgia` WRITE;
/*!40000 ALTER TABLE `thedocgia` DISABLE KEYS */;
INSERT INTO `thedocgia` VALUES (1,'m1','Nguyễn Huỳnh Kim','Man','01/09/2000','Giảng viên','CNTT','a@gmail.com','12 Nguyễn Văn Công quận Gò Vấp','09888778782','còn hạn'),(2,'m2','Huỳnh Văn Ngọc','Man','02/12/1989','Giảng viên','CNTT','a@gmail.com','12 Nguyễn Văn Công quận Gò Vấp','09888778782','còn hạn'),(3,'m3','Trần Ngọc Diễm','Woman','02/12/1989','Giảng viên','CNTT','a@gmail.com','12 Nguyễn Văn Công quận Gò Vấp','09888778782','hết hạn'),(4,'m4','Hoàng Kỳ','Man','04/03/2000','Sinh viên','CNTT','a@gmail.com','12 Nguyễn Văn Công quận Gò Vấp','09888778782','còn hạn'),(5,'m5','Nguyễn Thị Bích Quyên','Woman','02/10/2000','Sinh viên','CNTT','a@gmail.com','12 Nguyễn Văn Công quận Gò Vấp','09888778782','hết hạn'),(6,'m6','Trần Xuân Lộc','Man','25/06/2000','Sinh viên','CNTT','a@gmail.com','12 Nguyễn Văn Công quận Gò Vấp','09888778782','còn hạn'),(7,'m7','Mai Ngọc Quỳnh Trang','Woman','12/07/2000','Sinh viên','CNTT','a@gmail.com','12 Nguyễn Văn Công quận Gò Vấp','09888778782','hết hạn');
/*!40000 ALTER TABLE `thedocgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` text,
  `password` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-14 10:45:49
