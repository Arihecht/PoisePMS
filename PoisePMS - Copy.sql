-- MySQL dump 10.13  Distrib 9.0.1, for Win64 (x86_64)
--
-- Host: localhost    Database: PoisePMS
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `architect`
--

DROP TABLE IF EXISTS `architect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `architect` (
  `architect_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`architect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `architect`
--

LOCK TABLES `architect` WRITE;
/*!40000 ALTER TABLE `architect` DISABLE KEYS */;
INSERT INTO `architect` VALUES (1,'Dave Sacks','3456789012','davesacks@example.com','142 Grape St'),(2,'Jack Black','8765432109','jackblack@example.com','851 River St');
/*!40000 ALTER TABLE `architect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Sara Day','4567891223','saraday@example.com','851 Sleep St'),(2,'Betty Parker','1254321098','bettyparker@example.com','783 Bird St');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `project_id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) DEFAULT NULL,
  `building_type` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  `erf_number` varchar(255) DEFAULT NULL,
  `total_fee` decimal(10,2) DEFAULT NULL,
  `amount_paid` decimal(10,2) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `completion_date` date DEFAULT NULL,
  `is_finalised` tinyint(1) DEFAULT NULL,
  `engineer_id` int DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
  `architect_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `engineer_id` (`engineer_id`),
  KEY `manager_id` (`manager_id`),
  KEY `architect_id` (`architect_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`engineer_id`) REFERENCES `structuralengineer` (`engineer_id`),
  CONSTRAINT `project_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `projectmanager` (`manager_id`),
  CONSTRAINT `project_ibfk_3` FOREIGN KEY (`architect_id`) REFERENCES `architect` (`architect_id`),
  CONSTRAINT `project_ibfk_4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'House Day','House','851 Sleep St','ERF001',100000.00,50000.00,'2024-12-31','2024-10-01',1,1,1,1,1),(3,'New Project','Residential','123 Main St',NULL,500000.00,NULL,'2024-12-31',NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectmanager`
--

DROP TABLE IF EXISTS `projectmanager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectmanager` (
  `manager_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectmanager`
--

LOCK TABLES `projectmanager` WRITE;
/*!40000 ALTER TABLE `projectmanager` DISABLE KEYS */;
INSERT INTO `projectmanager` VALUES (1,'Sam Smith','2565678901','samsmith@example.com','789 Eat St'),(2,'Alice Brown','9876543210','alicebrown@example.com','388 Leaf St');
/*!40000 ALTER TABLE `projectmanager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `structuralengineer`
--

DROP TABLE IF EXISTS `structuralengineer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `structuralengineer` (
  `engineer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`engineer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `structuralengineer`
--

LOCK TABLES `structuralengineer` WRITE;
/*!40000 ALTER TABLE `structuralengineer` DISABLE KEYS */;
INSERT INTO `structuralengineer` VALUES (1,'Adam Smith','1234567890','adamsmith@example.com','123 Grey St'),(2,'John Shay','0987654521','johnshay@example.com','456 Spiral St');
/*!40000 ALTER TABLE `structuralengineer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-02 14:21:52
