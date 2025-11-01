-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: godofthegym
-- ------------------------------------------------------
-- Server version	8.0.43

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
  `cpf` varchar(11) NOT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  `entrada` time DEFAULT NULL,
  `saida` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cpf` (`cpf`),
  CONSTRAINT `atendentes_ibfk_1` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`),
  CONSTRAINT `atendentes_ibfk_2` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atendentes`
--

LOCK TABLES `atendentes` WRITE;
/*!40000 ALTER TABLE `atendentes` DISABLE KEYS */;
INSERT INTO `atendentes` VALUES (1,'03274063053',1789.00,'13:30:00','16:30:00'),(2,'50333387902',3456.00,'12:10:00','21:10:00');
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
  `Data` datetime NOT NULL,
  `Descricao` varchar(500) NOT NULL,
  `Vagas` int NOT NULL,
  `Professor` int DEFAULT NULL,
  `Id_Professor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Id_Professor` (`Id_Professor`),
  KEY `Professor` (`Professor`),
  CONSTRAINT `aulas_ibfk_1` FOREIGN KEY (`Professor`) REFERENCES `instrutores` (`id`),
  CONSTRAINT `aulas_ibfk_2` FOREIGN KEY (`Id_Professor`) REFERENCES `instrutores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aulas`
--

LOCK TABLES `aulas` WRITE;
/*!40000 ALTER TABLE `aulas` DISABLE KEYS */;
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
  `cpf` varchar(11) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `porcentagem_gordura` int DEFAULT NULL,
  `imc` float DEFAULT NULL,
  `experiencia` varchar(40) DEFAULT NULL,
  `medicamentos` varchar(250) DEFAULT NULL,
  `limitacoes` varchar(300) DEFAULT NULL,
  `id_plano` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cpf` (`cpf`),
  KEY `id_plano` (`id_plano`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`),
  CONSTRAINT `clientes_ibfk_4` FOREIGN KEY (`id_plano`) REFERENCES `planos` (`nome`),
  CONSTRAINT `clientes_ibfk_5` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (3,'80212571990',78,1.78,15,24.6181,'Intermediario',' ',' ','Gold');
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
  `salario` decimal(10,2) DEFAULT NULL,
  `formacao` varchar(250) DEFAULT NULL,
  `entrada` time DEFAULT NULL,
  `saida` time DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cpf` (`cpf`),
  CONSTRAINT `instrutores_ibfk_1` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`),
  CONSTRAINT `instrutores_ibfk_2` FOREIGN KEY (`cpf`) REFERENCES `usuarios` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrutores`
--

LOCK TABLES `instrutores` WRITE;
/*!40000 ALTER TABLE `instrutores` DISABLE KEYS */;
INSERT INTO `instrutores` VALUES (2,1990.00,'EDUCAÇÃO FISICA - FURB','12:00:00','13:00:00','12345678910'),(4,1690.00,'EDUCAÇÃO FISICA ','11:30:00','15:30:00','44522009909');
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
  `preco` float NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planos`
--

LOCK TABLES `planos` WRITE;
/*!40000 ALTER TABLE `planos` DISABLE KEYS */;
INSERT INTO `planos` VALUES (1,'Bimestral',450,'Gold');
/*!40000 ALTER TABLE `planos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `cpf` varchar(11) NOT NULL,
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
INSERT INTO `usuarios` VALUES ('03274063053','Bruno','Mars','1999-03-12','mars@gmail.com','f','f38fef4c0e4988792723c29a0bd3ca98','atendente','49984784224'),('12345678910','Stefanie','Miller','1990-10-31','ster1990@gmail.com','f','af21d0c97db2e27e13572cbf59eb343d','instrutor','4798134678'),('15004161933','Camila','Schmidt','2006-11-05','camila@gmail.com','f','ea5a486c712a91e48443cd802642223d','administrador','47998430987'),('44522009909','Joaquim Geraldo','Emanuel da Rosa','1988-05-23','joaquim_geraldo-rosa@gmail.com','m','827ccb0eea8a706c4c34a16891f84e7b','instrutor','4838752710'),('50333387902','Gabriel Daniel','Alves','2004-10-14','gabriel_daniel_alves@gmail.com','m','81dc9bdb52d04dc20036dbd8313ed055','atendente','47937293098'),('80212571990','Sueli Vanessa ','Lara Fogaça','1976-07-13','sueli_fogaca@hotmail.com','f','827ccb0eea8a706c4c34a16891f84e7b','cliente','47927627722');
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

-- Dump completed on 2025-11-01 19:17:27
