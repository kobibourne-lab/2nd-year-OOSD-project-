-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: librarydb
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `creator` varchar(100) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  `rental_price` decimal(6,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1001,'Star Wars: i','George.L','DVD','Sifi',20.00,12.00,5),(1002,'Harry Potter','J.K Rowling','Book','Magical',9.99,2.99,5),(1003,'Fifa','EA Sports','Game','Sports',49.99,14.99,3),(1004,'8 Mile','Eminem','DVD','Real-life',15.00,4.00,6),(1008,'345','345','Game','34534',55.00,15.00,3),(1009,'Fifa16','EA Sports','Game','Sports',60.00,20.00,9),(1010,'Fifa22','EA Sports','Game','Sports',70.00,25.00,0),(1012,'Star Wars: iii','George.L','DVD','Sifi',25.00,15.00,8),(1013,'The Grey','H.T Ling','Book','Horror',16.00,6.00,5),(1014,'After 8','Sham Aarons','Book','Thriller ',13.00,4.00,2),(1015,'Black Book of Secrets','Steven King','Book','Horror',20.00,9.00,1);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `itemID` int DEFAULT NULL,
  `orderType` varchar(10) DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `userID` (`userID`),
  KEY `itemID` (`itemID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=2026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2002,2,1001,'Short','2026-02-22','2026-02-25'),(2006,3,1003,'Long','2026-02-22','2026-03-22'),(2009,1,1009,'Short','2026-01-20','2026-02-27'),(2010,10,1015,'Long','2026-02-22','2026-03-17'),(2011,1,1009,'Short','2026-01-20','2026-02-28'),(2015,11,1002,'Short','2026-02-22','2026-03-01'),(2016,7,1004,'Short','2026-03-20','2026-03-28'),(2019,2,1015,'Short','2026-02-28','2026-03-03'),(2023,9,1010,'Long','2026-02-28','2026-03-13'),(2024,9,1010,'Long','2026-02-28','2026-03-13');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Kelly Kooper','kellykopper@gmail.com','0879166268'),(2,'Katy Taylor','katytaylor@gmail.com','0871784928'),(3,'Freya Gown','freyagown@gmail.com','0876868567'),(4,'Jeff Johns','Jeff@mail','0879876259'),(7,'Jess ','JesssB@gmail.com','0867465386'),(8,'Luke Cage','LCage@mail.com','0862739964'),(9,'Tom Burn','TomBurn@gmail.com','0856107337'),(10,'Darren Daly','DDaly@mail.com','0838864241'),(11,'Adam Jefferson','AdamJ@mail.com','0842218726');
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

-- Dump completed on 2026-04-21 22:35:45
