-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2025 at 10:43 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `orderflowsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `user_id` int(20) NOT NULL,
  `user_Fname` varchar(50) NOT NULL,
  `user_Mname` varchar(50) NOT NULL,
  `user_Lname` varchar(50) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_phone` varchar(50) NOT NULL,
  `user_username` varchar(50) NOT NULL,
  `user_pass` varchar(150) NOT NULL,
  `user_type` varchar(50) NOT NULL,
  `user_stats` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`user_id`, `user_Fname`, `user_Mname`, `user_Lname`, `user_email`, `user_phone`, `user_username`, `user_pass`, `user_type`, `user_stats`) VALUES
(15, 'Marc', 'Canas', 'Violon', 'mstevenviolon@yahoo.com', '09123456789', 'biolonUser', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Active'),
(16, 'Test', 'Inactive', 'Violon', 'mstevenviolon@gmail.com', '09081636645', 'test', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Active'),
(17, 'Steven', 'Canas', 'Violon', 'mstevenviolon1@gmail.com', '09081636690', 'biolonAdmin', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'Admin', 'Active'),
(18, 'asd', 'asd', 'asd', 'asdas@gmail.com', '09123456783', 'asd', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Inactive'),
(19, 'asd', 'asd', 'asd', 'asd.asda@scc.net', '09123456781', 'asdasd', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Active'),
(20, 'asd', 'asd', 'asd', 'asdas@YAHOO.com', '09123456785', 'asdasd111', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Pending'),
(21, 'asd', 'asd', 'asdasdasdasdasd', 'mstevenviolonnnnn@gmail.com', '09123456784', 'admin', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'Admin', 'Active'),
(23, 'asdasd', 'asdasdas', 'dasdas', 'asdasdasd@gmail.com', '09123456780', 'userbiol', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Active'),
(24, 'asdasd', 'asdasda', 'sdasdasd', 'asdws2@gmail.com', '09321654987', 'violon', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Pending'),
(25, 'violon', 'asd', 'as', 'violon@gmail.com', '09132456987', 'violon1', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Active'),
(26, 'asda', 'asd', 'asd', 'asdasdss@gmail.com', '09156987462', 'koko', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Active'),
(27, 'test', 'test', 'test', 'test@gmail.com', '09123654879', 'testtest', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'Admin', 'Active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
