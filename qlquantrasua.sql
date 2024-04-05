-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2024 at 09:23 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlquantrasua`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MaDV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoLuong` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaDV`, `SoLuong`) VALUES
('HD001', 'NL001', '1'),
('HD002', 'NL005', '1'),
('HD003', 'NL003', '1'),
('HD004', 'NL004', '1');

-- --------------------------------------------------------

--
-- Table structure for table `dichvu`
--

CREATE TABLE `dichvu` (
  `MaMon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenMon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SoLuong` varchar(50) NOT NULL,
  `Gia` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Anh` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dichvu`
--

INSERT INTO `dichvu` (`MaMon`, `TenMon`, `SoLuong`, `Gia`, `Anh`) VALUES
('M001', 'Sting vàng', '50', '9000', '/Image/Menu/stingVang.jpg'),
('M002', 'Sting dâu', '50', '9000', '/Image/Menu/stingDo.jpg'),
('M003', 'Trà sữa truyền thống', '22', '30000', '/Image/Menu/traSuaTruyenThong.jpg'),
('M004', 'Trà sữa thái xanh', '18', '35000', '/Image/Menu/traSuaThaiXanh.jpg'),
('M005', 'Hạt điều', '55', '10000', '/Image/Menu/hatDieu.jpg'),
('M006', 'Hạt dưa', '55', '10000', '/Image/Menu/hatDua.jpg'),
('M007', 'Cafe sữa', '27', '15000', '/Image/Menu/cafeSua.png'),
('M008', 'Cafe đá sài gòn', '23', '14000', '/Image/Menu/cafeDaSaiGon.jpg'),
('M009', 'Cafe bạc xỉu', '23', '15000', '/Image/Menu/cafeBacXiu.jpg'),
('M010', 'Hạt dẻ', '55', '10000', '/Image/Menu/hatDe.jpg'),
('M011', 'Pizza xúc xích', '20', '50000', '/Image/Menu/pizzaXucXich.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(50) NOT NULL,
  `MaNhanVien` varchar(50) NOT NULL,
  `Ngay` date NOT NULL,
  `MaBan` varchar(50) NOT NULL,
  `ThanhTien` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaNhanVien`, `Ngay`, `MaBan`, `ThanhTien`) VALUES
('HD001', 'NV001', '2024-03-01', 'B001', '100000'),
('HD002', 'NV001', '2024-03-02', 'B001', '100000'),
('HD003', 'NV002', '2024-03-12', 'B005', '50000'),
('HD004', 'NV003', '2024-03-11', 'B003', '120000');

-- --------------------------------------------------------

--
-- Table structure for table `nguyenlieu`
--

CREATE TABLE `nguyenlieu` (
  `MaNL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenNL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NgayNhap` date DEFAULT NULL,
  `SoLuong` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DvTinh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gia` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nguyenlieu`
--

INSERT INTO `nguyenlieu` (`MaNL`, `TenNL`, `NgayNhap`, `SoLuong`, `DvTinh`, `Gia`) VALUES
('NL001', 'Sữa bột trắng', '2022-03-13', '10', 'Hộp', '8000'),
('NL002', 'Trân Châu Đen', '2022-03-13', '10', 'Hộp', '7000'),
('NL003', 'Thạch dừa', '2022-03-13', '10', 'Hộp', '6000'),
('NL004', 'Cacao', '2022-03-13', '10', 'Hộp', '5000'),
('NL005', 'Trà Matcha', '2022-03-13', '10', 'Hộp', '10000'),
('NL006', 'Khoai tây', '2022-03-13', '10', 'Kg', '5000'),
('NL007', 'Xúc xích', '2022-03-13', '20', 'Cái', '10000'),
('NL008', 'Trà Xanh', '2022-03-13', '11', 'Hộp', '10000'),
('NL009', 'Bột pizza làm sẵn', '2022-03-13', '15', 'Cái', '25000'),
('NL010', 'Trà Đào', '2022-03-13', '10', 'Hộp', '10000'),
('NL011', 'Trân Châu Trắng', '2024-03-30', '10', 'Hộp', '10000'),
('NL012', 'Bột Cà Phê', '2024-04-02', '30', 'Hộp', '10000'),
('NL013', 'Sữa Đặc', '2024-04-02', '30', 'Hộp', '5000'),
('NL014', 'Phô mai', '2024-04-02', '20', 'Hộp', '20000'),
('NL015', 'Cà chua', '2024-04-02', '3', 'Kg', '5000');

-- --------------------------------------------------------

--
-- Table structure for table `qlnhan_vien`
--

CREATE TABLE `qlnhan_vien` (
  `MaNhanVien` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenNhanVien` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CMND` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NgayLamViec` date DEFAULT NULL,
  `CaLamViec` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LuongCoBan` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HeSoLuong` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TienLuong` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `qlnhan_vien`
--

INSERT INTO `qlnhan_vien` (`MaNhanVien`, `TenNhanVien`, `Password`, `Phone`, `Email`, `CMND`, `NgayLamViec`, `CaLamViec`, `LuongCoBan`, `HeSoLuong`, `TienLuong`) VALUES
('admin', NULL, 'YWRtaW4=', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('NV001', 'Nguyễn Văn Bảoo', 'MTIz', '098578985', 'mailtailieudn99@gmail.com', '201985789', '2024-03-28', '1', '2500000', '1.0', '0'),
('NV002', 'Lê Khánh Linh', 'MTIz', '0986898578', 'khanhlinh@gmail.com', '209187587', '2024-03-12', '1', '2500000', '1.0', '0'),
('NV003', 'Đỗ Hùng Tiến', 'MTIz', '0985986798', 'mmmm@gmail.com', '298190589', '2024-03-11', '2', '3000000', '1.5', '0'),
('NV004', 'Hoa', 'MTIz', '0957876451', 'hoa@gmail.com', '297589085', '2024-03-12', '1', '2500000', '1.0', '0'),
('NV005', 'Chu Nguyên Phong', 'MTIz', '0388298110', 'phong@gmail.com', '123123123123', '2024-03-30', '2', '3500000', '1.2000000000000002', '0'),
('NV006', 'Nguyễn Quốc Duy', 'MTIz', '012345678901', 'aa@gmail.com', '123123456456', '2024-03-01', '2', '3500000', '1.2000000000000002', '0');

-- --------------------------------------------------------

--
-- Table structure for table `thongke`
--

CREATE TABLE `thongke` (
  `TongTienNL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thongke`
--

INSERT INTO `thongke` (`TongTienNL`) VALUES
(NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`);

--
-- Indexes for table `dichvu`
--
ALTER TABLE `dichvu`
  ADD PRIMARY KEY (`MaMon`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`);

--
-- Indexes for table `nguyenlieu`
--
ALTER TABLE `nguyenlieu`
  ADD PRIMARY KEY (`MaNL`);

--
-- Indexes for table `qlnhan_vien`
--
ALTER TABLE `qlnhan_vien`
  ADD PRIMARY KEY (`MaNhanVien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
