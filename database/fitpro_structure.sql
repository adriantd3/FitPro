CREATE DATABASE  IF NOT EXISTS `fitpro` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fitpro`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fitpro
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `comida`
--

DROP TABLE IF EXISTS `comida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comida` (
  `id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `calorias` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comida_menu`
--

DROP TABLE IF EXISTS `comida_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comida_menu` (
  `comida_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`comida_id`,`menu_id`),
  KEY `menu_FK_idx` (`menu_id`),
  CONSTRAINT `comida_FK` FOREIGN KEY (`comida_id`) REFERENCES `comida` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_comida_FK` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `desempenyo_comida`
--

DROP TABLE IF EXISTS `desempenyo_comida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_comida` (
  `id` int NOT NULL,
  `comida_id` int NOT NULL,
  `desempenyo_menu_id` int NOT NULL,
  `comido` tinyint NOT NULL,
  `gustado` tinyint NOT NULL,
  PRIMARY KEY (`id`,`comida_id`,`desempenyo_menu_id`),
  KEY `comida_desempenyo_FK_idx` (`comida_id`),
  KEY `desempenyomenu_FK_idx` (`desempenyo_menu_id`),
  CONSTRAINT `comida_desempenyo_FK` FOREIGN KEY (`comida_id`) REFERENCES `comida` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `desempenyo_menu_FK` FOREIGN KEY (`desempenyo_menu_id`) REFERENCES `desempenyo_menu` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `desempenyo_menu`
--

DROP TABLE IF EXISTS `desempenyo_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_menu` (
  `id` int NOT NULL,
  `menu_id` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_FK_idx` (`menu_id`),
  CONSTRAINT `menu_desempenyo_FK` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `desempenyo_serie`
--

DROP TABLE IF EXISTS `desempenyo_serie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_serie` (
  `id` int NOT NULL,
  `desempenyo_sesion_id` int NOT NULL,
  `serie_id` int DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `repeticiones` int DEFAULT NULL,
  `distancia` float DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `desempenyo_sesion_FK_idx` (`desempenyo_sesion_id`),
  KEY `serie_FK_idx` (`serie_id`),
  CONSTRAINT `desempenyo_sesion_FK` FOREIGN KEY (`desempenyo_sesion_id`) REFERENCES `desempenyo_sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `serie_FK` FOREIGN KEY (`serie_id`) REFERENCES `serie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `desempenyo_sesion`
--

DROP TABLE IF EXISTS `desempenyo_sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_sesion` (
  `id` int NOT NULL,
  `sesion_id` int NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sesion_desempenyo_FK_idx` (`sesion_id`),
  CONSTRAINT `sesion_desempenyo_FK` FOREIGN KEY (`sesion_id`) REFERENCES `sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dieta`
--

DROP TABLE IF EXISTS `dieta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dieta` (
  `id` int NOT NULL,
  `dietista_id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `fecha_creacion` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dietista_dieta_FK_idx` (`dietista_id`),
  CONSTRAINT `dietista_dieta_FK` FOREIGN KEY (`dietista_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dieta_cliente`
--

DROP TABLE IF EXISTS `dieta_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dieta_cliente` (
  `dieta_id` int NOT NULL,
  `cliente_id` int NOT NULL,
  PRIMARY KEY (`dieta_id`,`cliente_id`),
  KEY `dieta_cliente_FK_idx` (`cliente_id`),
  CONSTRAINT `dieta_cliente_FK` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dieta_dieta_FK` FOREIGN KEY (`dieta_id`) REFERENCES `dieta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dietista_cliente`
--

DROP TABLE IF EXISTS `dietista_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dietista_cliente` (
  `dietista_id` int NOT NULL,
  `cliente_id` int NOT NULL,
  PRIMARY KEY (`dietista_id`,`cliente_id`),
  KEY `dietista_cliente_FK_idx` (`cliente_id`),
  CONSTRAINT `dietista_cliente_FK` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dietista_FK` FOREIGN KEY (`dietista_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ejercicio`
--

DROP TABLE IF EXISTS `ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejercicio` (
  `id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` longtext,
  `imagen` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `grupo_muscular` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `video_UNIQUE` (`video`),
  UNIQUE KEY `tipo_UNIQUE` (`tipo`),
  UNIQUE KEY `grupo_muscular_UNIQUE` (`grupo_muscular`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrenador_cliente`
--

DROP TABLE IF EXISTS `entrenador_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenador_cliente` (
  `entrenador_id` int NOT NULL,
  `cliente_id` int NOT NULL,
  PRIMARY KEY (`entrenador_id`,`cliente_id`),
  KEY `entrenador_cliente_FK_idx` (`cliente_id`),
  CONSTRAINT `entrenador_cliente_FK` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entrenador_FK` FOREIGN KEY (`entrenador_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `calorias` float NOT NULL DEFAULT '0',
  `fecha_creacion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orden_menu_dieta`
--

DROP TABLE IF EXISTS `orden_menu_dieta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_menu_dieta` (
  `menu_id` int NOT NULL,
  `dieta_id` int NOT NULL,
  `orden` int NOT NULL,
  PRIMARY KEY (`menu_id`,`dieta_id`),
  KEY `dieta_FK_idx` (`dieta_id`),
  CONSTRAINT `dieta_FK` FOREIGN KEY (`dieta_id`) REFERENCES `dieta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_FK` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orden_sesion_rutina`
--

DROP TABLE IF EXISTS `orden_sesion_rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_sesion_rutina` (
  `sesion_id` int NOT NULL,
  `rutina_id` int NOT NULL,
  `orden` int NOT NULL,
  PRIMARY KEY (`sesion_id`,`rutina_id`),
  KEY `sesion_FK_idx` (`sesion_id`),
  KEY `rutina_FK_idx` (`rutina_id`),
  CONSTRAINT `rutina_FK` FOREIGN KEY (`rutina_id`) REFERENCES `rutina` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sesion_FK` FOREIGN KEY (`sesion_id`) REFERENCES `sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rutina`
--

DROP TABLE IF EXISTS `rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rutina` (
  `id` int NOT NULL,
  `entrenador_id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `fecha_creacion` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entrenador_rutina_FK_idx` (`entrenador_id`),
  CONSTRAINT `entrenador_rutina_FK` FOREIGN KEY (`entrenador_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rutina_cliente`
--

DROP TABLE IF EXISTS `rutina_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rutina_cliente` (
  `rutina_id` int NOT NULL,
  `cliente_id` int NOT NULL,
  PRIMARY KEY (`rutina_id`,`cliente_id`),
  KEY `rutina_cliente_FK_idx` (`cliente_id`),
  CONSTRAINT `rutina_cliente_FK` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rutina_rutina_cliente_FK` FOREIGN KEY (`rutina_id`) REFERENCES `rutina` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `serie`
--

DROP TABLE IF EXISTS `serie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serie` (
  `id` int NOT NULL,
  `sesion_id` int NOT NULL,
  `ejercicio_id` int NOT NULL,
  `peso` float DEFAULT NULL,
  `repeticiones` int DEFAULT NULL,
  `distancia` float DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ejercicio_FK_idx` (`ejercicio_id`),
  KEY `sesion_FK_idx` (`sesion_id`),
  CONSTRAINT `ejercicio_FK` FOREIGN KEY (`ejercicio_id`) REFERENCES `ejercicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sesion_serie_FK` FOREIGN KEY (`sesion_id`) REFERENCES `sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sesion` (
  `id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL,
  `dni` varchar(9) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `sexo` binary(1) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `altura` float DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `contrasenya` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-08 19:27:26
