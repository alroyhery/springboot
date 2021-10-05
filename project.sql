-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2021 at 06:09 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id_category` int(11) NOT NULL,
  `categoryname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id_category`, `categoryname`) VALUES
(1, 'horror');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id_comment`, `content`) VALUES
(1, 'asd'),
(4, 'asdadsasdasd'),
(5, 'komentar 1'),
(6, 'komentar 2'),
(7, 'asdasdasdasd');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id_report` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_thread` int(11) DEFAULT NULL,
  `id_comment` int(11) DEFAULT NULL,
  `reason` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `thread`
--

CREATE TABLE `thread` (
  `id` int(11) NOT NULL,
  `title` varchar(230) NOT NULL,
  `content` text NOT NULL,
  `category` text NOT NULL,
  `id_category` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `thread`
--

INSERT INTO `thread` (`id`, `title`, `content`, `category`, `id_category`, `id_user`, `created_at`) VALUES
(1, 'CERITAKU DI RUMAH', 'Malam itu aku mlihat aloroy sedang di kamar dan aksdjasdkjaskdjaskdjaskdjaksdjkasjdkjasdkjasdkjaskjd kemudian dia asdaskdjaskdjkasdkjaskjd lalu dia askjdkasjdkasdjasjdkjaskdaskdjaksdasdasd', 'horror', 0, 1, '2021-10-02 23:46:01'),
(2, 'AKU ADA IDE', 'Ada beberapa jenis media sosial yang populer dan banyak digunakan semua kalangan, seperti Instagram, Facebook, Twitter, hingga Whatsapp.\r\nKamu juga dapat mem-posting foto atau video ke media sosial. Saat mengunggahnya, kamu biasanya membutuhkan kata-kata yang dijadikan sebagai caption.\r\nKata-kata tersebut berguna untuk membuat postingan yang kamu buat menjadi lebih keren dan menarik sehingga banyak orang yang tertarik dengan postinganmu.\r\nHal tersebut dibutuhkan karena pada era modern saat ini banyak orang yang membutuhkan eksistensi untuk kehidupan bersosial.\r\nNamun, tidak semua orang dapat merangkai kata-kata keren untuk dijadikan sebagai caption di berbagai media sosial yang mereka miliki.', 'EDUKATIF', NULL, NULL, '2021-10-04 20:16:48'),
(3, 'ERLANGGA', 'AKU PUNYA TEMAN NAMANYA ERLANGGA DIA SELALU ASDKASJDKASDKASJDKASJDKASDJ KEMUDIAN ASKDASKDJASKDJASKJDKASD LALU ASKDAKSDJAKSJDKASKDJAS DAN AKSDJASKDJASKJDKASJDKDASJ SAAT ASKDNASKDAKSDNASDNASDASD', 'psychology horror', NULL, NULL, '2021-10-04 21:27:48'),
(5, 'asdlasdasdkasdkk', 'asdasdasdasdasdaasdasdasdad askdjaskdjaskdkaskdjaskj aksj askdjaksjdaskjdkj akjd kasjd kasjdaksdjasldjasldjalsj lj alskjdaskljdlaskjdlas kasdjkjkjk askdjaksdjakj kj aksjdaksjdkja skdjka d', 'Comedy', NULL, NULL, '2021-10-04 21:39:24');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `created_at`) VALUES
(1, 'qwerty', '123@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2021-10-02 23:13:55'),
(2, 'adly', 'adly@a.com', '202cb962ac59075b964b07152d234b70', '2021-10-04 20:57:50');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_category`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id_comment`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id_report`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_thread` (`id_thread`),
  ADD KEY `id_comment` (`id_comment`);

--
-- Indexes for table `thread`
--
ALTER TABLE `thread`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id_comment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id_report` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `thread`
--
ALTER TABLE `thread`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
