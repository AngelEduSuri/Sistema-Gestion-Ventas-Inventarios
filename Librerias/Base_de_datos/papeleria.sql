-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: papeleria
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- Table structure for table `detalle`
--

DROP TABLE IF EXISTS `detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle` (
  `idDetalleVenta` int(11) NOT NULL AUTO_INCREMENT,
  `ventasIdVentas` int(11) NOT NULL,
  `producIdProduc` int(11) NOT NULL,
  `nombreProd` varchar(50) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(4,2) NOT NULL,
  `total` decimal(4,2) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  PRIMARY KEY (`idDetalleVenta`),
  KEY `ventasIdVentas_idx` (`ventasIdVentas`),
  KEY `producIdProduc_idx` (`producIdProduc`),
  CONSTRAINT `producIdProduc` FOREIGN KEY (`producIdProduc`) REFERENCES `productos` (`idproductos`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ventasIdVentas` FOREIGN KEY (`ventasIdVentas`) REFERENCES `ventas` (`idventas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle`
--

LOCK TABLES `detalle` WRITE;
/*!40000 ALTER TABLE `detalle` DISABLE KEYS */;
INSERT INTO `detalle` VALUES (161,64,15,'Juego de marcadores norma 12 colores',2,3.55,7.10,'2021-01-22'),(163,64,17,'Borrador pequeño',2,0.15,0.30,'2021-01-22'),(164,65,20,'Marcador permanete negro pelikan',3,0.55,1.65,'2021-01-23'),(165,65,19,'Marcador permanente rojo pelikan',3,0.55,1.65,'2021-01-23'),(166,65,18,'Marcador permanete azul pelikan',4,0.55,2.20,'2021-01-23'),(167,66,12,'Cuaderno pequeño espiral de cuadros 100 hojas',2,0.85,1.70,'2021-01-23'),(168,66,17,'Borrador pequeño',3,0.15,0.45,'2021-01-23'),(169,67,20,'Marcador permanete negro pelikan',2,0.55,1.10,'2021-01-25'),(170,67,19,'Marcador permanente rojo pelikan',3,0.55,1.65,'2021-01-25'),(171,68,11,'Fomix A4 color amarillo',3,0.45,1.35,'2021-01-26'),(172,68,12,'Cuaderno pequeño espiral de cuadros 100 hojas',2,0.85,1.70,'2021-01-26'),(173,69,1,'Lapicero bic punta fina',3,0.45,1.35,'2021-01-27'),(174,69,2,'Cuadernos de 100 hojas a cuadros',3,1.20,3.60,'2021-01-27'),(175,69,3,'Carpetas plasticas azul',2,0.80,1.60,'2021-01-27'),(176,70,7,'Paquete de hojas a cuadros ',3,0.75,2.25,'2021-01-28'),(178,70,10,'Fomix A4 color rojo',3,0.45,1.35,'2021-01-28'),(179,71,2,'Cuadernos pequeño de 100 hojas a cuadros',3,0.85,2.55,'2021-01-28'),(180,71,3,'Carpetas plasticas azul',2,0.80,1.60,'2021-01-28'),(181,71,1,'Lapicero bic punta fina',2,0.45,0.90,'2021-01-28'),(182,72,23,'Cuaderno academico 100 hojas a cuadros',2,1.35,2.70,'2021-01-28'),(183,72,1,'Lapicero bic punta fina',2,0.45,0.90,'2021-01-28'),(184,72,17,'Borrador pequeño',1,0.15,0.15,'2021-01-28'),(202,82,17,'Borrador pequeño',2,0.15,0.30,'2021-01-28'),(203,82,11,'Fomix A4 color amarillo',2,0.45,0.90,'2021-01-28'),(204,83,1,'Lapicero bic punta fina',2,0.45,0.90,'2021-01-28'),(205,84,1,'Lapicero bic punta fina',2,0.45,0.90,'2021-01-28'),(206,85,17,'Borrador pequeño',3,0.15,0.45,'2021-01-28'),(207,85,1,'Lapicero bic punta fina',1,0.45,0.45,'2021-01-28'),(208,86,1,'Lapicero bic punta fina',2,0.45,0.90,'2021-01-28'),(209,87,1,'Lapicero bic punta fina',3,0.45,1.35,'2021-01-28'),(210,88,1,'Lapicero bic punta fina',3,0.45,1.35,'2021-01-28'),(211,88,2,'Cuadernos pequeño de 100 hojas a cuadros',1,0.85,0.85,'2021-01-28'),(212,89,1,'Lapicero bic punta fina',7,0.45,3.15,'2021-01-28'),(215,91,1,'Lapicero bic punta fina',2,0.45,0.90,'2021-01-28'),(216,91,2,'Cuadernos pequeño de 100 hojas a cuadros',2,0.85,1.70,'2021-01-28'),(217,92,1,'Lapicero bic punta fina',1,0.45,0.45,'2021-01-28'),(218,92,2,'Cuadernos pequeño de 100 hojas a cuadros',1,0.85,0.85,'2021-01-28'),(219,93,6,'Cuadernos pequeño de 100 hojas de 2 lineas',1,0.85,0.85,'2021-01-28'),(220,93,17,'Borrador pequeño',2,0.15,0.30,'2021-01-28'),(221,93,15,'Juego de marcadores norma 12 colores',2,3.55,7.10,'2021-01-28'),(222,94,33,'Planca de fomix 100x100',2,1.75,3.50,'2021-01-30'),(223,94,17,'Borrador pequeño',2,0.15,0.30,'2021-01-30'),(224,94,1,'Lapicero bic punta fina',2,0.45,0.90,'2021-01-30'),(225,94,19,'Marcador permanente rojo pelikan',1,0.55,0.55,'2021-01-30'),(226,95,18,'Marcador permanete azul pelikan',2,0.55,1.10,'2021-01-30'),(227,95,26,'Marcador acrilico azul pelikan ',2,0.65,1.30,'2021-01-30'),(228,95,7,'Paquete de hojas a cuadros ',3,0.75,2.25,'2021-01-30'),(229,95,11,'Fomix A4 color amarillo',2,0.45,0.90,'2021-01-30'),(230,96,17,'Borrador pequeño',3,0.15,0.45,'2021-01-30'),(231,96,1,'Lapicero bic punta fina',3,0.45,1.35,'2021-01-30'),(232,96,12,'Cuaderno pequeño de 100 hojas de 1 linea',3,0.85,2.55,'2021-01-30'),(233,96,11,'Fomix A4 color amarillo',2,0.45,0.90,'2021-01-30');
/*!40000 ALTER TABLE `detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idproductos` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_pro` varchar(45) NOT NULL,
  `precio` decimal(18,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idproductos`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Lapicero bic punta fina rojo',0.35,20),(2,'Cuadernos pequeño de 100 hojas a cuadros',0.85,25),(3,'Carpetas plasticas azul',0.80,17),(6,'Cuadernos pequeño de 100 hojas de 2 lineas',0.85,49),(7,'Paquete de hojas a cuadros ',0.75,27),(10,'Fomix A4 color rojo',0.45,30),(11,'Fomix A4 color amarillo',0.45,25),(12,'Cuaderno pequeño de 100 hojas de 1 linea',0.85,27),(13,'Juego de marcadores pelican 12 colores',1.55,35),(15,'Juego de marcadores norma 12 colores',3.55,28),(17,'Borrador pequeño',0.15,6),(18,'Marcador permanete azul pelikan',0.55,22),(19,'Marcador permanente rojo pelikan',0.55,23),(20,'Marcador permanete negro pelikan',0.55,15),(21,'Carpetas de carton amarillas',0.55,25),(22,'Plancha plumafon 50x50',0.95,28),(23,'Cuaderno academico 100 hojas a cuadros',1.35,51),(24,'Cuaderno academico 100 hojas de 2 lineas',1.25,30),(25,'Cuaderno academico 100 hojas de 1 linea',1.25,30),(26,'Marcador acrilico azul pelikan ',0.65,18),(27,'Marcador acrilico rojo pelikan ',0.65,25),(28,'Marcador acrilico negro pelikan ',0.65,30),(29,'Cuaderno cosido cuadros',1.25,14),(30,'Cuaderno cosido 2 lineas',1.25,15),(31,'Cartulina A4 Amarilla',0.15,40),(32,'Papelote a cuadros',0.25,55),(33,'Plancha de plumafon100x100',1.75,20),(34,'Lapicero bic punta fina negro',0.35,20),(35,'Lapicero bic punta fina azul',0.35,20),(36,'Lapicero bic punta gruesa negro',0.30,20),(37,'Lapicero bic punta gruesa azul',0.30,20),(38,'Lapicero bic punta gruesa rojo',0.30,20),(39,'Fomix A4 color verde',0.45,25),(40,'Fomix A4 color azul',0.45,30),(41,'Fomix A4 color negro',0.45,30),(42,'Fomix A4 color rosado',0.45,30),(43,'Carpetas plasticas verde',0.80,17),(44,'Carpetas plasticas negra',0.80,17),(45,'Carpetas plasticas amarilla',0.80,17),(46,'Carpetas plasticas celeste',0.80,17);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `cedula_prov` varchar(45) NOT NULL,
  `nombre_prov` varchar(45) NOT NULL,
  `telefono_prov` varchar(45) NOT NULL,
  `productos_prov` varchar(45) NOT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (3,'0706479631','Distribuidora Joes','0994523548','Articulos de oficina'),(4,'0706479453','MariKaren ','0993456789','Libros y Pinturas'),(5,'1101587461','Proveedora ONEROM','(02)2548210','Tienda de material de oficina'),(6,'0945615492499','Distribuidora Comsucre','(04)2516315','Distribuidor de papel');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendedor` (
  `idvendedor` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  PRIMARY KEY (`idvendedor`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,'vendedor01','0706479331','Angel Salazar','0984330045'),(2,'vendedor03','1026479735','Hernan Zarate','0998843383'),(5,'vendedor05','0705479636','Angel Eduardo Suriaga Salazar','0994658120'),(6,'vendedor02','1209469631','Erick Fabricio','0993300954'),(7,'vendedor04','0703456122','Oswaldo Aguilar','0994234551'),(8,'vendedor06','1502030998','Karla Cristina Minga','0993465612'),(9,'vendedor07','0706479634','Añadiendo Vendedor','0994567893'),(10,'administrador','1234567890','Dueño del Negocio','0994561259');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `idventas` int(11) NOT NULL AUTO_INCREMENT,
  `idvendedo` int(11) NOT NULL,
  `nombreCliente` varchar(45) NOT NULL,
  `numeroVenta` varchar(244) NOT NULL,
  `fechaVenta` varchar(45) NOT NULL,
  `monto` decimal(4,2) NOT NULL,
  PRIMARY KEY (`idventas`),
  KEY `fk_ventavendedor_idx` (`idvendedo`),
  CONSTRAINT `fk_ventavendedor` FOREIGN KEY (`idvendedo`) REFERENCES `vendedor` (`idvendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (64,1,'Luis Rojas','0000001','2021-01-22',7.90),(65,1,'','0000002','2021-01-23',5.50),(66,1,'','0000003','2021-01-23',2.15),(67,1,'Luis Moncada','0000004','2021-01-25',2.75),(68,1,'Ana Lopez','0000005','2021-01-26',5.80),(69,1,'Jandri Luna','0000006','2021-01-27',12.35),(70,1,'Kleber Gonzales','0000007','2021-01-28',4.65),(71,1,'Cliente 1','0000008','2021-01-28',5.05),(72,1,'','0000009','2021-01-28',3.75),(73,1,'Cliente de Prueba','0000010','2021-01-28',4.15),(82,1,'Cliente Prueba 2','0000011','2021-01-28',1.20),(83,1,'dsfkodsofdsf','0000012','2021-01-28',0.90),(84,1,'Cliente ada','0000013','2021-01-28',0.90),(85,1,'Cliente asd123','0000014','2021-01-28',0.90),(86,1,'Cliente 005','0000015','2021-01-28',0.90),(87,1,'Cliente 0004','0000016','2021-01-28',1.35),(88,1,'Cliente no jodas','0000017','2021-01-28',2.20),(89,1,'asds123','0000018','2021-01-28',3.15),(91,1,'Cliente ultimo','0000020','2021-01-28',2.60),(92,1,'Cliente ahora si ultimo','0000021','2021-01-28',1.30),(93,1,'Cliente Prueba','0000022','2021-01-28',8.25),(94,1,'Cliente de prueba2','0000023','2021-01-30',5.25),(95,10,'Cliente Regular','0000024','2021-01-30',5.55),(96,10,'Cliente de Prueba Final','0000025','2021-01-30',5.25);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-30 21:05:09
