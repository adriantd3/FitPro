-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: fitpro
-- ------------------------------------------------------
-- Server version	8.0.35

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
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `calorias` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comida`
--

LOCK TABLES `comida` WRITE;
/*!40000 ALTER TABLE `comida` DISABLE KEYS */;
INSERT INTO `comida` VALUES (1,'Salmón con ga',450),(2,'Pollo al curry',350),(3,'Ensalada César',250),(4,'Sopa de verduras',150),(5,'Arroz frito con pollo',500),(6,'Pizza de pepperoni',750),(7,'Ensalada de quinoa',300),(8,'Pasta Alfreda',500),(15,'Pasta Gansa',1000);
/*!40000 ALTER TABLE `comida` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `comida_menu`
--

LOCK TABLES `comida_menu` WRITE;
/*!40000 ALTER TABLE `comida_menu` DISABLE KEYS */;
INSERT INTO `comida_menu` VALUES (1,1),(2,1),(3,1),(4,1),(5,2),(6,2),(7,2),(8,2);
/*!40000 ALTER TABLE `comida_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desempenyo_comida`
--

DROP TABLE IF EXISTS `desempenyo_comida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_comida` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comida_id` int NOT NULL,
  `desempenyo_menu_id` int NOT NULL,
  `comido` tinyint NOT NULL,
  `gustado` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comida_desempenyo_FK_idx` (`comida_id`),
  KEY `desempenyomenu_FK_idx` (`desempenyo_menu_id`),
  CONSTRAINT `comida_desempenyo_FK` FOREIGN KEY (`comida_id`) REFERENCES `comida` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `desempenyo_menu_FK` FOREIGN KEY (`desempenyo_menu_id`) REFERENCES `desempenyo_menu` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desempenyo_comida`
--

LOCK TABLES `desempenyo_comida` WRITE;
/*!40000 ALTER TABLE `desempenyo_comida` DISABLE KEYS */;
INSERT INTO `desempenyo_comida` VALUES (1,1,1,1,0),(2,2,1,0,0),(3,3,1,1,1),(4,4,1,0,0);
/*!40000 ALTER TABLE `desempenyo_comida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desempenyo_menu`
--

DROP TABLE IF EXISTS `desempenyo_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `menu_id` int NOT NULL,
  `fecha_creacion` date NOT NULL,
  `terminado` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_FK_idx` (`menu_id`),
  KEY `menu_desempenyo_usuario_FK_idx` (`usuario_id`),
  CONSTRAINT `menu_desempenyo_FK` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_desempenyo_usuario_FK` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desempenyo_menu`
--

LOCK TABLES `desempenyo_menu` WRITE;
/*!40000 ALTER TABLE `desempenyo_menu` DISABLE KEYS */;
INSERT INTO `desempenyo_menu` VALUES (1,1,1,'2024-06-17',1);
/*!40000 ALTER TABLE `desempenyo_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desempenyo_serie`
--

DROP TABLE IF EXISTS `desempenyo_serie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_serie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `desempenyo_sesion_id` int NOT NULL,
  `ejercicio_id` int NOT NULL,
  `metrica1` float DEFAULT NULL,
  `metrica2` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `desempenyo_sesion_FK_idx` (`desempenyo_sesion_id`),
  KEY `ejercicio_FK_idx` (`ejercicio_id`),
  CONSTRAINT `desempenyo_sesion_FK` FOREIGN KEY (`desempenyo_sesion_id`) REFERENCES `desempenyo_sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ejercicio_desempenyo_FK` FOREIGN KEY (`ejercicio_id`) REFERENCES `ejercicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desempenyo_serie`
--

LOCK TABLES `desempenyo_serie` WRITE;
/*!40000 ALTER TABLE `desempenyo_serie` DISABLE KEYS */;
/*!40000 ALTER TABLE `desempenyo_serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desempenyo_sesion`
--

DROP TABLE IF EXISTS `desempenyo_sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desempenyo_sesion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `sesion_id` int NOT NULL,
  `fecha` date NOT NULL,
  `terminado` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sesion_desempenyo_FK_idx` (`sesion_id`),
  KEY `sesion_desempenyo_usuario_FK_idx` (`usuario_id`),
  CONSTRAINT `sesion_desempenyo_FK` FOREIGN KEY (`sesion_id`) REFERENCES `sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sesion_desempenyo_usuario_FK` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desempenyo_sesion`
--

LOCK TABLES `desempenyo_sesion` WRITE;
/*!40000 ALTER TABLE `desempenyo_sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `desempenyo_sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dieta`
--

DROP TABLE IF EXISTS `dieta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dieta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dietista_id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `fecha_creacion` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dietista_dieta_FK_idx` (`dietista_id`),
  CONSTRAINT `dietista_dieta_FK` FOREIGN KEY (`dietista_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dieta`
--

LOCK TABLES `dieta` WRITE;
/*!40000 ALTER TABLE `dieta` DISABLE KEYS */;
INSERT INTO `dieta` VALUES (1,5,'Dieta1','2024-04-10'),(2,5,'Dieta2','2024-04-10'),(3,5,'Dieta3','2024-04-11');
/*!40000 ALTER TABLE `dieta` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `dieta_cliente`
--

LOCK TABLES `dieta_cliente` WRITE;
/*!40000 ALTER TABLE `dieta_cliente` DISABLE KEYS */;
INSERT INTO `dieta_cliente` VALUES (1,1),(2,1),(3,1),(1,10),(3,10),(3,12),(4,12);
/*!40000 ALTER TABLE `dieta_cliente` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `dietista_cliente`
--

LOCK TABLES `dietista_cliente` WRITE;
/*!40000 ALTER TABLE `dietista_cliente` DISABLE KEYS */;
INSERT INTO `dietista_cliente` VALUES (5,10),(5,12);
/*!40000 ALTER TABLE `dietista_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejercicio`
--

DROP TABLE IF EXISTS `ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejercicio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` longtext,
  `imagen` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `tipo` int NOT NULL,
  `grupo_muscular` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo_FK_idx` (`tipo`),
  KEY `grupo_muscular_FK_idx` (`grupo_muscular`),
  CONSTRAINT `grupo_muscular_FK` FOREIGN KEY (`grupo_muscular`) REFERENCES `grupo_muscular` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tipo_FK` FOREIGN KEY (`tipo`) REFERENCES `tipo_ejercicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejercicio`
--

LOCK TABLES `ejercicio` WRITE;
/*!40000 ALTER TABLE `ejercicio` DISABLE KEYS */;
INSERT INTO `ejercicio` VALUES (1,'Incline Bench Pres','     The incline bench press is a variatio of the bench press and an exercise used to build the muscles of the chest.     ','https://cdn.muscleandstrength.com/sites/default/files/incline-bench-press.jpg','https://youtube.com/embed/uIzbJX5EVIY',1,2),(2,'Seated Cable Row','Your back must remain straight at all times. Your torso should be kept still throughout the entire set.','https://cdn.muscleandstrength.com/sites/default/files/styles/800x500/public/seated-cable-row.jpg?itok=b8Yzo0KK',NULL,1,14),(3,'Leg Press','The leg press is a variation of the squat and an exercise used to target the muscles of the leg.','https://cdn.muscleandstrength.com/sites/default/files/leg-press.jpg','https://youtube.com/embed/sEM_zo9w2ss',1,19),(4,'Smith Machine Shoulder Press','The Smith machine shoulder press is a variation of the barbell shoulder press and is used to strengthen the muscles of the shoulders.','https://cdn.muscleandstrength.com/sites/default/files/seated-military-press.jpg',NULL,1,20),(7,'Caminar',' Camina','','',5,5),(8,'Estiramientps','Estira el pecho','','',5,6),(9,'Salto de comba','Salta a la comba','','',2,5),(10,'Salto estrella','Realiza un salto y chocas las palmas cuando estas en lo alto','','',2,8),(11,'Sprint','Correr a máxima potencia','','',3,19),(12,'Cambios de dirección','  Correr y hacer cambios de dirección bruscos ','','',3,5),(13,'Equilibrio sobre una pierna','  Sentadillas sobre una pierna','','',4,19),(14,'Plancha','  Mantente recto con los brazos y pies apoyados en el suelo','','',4,2);
/*!40000 ALTER TABLE `ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `entrenador_cliente`
--

LOCK TABLES `entrenador_cliente` WRITE;
/*!40000 ALTER TABLE `entrenador_cliente` DISABLE KEYS */;
INSERT INTO `entrenador_cliente` VALUES (3,1),(4,1),(3,10),(3,12),(4,12);
/*!40000 ALTER TABLE `entrenador_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_muscular`
--

DROP TABLE IF EXISTS `grupo_muscular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_muscular` (
  `id` int NOT NULL,
  `grupo_muscular` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_muscular`
--

LOCK TABLES `grupo_muscular` WRITE;
/*!40000 ALTER TABLE `grupo_muscular` DISABLE KEYS */;
INSERT INTO `grupo_muscular` VALUES (1,'Abductores'),(2,'Abdominales'),(3,'Aductores'),(4,'Biceps'),(5,'Gemelos'),(6,'Pecho'),(7,'Antebrazo'),(8,'Gluteos'),(9,'Isquiotibiales'),(10,'Flexores de cadera'),(11,'Cintilla Iliotibial'),(12,'Dorsales'),(13,'Espalda Baja'),(14,'Espalda Superior'),(15,'Cuello'),(16,'Oblicuos'),(17,'Fascia Palmar'),(18,'Fascia Plantar'),(19,'Cuadriceps'),(20,'Hombros'),(21,'Trapecios'),(22,'Triceps');
/*!40000 ALTER TABLE `grupo_muscular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `calorias` float NOT NULL DEFAULT '0',
  `fecha_creacion` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'Menu1',1000,'2024-04-11'),(2,'Menu2',1500,'2024-04-12'),(3,'Menu3',1000,'2024-04-01');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`menu_id`,`dieta_id`,`orden`),
  KEY `dieta_FK_idx` (`dieta_id`),
  CONSTRAINT `dieta_FK` FOREIGN KEY (`dieta_id`) REFERENCES `dieta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_FK` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_menu_dieta`
--

LOCK TABLES `orden_menu_dieta` WRITE;
/*!40000 ALTER TABLE `orden_menu_dieta` DISABLE KEYS */;
INSERT INTO `orden_menu_dieta` VALUES (1,1,1),(1,2,2),(2,2,1),(1,3,3),(2,3,2);
/*!40000 ALTER TABLE `orden_menu_dieta` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`sesion_id`,`rutina_id`,`orden`),
  KEY `sesion_FK_idx` (`sesion_id`),
  KEY `rutina_FK_idx` (`rutina_id`),
  CONSTRAINT `rutina_FK` FOREIGN KEY (`rutina_id`) REFERENCES `rutina` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sesion_FK` FOREIGN KEY (`sesion_id`) REFERENCES `sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_sesion_rutina`
--

LOCK TABLES `orden_sesion_rutina` WRITE;
/*!40000 ALTER TABLE `orden_sesion_rutina` DISABLE KEYS */;
INSERT INTO `orden_sesion_rutina` VALUES (5,9,1),(5,10,4),(6,9,3),(6,10,2),(7,9,5),(7,10,6),(8,10,1);
/*!40000 ALTER TABLE `orden_sesion_rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin'),(2,'entrenador_fuerza'),(3,'entrenador_cross_training'),(4,'dietista'),(5,'cliente');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rutina`
--

DROP TABLE IF EXISTS `rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rutina` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entrenador_id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `fecha_creacion` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entrenador_rutina_FK_idx` (`entrenador_id`),
  CONSTRAINT `entrenador_rutina_FK` FOREIGN KEY (`entrenador_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rutina`
--

LOCK TABLES `rutina` WRITE;
/*!40000 ALTER TABLE `rutina` DISABLE KEYS */;
INSERT INTO `rutina` VALUES (4,4,'Rutina 1','2024-05-29'),(5,4,'Rutina 2','2024-05-29'),(6,4,'Rutina 3','2024-05-29'),(7,4,'Rutina 4','2024-05-29'),(9,3,'Rutina Mikel','2024-06-23'),(10,3,'Rutina PepeGomex','2024-06-23');
/*!40000 ALTER TABLE `rutina` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `rutina_cliente`
--

LOCK TABLES `rutina_cliente` WRITE;
/*!40000 ALTER TABLE `rutina_cliente` DISABLE KEYS */;
INSERT INTO `rutina_cliente` VALUES (9,1),(10,10);
/*!40000 ALTER TABLE `rutina_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serie`
--

DROP TABLE IF EXISTS `serie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sesion_id` int NOT NULL,
  `ejercicio_id` int NOT NULL,
  `metrica1` float DEFAULT NULL,
  `metrica2` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ejercicio_FK_idx` (`ejercicio_id`),
  KEY `sesion_FK_idx` (`sesion_id`),
  CONSTRAINT `ejercicio_FK` FOREIGN KEY (`ejercicio_id`) REFERENCES `ejercicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sesion_serie_FK` FOREIGN KEY (`sesion_id`) REFERENCES `sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serie`
--

LOCK TABLES `serie` WRITE;
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
INSERT INTO `serie` VALUES (15,5,1,25,15),(16,5,1,30,10),(17,5,1,30,10),(18,5,1,35,8),(19,6,3,60,15),(20,6,3,60,14),(21,6,3,70,12),(22,6,3,70,12),(23,6,3,80,10),(24,6,3,80,10),(25,7,4,20,15),(26,7,4,20,12),(27,7,4,25,10),(28,7,4,25,10),(29,7,4,30,9),(30,8,2,50,10),(31,8,2,50,10),(32,8,2,50,10),(33,8,2,60,8),(34,8,2,60,8),(35,9,1,30,10),(36,9,1,30,10),(37,9,1,30,10),(38,9,2,50,10),(39,9,2,50,10),(40,9,2,50,10),(41,9,3,60,15),(42,9,3,60,15),(43,9,3,70,12),(44,9,4,25,12),(45,9,4,25,12),(46,9,4,25,10);
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sesion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` VALUES (5,'Pecho'),(6,'Pierna'),(7,'Brazo'),(8,'Espalda'),(9,'FullBody');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_ejercicio`
--

DROP TABLE IF EXISTS `tipo_ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_ejercicio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `metrica1` varchar(45) NOT NULL,
  `metrica2` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_ejercicio`
--

LOCK TABLES `tipo_ejercicio` WRITE;
/*!40000 ALTER TABLE `tipo_ejercicio` DISABLE KEYS */;
INSERT INTO `tipo_ejercicio` VALUES (1,'Fuerza/Resistencia','Peso','Repeticiones'),(2,'Capacidad aerobica','Distancia','Duración'),(3,'Velocidad/Potencia','Duración','Descanso'),(4,'Estabilidad','Repeticiones','Descanso'),(5,'Movilidad','Repeticiones','Descanso');
/*!40000 ALTER TABLE `tipo_ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `rol` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `sexo` tinyint DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `altura` float DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `contrasenya` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rol_FK_idx` (`rol`),
  CONSTRAINT `rol_FK` FOREIGN KEY (`rol`) REFERENCES `rol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'12345678A',5,'Mikel','Arias Montiel',1,20,1.78,80,'kirito','mikel@gmail.com'),(2,'25915268B',1,'David','Garcia Sanchez',1,19,1.9,87,'david','david@gmail.com'),(3,'75249108B',2,'Alvaro','Pérez Navarro',1,20,1.67,70,'alvaro','alvaro@gmail.com'),(4,'34345612H',3,'María','Cruz Martínez',0,18,1.54,60,'maria','maria@gmail.com'),(5,'68193415I',4,'Irene','Iglesias Gutierrez',0,24,1.5,54,'irene','irene@gmail.com'),(10,'123',5,'Pepe','Gomex',1,12,12,123,'pepe','pepe@gmail.com'),(11,'123',5,'Pepa','Gomex',0,12,12,123,'pepa','pepa@gmail.com');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-23 17:42:16
