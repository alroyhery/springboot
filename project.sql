-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 07, 2021 at 06:21 PM
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
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `description`) VALUES
(1, 'horror', NULL),
(2, 'scifi', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` varchar(230) NOT NULL,
  `user_id` int(11) NOT NULL,
  `thread_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `content`, `user_id`, `thread_id`, `created_at`) VALUES
(9, 'oh iya sasuke kehilangan rinnegannya', 2, 11, '2021-10-07 23:13:08'),
(10, 'test', 2, 11, '2021-10-07 23:13:15'),
(11, 'yang bener???', 1, 11, '2021-10-07 23:14:39'),
(12, 'oh ya??', 1, 12, '2021-10-07 23:15:35');

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
  `user_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `thread`
--

INSERT INTO `thread` (`id`, `title`, `content`, `user_id`, `category_id`, `created_at`) VALUES
(11, 'Naruto', 'Kurama baru saja mati gaes!!!!!', 2, 2, '2021-10-07 23:12:53'),
(12, 'Jualan', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor, purus sit amet cursus laoreet, mi quam porta nibh, scelerisque pellentesque enim magna quis dui. Mauris laoreet laoreet augue non vehicula. Aenean aliquam massa a sapien ultrices ornare. Morbi nec nunc placerat, hendrerit sapien sit amet, vulputate mauris. Nam rhoncus cursus nisi a laoreet. Fusce fringilla augue neque, id pretium massa vestibulum ac. Praesent interdum velit eget accumsan sodales. Nunc blandit velit turpis, eget tempus justo consequat eu. Aenean vitae libero nunc. Fusce tristique mauris quis lectus porta, eget tempor dui interdum.', 2, 1, '2021-10-07 23:13:55'),
(13, 'asdasdasd', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor, purus sit amet cursus laoreet, mi quam porta nibh, scelerisque pellentesque enim magna quis dui. Mauris laoreet laoreet augue non vehicula. Aenean aliquam massa a sapien ultrices ornare. Morbi nec nunc placerat, hendrerit sapien sit amet, vulputate mauris. Nam rhoncus cursus nisi a laoreet. Fusce fringilla augue neque, id pretium massa vestibulum ac. Praesent interdum velit eget accumsan sodales. Nunc blandit velit turpis, eget tempus justo consequat eu. Aenean vitae libero nunc. Fusce tristique mauris quis lectus porta, eget tempor dui interdum.', 2, 1, '2021-10-07 23:14:05'),
(14, 'Aku ada spoiler', 'Spoilernya Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor, purus sit amet cursus laoreet, mi quam porta nibh, scelerisque pellentesque enim magna quis dui. Mauris laoreet laoreet augue non vehicula. Aenean aliquam massa a sapien ultrices ornare. Morbi nec nunc placerat, hendrerit sapien sit amet, vulputate mauris. Nam rhoncus cursus nisi a laoreet. Fusce fringilla augue neque, id pretium massa vestibulum ac. Praesent interdum velit eget accumsan sodales. Nunc blandit velit turpis, eget tempus justo consequat eu. Aenean vitae libero nunc. Fusce tristique mauris quis lectus porta, eget tempor dui interdum.', 1, 1, '2021-10-07 23:15:28');

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
(2, 'adly', 'adly@a.com', '202cb962ac59075b964b07152d234b70', '2021-10-06 20:43:26');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `thread_id` (`thread_id`);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `category_id` (`category_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id_report` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `thread`
--
ALTER TABLE `thread`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);

--
-- Constraints for table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `thread_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `thread_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
