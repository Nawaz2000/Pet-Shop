-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 12, 2021 at 04:45 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.30

drop database if exists petshop;

CREATE database petshop;

use petshop;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `petshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `contactus`
--

CREATE TABLE `contactus` (
  `id` int(11) NOT NULL,
  `fname` varchar(200) NOT NULL,
  `lname` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `subject` varchar(200) NOT NULL,
  `msg` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contactus`
--

INSERT INTO `contactus` (`id`, `fname`, `lname`, `email`, `subject`, `msg`) VALUES
(1, 'sayoooj', 'k', 'sayoojk221@gmail.com', 'nesaa', 'sdsd'),
(2, 'kiran', 'k', 'kiran@gmail.com', 'nesaa', 'kjkkkk'),
(3, 'sayooj222', 'k', 'admin@gmail.com', 'nesaa', 'aSASSDDFDSFDSFSDFSDFSDF');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `qty` int(11) NOT NULL,
  `country` varchar(200) NOT NULL,
  `district` varchar(200) NOT NULL,
  `postcode` varchar(200) NOT NULL,
  `address` longtext NOT NULL,
  `payment` varchar(100) NOT NULL,
  `status` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `profile_id`, `product_id`, `price`, `qty`, `country`, `district`, `postcode`, `address`, `payment`, `status`) VALUES
(21, 1, 10, 46666, 2, 'india', 'kannur', '670018', 'devaki nilayalam, sdsd', '', 'Pending'),
(23, 5, 10, 200, 4, 'usa', 'kannur', '670018', 'devaki nilayalam, sdsd', 'direct bank', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL,
  `image` varchar(200) NOT NULL,
  `descrip` longtext NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `category`, `image`, `descrip`, `price`) VALUES
(9, 'product 4', 'cat', 'images/uploads/6.jpg', 'dsasdsadasdsadasd', 2),
(10, 'product 5', 'reptile', 'images/uploads/7.jpg', 'sadddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd', 50),
(11, 'product 6', 'bird', 'images/uploads/portfolio-details-1.jpeg', 'dsfdssdfdsfsdafsfaDSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS', 100);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `fname` varchar(200) NOT NULL,
  `lname` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `email` varchar(300) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `dob` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `fname`, `lname`, `username`, `email`, `gender`, `phone`, `dob`, `password`, `role`) VALUES
(1, 'sayooj222', 'k','kiran', 'kiran@gmail.com', 'male', '09539864461', '2021-09-19', '123', 'ROLE_USER'),
(3, 'sayooj', 'k3', 'sayoojjkl', 'sayoojk1@gmail.com', 'male3', '0953', '2021-10-08', '12', 'ROLE_USER'),
(4, '', '', 'admin', 'admin@gmail.com', '', '', '', '123', 'ROLE_ADMIN'),
(5, 'kiran', 'k', 'anju', 'anju@gmail.com', 'female', '09539864461', '2021-09-16', '123', 'ROLE_USER');

-- --------------------------------------------------------

-- CREATE TABLE `authorities` (
--  `id` int(11) NOT NULL primary key,
--  `email` varchar(300) NOT NULL,
--  `authority` VARCHAR(100) NOT NULL,
--  `userid` int NOT NULL unique
  
-- );

-- CREATE UNIQUE INDEX ix_auth_email on authorities (email,authority);

-- INSERT INTO authorities (id, email, authority, useridauthoritiesauthorities) values 
-- (1, 'kiran@gmail.com', 'ROLE_USER', 1),
-- (3, 'sayoojk1@gmail.com','ROLE_USER', 3),
-- (4, 'admin@gmail.com','ROLE_ADMIN', 4),
-- (5, 'anju@gmail.com','ROLE_USER', 5);


--
-- Table structure for table `vetfinder`
--

CREATE TABLE `vetfinder` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` longtext NOT NULL,
  `note` longtext NOT NULL,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vetfinder`
--

INSERT INTO `vetfinder` (`id`, `name`, `email`, `phone`, `address`, `note`, `image`) VALUES
(2, 'sayoooj k', 'sayoojk221@gmail.com', '2323', 'devaki nilayalam', 'Nowadays , People are seeking fresh and modern marketing strategies to implement to their businesses.', 'images/uploads/5.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_cart` (`product_id`),
  ADD KEY `user_cart` (`user_id`);

--
-- Indexes for table `contactus`
--
ALTER TABLE `contactus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id_order` (`product_id`),
  ADD KEY `order_profile` (`profile_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vetfinder`
--
ALTER TABLE `vetfinder`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `contactus`
--
ALTER TABLE `contactus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `vetfinder`
--
ALTER TABLE `vetfinder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `product_cart` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_cart` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- ALTER TABLE authorities
--  ADD constraint authorities_ibfk_1 foreign key (userid) references user (id) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `product_order` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `profile_order` FOREIGN KEY (`profile_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
