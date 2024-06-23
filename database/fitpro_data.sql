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
INSERT INTO `comida` VALUES (1,'Salmón con gapazo',450),(2,'Pollo al curry',350),(3,'Ensalada César',250),(4,'Sopa de verduras',150),(5,'Arroz frito con pollo',500),(6,'Pizza de pepperoni',750),(7,'Ensalada de quinoa',300),(8,'Pasta Alfreda',500),(15,'Pasta Gansa',1000);
/*!40000 ALTER TABLE `comida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comida_menu`
--

LOCK TABLES `comida_menu` WRITE;
/*!40000 ALTER TABLE `comida_menu` DISABLE KEYS */;
INSERT INTO `comida_menu` VALUES (1,1),(2,1),(3,1),(4,1),(2,2),(6,2),(7,2),(8,2),(15,2),(1,3),(3,3),(4,3),(6,3),(7,3),(4,4),(5,4),(6,4),(7,4);
/*!40000 ALTER TABLE `comida_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_comida`
--

LOCK TABLES `desempenyo_comida` WRITE;
/*!40000 ALTER TABLE `desempenyo_comida` DISABLE KEYS */;
INSERT INTO `desempenyo_comida` VALUES (5,4,2,1,0),(6,5,2,1,1),(7,6,2,1,1),(8,7,2,0,1),(9,2,3,0,0),(10,6,3,1,1),(11,7,3,0,1),(12,8,3,0,0),(13,15,3,1,0),(14,1,4,0,0),(15,3,4,1,1),(16,4,4,0,0),(17,6,4,0,1),(18,7,4,0,0),(19,4,5,0,0),(20,5,5,0,0),(21,6,5,0,0),(22,7,5,0,0),(23,4,6,0,0),(24,5,6,0,0),(25,6,6,0,0),(26,7,6,0,0);
/*!40000 ALTER TABLE `desempenyo_comida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_menu`
--

LOCK TABLES `desempenyo_menu` WRITE;
/*!40000 ALTER TABLE `desempenyo_menu` DISABLE KEYS */;
INSERT INTO `desempenyo_menu` VALUES (2,12,4,'2024-06-23',1),(3,12,2,'2024-06-23',1),(4,12,3,'2024-06-23',0),(5,10,4,'2024-06-23',1),(6,10,4,'2024-06-23',1);
/*!40000 ALTER TABLE `desempenyo_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_serie`
--

LOCK TABLES `desempenyo_serie` WRITE;
/*!40000 ALTER TABLE `desempenyo_serie` DISABLE KEYS */;
INSERT INTO `desempenyo_serie` VALUES (329,59,1,50,10),(330,59,1,55,10),(331,59,1,60,12),(332,59,1,55,10),(335,60,1,50,10),(336,60,1,55,10),(340,61,3,60,20),(341,61,3,70,15),(342,61,3,70,15),(343,61,3,80,10),(344,61,3,80,10),(345,62,4,25,12),(346,62,4,30,10),(347,62,4,40,10),(348,63,12,30,15),(349,63,12,60,30),(351,63,17,30,15),(353,63,17,15,20),(354,64,12,30,15),(355,64,12,60,30),(356,64,12,15,10),(359,64,17,15,20),(360,64,12,50,35),(361,65,7,4,30),(364,65,11,20,60),(365,66,7,4,30),(366,66,7,6,15),(367,66,11,10,30),(368,66,11,20,60),(369,66,7,8,60),(370,66,11,30,60),(371,67,3,60,20),(372,67,3,70,15),(373,67,3,70,15),(374,67,3,80,10),(376,67,7,0,0),(377,67,7,6,20),(378,67,7,4,15),(379,68,1,30,12),(380,68,1,35,10),(382,68,2,50,10),(383,68,2,50,10),(385,68,3,65,13),(386,68,3,70,10),(387,68,4,25,10),(388,68,4,30,10),(390,69,1,30,12),(393,69,2,50,10),(394,69,2,50,10),(395,69,3,60,15),(396,69,3,65,13),(401,69,1,70,2),(402,70,12,30,15),(403,70,12,60,30),(404,70,12,15,10),(405,70,17,30,15),(406,70,17,60,45),(407,70,17,15,20);
/*!40000 ALTER TABLE `desempenyo_serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desempenyo_sesion`
--

LOCK TABLES `desempenyo_sesion` WRITE;
/*!40000 ALTER TABLE `desempenyo_sesion` DISABLE KEYS */;
INSERT INTO `desempenyo_sesion` VALUES (59,12,5,'2024-06-23',1),(60,12,5,'2024-06-23',0),(61,12,7,'2024-06-23',1),(62,12,8,'2024-06-23',1),(63,11,13,'2024-06-23',1),(64,11,13,'2024-06-23',1),(65,11,10,'2024-06-23',1),(66,11,10,'2024-06-23',1),(67,11,7,'2024-06-23',1),(68,11,9,'2024-06-23',1),(69,11,9,'2024-06-23',1),(70,11,13,'2024-06-23',0);
/*!40000 ALTER TABLE `desempenyo_sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dieta`
--

LOCK TABLES `dieta` WRITE;
/*!40000 ALTER TABLE `dieta` DISABLE KEYS */;
INSERT INTO `dieta` VALUES (1,5,'Dieta1','2024-04-10'),(2,5,'Dieta2','2024-04-10'),(3,5,'Dieta3','2024-04-11'),(4,5,'DietaEspecial','2024-06-23');
/*!40000 ALTER TABLE `dieta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dieta_cliente`
--

LOCK TABLES `dieta_cliente` WRITE;
/*!40000 ALTER TABLE `dieta_cliente` DISABLE KEYS */;
INSERT INTO `dieta_cliente` VALUES (1,10),(3,10),(3,12),(4,12);
/*!40000 ALTER TABLE `dieta_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dietista_cliente`
--

LOCK TABLES `dietista_cliente` WRITE;
/*!40000 ALTER TABLE `dietista_cliente` DISABLE KEYS */;
INSERT INTO `dietista_cliente` VALUES (5,10),(5,12);
/*!40000 ALTER TABLE `dietista_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ejercicio`
--

LOCK TABLES `ejercicio` WRITE;
/*!40000 ALTER TABLE `ejercicio` DISABLE KEYS */;
INSERT INTO `ejercicio` VALUES (1,'Incline Bench Press',' The incline bench press is a variatio of the bench press and an exercise used to build the muscles of the chest.      ','https://cdn.muscleandstrength.com/sites/default/files/incline-bench-press.jpg','https://youtube.com/embed/uIzbJX5EVIY',1,2),(2,'Seated Cable Row','Your back must remain straight at all times. Your torso should be kept still throughout the entire set.','https://cdn.muscleandstrength.com/sites/default/files/styles/800x500/public/seated-cable-row.jpg?itok=b8Yzo0KK',NULL,1,14),(3,'Leg Press','The leg press is a variation of the squat and an exercise used to target the muscles of the leg.','https://cdn.muscleandstrength.com/sites/default/files/leg-press.jpg','https://youtube.com/embed/sEM_zo9w2ss',1,19),(4,'Smith Machine Shoulder Press','The Smith machine shoulder press is a variation of the barbell shoulder press and is used to strengthen the muscles of the shoulders.','https://cdn.muscleandstrength.com/sites/default/files/seated-military-press.jpg',NULL,1,20),(7,'Go for a walk','Go for a walk to improve your overall health','https://www.mpcp.com/wp-content/uploads/2022/06/mpcp-walking-320x240-1.jpg','https://www.youtube.com/embed/RrphLKih470?si=gsCCgy3viD4rSg5v',5,5),(8,'Side Lying Hip Flexor And Quad Stretchor','The side lying hip Flexor and quad stretch helps improve mobility and flexibility in the quads and hip flexor area.','https://i0.wp.com/www.muscleandfitness.com/wp-content/uploads/2014/02/992_B.jpg?quality=86&strip=all','https://www.youtube.com/embed/_xU-wIiMxpI?si=JZv3iVcvTboJzR0c',5,19),(9,'Skipping','It is about running by raising the knees exaggeratedly, and it is one of the most important exercises to improve our running technique.','https://www.entrenamientos.com/media/cache/exercise_375/uploads/exercise/carrera-en-el-sitio-o-skipinng-init-pos-6552.png','https://www.youtube.com/embed/98Qbs7ghKnU?si=D8ktK5lh3qXETFAC',2,10),(10,'Salto estrella',' Realiza un salto y chocas las palmas cuando estas en lo alto ','https://d3h3bmeuj906e6.cloudfront.net/wp-content/uploads/2020/05/SALTO-DE-ESTRELLA.jpg','',2,8),(11,'Sprint','These challenging sprint workouts are built to help you gain speed and power.','https://www.dummies.com/wp-content/uploads/sprint-training.jpg','https://www.youtube.com/embed/TFdHV9rYcz0?si=_TILoygfsuI-mDld',3,5),(12,'Stride Length Drill','Strides are fast running sets with short rests. By running fast, your body automatically reverts back to what is a better technique than the slow plod.','https://s3-us-west-2.amazonaws.com/mostfit/posts/featured_images/000/000/012/medium/minihurdles.png?1556994256','https://www.youtube.com/embed/VjSONvP3kds?si=wtWWwXU37302Qupe',3,5),(15,'T Stabilization','  The T-stabilization is an intermediate stability exercise that trains the entire core, including your shoulder complex, abs, and hips, while helping build endurance in your lower back.','https://workouttrends.com/wp-content/uploads/2014/05/t-stabilization-300x300.jpg','https://www.youtube.com/embed/5nqMNkVwASE?si=ACCzzERDRzcbAZmB',4,2),(16,'Scorpion','   The scorpion is a dynamic warm-up exercise that improves hip mobility while strengthening the lower back and core. The exercise also improves coordination and flexibility throughout the body. ','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSS1QQNw6FhbYBvRyzHJYKVMFlGQxYMgrfCCA&s','https://www.youtube.com/embed/zFlwrxVeQxc?si=ADe8zh8e6qOPao7C',5,10),(17,'Clean','The clean is a multi-joint exercise that develops power and strength throughout the entire body. The exercise also helps build core strength and athletic coordination.  ','https://cdn.mos.cms.futurecdn.net/c7QzqURhcSy7MqwSbWevoA-1200-80.jpg','https://www.youtube.com/embed/EKRiW9Yt3Ps?si=XZBU7mBSyhuuHwZZ',3,8),(18,'Weighted Situp','The weighted sit up is a progression from a bodyweight sit up that strengthens the entire core region including the lower abdominals, hip flexors, and lower back.','https://i0.wp.com/www.muscleandfitness.com/wp-content/uploads/2017/10/weighted-situp-2.jpg?w=800&h=630&crop=1&quality=86&strip=all','https://www.youtube.com/embed/kZvSaq192cg?si=MGxLw8y_PkcxrChW',1,2),(19,'Weighted Dip','  The weighted dip is a compound upper-body pressing exercise and progression of the bodyweight dip that strengthens the chest, shoulders, and triceps.','https://www.hevyapp.com/wp-content/uploads/DSC04897.jpg','https://www.youtube.com/embed/l0b_W3Gk4vM?si=NTK5DkU3Ma3EOnu7',1,6),(20,'Thrusters','  Thrusters improve strength and power in the legs, shoulders, triceps, and core while increasing cardiovascular endurance. ','https://media1.popsugar-assets.com/files/thumbor/900e0spHYKKugbcA2xANt1hyi4U=/fit-in/792x792/filters:format_auto():upscale()/2017/09/28/732/n/1922729/tmp_FPCm3l_1c11759740338fc7_Dumbbell-Thrusters.jpg','https://www.youtube.com/embed/u3wKkZjE8QM?si=wTpjNboVAijWC3yU',1,20);
/*!40000 ALTER TABLE `ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `entrenador_cliente`
--

LOCK TABLES `entrenador_cliente` WRITE;
/*!40000 ALTER TABLE `entrenador_cliente` DISABLE KEYS */;
INSERT INTO `entrenador_cliente` VALUES (3,10),(4,11),(3,12);
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
INSERT INTO `menu` VALUES (1,'Menu1',1200,'2024-04-11'),(2,'Menu2',2900,'2024-04-12'),(3,'Menu3',1900,'2024-04-01'),(4,'MenuEspecial',1700,'2024-06-23');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orden_menu_dieta`
--

LOCK TABLES `orden_menu_dieta` WRITE;
/*!40000 ALTER TABLE `orden_menu_dieta` DISABLE KEYS */;
INSERT INTO `orden_menu_dieta` VALUES (1,1,1),(2,1,3),(2,1,4),(3,1,5),(4,1,2),(1,2,2),(2,2,1),(1,3,3),(1,3,4),(2,3,2),(2,3,5),(3,3,7),(4,3,1),(4,3,6),(4,4,1),(4,4,3),(4,4,5);
/*!40000 ALTER TABLE `orden_menu_dieta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orden_sesion_rutina`
--

LOCK TABLES `orden_sesion_rutina` WRITE;
/*!40000 ALTER TABLE `orden_sesion_rutina` DISABLE KEYS */;
INSERT INTO `orden_sesion_rutina` VALUES (5,8,1),(5,12,1),(6,12,3),(7,8,3),(7,9,5),(7,13,1),(8,8,5),(8,12,5),(9,9,1),(9,13,5),(10,10,4),(10,12,7),(10,13,3),(11,11,2),(12,11,4),(13,10,2),(13,13,7);
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
INSERT INTO `rutina` VALUES (8,3,'Rutina Mikel','2024-06-23'),(9,3,'Rutina PepeGomex','2024-06-23'),(10,4,'Cardio + Potencia','2024-06-23'),(11,4,'Equilibrio + Movilidad','2024-06-23'),(12,4,'Fuerza + Cardio','2024-06-23'),(13,4,'Rutina mixta','2024-06-23');
/*!40000 ALTER TABLE `rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rutina_cliente`
--

LOCK TABLES `rutina_cliente` WRITE;
/*!40000 ALTER TABLE `rutina_cliente` DISABLE KEYS */;
INSERT INTO `rutina_cliente` VALUES (9,10),(10,11),(13,11),(8,12);
/*!40000 ALTER TABLE `rutina_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `serie`
--

LOCK TABLES `serie` WRITE;
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
INSERT INTO `serie` VALUES (15,5,1,50,10),(16,5,1,55,10),(17,5,1,55,9),(18,5,1,60,8),(19,5,1,65,7),(20,6,2,50,10),(21,6,2,50,10),(22,6,2,55,10),(23,6,2,55,10),(24,6,2,60,10),(25,7,3,60,20),(26,7,3,70,15),(27,7,3,70,15),(28,7,3,80,10),(29,7,3,80,10),(30,8,4,25,12),(31,8,4,30,10),(32,8,4,40,10),(33,8,4,45,8),(34,8,4,45,8),(35,9,1,30,12),(36,9,1,35,10),(37,9,1,40,10),(38,9,2,50,10),(39,9,2,50,10),(40,9,3,60,15),(41,9,3,65,13),(42,9,3,70,10),(43,9,4,25,10),(44,9,4,30,10),(45,9,4,40,8),(46,7,7,0,0),(47,10,7,4,30),(48,10,7,6,15),(49,10,11,10,30),(50,10,11,20,60),(52,11,15,10,15),(53,11,15,20,30),(54,11,15,30,60),(55,12,16,15,10),(56,12,16,20,20),(57,12,16,30,60),(58,12,8,10,15),(59,12,8,20,20),(60,12,8,30,45),(62,13,12,30,15),(63,13,12,60,30),(64,13,12,15,10),(65,13,17,30,15),(66,13,17,60,45),(67,13,17,15,20);
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` VALUES (5,'Pecho'),(6,'Espalda'),(7,'Pierna'),(8,'Brazo'),(9,'FullBody'),(10,'Cardio'),(11,'Equilibrio'),(12,'Movilidad'),(13,'Potencia');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_ejercicio`
--

LOCK TABLES `tipo_ejercicio` WRITE;
/*!40000 ALTER TABLE `tipo_ejercicio` DISABLE KEYS */;
INSERT INTO `tipo_ejercicio` VALUES (1,'Fuerza/Resistencia','Peso (kg)','Repeticiones'),(2,'Capacidad aerobica','Distancia (m)','Duración (seg)'),(3,'Velocidad/Potencia','Duración (seg)','Descanso (seg)'),(4,'Estabilidad','Repeticiones','Descanso (seg)'),(5,'Movilidad','Repeticiones','Descanso (seg)');
/*!40000 ALTER TABLE `tipo_ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'25915268B',1,'David','Garcia Sanchez',1,19,1.9,87,'david','david@gmail.com'),(3,'75249108B',2,'Alvaro','Pérez Navarro',1,20,1.67,70,'alvaro','alvaro@gmail.com'),(4,'34345612H',3,'María','Cruz Martínez',0,18,1.54,60,'maria','maria@gmail.com'),(5,'68193415I',4,'Irene','Iglesias Gutierrez',0,24,1.5,54,'irene','irene@gmail.com'),(10,'123',5,'Pepe','Gomex',1,35,1.8,90,'pepe','pepe@gmail.com'),(11,'123',5,'Pepa','Gomex',0,30,1.6,60,'pepa','pepa@gmail.com'),(12,'12345678A',5,'Mikel','Arias',1,20,1.7,80,'kirito','mikel@gmail.com');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'fitpro'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-23 23:21:27
