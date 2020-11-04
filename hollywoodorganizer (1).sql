-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2020 at 03:58 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hollywoodorganizer`
--

-- --------------------------------------------------------

--
-- Table structure for table `actors`
--

CREATE TABLE `actors` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Dumping data for table `actors`
--

INSERT INTO `actors` (`id`, `name`) VALUES
(1, 'Malcom McDowell'),
(3, 'Timothée Chalamet'),
(4, 'John David Washington'),
(5, 'Ben Affleck'),
(6, 'Ryan Gosling'),
(10, 'Bill Murray');

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `id` int(11) NOT NULL,
  `genre` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`id`, `genre`) VALUES
(3, 'Sci-Fi'),
(10, 'NULL'),
(11, 'Thriller');

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `genreId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `name`, `genreId`) VALUES
(1, 'The Clockwork Orange', 3),
(3, 'Dune', 3),
(4, 'Gone Girl', 11),
(8, 'Drive', 11),
(12, 'Osmosis Jones', 10);

-- --------------------------------------------------------

--
-- Table structure for table `movies_actors`
--

CREATE TABLE `movies_actors` (
  `id` int(11) NOT NULL,
  `movie_id` int(11) DEFAULT NULL,
  `actor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Dumping data for table `movies_actors`
--

INSERT INTO `movies_actors` (`id`, `movie_id`, `actor_id`) VALUES
(3, 1, 1),
(4, 3, 3),
(7, 4, 5),
(10, 8, 6),
(14, 12, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actors`
--
ALTER TABLE `actors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `genreId` (`genreId`);

--
-- Indexes for table `movies_actors`
--
ALTER TABLE `movies_actors`
  ADD PRIMARY KEY (`id`),
  ADD KEY `movie_id` (`movie_id`),
  ADD KEY `actor_id` (`actor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actors`
--
ALTER TABLE `actors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `movies_actors`
--
ALTER TABLE `movies_actors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `movies`
--
ALTER TABLE `movies`
  ADD CONSTRAINT `movies_ibfk_1` FOREIGN KEY (`genreId`) REFERENCES `genres` (`id`);

--
-- Constraints for table `movies_actors`
--
ALTER TABLE `movies_actors`
  ADD CONSTRAINT `movies_actors_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  ADD CONSTRAINT `movies_actors_ibfk_2` FOREIGN KEY (`actor_id`) REFERENCES `actors` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
