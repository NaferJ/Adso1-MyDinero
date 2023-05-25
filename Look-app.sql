CREATE DATABASE  IF NOT EXISTS `lookapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lookapp`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lookapp
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `bienes_servicios`
--

DROP TABLE IF EXISTS `bienes_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bienes_servicios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` smallint DEFAULT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `id_entidad` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK966b2offokr3xtgxiypulvwvk` (`id_entidad`),
  CONSTRAINT `FK966b2offokr3xtgxiypulvwvk` FOREIGN KEY (`id_entidad`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bienes_servicios`
--

LOCK TABLES `bienes_servicios` WRITE;
/*!40000 ALTER TABLE `bienes_servicios` DISABLE KEYS */;
/*!40000 ALTER TABLE `bienes_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidad`
--

DROP TABLE IF EXISTS `entidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entidad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(100) DEFAULT NULL,
  `codigo_zip` varchar(50) DEFAULT NULL,
  `contact_email` varchar(45) NOT NULL,
  `contact_nombre` varchar(45) NOT NULL,
  `contact_telefono` varchar(45) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `direccion` varchar(50) NOT NULL,
  `estado` smallint DEFAULT NULL,
  `logo_nombre` varchar(255) NOT NULL,
  `nit` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `telefono` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidad`
--

LOCK TABLES `entidad` WRITE;
/*!40000 ALTER TABLE `entidad` DISABLE KEYS */;
INSERT INTO `entidad` VALUES (1,'x','x','x','x','x','x','x',1,'24cd6d4c-717e-42a7-b6b6-c3220bf4f295_WIN_20211102_20_38_19_Pro.jpg','x','x','x','x');
/*!40000 ALTER TABLE `entidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchangerate`
--

DROP TABLE IF EXISTS `exchangerate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exchangerate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `origin_currency` varchar(45) NOT NULL,
  `final_currency` varchar(45) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `tasa` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchangerate`
--

LOCK TABLES `exchangerate` WRITE;
/*!40000 ALTER TABLE `exchangerate` DISABLE KEYS */;
/*!40000 ALTER TABLE `exchangerate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'demo'),(2,'admin'),(3,'José');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parametro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `abrev` varchar(45) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `valor` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro`
--

LOCK TABLES `parametro` WRITE;
/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilegios`
--

DROP TABLE IF EXISTS `privilegios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privilegios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilegios`
--

LOCK TABLES `privilegios` WRITE;
/*!40000 ALTER TABLE `privilegios` DISABLE KEYS */;
INSERT INTO `privilegios` VALUES (1,'1');
/*!40000 ALTER TABLE `privilegios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilegios_roles`
--

DROP TABLE IF EXISTS `privilegios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privilegios_roles` (
  `role_id` bigint NOT NULL,
  `privilegio_id` bigint NOT NULL,
  PRIMARY KEY (`privilegio_id`,`role_id`),
  KEY `FK3buvsk92xb2wk76pw1gvjxjhg` (`role_id`),
  CONSTRAINT `FK3buvsk92xb2wk76pw1gvjxjhg` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKcugs6dljgajqlncdu1dq0b5wr` FOREIGN KEY (`privilegio_id`) REFERENCES `privilegios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilegios_roles`
--

LOCK TABLES `privilegios_roles` WRITE;
/*!40000 ALTER TABLE `privilegios_roles` DISABLE KEYS */;
INSERT INTO `privilegios_roles` VALUES (1,1);
/*!40000 ALTER TABLE `privilegios_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  `codigo_bar` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` smallint DEFAULT NULL,
  `img_producto` varchar(255) DEFAULT NULL,
  `no_factura` varchar(50) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `precio_compra` double NOT NULL,
  `precio_venta` double NOT NULL,
  `stock_min` int DEFAULT NULL,
  `ubicacion` varchar(45) DEFAULT NULL,
  `utilidad` int NOT NULL,
  `utilidad_esperada` int NOT NULL,
  `id_entidad` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK624oghrg5lmlcfqwxe5rrmo0n` (`id_entidad`),
  CONSTRAINT `FK624oghrg5lmlcfqwxe5rrmo0n` FOREIGN KEY (`id_entidad`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_proveedores`
--

DROP TABLE IF EXISTS `productos_proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_proveedores` (
  `productos_id` bigint NOT NULL,
  `proovedores_id` bigint NOT NULL,
  KEY `FKna4tx12qkopsk671gpdvtel85` (`proovedores_id`),
  KEY `FKf4eb0wqidqnmfqk0nt4lib7eu` (`productos_id`),
  CONSTRAINT `FKf4eb0wqidqnmfqk0nt4lib7eu` FOREIGN KEY (`productos_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKna4tx12qkopsk671gpdvtel85` FOREIGN KEY (`proovedores_id`) REFERENCES `proveedores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_proveedores`
--

LOCK TABLES `productos_proveedores` WRITE;
/*!40000 ALTER TABLE `productos_proveedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos_proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_unidad_medida`
--

DROP TABLE IF EXISTS `productos_unidad_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_unidad_medida` (
  `productos_id` bigint NOT NULL,
  `unidad_medida_id` bigint NOT NULL,
  KEY `FKkps990ujisdfsg9gqg144jdq1` (`unidad_medida_id`),
  KEY `FK4h8vxtyu94lbvqrmsltqnnui` (`productos_id`),
  CONSTRAINT `FK4h8vxtyu94lbvqrmsltqnnui` FOREIGN KEY (`productos_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKkps990ujisdfsg9gqg144jdq1` FOREIGN KEY (`unidad_medida_id`) REFERENCES `unidad_medida` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_unidad_medida`
--

LOCK TABLES `productos_unidad_medida` WRITE;
/*!40000 ALTER TABLE `productos_unidad_medida` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos_unidad_medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `estado` smallint DEFAULT NULL,
  `nit` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'758 Oakleaf Plantation pkwy 1324','recantilloc@gmail.com',1,'12345','RICARDO ENRIQUE CANTILLO CARRILLO','9046608721');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puestos_trabajo`
--

DROP TABLE IF EXISTS `puestos_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puestos_trabajo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(150) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `utilidad` int DEFAULT NULL,
  `id_entidad` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqg3k0trne9nuxljkkn3m1dt0r` (`id_entidad`),
  CONSTRAINT `FKqg3k0trne9nuxljkkn3m1dt0r` FOREIGN KEY (`id_entidad`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puestos_trabajo`
--

LOCK TABLES `puestos_trabajo` WRITE;
/*!40000 ALTER TABLE `puestos_trabajo` DISABLE KEYS */;
INSERT INTO `puestos_trabajo` VALUES (1,'Trabajo de Peluquería ',_binary '','Silla 1',10,1);
/*!40000 ALTER TABLE `puestos_trabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ldv0v52e0udsh2h1rs0r0gw1n` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salidas`
--

DROP TABLE IF EXISTS `salidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salidas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `calificacion` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `tipo_pago` varchar(8) DEFAULT NULL,
  `id_entidad` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK838ixum0ic3c6f70thu14c7sl` (`id_entidad`),
  CONSTRAINT `FK838ixum0ic3c6f70thu14c7sl` FOREIGN KEY (`id_entidad`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salidas`
--

LOCK TABLES `salidas` WRITE;
/*!40000 ALTER TABLE `salidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `salidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salidas_detalle`
--

DROP TABLE IF EXISTS `salidas_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salidas_detalle` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dcto` varchar(45) DEFAULT NULL,
  `valor_pagado` double DEFAULT NULL,
  `id_bien_servicio` bigint NOT NULL,
  `id_puesto_trabajo` bigint NOT NULL,
  `id_salida` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp4aqlgqq2m1xupwyuuyuplay3` (`id_bien_servicio`),
  KEY `FKeuaoqvw4syoj5evodguqr02p8` (`id_puesto_trabajo`),
  KEY `FK8pvrn7388xq5bvosegf9aiq3j` (`id_salida`),
  CONSTRAINT `FK8pvrn7388xq5bvosegf9aiq3j` FOREIGN KEY (`id_salida`) REFERENCES `salidas` (`id`),
  CONSTRAINT `FKeuaoqvw4syoj5evodguqr02p8` FOREIGN KEY (`id_puesto_trabajo`) REFERENCES `puestos_trabajo` (`id`),
  CONSTRAINT `FKp4aqlgqq2m1xupwyuuyuplay3` FOREIGN KEY (`id_bien_servicio`) REFERENCES `bienes_servicios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salidas_detalle`
--

LOCK TABLES `salidas_detalle` WRITE;
/*!40000 ALTER TABLE `salidas_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `salidas_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorials`
--

DROP TABLE IF EXISTS `tutorials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutorials` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorials`
--

LOCK TABLES `tutorials` WRITE;
/*!40000 ALTER TABLE `tutorials` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_medida`
--

DROP TABLE IF EXISTS `unidad_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidad_medida` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_medida`
--

LOCK TABLES `unidad_medida` WRITE;
/*!40000 ALTER TABLE `unidad_medida` DISABLE KEYS */;
INSERT INTO `unidad_medida` VALUES (1,'Metros',_binary '','mts'),(2,'Kilogramos',_binary '','Kg'),(3,'Centrígrado',_binary '','C'),(4,'Corte de uñas',_binary '\0','Unidad'),(5,'Kilogramo',_binary '','Kg');
/*!40000 ALTER TABLE `unidad_medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `direccion` varchar(300) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `identificacion` varchar(45) NOT NULL,
  `imagen` varchar(300) NOT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `id_entidad` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`username`),
  KEY `FKqu4qdd94ou0ge8opq8c6lveb1` (`id_entidad`),
  CONSTRAINT `FKqu4qdd94ou0ge8opq8c6lveb1` FOREIGN KEY (`id_entidad`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','admin','admin@admin.com',_binary '',NULL,'12345','admin.jpg',NULL,'$2a$10$IxtUX1tnXYKXeqZ/aMwA5O14mN8XRIdtT.jpT5WvbwHk/AoxBSZe2',NULL,'admin',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`usuario_id`),
  KEY `FKqcxu02bqipxpr7cjyj9dmhwec` (`usuario_id`),
  CONSTRAINT `FKihom0uklpkfpffipxpoyf7b74` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_roles`
--

LOCK TABLES `usuarios_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_roles` DISABLE KEYS */;
INSERT INTO `usuarios_roles` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-16  9:24:31
