-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 26, 2020 at 04:25 PM
-- Server version: 5.7.22
-- PHP Version: 7.3.14-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vg_birdy69`
--

-- --------------------------------------------------------

--
-- Table structure for table `FRIEND`
--

CREATE TABLE `FRIEND` (
  `user_id` varchar(32) NOT NULL,
  `friend_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FRIEND`
--

INSERT INTO `FRIEND` (`user_id`, `friend_id`) VALUES
('user1', 'user2'),
('user1', 'user3'),
('user4', 'user2');

-- --------------------------------------------------------

--
-- Table structure for table `SESSION`
--

CREATE TABLE `SESSION` (
  `user_id` varchar(32) NOT NULL,
  `session_id` varchar(32) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SESSION`
--

INSERT INTO `SESSION` (`user_id`, `session_id`, `date`) VALUES
('user1', '03d39b53d4e44fe8a431b5f6049bf5c1', '2020-02-26 15:17:24');

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `user_id` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `pass` varchar(32) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `DNN` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`user_id`, `email`, `pass`, `fname`, `lname`, `DNN`) VALUES
('user1', '', 'ycfhdcyjt', 'user1', 'user1', NULL),
('user2', '', 'fzefgz', 'user2', 'user2', NULL),
('user3', '', 'gzrgzr', 'user3', 'user3', NULL),
('user4', '', 'gzegrgaz', 'user4', 'user4', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `FRIEND`
--
ALTER TABLE `FRIEND`
  ADD PRIMARY KEY (`user_id`,`friend_id`);

--
-- Indexes for table `SESSION`
--
ALTER TABLE `SESSION`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `FRIEND`
--
ALTER TABLE `FRIEND`
  ADD CONSTRAINT `fk_user_friend` FOREIGN KEY (`user_id`) REFERENCES `USER` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `SESSION`
--
ALTER TABLE `SESSION`
  ADD CONSTRAINT `fk_user_session` FOREIGN KEY (`user_id`) REFERENCES `USER` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
