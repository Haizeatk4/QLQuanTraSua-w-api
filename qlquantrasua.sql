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
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS chitiethoadon;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE chitiethoadon (
  STT int NOT NULL AUTO_INCREMENT,
  MaHD varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  MaDV varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  SoLuong double DEFAULT NULL,
  DonGia int DEFAULT NULL,
  PRIMARY KEY (STT)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO chitiethoadon VALUES (7,'HD002','M001',2,18000);
INSERT INTO chitiethoadon VALUES (10,'HD002','NL005',1,10000);
INSERT INTO chitiethoadon VALUES (11,'HD001','M001',1,9000);
INSERT INTO chitiethoadon VALUES (12,'HD003','NL003',1,6000);
INSERT INTO chitiethoadon VALUES (13,'HD004','NL004',2,10000);
INSERT INTO chitiethoadon VALUES (15,'HD005','M011',2,100000);
INSERT INTO chitiethoadon VALUES (16,'HD005','M001',4,36000);
INSERT INTO chitiethoadon VALUES (17,'HD001','M002',1,9000);
INSERT INTO chitiethoadon VALUES (18,'HD001','M005',1,10000);
INSERT INTO chitiethoadon VALUES (19,'HD001','M003',1,30000);
INSERT INTO chitiethoadon VALUES (20,'HD003','NL001',1,8000);
INSERT INTO chitiethoadon VALUES (21,'HD003','NL008',1,10000);
INSERT INTO chitiethoadon VALUES (22,'HD003','NL002',1,7000);
INSERT INTO chitiethoadon VALUES (23,'HD004','NL001',1,8000);
INSERT INTO chitiethoadon VALUES (24,'HD004','M006',1,10000);
INSERT INTO chitiethoadon VALUES (25,'HD006','M002',2,18000);
INSERT INTO chitiethoadon VALUES (26,'HD007','M001',1,9000);
INSERT INTO chitiethoadon VALUES (27,'HD007','M002',1,9000);
INSERT INTO chitiethoadon VALUES (28,'HD007','M010',1,10000);
INSERT INTO chitiethoadon VALUES (29,'HD008','M001',2,18000);
INSERT INTO chitiethoadon VALUES (30,'HD009','M001',1,9000);
INSERT INTO chitiethoadon VALUES (31,'HD009','M004',1,35000);
INSERT INTO chitiethoadon VALUES (32,'HD009','M006',1,10000);

--
-- Table structure for table `dichvu`
--

DROP TABLE IF EXISTS dichvu;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE dichvu (
  MaDV varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  TenDV varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  SoLuong varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  Gia int DEFAULT NULL,
  Anh varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (MaDV)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu`
--

INSERT INTO dichvu VALUES ('M001','Sting vàng','44',9000,'/Image/Menu/stingVang.jpg');
INSERT INTO dichvu VALUES ('M002','Sting dâu','46',9000,'/Image/Menu/stingDo.jpg');
INSERT INTO dichvu VALUES ('M003','Trà sữa truyền thống','21',30000,'/Image/Menu/traSuaTruyenThong.jpg');
INSERT INTO dichvu VALUES ('M004','Trà sữa thái xanh','17',35000,'/Image/Menu/traSuaThaiXanh.jpg');
INSERT INTO dichvu VALUES ('M005','Hạt điều','54',10000,'/Image/Menu/hatDieu.jpg');
INSERT INTO dichvu VALUES ('M006','Hạt dưa','53',10000,'/Image/Menu/hatDua.jpg');
INSERT INTO dichvu VALUES ('M007','Cafe sữa','27',15000,'/Image/Menu/cafeSua.png');
INSERT INTO dichvu VALUES ('M008','Cafe đá sài gòn','24',14000,'/Image/Menu/cafeDaSaiGon.jpg');
INSERT INTO dichvu VALUES ('M009','Cafe bạc xỉu','23',15000,'/Image/Menu/cafeBacXiu.jpg');
INSERT INTO dichvu VALUES ('M010','Hạt dẻ','54',10000,'/Image/Menu/hatDe.jpg');
INSERT INTO dichvu VALUES ('M011','Pizza xúc xíchhh','20',50000,'/Image/Menu/pizzaXucXich.jpg');

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS hoadon;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE hoadon (
  MaHD varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  MaNhanVien varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  Ngay date NOT NULL,
  MaBan varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  ThanhTien int DEFAULT NULL,
  TinhTrang varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (MaHD)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

INSERT INTO hoadon VALUES ('HD001','NV001','2024-04-02','Bàn 003',58000,'Chưa thanh toán');
INSERT INTO hoadon VALUES ('HD002','NV001','2024-04-02','Bàn 002',28000,'Đã thanh toán');
INSERT INTO hoadon VALUES ('HD003','NV002','2024-03-12','Đem về',31000,'Đã thanh toán');
INSERT INTO hoadon VALUES ('HD004','NV003','2024-03-11','Bàn 003',28000,'Đã thanh toán');
INSERT INTO hoadon VALUES ('HD005','NV001','2024-04-06','Bàn 001',136000,'Đã thanh toán');
INSERT INTO hoadon VALUES ('HD006','NV002','2024-03-07','Bàn 003',18000,'Đã thanh toán');
INSERT INTO hoadon VALUES ('HD007','NV003','2024-02-01','Bàn 001',28000,'Đã thanh toán');
INSERT INTO hoadon VALUES ('HD008','NV003','2024-02-01','Bàn 002',18000,'Đã thanh toán');
INSERT INTO hoadon VALUES ('HD009','NV002','2024-02-01','Bàn 001',54000,'Đã thanh toán');

--
-- Table structure for table `nguyenlieu`
--

DROP TABLE IF EXISTS nguyenlieu;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE nguyenlieu (
  MaDV varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  TenDV varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  NgayNhap date DEFAULT NULL,
  SoLuong int DEFAULT NULL,
  DvTinh varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  Gia int DEFAULT NULL,
  PRIMARY KEY (MaDV)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguyenlieu`
--

INSERT INTO nguyenlieu VALUES ('NL001','Sữa bột trắng','2022-03-13',8,'Hộp',8000);
INSERT INTO nguyenlieu VALUES ('NL002','Trân Châu Đen','2022-03-13',9,'Hộp',7000);
INSERT INTO nguyenlieu VALUES ('NL003','Thạch dừa','2022-03-13',10,'Hộp',6000);
INSERT INTO nguyenlieu VALUES ('NL004','Cacao','2022-03-13',9,'Hộp',5000);
INSERT INTO nguyenlieu VALUES ('NL005','Trà Matcha','2022-03-13',10,'Hộp',10000);
INSERT INTO nguyenlieu VALUES ('NL006','Khoai tây','2022-03-13',10,'Kg',5000);
INSERT INTO nguyenlieu VALUES ('NL007','Xúc xích','2022-03-13',20,'Cái',10000);
INSERT INTO nguyenlieu VALUES ('NL008','Trà Xanh','2022-03-13',10,'Hộp',10000);
INSERT INTO nguyenlieu VALUES ('NL009','Bột pizza làm sẵn','2022-03-13',15,'Cái',25000);
INSERT INTO nguyenlieu VALUES ('NL010','Trà Đào','2022-03-13',10,'Hộp',10000);
INSERT INTO nguyenlieu VALUES ('NL011','Trân Châu Trắng','2024-03-30',10,'Hộp',10000);
INSERT INTO nguyenlieu VALUES ('NL012','Bột Cà Phê','2024-04-02',30,'Hộp',10000);
INSERT INTO nguyenlieu VALUES ('NL013','Sữa Đặc','2024-04-02',30,'Hộp',5000);
INSERT INTO nguyenlieu VALUES ('NL014','Phô mai','2024-04-02',20,'Hộp',20000);
INSERT INTO nguyenlieu VALUES ('NL015','Cà chua','2024-04-02',3,'Kg',5000);
INSERT INTO nguyenlieu VALUES ('NL016','Thạch Xoài','2024-04-05',40,'Hộp',6000);

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS nhanvien;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE nhanvien (
  MaNhanVien varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  TenNhanVien varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  Phone varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  Email varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  CMND varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  NgayLamViec date DEFAULT NULL,
  PRIMARY KEY (MaNhanVien)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO nhanvien VALUES ('NV001','Nguyễn Văn Bảoo','098578985','mailtailieudn99@gmail.com','201985789','2022-10-01');
INSERT INTO nhanvien VALUES ('NV002','Lê Khánh Linh','0986898578','khanhlinh@gmail.com','209187587','2022-10-01');
INSERT INTO nhanvien VALUES ('NV003','Đỗ Hùng Tiến','0985986798','mmmm@gmail.com','298190589','2023-02-05');
INSERT INTO nhanvien VALUES ('NV004','Hoa','0957876451','hoa@gmail.com','297589085','2023-02-05');
INSERT INTO nhanvien VALUES ('NV005','Chu Nguyên Phong','0388298110','phong@gmail.com','123123123123','2023-02-05');
INSERT INTO nhanvien VALUES ('NV006','Nguyễn Quốc Duy','012345678901','aa@gmail.com','123123456456','2023-02-05');
INSERT INTO nhanvien VALUES ('NV007','Nguyễn Minh Đức','0123456789','d123@gmail.com','112233445566','2023-02-05');
INSERT INTO nhanvien VALUES ('NV008','1','1','1','1','2023-02-05');

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS taikhoan;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS taikhoan;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE taikhoan (
  STT int AUTO_INCREMENT,
  MaNhanVien varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Password` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PhanQuyen varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (STT),
  check (PhanQuyen in('Nhân viên','Quản lý'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO taikhoan VALUES (null,'NV001','MTIz','Quản lý');
INSERT INTO taikhoan VALUES (null,'NV002','MTIz','Quản lý');
INSERT INTO taikhoan VALUES (null,'NV003','MTIz','Nhân viên');
INSERT INTO taikhoan VALUES (null,'NV004','MTIz','Nhân viên');
INSERT INTO taikhoan VALUES (null,'NV005','MTIz','Nhân viên');
INSERT INTO taikhoan VALUES (null,'NV006','MTIz','Nhân viên');
INSERT INTO taikhoan VALUES (null,'NV007','MTIz','Nhân viên');
INSERT INTO taikhoan VALUES (null,'NV008','MQ==','Nhân viên');
--
-- Table structure for table `thongke`
--

DROP TABLE IF EXISTS thongke;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE thongke (
  MaTK varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  DoanhThu int DEFAULT NULL,
  PRIMARY KEY (MaTK)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongke`
--

INSERT INTO thongke VALUES ('2023M10',50000);
INSERT INTO thongke VALUES ('2023M11',100000);
INSERT INTO thongke VALUES ('2023M12',150000);
INSERT INTO thongke VALUES ('2024M01',100000);
INSERT INTO thongke VALUES ('2024M02',100000);
INSERT INTO thongke VALUES ('2024M03',262000);
INSERT INTO thongke VALUES ('2024M04',278000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
