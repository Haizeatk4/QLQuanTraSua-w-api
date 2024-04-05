CREATE DATABASE  IF NOT EXISTS `qlquantrasua` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlquantrasua`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: qlquantrasua
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
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `STT` int NOT NULL AUTO_INCREMENT,
  `MaHD` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `MaDV` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `SoLuong` double DEFAULT NULL,
  `DonGia` int DEFAULT NULL,
  PRIMARY KEY (`STT`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (1,'HD001','NL001',1,8000),(2,'HD002','NL005',1,NULL),(3,'HD003','NL003',1,NULL),(4,'HD004','NL004',1,NULL),(5,'HD001','M002',1,9000);
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvu`
--

DROP TABLE IF EXISTS `dichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvu` (
  `MaDV` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `TenDV` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `SoLuong` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Gia` int DEFAULT NULL,
  `Anh` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`MaDV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu`
--

LOCK TABLES `dichvu` WRITE;
/*!40000 ALTER TABLE `dichvu` DISABLE KEYS */;
INSERT INTO `dichvu` VALUES ('M001','Sting vàng','50',9000,'/Image/Menu/stingVang.jpg'),('M002','Sting dâu','50',9000,'/Image/Menu/stingDo.jpg'),('M003','Trà sữa truyền thống','22',30000,'/Image/Menu/traSuaTruyenThong.jpg'),('M004','Trà sữa thái xanh','18',35000,'/Image/Menu/traSuaThaiXanh.jpg'),('M005','Hạt điều','55',10000,'/Image/Menu/hatDieu.jpg'),('M006','Hạt dưa','55',10000,'/Image/Menu/hatDua.jpg'),('M007','Cafe sữa','27',15000,'/Image/Menu/cafeSua.png'),('M008','Cafe đá sài gòn','23',14000,'/Image/Menu/cafeDaSaiGon.jpg'),('M009','Cafe bạc xỉu','23',15000,'/Image/Menu/cafeBacXiu.jpg'),('M010','Hạt dẻ','55',10000,'/Image/Menu/hatDe.jpg'),('M011','Pizza xúc xích','20',50000,'/Image/Menu/pizzaXucXich.jpg');
/*!40000 ALTER TABLE `dichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `MaNhanVien` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Ngay` date NOT NULL,
  `MaBan` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `ThanhTien` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TinhTrang` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaHD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('HD001','NV001','2024-04-02','B001','0','Chưa thanh toán'),('HD002','NV001','2024-04-02','B001','0','Chưa thanh toán'),('HD003','NV002','2024-03-12','B005','0','Đã thanh toán'),('HD004','NV003','2024-03-11','B003','0','Đã thanh toán');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguyenlieu`
--

DROP TABLE IF EXISTS `nguyenlieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguyenlieu` (
  `MaDV` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `TenDV` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NgayNhap` date DEFAULT NULL,
  `SoLuong` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `DvTinh` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Gia` int DEFAULT NULL,
  PRIMARY KEY (`MaDV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguyenlieu`
--

LOCK TABLES `nguyenlieu` WRITE;
/*!40000 ALTER TABLE `nguyenlieu` DISABLE KEYS */;
INSERT INTO `nguyenlieu` VALUES ('NL001','Sữa bột trắng','2022-03-13','10','Hộp',8000),('NL002','Trân Châu Đen','2022-03-13','10','Hộp',7000),('NL003','Thạch dừa','2022-03-13','10','Hộp',6000),('NL004','Cacao','2022-03-13','10','Hộp',5000),('NL005','Trà Matcha','2022-03-13','10','Hộp',10000),('NL006','Khoai tây','2022-03-13','10','Kg',5000),('NL007','Xúc xích','2022-03-13','20','Cái',10000),('NL008','Trà Xanh','2022-03-13','11','Hộp',10000),('NL009','Bột pizza làm sẵn','2022-03-13','15','Cái',25000),('NL010','Trà Đào','2022-03-13','10','Hộp',10000),('NL011','Trân Châu Trắng','2024-03-30','10','Hộp',10000),('NL012','Bột Cà Phê','2024-04-02','30','Hộp',10000),('NL013','Sữa Đặc','2024-04-02','30','Hộp',5000),('NL014','Phô mai','2024-04-02','20','Hộp',20000),('NL015','Cà chua','2024-04-02','3','Kg',5000),('NL016','Thạch Xoài','2024-04-05','40','Hộp',6000);
/*!40000 ALTER TABLE `nguyenlieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qlnhan_vien`
--

DROP TABLE IF EXISTS `qlnhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qlnhan_vien` (
  `MaNhanVien` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TenNhanVien` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Password` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Phone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CMND` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NgayLamViec` date DEFAULT NULL,
  `CaLamViec` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `LuongCoBan` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `HeSoLuong` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `TienLuong` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qlnhan_vien`
--

LOCK TABLES `qlnhan_vien` WRITE;
/*!40000 ALTER TABLE `qlnhan_vien` DISABLE KEYS */;
INSERT INTO `qlnhan_vien` VALUES ('admin',NULL,'YWQ=',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('NV001','Nguyễn Văn Bảoo','MTIz','098578985','mailtailieudn99@gmail.com','201985789','2024-03-28','1','2500000','1.0','0'),('NV002','Lê Khánh Linh','MTIz','0986898578','khanhlinh@gmail.com','209187587','2024-03-12','1','2500000','1.0','0'),('NV003','Đỗ Hùng Tiến','MTIz','0985986798','mmmm@gmail.com','298190589','2024-03-11','2','3000000','1.5','0'),('NV004','Hoa','MTIz','0957876451','hoa@gmail.com','297589085','2024-03-12','1','2500000','1.0','0'),('NV005','Chu Nguyên Phong','MTIz','0388298110','phong@gmail.com','123123123123','2024-03-30','2','3500000','1.2000000000000002','0'),('NV006','Nguyễn Quốc Duy','MTIz','012345678901','aa@gmail.com','123123456456','2024-03-01','2','3500000','1.2000000000000002','0');
/*!40000 ALTER TABLE `qlnhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongke`
--

DROP TABLE IF EXISTS `thongke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongke` (
  `TongTienNL` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongke`
--

LOCK TABLES `thongke` WRITE;
/*!40000 ALTER TABLE `thongke` DISABLE KEYS */;
INSERT INTO `thongke` VALUES (NULL);
/*!40000 ALTER TABLE `thongke` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-05 19:19:03
