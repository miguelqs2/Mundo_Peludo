-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: mundo_peludo
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carrito_items`
--

DROP TABLE IF EXISTS `carrito_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carrito_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `pedido_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfaxp2h71mb51yhv0atg107irl` (`producto_id`),
  KEY `FKpc6urun1ach0hq3jdvc18moh8` (`pedido_id`),
  CONSTRAINT `FKfaxp2h71mb51yhv0atg107irl` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKpc6urun1ach0hq3jdvc18moh8` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito_items`
--

LOCK TABLES `carrito_items` WRITE;
/*!40000 ALTER TABLE `carrito_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrito_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (5,'Alimentos'),(6,'Juguetes'),(7,'Accesorios'),(8,'Higiene');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (9,'Preparando','2026-11-07 02:00:17.000000',67.915),(10,'Preparando','2026-11-07 02:26:15.000000',90),(11,'Preparando','2026-11-07 03:15:12.000000',76.5);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen_url` varchar(1000) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2fwq10nwymfv7fumctxt9vpgb` (`categoria_id`),
  CONSTRAINT `FK2fwq10nwymfv7fumctxt9vpgb` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (14,'','https://media.falabella.com/falabellaPE/155244190_01/w=1500,h=1500,fit=cover','Ricocan Sabor Cordero Cereales Razas Pequeñas Adultos 15kg',90,5,5),(15,'','https://media.falabella.com/falabellaPE/156205820_01/w=1500,h=1500,fit=cover','Canbo Súper Premium Cachorro Cordero Razas Medianas y Grandes 15 Kg',200,5,5),(16,'','https://media.falabella.com/sodimacPE/428321X_1/w=1500,h=1500,fit=cover','Dog Chow Alimento Seco para Perro Cachorro Sabor Carne, Pollo y Leche 15 kg',110,5,5),(17,'','https://media.falabella.com/sodimacPE/4204840_01/w=1500,h=1500,fit=cover','Ricocan Alimento Seco para Perro Cachorro Carne y Leche 15kg',90.9,5,5),(18,'','https://media.falabella.com/falabellaPE/155242675_01/w=1500,h=1500,fit=cover','Ricocat Sabor Pollo Sardina Salmón Adultos 9kg',60,5,5),(19,'','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRi53JCLXdtaUlsGfx6UTW6QIHE_LMo1uIUwvYQgv1_OWoAoLMujPoHfAST&s=10','champo beaphar',15,5,8),(20,'','https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSOgfXTSDJ8m7D-JN0IB4X40h7FS_tQSySKh9elL2M1g599uKEd','Virbac C.E.T. Pasta Dental Enzimática',40,6,8),(23,'','https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcRuivWjYpNVTPE4JLc7b35lBbz746s-40mjWpYxEIt-9j8eVgx3IBXd6qhqu1vB_cL5L2ICkiGL_f7lOHPEnFztufc650K-Et-gaiS4_RbCp_IKoRdrDvvjKc59vc3vYoEfvY3bjA&usqp=CAc','Tkpet Arena Con Carbón Activado',80,5,8),(26,'','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2qKxYNuY4NQmHi8oAq1fpfKaUnZZ1sr7hUoqH25jYKg&s','juguete dispensador de comida',32,5,6),(27,'','https://oechsle.vteximg.com.br/arquivos/ids/19291395-1000-1000/image.jpg?v=638653622988600000','Pelota Interactiva para Gato Juguete Inteligente Azul',20,5,6),(28,'','https://media.falabella.com/falabellaPE/137515334_01/w=1500,h=1500,fit=cover','ARNÉS COLLAR Y CORREA DE JEAN Y AZUL PARA PERRO TALLA XL',50,5,7),(29,'','https://mascotas365.vtexassets.com/arquivos/ids/155974-1200-auto?v=639234011660870000&width=1200&height=auto&aspect=true','Ferplast Correa Retráctil para Perros',30,6,7),(30,'','https://media.falabella.com/falabellaPE/116366502_01/w=1500,h=1500,fit=cover','Arnes para gatos',20,6,7),(31,'','https://dojiw2m9tvv09.cloudfront.net/32548/product/kongclassicl3566.png','kong classic',60,6,6);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `descuento_aplicado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'gisselle2@gmail.com','gisselle','$2a$10$EybF7BIFLImnVEUZGl8zxu.eOo34zOQ6aaGhf4K1atnyIKQcmDSqy','ROLE_ADMIN',''),(3,'gisselle@gmail.com','gisselle','$2a$10$ZFF7Tcjd3mLHORJk43MQzeSck5BzoURHN6jmLs1cLyZyT43RzEnkq','ROLE_CLIENTE',''),(4,'miguel@gmail.com','Miguel','$2a$10$E2yoLo2ce3SXH3cY9FcP7uvqkETlj7BRX8xzgVsjOdsTDAeFk9beW','ROLE_CLIENTE','');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-06 23:09:26
