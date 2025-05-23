-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2025 at 10:47 AM
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
-- Table structure for table `tbl_item`
--

CREATE TABLE `tbl_item` (
  `itemID` int(20) NOT NULL,
  `itemName` varchar(50) NOT NULL,
  `itemPrice` varchar(50) NOT NULL,
  `itemQuan` varchar(50) NOT NULL,
  `itemImage` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_item`
--

INSERT INTO `tbl_item` (`itemID`, `itemName`, `itemPrice`, `itemQuan`, `itemImage`) VALUES
(1005, 'jowabo', '10', '3', 'src/userimage/RobloxScreenShot20241011_001623576.png'),
(1006, 'louise', '100', '0', ''),
(1007, 'asd', '10', '5', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_logs`
--

CREATE TABLE `tbl_logs` (
  `log_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `actions` varchar(200) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_logs`
--

INSERT INTO `tbl_logs` (`log_id`, `user_id`, `actions`, `date`) VALUES
(1, 55, 'Added User Record with ID No.  57', '2025-05-23 12:04:52');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order`
--

CREATE TABLE `tbl_order` (
  `orderID` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `customer` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `orderStats` varchar(50) NOT NULL,
  `orderDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_order`
--

INSERT INTO `tbl_order` (`orderID`, `user_id`, `customer`, `address`, `phone`, `orderStats`, `orderDate`) VALUES
(4, 56, 'sdfds', 'fsdfsd', '09123456789', 'Approved', '2025-05-22'),
(6, 56, 'asd', 'sss', '09152365111', 'Pending', '2025-05-22');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order_items`
--

CREATE TABLE `tbl_order_items` (
  `itemOrderID` int(11) NOT NULL,
  `orderID` int(11) NOT NULL,
  `itemID` int(11) NOT NULL,
  `orderQuan` int(11) NOT NULL,
  `itemPrice` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_order_items`
--

INSERT INTO `tbl_order_items` (`itemOrderID`, `orderID`, `itemID`, `orderQuan`, `itemPrice`) VALUES
(4, 4, 1005, 4, 10),
(6, 6, 1005, 3, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pass`
--

CREATE TABLE `tbl_pass` (
  `pass_id` int(11) NOT NULL,
  `user_id` int(20) NOT NULL,
  `reason` varchar(200) NOT NULL,
  `status` varchar(20) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_pass`
--

INSERT INTO `tbl_pass` (`pass_id`, `user_id`, `reason`, `status`, `data`) VALUES
(1, 55, 'asdasd', 'Approved', '2025-05-23 13:35:21'),
(2, 56, 'idk', 'Pending', '2025-05-23 15:46:42');

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
  `user_stats` varchar(50) NOT NULL,
  `user_image` varchar(300) DEFAULT NULL,
  `force_reset` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`user_id`, `user_Fname`, `user_Mname`, `user_Lname`, `user_email`, `user_phone`, `user_username`, `user_pass`, `user_type`, `user_stats`, `user_image`, `force_reset`) VALUES
(55, 'test', 'test', 'test', 'test@gmail.com', '09123456789', 'test', 'osltUY8QmaO2r+KeRDNA+fX98SiYU/wDSQhETyvLiYI=', 'Admin', 'Active', '', 0),
(56, 'test', 'test', 'test1', 'test1@gmail.com', '09321654987', 'test1', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'User', 'Active', '', 0),
(57, 'test', 'test', 'test', 'testtest2@gmail.com', '09123654789', 'test2', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'Admin', 'Active', 'src/userimage/Screenshot 2025-05-10 113726.png', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_item`
--
ALTER TABLE `tbl_item`
  ADD PRIMARY KEY (`itemID`);

--
-- Indexes for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `userID` (`user_id`);

--
-- Indexes for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `uid` (`user_id`);

--
-- Indexes for table `tbl_order_items`
--
ALTER TABLE `tbl_order_items`
  ADD PRIMARY KEY (`itemOrderID`),
  ADD KEY `IDorder` (`orderID`),
  ADD KEY `IDitem` (`itemID`);

--
-- Indexes for table `tbl_pass`
--
ALTER TABLE `tbl_pass`
  ADD PRIMARY KEY (`pass_id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_item`
--
ALTER TABLE `tbl_item`
  MODIFY `itemID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1008;

--
-- AUTO_INCREMENT for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_order`
--
ALTER TABLE `tbl_order`
  MODIFY `orderID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_order_items`
--
ALTER TABLE `tbl_order_items`
  MODIFY `itemOrderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_pass`
--
ALTER TABLE `tbl_pass`
  MODIFY `pass_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD CONSTRAINT `userID` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`);

--
-- Constraints for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD CONSTRAINT `uid` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`);

--
-- Constraints for table `tbl_order_items`
--
ALTER TABLE `tbl_order_items`
  ADD CONSTRAINT `IDitem` FOREIGN KEY (`itemID`) REFERENCES `tbl_item` (`itemID`),
  ADD CONSTRAINT `IDorder` FOREIGN KEY (`orderID`) REFERENCES `tbl_order` (`orderID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
