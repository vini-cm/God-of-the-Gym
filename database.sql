-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: godofthegym
-- ------------------------------------------------------
-- Server version	8.4.4

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
-- Table structure for table `atendentes`
--

DROP TABLE IF EXISTS `atendentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atendentes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(14) NOT NULL,
  `salario` varchar(10) DEFAULT NULL,
  `entrada` time DEFAULT NULL,
  `saida` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cpf` (`cpf`),
  CONSTRAINT `atendentes_ibfk_1` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`),
  CONSTRAINT `atendentes_ibfk_2` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atendentes`
--

LOCK TABLES `atendentes` WRITE;
/*!40000 ALTER TABLE `atendentes` DISABLE KEYS */;
INSERT INTO `atendentes` VALUES (1,'03274063053','1789.00','13:30:00','16:30:00'),(2,'50333387902','2500.00','12:10:00','21:10:00');
/*!40000 ALTER TABLE `atendentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aulas`
--

DROP TABLE IF EXISTS `aulas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aulas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(45) NOT NULL,
  `comeco` time DEFAULT NULL,
  `fim` time DEFAULT NULL,
  `Descricao` varchar(500) NOT NULL,
  `Vagas` int NOT NULL,
  `cpf_professor` varchar(11) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cpf_professor` (`cpf_professor`),
  CONSTRAINT `cpf_professor` FOREIGN KEY (`cpf_professor`) REFERENCES `instrutores` (`cpf`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aulas`
--

LOCK TABLES `aulas` WRITE;
/*!40000 ALTER TABLE `aulas` DISABLE KEYS */;
INSERT INTO `aulas` VALUES (3,'Pilates','09:30:00','10:30:00','PILATES REALIZADO NO CHÃO COM APENAS O PESO DO CORPO',15,'12345678910','Pilates de Solo','2025-11-25'),(5,'Dança','09:30:00','10:30:00','CARDIO DE ZUMBA AO SOM DE FUNK ANOS DOIS MIL',25,'61752432185','Zumba Funk','2025-11-07'),(6,'Spinning','16:00:00','17:30:00','SPINNING HIT (TEMPO INTERVALADO DE ALTA INTENSIDADE)',12,'27678717140','Spinning HIT','2025-11-08');
/*!40000 ALTER TABLE `aulas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(14) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `porcentagem_gordura` int DEFAULT NULL,
  `imc` float DEFAULT NULL,
  `experiencia` varchar(40) DEFAULT NULL,
  `medicamentos` varchar(250) DEFAULT NULL,
  `limitacoes` varchar(300) DEFAULT NULL,
  `id_plano` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cpf` (`cpf`),
  KEY `clientes_ibfk_4_idx` (`id_plano`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`),
  CONSTRAINT `clientes_ibfk_5` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`),
  CONSTRAINT `id_plano` FOREIGN KEY (`id_plano`) REFERENCES `planos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (5,'82568340123',89,1.98,67,22.7018,'Intermediario',' ',' ',2);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instrutores`
--

DROP TABLE IF EXISTS `instrutores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instrutores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `salario` varchar(10) DEFAULT NULL,
  `formacao` varchar(250) DEFAULT NULL,
  `entrada` time DEFAULT NULL,
  `saida` time DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`),
  CONSTRAINT `instrutores_ibfk_1` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrutores`
--

LOCK TABLES `instrutores` WRITE;
/*!40000 ALTER TABLE `instrutores` DISABLE KEYS */;
INSERT INTO `instrutores` VALUES (2,'2.500,00','EDUCAÇÃO FISICA - FURB','12:00:00','13:00:00','12345678910'),(5,'6500.00','EDUCAÇÃO FISICA \nNUTRIÇÃO \nFISIOTERAPIA','12:30:00','17:30:00','61752432185'),(6,'5000.00','EDUCAÇÃO FISICA','07:30:00','15:30:00','27678717140');
/*!40000 ALTER TABLE `instrutores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planos`
--

DROP TABLE IF EXISTS `planos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `preco` varchar(10) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planos`
--

LOCK TABLES `planos` WRITE;
/*!40000 ALTER TABLE `planos` DISABLE KEYS */;
INSERT INTO `planos` VALUES (1,'Bimestral','450','Gold'),(2,'Bimestral','369','Silver');
/*!40000 ALTER TABLE `planos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `sobrenome` varchar(45) NOT NULL,
  `datanascimento` date NOT NULL,
  `email` varchar(60) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE KEY `CPF_UNIQUE` (`cpf`),
  UNIQUE KEY `Email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('03274063053','Bruno','Mares','1999-03-12','mars@gmail.com','f','f38fef4c0e4988792723c29a0bd3ca98','atendente','49984784224'),('12345678910','Stefanie','Miller','1990-10-31','ster1990@gmail.com','f','af21d0c97db2e27e13572cbf59eb343d','instrutor','4798134678'),('15004161933','Camila','Schmidt','2006-11-05','camila@gmail.com','f','ea5a486c712a91e48443cd802642223d','administrador','47998430987'),('27678717140','Esther','Olivia Sales','1976-02-20','esther-sales82@gmail.com','f','1caa8139a8bb1cbf7f2e47aace444ba4','instrutor','3138567881'),('50333387902','Gabriel Daniel','Alves','2004-10-14','gabriel_daniel_alves@gmail.com','m','81dc9bdb52d04dc20036dbd8313ed055','atendente','47937293098'),('57053394964','Isis Jaqueline','Ana Ferreira','1966-11-07','isis_ferreira@dglnet.com.br','f','019f86efa622705318dc5b5d0c616dbe','cliente','47997304511'),('61752432185','Gabrielly ','Fernanda','1955-08-16','gabrielly_viaan@gmail.com','f','e9154328fcab4d0b8ac12461b2dd91a8','instrutor','2127478876'),('82568340123','Jasmin','Silva','2001-11-11','jasminSilva@gmail.com','f','75f34b5502bec3c609734fdf4d37fa5c','cliente','1234798654');
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

-- Dump completed on 2025-11-19 10:17:09
