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
-- Dumping data for table `comida`
--

LOCK TABLES `comida` WRITE;
/*!40000 ALTER TABLE `comida` DISABLE KEYS */;
INSERT INTO `comida` VALUES (1,'Salmón con garbanzos y brócoli',450),(2,'Pollo al curry',350),(3,'Ensalada César',250),(4,'Sopa de verduras',150),(5,'Arroz frito con pollo',500),(6,'Pizza de pepperoni',750),(7,'Ensalada de quinoa',300),(8,'Pasta Alfredo',500),(9,'Tacos de carne asada',450);
/*!40000 ALTER TABLE `comida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comida_menu`
--

LOCK TABLES `comida_menu` WRITE;
/*!40000 ALTER TABLE `comida_menu` DISABLE KEYS */;
INSERT INTO `comida_menu` VALUES (1,1),(2,1),(3,1),(4,1),(5,2),(6,2),(7,2),(8,2);
/*!40000 ALTER TABLE `comida_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_comida`
--

LOCK TABLES `desempenyo_comida` WRITE;
/*!40000 ALTER TABLE `desempenyo_comida` DISABLE KEYS */;
/*!40000 ALTER TABLE `desempenyo_comida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_menu`
--

LOCK TABLES `desempenyo_menu` WRITE;
/*!40000 ALTER TABLE `desempenyo_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `desempenyo_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_serie`
--

LOCK TABLES `desempenyo_serie` WRITE;
/*!40000 ALTER TABLE `desempenyo_serie` DISABLE KEYS */;
INSERT INTO `desempenyo_serie` VALUES (44,15,2,50,8,NULL,NULL,NULL),(45,15,2,40,15,NULL,NULL,NULL),(46,15,1,60,12,NULL,NULL,NULL),(47,15,1,70,6,NULL,NULL,NULL),(48,15,2,45,12,NULL,NULL,NULL),(49,15,1,65,10,NULL,NULL,NULL);
/*!40000 ALTER TABLE `desempenyo_serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_sesion`
--

LOCK TABLES `desempenyo_sesion` WRITE;
/*!40000 ALTER TABLE `desempenyo_sesion` DISABLE KEYS */;
INSERT INTO `desempenyo_sesion` VALUES (15,1,1,'2024-05-06',0);
/*!40000 ALTER TABLE `desempenyo_sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dieta`
--

LOCK TABLES `dieta` WRITE;
/*!40000 ALTER TABLE `dieta` DISABLE KEYS */;
INSERT INTO `dieta` VALUES (1,5,'Dieta1','2024-04-10'),(2,5,'Dieta2','2024-04-10'),(3,5,'Dieta3','2024-04-11');
/*!40000 ALTER TABLE `dieta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dieta_cliente`
--

LOCK TABLES `dieta_cliente` WRITE;
/*!40000 ALTER TABLE `dieta_cliente` DISABLE KEYS */;
INSERT INTO `dieta_cliente` VALUES (1,1),(2,1),(3,1);
/*!40000 ALTER TABLE `dieta_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dietista_cliente`
--

LOCK TABLES `dietista_cliente` WRITE;
/*!40000 ALTER TABLE `dietista_cliente` DISABLE KEYS */;
INSERT INTO `dietista_cliente` VALUES (5,1);
/*!40000 ALTER TABLE `dietista_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ejercicio`
--

LOCK TABLES `ejercicio` WRITE;
/*!40000 ALTER TABLE `ejercicio` DISABLE KEYS */;
INSERT INTO `ejercicio` VALUES (1,'Incline Bench Press','The incline bench press is a variation of the bench press and an exercise used to build the muscles of the chest.','https://cdn.muscleandstrength.com/sites/default/files/incline-bench-press.jpg','https://youtu.be/uIzbJX5EVIY',1,6),(2,'Seated Cable Row','Your back must remain straight at all times. Your torso should be kept still throughout the entire set.','https://cdn.muscleandstrength.com/sites/default/files/styles/800x500/public/seated-cable-row.jpg?itok=b8Yzo0KK',NULL,1,14),(3,'Leg Press','The leg press is a variation of the squat and an exercise used to target the muscles of the leg.','https://cdn.muscleandstrength.com/sites/default/files/leg-press.jpg','https://youtu.be/sEM_zo9w2ss',1,19),(4,'Smith Machine Shoulder Press','The Smith machine shoulder press is a variation of the barbell shoulder press and is used to strengthen the muscles of the shoulders.','https://cdn.muscleandstrength.com/sites/default/files/seated-military-press.jpg',NULL,1,20),(5,'Rope Tricep Extension','The rope tricep extension is a variation of the cable tricep extension and an exercise used to build the muscles of the triceps.','https://cdn.muscleandstrength.com/sites/default/files/rope-tricep-extension-1.jpg',NULL,1,22);
/*!40000 ALTER TABLE `ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `entrenador_cliente`
--

LOCK TABLES `entrenador_cliente` WRITE;
/*!40000 ALTER TABLE `entrenador_cliente` DISABLE KEYS */;
INSERT INTO `entrenador_cliente` VALUES (3,1);
/*!40000 ALTER TABLE `entrenador_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `grupo_muscular`
--

LOCK TABLES `grupo_muscular` WRITE;
/*!40000 ALTER TABLE `grupo_muscular` DISABLE KEYS */;
INSERT INTO `grupo_muscular` VALUES (1,'Abductores'),(2,'Abdominales'),(3,'Aductores'),(4,'Biceps'),(5,'Gemelos'),(6,'Pecho'),(7,'Antebrazo'),(8,'Gluteos'),(9,'Isquiotibiales'),(10,'Flexores de cadera'),(11,'Cintilla Iliotibial'),(12,'Dorsales'),(13,'Espalda Baja'),(14,'Espalda Superior'),(15,'Cuello'),(16,'Oblicuos'),(17,'Fascia Palmar'),(18,'Fascia Plantar'),(19,'Cuadriceps'),(20,'Hombros'),(21,'Trapecios'),(22,'Triceps');
/*!40000 ALTER TABLE `grupo_muscular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'Menu1',1000,'2024-04-11'),(2,'Menu2',1500,'2024-04-12'),(3,'Menu3',1000,'2024-04-01');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orden_menu_dieta`
--

LOCK TABLES `orden_menu_dieta` WRITE;
/*!40000 ALTER TABLE `orden_menu_dieta` DISABLE KEYS */;
INSERT INTO `orden_menu_dieta` VALUES (1,1,1),(1,2,2),(2,2,1),(1,3,3),(2,3,2);
/*!40000 ALTER TABLE `orden_menu_dieta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orden_sesion_rutina`
--

LOCK TABLES `orden_sesion_rutina` WRITE;
/*!40000 ALTER TABLE `orden_sesion_rutina` DISABLE KEYS */;
INSERT INTO `orden_sesion_rutina` VALUES (1,2,2),(1,3,3);
/*!40000 ALTER TABLE `orden_sesion_rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin'),(2,'entrenador_fuerza'),(3,'entrenador_cross_training'),(4,'dietista'),(5,'cliente');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rutina`
--

LOCK TABLES `rutina` WRITE;
/*!40000 ALTER TABLE `rutina` DISABLE KEYS */;
INSERT INTO `rutina` VALUES (2,3,'Rutina2','2024-04-12'),(3,3,'Rutina3','2024-04-13');
/*!40000 ALTER TABLE `rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rutina_cliente`
--

LOCK TABLES `rutina_cliente` WRITE;
/*!40000 ALTER TABLE `rutina_cliente` DISABLE KEYS */;
INSERT INTO `rutina_cliente` VALUES (2,1);
/*!40000 ALTER TABLE `rutina_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `serie`
--

LOCK TABLES `serie` WRITE;
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
INSERT INTO `serie` VALUES (1,1,1,60,12,NULL,NULL,NULL),(2,1,1,65,10,NULL,NULL,NULL),(3,1,1,70,6,NULL,NULL,NULL),(4,1,2,40,15,NULL,NULL,NULL),(5,1,2,45,12,NULL,NULL,NULL),(6,1,2,50,8,NULL,NULL,NULL),(7,2,3,70,10,NULL,NULL,NULL),(8,2,3,70,8,NULL,NULL,NULL),(9,2,3,75,5,NULL,NULL,NULL),(10,2,4,40,10,NULL,NULL,NULL),(11,2,4,45,8,NULL,NULL,NULL),(12,2,4,45,8,NULL,NULL,NULL);
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` VALUES (1,'Sesion1'),(2,'Sesion2'),(3,'Sesion3');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_ejercicio`
--

LOCK TABLES `tipo_ejercicio` WRITE;
/*!40000 ALTER TABLE `tipo_ejercicio` DISABLE KEYS */;
INSERT INTO `tipo_ejercicio` VALUES (1,'Fuerza/Resistencia'),(2,'Capacidad aerobica'),(3,'Velocidad/Potencia'),(4,'Estabilidad'),(5,'Movilidad');
/*!40000 ALTER TABLE `tipo_ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'12345678A',5,'Mikel','Arias Montiel',1,20,1.78,80,'kirito','mikel@gmail.com'),(2,'25915268B',1,'David','Garcia Sanchez',1,19,1.9,87,'david','david@gmail.com'),(3,'75249108B',2,'Alvaro','Pérez Navarro',1,20,1.67,70,'alvaro','alvaro@gmail.com'),(4,'34345612H',3,'María','Cruz Martínez',0,18,1.54,60,'maria','maria@gmail.com'),(5,'68193415I',4,'Irene','Iglesias Gutierrez',0,24,1.5,54,'irene','irene@gmail.com');
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

-- Dump completed on 2024-05-07 16:36:53
