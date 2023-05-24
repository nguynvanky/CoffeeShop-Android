-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 23, 2023 at 01:59 PM
-- Server version: 8.0.31
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffeshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int NOT NULL,
  `id_user` int DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `address` text COLLATE utf8mb4_general_ci,
  `_status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `id_user`, `total_price`, `address`, `_status`) VALUES
(16, 3, 5, '123', 1),
(17, 3, 16, 'hehehehehehe', 1),
(18, 1, 9, 'Ho chi minh, viet nam', 1),
(19, 1, 202, 'Ho chi minh, viet nam', 1),
(20, 1, 2, 'Ho chi minh, viet nam', 1),
(21, 1, 5, 'Ho chi minh, viet nam', 1),
(22, 1, 5, 'Ho chi minh, viet nam', 1),
(23, 1, 5, 'Ho chi minh, viet nam', 1),
(24, 1, 2, 'Ho chi minh, viet nam', 1),
(25, 1, 2, 'Ho CHi Minh hehehehe', 1),
(26, 1, 16, 'Hồ Chí Minh City', 1),
(27, 3, 2, 'ahhahahah', 1),
(28, 1, 2, '123123', 1),
(29, 1, 5, 'vcvcv', 1),
(30, 1, 2, 'bvb', 1),
(31, 1, 2, '123123123', 1),
(32, 1, 2, 'hồ chí minh', 1),
(33, 1, 2, 'cvcvc', 1),
(34, 4, 2, 'hello', 1),
(35, 4, 2, '123123123', 1),
(36, 4, 2, 'cvcvcvc', 1),
(37, 4, 12, 'vcccvvcbvcncncv', 1),
(38, 4, 2, 'Hồ chí minh', 1),
(39, 4, 4, 'Hồ Chí minh pvt', 1),
(40, 4, 2, 'Hò Chí Minh', 1),
(41, 5, 40, 'Hồ Chí Minh, Tân Phú', 1),
(42, 5, 2, 'hồ chí minh', 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Black Coffee'),
(2, 'Mocha Latee'),
(3, 'Espresso'),
(4, 'Latte'),
(5, 'Capuchino'),
(6, 'Americano');

-- --------------------------------------------------------

--
-- Table structure for table `detail_cart`
--

CREATE TABLE `detail_cart` (
  `id` int NOT NULL,
  `id_cart` int DEFAULT NULL,
  `id_product` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_cart`
--

INSERT INTO `detail_cart` (`id`, `id_cart`, `id_product`, `quantity`, `price`) VALUES
(38, 18, 3, 1, 2),
(39, 19, 2, 1, 2),
(40, 19, 4, 100, 2),
(41, 20, 4, 1, 2),
(42, 21, 1, 1, 5),
(43, 22, 1, 1, 5),
(44, 23, 1, 1, 5),
(45, 24, 4, 1, 2),
(46, 25, 4, 1, 2),
(47, 17, 4, 1, 2),
(48, 17, 8, 3, 4),
(50, 26, 3, 2, 2),
(51, 26, 8, 2, 4),
(52, 26, 4, 2, 2),
(53, 17, 2, 1, 2),
(54, 27, 4, 1, 2),
(55, 28, 4, 1, 2),
(56, 29, 1, 1, 5),
(57, 30, 4, 1, 2),
(58, 31, 4, 1, 2),
(59, 32, 4, 1, 2),
(60, 33, 4, 1, 2),
(61, 34, 4, 1, 2),
(62, 35, 4, 1, 2),
(63, 36, 3, 1, 2),
(64, 37, 11, 1, 12),
(65, 38, 4, 1, 2),
(66, 39, 2, 1, 2),
(67, 39, 4, 1, 2),
(68, 40, 4, 1, 2),
(70, 41, 8, 10, 4),
(71, 42, 4, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  `id_cate` int DEFAULT NULL,
  `image` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `description`, `id_cate`, `image`) VALUES
(1, 'Capuchino ice', 10, 'cappuccino, coffee drink made of equal parts espresso, steamed milk, and frothed milk, of probable Italian origin but influenced by an Austrian predecessor. Although a cappuccino is similar to a latte, the former is layered in three equal and distinct parts—with the foam typically spooned onto the drink in dollops—while the latter is a mixture of one part espresso and two parts poured steamed milk, with only a thin layer of foam on top. Because of its greater milk content, a latte is typically sweeter and higher in calories, fat, and protein.', 1, 'https://github.com/nguynvanky/images/blob/main/1.JPG?raw=true'),
(2, 'AFFOGATO', 2, 'An affogato, more traditionally known as \"affogato al caffe\", is an Italian coffee-based dessert', 1, 'https://github.com/nguynvanky/images/blob/main/10.jpg?raw=true'),
(3, 'AMERICANO', 2, 'Americanos are popular breakfast drinks and thought to have originated during World War II. Soldiers would add water to their coffee to extend their rations farther. The water dilutes the espresso while still maintaining a high level of caffeine.', 1, 'https://github.com/nguynvanky/images/blob/main/11.jpg?raw=true'),
(4, 'CAFFÈ LATTE', 2, 'Also known as a short black, an espresso is a single shot of coffee. No extra hot water is added, resulting in an intense and flavoursome drink.', 1, 'https://github.com/nguynvanky/images/blob/main/12.jpg?raw=true'),
(5, 'CAFFÈ MOCHA', 3, 'A latte with the added sweetness of chocolate. A mocha can be prepared by adding chocolate to the espresso shot before adding the textured milk, or adding the chocolate to the cold milk before frothing.', 1, 'https://github.com/nguynvanky/images/blob/main/13.jpg?raw=true'),
(6, 'CAFÈ AU LAIT', 7, 'In the United States, it often refers to concentrated drip coffee topped with steamed milk.', 1, 'https://github.com/nguynvanky/images/blob/main/14.jpg?raw=true'),
(7, 'COLD BREW COFFEE', 6, 'This means that cold brewed coffee is incredibly smooth and almost sweet-tasting. Perfect for iced coffee.', 2, 'https://github.com/nguynvanky/images/blob/main/15.jpg?raw=true'),
(8, 'Cold Brew Coffee', 4, 'CoffeA double espresso may also be listed as doppio, which is the Italian word for double. This drink is highly concentrated and stronge best', 2, 'https://github.com/nguynvanky/images/blob/main/16.jpg?raw=true'),
(9, 'ESPRESSO', 2, 'The espresso, also known as a short black, is approximately 1 oz. of highly concentrated coffee. Although simple in appearance, it can be difficult to master.', 2, 'https://github.com/nguynvanky/images/blob/main/17.jpg?raw=true'),
(10, 'ESPRESSO CON PANNA', 4, 'Cold brew is coffee that is brewed by steeping coffee grounds in cold water for an extended period of time. There is a style of cold brew coffee that uses a drip method instead of steeping, known as Kyoto coffee. Cold brew coffee has a smoother and sweeter taste than hot brewed coffee, with less acidity and bitterness. ', 1, 'https://github.com/nguynvanky/images/blob/main/18.JPG?raw=true'),
(11, 'Lungo', 12, 'Lungo is a shot of espresso that is pulled for a longer amount of time. The name comes from the Italian word “lungo” which means long, ie a long espresso. A lungo has more water and as a result is more diluted compared to a regular espresso.', 2, 'https://github.com/nguynvanky/images/blob/main/19.jpg?raw=true'),
(12, 'Cortado', 1, 'A cortado coffee is made with one part espresso, one part steamed milk, and no foam. It’s a popular drink in Spain, Portugal, and Latin America. In the United States, it is sometimes known as a Gibraltar and is served in a glass cup with a metal handle.', 2, 'https://github.com/nguynvanky/images/blob/main/2.jpg?raw=true'),
(13, 'Cascara Coffee', 5, 'Cascara coffee is coffee that is prepared by brewing coffee cherry leaves instead of coffee beans. It has a flavour that is closer to tea than coffee.', 3, 'https://github.com/nguynvanky/images/blob/main/20.jpg?raw=true'),
(14, 'FREAKSHAKE', 2, 'Cold brew is coffee that is brewed by steeping coffee grounds in cold water for an extended period of time. There is a style of cold brew coffee that uses a drip method instead of steeping, known as Kyoto coffee. Cold brew coffee has a smoother and sweeter taste than hot brewed coffee, with less acidity and bitterness. ', 3, 'https://github.com/nguynvanky/images/blob/main/21.jpg?raw=true'),
(15, 'ICED LATTE', 1234, 'Iced coffees become very popular in the summertime in the United States. The recipes do have some variance, with some locations choosing to interchange milk with water in the recipe.', 3, 'https://github.com/nguynvanky/images/blob/main/22.jpg?raw=true'),
(16, 'ICED MOCHA', 3, 'Also called a café latte, this drink is a single shot of espresso with three parts steamed milk', 3, 'https://github.com/nguynvanky/images/blob/main/3.jpg?raw=true'),
(17, 'IRISH COFFEE', 6, 'cappuccino, coffee drink made of equal parts espresso, steamed milk, and frothed milk, of probable Italian origin but influenced by an Austrian predecessor. Although a cappuccino is similar to a latte, the former is layered in three equal and distinct parts—with the foam typically spooned onto the drink in dollops—while the latter is a mixture of one part espresso and two parts poured steamed milk, with only a thin layer of foam on top. Because of its greater milk content, a latte is typically sweeter and higher in calories, fat, and protein.', 1, 'https://github.com/nguynvanky/images/blob/main/4.jpg?raw=true'),
(18, 'LATTE MACCHIATO', 6, 'A latte is a coffee espresso shot filled with steamed milk and with a layer of foamed milk crema.', 3, 'https://github.com/nguynvanky/images/blob/main/5.jpg?raw=true'),
(19, 'Bulletproof Coffee', 6, 'Bulletproof coffee is a mix of brewed coffee, coconut oil, and unsalted butter. It is very popular with people who are on a high-fat, low-carb diet and can serve as a substitute for breakfast. ', 4, 'https://github.com/nguynvanky/images/blob/main/6.jpg?raw=true'),
(20, 'Espresso Tonic', 6, 'CoffAn espresso tonic is made by adding a double shot of espresso to a glass of ice and topping it with tonic water and a squeeze of lime juice. It creates a refreshing drink with some bitterness that is balanced by the acidity of the lime.ee best', 4, 'https://github.com/nguynvanky/images/blob/main/7.jpg?raw=true'),
(21, 'Frappuccino', 8, 'The Frappuccino is a drink popularised by Starbucks. It is made of blended espresso, milk, crushed ice, whipped cream, and any syrup or sauce of choice. Like the traditional Greek coffee mentioned above, this drink is also called frappe. Here’s a classic and easy frappuccino recipe.', 4, 'https://github.com/nguynvanky/images/blob/main/8.jpg?raw=true'),
(22, 'Nitro Cold Brew', 7, 'Nitro cold brew coffee is made by infusing cold brew coffee with nitrogen. This creates a foamy, creamy coffee with a mild, sweet taste.', 4, 'https://github.com/nguynvanky/images/blob/main/9.jpg?raw=true'),
(23, 'Cortado', 9, 'A cortado coffee is made with one part espresso, one part steamed milk, and no foam. It’s a popular drink in Spain, Portugal, and Latin America.', 4, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/A_small_cup_of_coffee.JPG/640px-A_small_cup_of_coffee.JPG'),
(24, 'Red Eye', 23, 'The red eye\'s purpose is to add a boost of caffeine to your standard cup of coffee.', 4, 'https://upload.wikimedia.org/wikipedia/commons/d/d8/Blue_Bottle%2C_Kyoto_Style_Ice_Coffee_%285909775445%29.jpg'),
(25, 'Black Eye', 12, 'The black eye is just the doubled version of the red eye and is very high in caffeine.', 4, 'https://upload.wikimedia.org/wikipedia/commons/7/76/Nitro_Cold_Brew.jpg'),
(26, 'Long Black', 3, 'The long black is a similar coffee drink to the americano, but it originated in New Zealand and Australia. It generally has more crema than an americano.', 5, 'https://upload.wikimedia.org/wikipedia/commons/2/2c/Strawberry_Delight_Frappuccino.JPG'),
(27, 'Macchiato', 2, 'The cafe au lait is typically made with French press coffee instead of an espresso shot to bring out the different flavors in the coffee. It is then paired with scalded milk instead of steamed milk and poured at a 50/50 ratio.', 5, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/ColdBrewCoffeein_Cans.png/640px-ColdBrewCoffeein_Cans.png'),
(28, 'Long Macchiato', 2, 'Often confused with a standard macchiato, the long macchiato is a taller version and will usually be identifiable by its distinct layers of coffee and steamed milk.', 5, 'https://upload.wikimedia.org/wikipedia/commons/b/b0/Home_Made_Ice_Coffee.jpg'),
(29, 'Cortado', 4, 'The cortado takes the macchiato one step further by evenly balancing the espresso with warm milk in order to reduce the acidity.', 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS09xyq9-InGg6jzv70UddVX7pF1G2OyR7wHA&usqp=CAU'),
(30, 'Breve', 12, 'The breve provides a decadent twist on the average espresso, adding steamed half-and-half to create a rich and creamy texture.', 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5av7X0fhgwnlkTYNUdvw4__PJYZqrkX7Hcw&usqp=CAU'),
(31, 'Flat White', 6, 'A flat white also originates from New Zealand and Australia and is very similar to a cappuccino but lacks the foam layer and chocolate powder. To keep the drink creamy rather than frothy, steamed milk from the bottom of the jug is used instead of from the top.', 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIZ1seIsUIJVMHMgcZ0Ao_BDwzW0A-Mx-G7A&usqp=CAU'),
(32, 'Cafe Latte', 5, 'Cafe lattes are considered an introductory coffee drink since the acidity and bitterness of coffee are cut by the amount of milk in the beverage. Flavoring syrups are often added to the latte for those who enjoy sweeter drinks.', 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJ7SQ9ZcKWqg-FM2sS8fnFmb-0RzbQvrS_7w&usqp=CAU'),
(33, 'Mocha', 7, 'The mocha is considered a coffee and hot chocolate hybrid. The chocolate powder or syrup gives it a rich and creamy flavor and cuts the acidity of the espresso.', 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0ZqM1a10C_4gxkrtU10kOJIfdGFiD5mzQvCoURnGUOd7H0zHJ76px64uvd5RbkFQtZyk&usqp=CAU'),
(34, 'Vienna', 6, 'There are a few variations on the Vienna, but one of the most common is made with two ingredients: espresso and whipped cream. The whipped cream takes the place of milk and sugar to provide a creamy texture.', 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5K7MFheSqzgnKOdk7BN0MLpS5uNEiTFpudA&usqp=CAU'),
(35, 'Affogato', 6, 'Affogatos are more for a dessert coffee than a drink you would find at a cafe, but they can add a fun twist to your coffee menu. They are made by pouring a shot of espresso over a scoop of vanilla ice cream to create a sweet after-meal treat.', 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDlB04NyHbhMBfJI8qGXCOmuoL4YH4kc1jfQ&usqp=CAU'),
(36, 'Cafe au Lait', 8, 'The cafe au lait is typically made with French press coffee instead of an espresso shot to bring out the different flavors in the coffee. It is then paired with scalded milk instead of steamed milk and poured at a 50/50 ratio.', 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvHlbL-ulYobbnTucTVtWzT0QtdCIVmuve4KGqZ2klOgI_bhA96dKBug1WQRwdHfFRpio&usqp=CAU'),
(37, 'Double Espresso', 9, 'A double espresso may also be listed as doppio, which is the Italian word for double. This drink is highly concentrated and strong.', 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmBnObrObLx30g0YFSJsHy7sbPRFMSnRmIhw&usqp=CAU'),
(38, 'Red Eye', 4, 'The red eye\'s purpose is to add a boost of caffeine to your standard cup of coffee.', 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4OND9tI-k9Oo_Y-bWVT3KA-qd0guK1JlHGg&usqp=CAU');

-- --------------------------------------------------------

--
-- Table structure for table `_user`
--

CREATE TABLE `_user` (
  `id` int NOT NULL,
  `username` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `_password` text COLLATE utf8mb4_general_ci,
  `full_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phonenumber` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `_user`
--

INSERT INTO `_user` (`id`, `username`, `_password`, `full_name`, `phonenumber`, `role`, `email`) VALUES
(1, 'nguynvanky', '$2y$10$v7pLrnvJJVgp47YpqalccuUwq8R6JxJYJVpm3dSU0mQ.JLdaKOFMW', 'Van Ky', '1234567', 'user', 'nguynvanky.work@gmail.com'),
(3, 'admin', '$2y$10$UzfG2wNVb2YoiJldSgAN8.DqXG6PD3aXYxRk7J7JpjogpbUPlWo2.', 'admin', '1234567', 'admin', 'nguynvanky.work@gmail.com'),
(4, 'vanky', '$2y$10$KE4aMgDOZbTDJenKmPQqAuk7yy0BT5My2T9JD3AM6892A2OBLZPQC', 'Van Ky', '0357068009', 'user', 'k.nguyn21@gmail.com'),
(5, 'trongnghia', '$2y$10$/KWN/aaOLrVRY4DZTEQme.YQ0WAIGQc9hQwf4gAUHzMdL0AJSCrs.', 'Trong Nghia', '04746122332', 'user', 'k.nguyn21@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_cart`
--
ALTER TABLE `detail_cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cart` (`id_cart`),
  ADD KEY `id_product` (`id_product`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cate` (`id_cate`);

--
-- Indexes for table `_user`
--
ALTER TABLE `_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `detail_cart`
--
ALTER TABLE `detail_cart`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `_user`
--
ALTER TABLE `_user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `_user` (`id`);

--
-- Constraints for table `detail_cart`
--
ALTER TABLE `detail_cart`
  ADD CONSTRAINT `detail_cart_ibfk_2` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `detail_cart_ibfk_3` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_cate`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
