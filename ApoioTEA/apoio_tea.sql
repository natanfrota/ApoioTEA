CREATE DATABASE  IF NOT EXISTS `apoio_tea` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `apoio_tea`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: apoio_tea
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `atividade`
--

DROP TABLE IF EXISTS `atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atividade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) DEFAULT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `descricao` text,
  `localizacao` varchar(255) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `voluntario_usuario_id` int DEFAULT NULL,
  `familia_usuario_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_atividade_voluntário1_idx` (`voluntario_usuario_id`),
  KEY `fk_atividade_familia1_idx` (`familia_usuario_id`),
  CONSTRAINT `fk_atividade_familia1` FOREIGN KEY (`familia_usuario_id`) REFERENCES `familia` (`usuario_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_atividade_voluntário1` FOREIGN KEY (`voluntario_usuario_id`) REFERENCES `voluntario` (`usuario_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atividade`
--

LOCK TABLES `atividade` WRITE;
/*!40000 ALTER TABLE `atividade` DISABLE KEYS */;
INSERT INTO `atividade` VALUES (2,'Ajuda em evento escolar','Educação','João terá uma apresentação musical na escola, e gostaríamos de um voluntário para nos ajudar com a logística e o suporte emocional dele durante o evento.','Escola Municipal','aberta','2024-12-06','08:00:00',NULL,1),(3,'Ajuda em atividade de pintura em casa','Educação','Lucas está explorando sua criatividade e ama atividades de pintura. Precisamos de um voluntário para ajudá-lo em uma tarde de pintura em casa. O objetivo é incentivá-lo a se expressar artisticamente e manter o ambiente organizado durante a atividade.','Bairro Lagoinha','aberta','2024-12-09','10:00:00',NULL,2);
/*!40000 ALTER TABLE `atividade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atividade_has_voluntario`
--

DROP TABLE IF EXISTS `atividade_has_voluntario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atividade_has_voluntario` (
  `atividade_id` int NOT NULL,
  `voluntario_usuario_id` int NOT NULL,
  PRIMARY KEY (`atividade_id`,`voluntario_usuario_id`),
  KEY `fk_atividade_has_voluntario_voluntario1_idx` (`voluntario_usuario_id`),
  KEY `fk_atividade_has_voluntario_atividade1_idx` (`atividade_id`),
  CONSTRAINT `fk_atividade_has_voluntario_atividade1` FOREIGN KEY (`atividade_id`) REFERENCES `atividade` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_atividade_has_voluntario_voluntario1` FOREIGN KEY (`voluntario_usuario_id`) REFERENCES `voluntario` (`usuario_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atividade_has_voluntario`
--

LOCK TABLES `atividade_has_voluntario` WRITE;
/*!40000 ALTER TABLE `atividade_has_voluntario` DISABLE KEYS */;
INSERT INTO `atividade_has_voluntario` VALUES (3,4);
/*!40000 ALTER TABLE `atividade_has_voluntario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nota` tinyint DEFAULT NULL,
  `comentario` text,
  `data` date DEFAULT (curdate()),
  `familia_usuario_id` int NOT NULL,
  `voluntario_usuario_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_avaliacao_familia1_idx` (`familia_usuario_id`),
  KEY `fk_avaliacao_voluntário1_idx` (`voluntario_usuario_id`),
  CONSTRAINT `fk_avaliacao_familia1` FOREIGN KEY (`familia_usuario_id`) REFERENCES `familia` (`usuario_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_avaliacao_voluntário1` FOREIGN KEY (`voluntario_usuario_id`) REFERENCES `voluntario` (`usuario_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` VALUES (1,5,'ótimo','2024-12-05',1,4);
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `atualiza_avaliacao_media` AFTER INSERT ON `avaliacao` FOR EACH ROW BEGIN
    DECLARE media DECIMAL(2,1);

    SELECT AVG(nota) INTO media
    FROM avaliacao
    WHERE voluntario_usuario_id = NEW.voluntario_usuario_id;

    UPDATE voluntario
    SET avaliacao_media = media
    WHERE usuario_id = NEW.voluntario_usuario_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `conversa`
--

DROP TABLE IF EXISTS `conversa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversa` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversa`
--

LOCK TABLES `conversa` WRITE;
/*!40000 ALTER TABLE `conversa` DISABLE KEYS */;
INSERT INTO `conversa` VALUES (1);
/*!40000 ALTER TABLE `conversa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familia`
--

DROP TABLE IF EXISTS `familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familia` (
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`),
  CONSTRAINT `fk_familia_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familia`
--

LOCK TABLES `familia` WRITE;
/*!40000 ALTER TABLE `familia` DISABLE KEYS */;
INSERT INTO `familia` VALUES (1),(2);
/*!40000 ALTER TABLE `familia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `conteudo` text,
  `dataHoraDeEnvio` datetime DEFAULT CURRENT_TIMESTAMP,
  `conversa_id` int NOT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mensagem_conversa1_idx` (`conversa_id`),
  KEY `fk_mensagem_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_mensagem_conversa1` FOREIGN KEY (`conversa_id`) REFERENCES `conversa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mensagem_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
INSERT INTO `mensagem` VALUES (1,'Olá, Antônio','2024-12-05 11:54:56',1,1),(2,'Olá, Ana','2024-12-05 11:55:04',1,4),(3,'Como está?','2024-12-05 11:55:11',1,4);
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moderador`
--

DROP TABLE IF EXISTS `moderador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moderador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moderador`
--

LOCK TABLES `moderador` WRITE;
/*!40000 ALTER TABLE `moderador` DISABLE KEYS */;
INSERT INTO `moderador` VALUES (1,'Eduardo Braga','eduardo@gmail.com','1234');
/*!40000 ALTER TABLE `moderador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificacao`
--

DROP TABLE IF EXISTS `notificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notificacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `data` date DEFAULT (curdate()),
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notificacao_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_notificacao_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacao`
--

LOCK TABLES `notificacao` WRITE;
/*!40000 ALTER TABLE `notificacao` DISABLE KEYS */;
INSERT INTO `notificacao` VALUES (1,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(2,'O(a) voluntário(a) Carlos Eduardo Santos cancelou a participação dele(a) na atividadeAcompanhamento a consulta médica','2024-12-05',1),(3,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(4,'O(a) voluntário(a) Antônio Eduardo Santos cancelou a participação dele(a) na atividadeAcompanhamento a consulta médica','2024-12-05',1),(5,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(6,'O(a) voluntário(a) Antônio Eduardo Santos cancelou a participação dele(a) na atividadeAcompanhamento a consulta médica','2024-12-05',1),(7,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(8,'O(a) voluntário(a) Antônio Eduardo Santos cancelou a participação dele(a) na atividadeAcompanhamento a consulta médica','2024-12-05',1),(9,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(10,'O(a) voluntário(a) Antônio Eduardo Santos cancelou a participação dele(a) na atividadeAcompanhamento a consulta médica','2024-12-05',1),(11,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(12,'Sua participação na atividade Acompanhamento a consulta médica foi cancelada.','2024-12-05',4),(13,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(14,'O(a) voluntário(a) Antônio Eduardo Santos cancelou a participação dele(a) na atividadeAcompanhamento a consulta médica','2024-12-05',1),(15,'Você foi aceito como voluntário na atividade Acompanhamento a consulta médica da família de Ana Silva','2024-12-05',4),(16,'Você recebeu uma nova avaliação.','2024-12-05',4);
/*!40000 ALTER TABLE `notificacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `data_cadastro` date DEFAULT (curdate()),
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `descricao` text,
  `status_conta` enum('ativa','suspensa','excluida') DEFAULT 'ativa',
  `tipo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Ana Silva','ana@gmail.com','1234',NULL,'2002-02-05','2024-12-05','Guanambi','Bahia','Somos pais do pequeno João, de 8 anos, diagnosticado com TEA. Ele adora música e passeios ao ar livre, mas temos dificuldades em conciliar nossos compromissos com o cuidado dele. Estamos em busca de voluntários que possam nos ajudar em momentos pontuais.','ativa','familia'),(2,'Felipe Oliveira','felipe@gmail.com','1234',NULL,'1998-06-02','2024-12-05','Guanambi','Bahia','Somos uma família dedicada ao bem-estar do nosso filho Lucas, de 6 anos, que foi diagnosticado com TEA. Ele adora brincar com blocos de montar e assistir desenhos animados. Estamos procurando voluntários para nos apoiar em situações específicas.','ativa','familia'),(3,'Ana Maria Rodrigues','anamaria@gmail.com','1234',NULL,'2003-10-05','2024-12-05','Guanambi','Bahia','Olá, meu nome é Ana Maria, sou estudante de Psicologia e apaixonada por ajudar famílias e crianças que precisam de apoio. Tenho experiência com atividades recreativas e sempre busco criar um ambiente acolhedor e inclusivo.','ativa','voluntario'),(4,'Antônio Eduardo Santos','antonio@gmail.com','1234',NULL,'2000-01-05','2024-12-05','Guanambi','Bahia','Sou Carlos Eduardo, formado em Educação Física, com foco em promover atividades que desenvolvam a coordenação motora e socialização. Gosto de trabalhar em equipe e acredito no poder do esporte e da brincadeira para transformar vidas.','ativa','voluntario');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_has_conversa`
--

DROP TABLE IF EXISTS `usuario_has_conversa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_has_conversa` (
  `usuario_id` int NOT NULL,
  `conversa_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`,`conversa_id`),
  KEY `fk_usuario_has_conversa_conversa1_idx` (`conversa_id`),
  KEY `fk_usuario_has_conversa_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_usuario_has_conversa_conversa1` FOREIGN KEY (`conversa_id`) REFERENCES `conversa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_has_conversa_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_conversa`
--

LOCK TABLES `usuario_has_conversa` WRITE;
/*!40000 ALTER TABLE `usuario_has_conversa` DISABLE KEYS */;
INSERT INTO `usuario_has_conversa` VALUES (1,1),(4,1);
/*!40000 ALTER TABLE `usuario_has_conversa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voluntario`
--

DROP TABLE IF EXISTS `voluntario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voluntario` (
  `experiencia` text,
  `habilidades` text,
  `avaliacao_media` decimal(2,1) DEFAULT NULL,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`),
  CONSTRAINT `fk_voluntário_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voluntario`
--

LOCK TABLES `voluntario` WRITE;
/*!40000 ALTER TABLE `voluntario` DISABLE KEYS */;
INSERT INTO `voluntario` VALUES ('Voluntária em eventos comunitários de inclusão social para crianças com necessidades especiais (2 anos).\r\nMonitora de recreação infantil em escolas públicas (1 ano).\r\nParticipação em workshops sobre desenvolvimento infantil e transtornos do neurodesenvolvimento.','Comunicação interpessoal.\r\nOrganização de atividades recreativas.\r\nConhecimento básico sobre TEA (Transtorno do Espectro Autista).',NULL,3),('Instrutor de esportes adaptados em uma ONG para crianças com TEA (3 anos).\r\nAcompanhante voluntário em passeios escolares para alunos com necessidades especiais (1 ano).\r\nCurso de capacitação sobre autismo e inclusão social.','Planejamento e condução de atividades físicas adaptadas.\nPaciência e empatia em situações desafiadoras.\nPrimeiros socorros básicos.',5.0,4);
/*!40000 ALTER TABLE `voluntario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'apoio_tea'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;