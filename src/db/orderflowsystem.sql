-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2025 at 07:05 PM
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
  `user_stats` varchar(50) NOT NULL,
  `user_image` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`user_id`, `user_Fname`, `user_Mname`, `user_Lname`, `user_email`, `user_phone`, `user_username`, `user_pass`, `user_type`, `user_stats`, `user_image`) VALUES
(26, 'asda', 'asd', 'asd', 'asdasdss@gmail.com', '09156987462', 'koko', 'uoAeuSTkujyCZbuJRnyXLkznIi6w9vgK9ebredAJPDE=', 'User', 'Active', ''),
(27, 'test', 'test', 'test', 'test1@gmail.com', '09123654879', 'testtest', 'asdasdasd', 'User', 'Active', ''),
(31, 'test', 'test', 'test', 'test12121@gmail.com', '09123456987', 'test12', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'Admin', 'Pending', ''),
(32, 'test', 'test', 'test', 'test@g.gmail.com', '09123654987', 'test4', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'Admin', 'Active', ''),
(33, 'test', 'test', 'test', 'test23232@gmail.com', '09159874362', 'testu', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'User', 'Active', ''),
(34, 'OHAHA', 'ulol', 'jowabol', 'ftest@gmail.com', '09654798213', 'testt', 'Vp/xUYDo7gVm5vVNbXd0mjgft4aG2RCAurzu1/hQzT4=', 'Userte', 'Active', ''),
(35, 'asd', 'asd', 'asdasdasdasdasd', 'mstevenviolonnnnn@gmail.com', '09123456784', 'admin', 'dBqF6NVFXoCW/lAJKqQvOhGo9/gvETAXq5S7sx591HM=', 'Admin', 'Active', ''),
(36, 'asd', 'asd', 'asd', 'asdas@YAHOO.com', '09123456785', 'asdasd111', 'ebPBlWEIwYbXDITCrorQURMvVwhqqngNfeXEjapxWFI=', 'User', 'Pending', ''),
(37, 'asd', 'asd', 'asd', 'asd.asda@scc.net', '09123456781', 'asdasd', 'dBqF6NVFXoCW/lAJKqQvOhGo9/gvETAXq5S7sx591HM=', 'User', 'Active', ''),
(42, 'test', 'test', 'test', 'testtesttesttest@gmail.com', '09467913582', 'test1', 'NKvpc40QXAj4ki4d/NCRaJjLQOGsL1ULTZdVxAt6VOc=', 'Admin', 'Active', ''),
(45, 'www', 'www', 'www', 'www@mgio.com', '09159487263', 'www', 'UfilRx+oIuG+mp+jLcNidooK8U+9CWEryjVy0E2oVZY=', 'Admin', 'Active', 'src/userimage/Screenshot 2024-12-05 083958.png'),
(46, 'test2', 'test2', 'test2', 'test2test2@gmail.casd', '09159846237', 'test2', 'pqqpZ8i1Pm4iYDRyyUZITJsmRA/nHbJ9nIi0O6wpR74=', 'Admin', 'Active', ''),
(47, 'qwe', 'qwe', 'qwe', 'qweqwe@gmail.com', '09159487623', 'qwe', 'DR6kwlbNUKKnzL/SKz2ZWfb9ML2EC5/zx8Ze5OId8G0=', 'Admin', 'Active', ''),
(48, 'test3', 'test3', 'test3', 'test3@gmail.com', '09111111111', 'test3', 'abOPF39h/W0QB7+txd8Ggx29Iz6c+XvSdEteQ6LRkW0=', 'Admin', 'Active', ''),
(49, 'test4', 'test4', 'test4', 'test4@gm.gaaS', '09222222222', 'test4test4', '08QPeR/1Ut87P7Gqr8EtBK/Zziboov+Ho9Eyp+bliYs=', 'Admin', 'Active', 'src/userimage/Screenshot 2024-11-05 010927.png'),
(50, 'test5', 'test5', 'test5', 'test5test5@gmail.com', '09333333331', 'test5test5', 'gFYPBpuaGjH01qdYVaqnfkbEiYXZHPP7Nxa5VjtzH4o=', 'Admin', 'Active', 'src/userimage/Screenshot 2024-06-04 164158.png'),
(51, 'test6', 'test6', 'test6', 'test6@gmail.com', '09333333333', 'test6', 'yoTCaOn0lxR5btEbQ25xWueUnKMz4kHWijFK7bnBrdk=', 'Admin', 'Active', ''),
(52, 'test7', 'test7', 'test7', 'test7@gmail.co', '09444444444', 'test7test7', 'QdOpRRy0pC3HL/8MNeWiuDhnV7ccYz/VSsPl7SiJE5U=', 'Admin', 'Active', 'default.jpg'),
(53, 'test8', 'test8', 'test8', 'test8@gmail.cs', '09555555555', 'test8', 'fQF+Oa2s9Wy5AlJ3Nn2JYPwdBsrBJzoI1stJ6mPjMVA=', 'Admin', 'Active', 'src/userimage/Screenshot 2025-02-22 184050.png'),
(54, 'test8', 'test8', 'test8', 'test8@gmail.com', '09666666666', 'test9', 'cT4PjtU612NW+l5Prrz5M8DTWwTdoIANxJ7JwHIgrNw=', 'Admin', 'Active', '');

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
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
