-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: recordshop
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Vinyl Records','Discover classic and modern albums on vinyl format.'),(2,'CDs','Browse our extensive collection of compact discs.'),(3,'Music Accessories','Everything you need for your music setup and collection.');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line_items`
--

DROP TABLE IF EXISTS `order_line_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_line_items` (
  `order_line_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `sales_price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `discount` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`order_line_item_id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_line_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_line_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line_items`
--

LOCK TABLES `order_line_items` WRITE;
/*!40000 ALTER TABLE `order_line_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_line_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `date` datetime NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `zip` varchar(20) NOT NULL,
  `shipping_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `category_id` int NOT NULL,
  `description` text,
  `subcategory` varchar(20) DEFAULT NULL,
  `image_url` varchar(200) DEFAULT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `featured` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'The Beatles - Abbey Road Vinyl',29.99,1,'Classic Beatles album remastered on 180-gram vinyl.','Rock','abbey-road-vinyl.jpg',50,1),(2,'Pink Floyd - Atom Heart Mother Vinyl',36.99,1,'Experimental progressive rock with orchestral arrangements.','Rock','atom-heart-mother-vinyl.jpg',25,1),(3,'Led Zeppelin IV Vinyl',32.99,1,'Rock masterpiece featuring Stairway to Heaven.','Rock','led-zeppelin-iv-vinyl.jpg',25,0),(4,'Miles Davis - Kind of Blue Vinyl',39.99,1,'Essential jazz album on premium vinyl pressing.','Jazz','kind-of-blue-vinyl.jpg',20,1),(5,'Fleetwood Mac - Rumours Vinyl',31.99,1,'Best-selling album with hits like Go Your Own Way.','Rock','rumours-vinyl.jpg',40,0),(6,'Simon & Garfunkel - Bookends Vinyl',32.99,1,'Folk rock masterpiece with Mrs. Robinson and America.','Folk','bookends-vinyl.jpg',30,1),(7,'Bob Marley - Legend Vinyl',33.99,1,'Greatest hits collection from the reggae legend.','Reggae','legend-vinyl.jpg',30,0),(8,'Queen - A Night at the Opera Vinyl',36.99,1,'Features the epic Bohemian Rhapsody.','Rock','night-opera-vinyl.jpg',15,1),(9,'Johnny Cash - At Folsom Prison Vinyl',29.99,1,'Live country classic recorded in prison.','Country','folsom-prison-vinyl.jpg',25,0),(10,'Stevie Wonder - Songs in the Key of Life Vinyl',45.99,1,'Double album soul masterpiece.','R&B','key-of-life-vinyl.jpg',20,0),(11,'David Bowie - The Rise and Fall of Ziggy Stardust Vinyl',33.99,1,'Glam rock concept album classic.','Rock','ziggy-stardust-vinyl.jpg',30,1),(12,'The Rolling Stones - Goats Head Soup Vinyl',34.99,1,'Rock and roll but with soup and goats','Rock','sticky-fingers-vinyl.jpg',25,0),(13,'Billie Eilish - When We All Fall Asleep Vinyl',27.99,1,'Modern pop sensation on colored vinyl.','Pop','billie-eilish-vinyl.jpg',50,1),(14,'The Animals - The Animals Vinyl',28.99,1,'British blues rock classic with House of the Rising Sun.','Rock','animals-vinyl.jpg',40,0),(15,'Mac Miller - Balloonerism Vinyl',34.99,1,'Posthumous experimental hip-hop album on colored vinyl.','Hip-Hop','balloonerism-vinyl.jpg',30,1),(16,'Radiohead - OK Computer Vinyl',38.99,1,'Alternative rock masterpiece on vinyl.','Alternative','ok-computer-vinyl.jpg',20,1),(17,'Joni Mitchell - Hejira Vinyl',38.99,1,'Folk jazz masterpiece with introspective songwriting.','Folk','hejira-vinyl.jpg',25,0),(18,'Parquet Courts - Wide Awake! Vinyl',29.99,1,'Post-punk revival with political commentary.','Alternative','parquet-courts-vinyl.jpg',35,0),(19,'Kendrick Lamar - DAMN. Vinyl',35.99,1,'Pulitzer Prize-winning hip-hop album. by Toyin Ojih Odutola Gallery Vinyl','Hip-Hop','damn-vinyl.jpg',25,1),(20,'Tame Impala - Currents Vinyl',34.99,1,'Psychedelic pop masterpiece on colored vinyl.','Electronic','currents-vinyl.jpg',30,0),(21,'King Gizzard - Flight b741 Vinyl',32.99,1,'Australian psych rock experimental album on vinyl.','Rock','king-gizzard-flight-vinyl.jpg',25,0),(22,'The Beatles - White Album CD',15.99,2,'Double CD remaster of the iconic White Album.','Rock','white-album-cd.jpg',60,0),(23,'Miles Davis - Kind of Blue CD',16.99,2,'Essential jazz masterpiece and legendary recording.','Jazz','kind-of-blue-cd.jpg',45,1),(24,'AC/DC - Back in Black CD',14.99,2,'Hard rock classic remastered.','Rock','back-in-black-cd.jpg',50,0),(25,'The Cars - Cars CD',14.99,2,'New wave classic with Drive and Just What I Needed.','Rock','cars-cars-cd.jpg',35,0),(26,'U2 - The Joshua Tree CD',16.99,2,'Irish rock band\'s most acclaimed work.','Rock','joshua-tree-cd.jpg',35,1),(27,'Metallica - Master of Puppets CD',17.99,2,'Thrash metal masterpiece remastered.','Rock','master-puppets-cd.jpg',45,0),(28,'Motörhead - Ace of Spades CD',16.99,2,'Heavy metal classic with the iconic title track.','Rock','ace-of-spades-cd.jpg',40,1),(29,'Nirvana - MTV Unplugged in New York CD',14.99,2,'Acoustic performance capturing raw emotion.','Alternative','unplugged-cd.jpg',35,0),(30,'Red Hot Chili Peppers - By the way CD',15.99,2,'Funk rock album with hit singles.','Rock','blood-sugar-cd.jpg',30,0),(31,'Radiohead - Pablo Honey CD',16.99,2,'Alternative rock evolution before OK Computer.','Alternative','bends-cd.jpg',25,0),(32,'Oasis - (What\'s the Story) Morning Glory? CD',14.99,2,'Britpop classic with Wonderwall and Champagne Supernova.','Rock','morning-glory-cd.jpg',40,1),(33,'Foo Fighters - The Colour and the Shape CD',15.99,2,'Post-grunge album with Everlong and My Hero.','Alternative','colour-shape-cd.jpg',35,0),(34,'Beastie Boys - License to Ill CD',15.99,2,'Hip-hop classic debut album with Fight For Your Right.','Hip-Hop','license-to-ill-cd.jpg',40,0),(35,'Pearl Jam - Ten CD',16.99,2,'Grunge classic debut album.','Alternative','ten-cd.jpg',30,0),(36,'Soundgarden - Superunknown CD',17.99,2,'Heavy alternative rock with Black Hole Sun.','Alternative','superunknown-cd.jpg',25,0),(37,'Stone Temple Pilots - Core CD',14.99,2,'Grunge and hard rock hybrid.','Alternative','core-cd.jpg',30,0),(38,'Alice in Chains - Dirt CD',15.99,2,'Dark grunge masterpiece.','Alternative','dirt-cd.jpg',25,1),(39,'Sublime - 40oz. to Freedom CD',13.99,2,'Ska punk classic with reggae influences.','Reggae','sublime-cd.jpg',40,0),(40,'Blink-182 - Enema of the State CD',12.99,2,'Pop punk album that defined late 90s.','Pop','enema-state-cd.jpg',45,0),(41,'Audio-Technica AT-LP120XUSB Turntable',349.99,3,'Professional direct drive turntable with USB output.','Silver','turntable.jpg',15,1),(42,'Sony MDR-7506 Headphones',99.99,3,'Professional studio monitor headphones.','Black','headphones.jpg',40,1),(43,'Vinyl Record Storage Crate',29.99,3,'Wooden crate holds up to 100 LP records.','Brown','record-crate.jpg',50,0),(44,'Anti-Static Record Cleaning Kit',24.99,3,'Complete kit for maintaining your vinyl collection.','Blue','cleaning-kit.jpg',75,1),(45,'Stylus Replacement Cartridge',79.99,3,'High-quality replacement stylus for turntables.','Silver','stylus.jpg',30,0),(46,'Record Player Preamp',129.99,3,'Phono preamp for connecting turntables to modern systems.','Black','preamp.jpg',20,0),(47,'CD Storage Shelf',89.99,3,'Holds up to 200 CDs in an organized display.','Brown','cd-shelf.jpg',25,0),(48,'Marantz 2270 Vintage Receiver',899.99,3,'Classic 1970s stereo receiver with original wood case.','Silver','marantz-2270.jpg',5,1),(49,'Anti-Static Inner Sleeves',19.99,3,'Pack of 50 anti-static sleeves for vinyl protection.','White','inner-sleeves.jpg',100,0),(50,'Record Weight Stabilizer',49.99,3,'Brass weight to reduce vibration during playback.','Gold','record-weight.jpg',35,0),(51,'McIntosh MC275 Tube Amplifier',1999.99,3,'Legendary tube amplifier for audiophile listening.','Black','mcintosh-mc275.jpg',3,1),(52,'Studio Monitor Speakers',299.99,3,'Pair of active studio monitors for accurate sound.','Black','monitor-speakers.jpg',15,1),(53,'Vinyl Record Outer Sleeves',14.99,3,'Pack of 25 protective outer sleeves for album covers.','Clear','outer-sleeves.jpg',80,0),(54,'Digital Audio Converter',159.99,3,'Convert analog audio to digital with high fidelity.','Silver','dac-converter.jpg',25,0),(55,'Record Clamp',34.99,3,'Precision clamp for better vinyl playback stability.','Black','record-clamp.jpg',40,0),(56,'Headphone Amplifier',179.99,3,'Drive high-impedance headphones with clean power.','Silver','headphone-amp.jpg',20,0),(57,'USB Audio Interface',149.99,3,'Professional audio interface for recording and playback.','Red','audio-interface.jpg',18,1),(58,'Cable Management Kit',19.99,3,'Organize your audio cables with velcro ties and clips.','Black','cable-kit.jpg',60,0),(59,'Acoustic Foam Panels',69.99,3,'Set of 12 foam panels for room acoustic treatment.','Charcoal','foam-panels.jpg',30,0),(60,'Music Stand',39.99,3,'Adjustable music stand for sheet music and tablets.','Black','music-stand.jpg',25,0),(61,'Audio-Technica AT-LP120XUSB Turntable',379.99,3,'Professional direct drive turntable with USB and analog output.','Silver','turntable.jpg',15,0),(62,'Audio-Technica AT-LP120XUSB Turntable',379.99,3,'High-end turntable for professional DJs.','Silver','turntable.jpg',15,0),(63,'Vinyl Record Storage Crate',29.99,3,'Classic wooden crate for LP storage.','Brown','record-crate.jpg',50,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profiles` (
  `user_id` int NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `zip` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `profiles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (1,'Joe','Joesephus','800-555-1234','joejoesephus@email.com','789 Oak Avenue','Dallas','TX','75051'),(2,'Adam','Admamson','800-555-1212','aaadamson@email.com','456 Elm Street','Dallas','TX','75052'),(3,'George','Jetson','800-555-9876','george.jetson@email.com','123 Birch Parkway','Dallas','TX','75051');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` VALUES (3,8,1),(3,10,1);
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user','$2a$10$NkufUPF3V8dEPSZeo1fzHe9ScBu.LOay9S3N32M84yuUM2OJYEJ/.','ROLE_USER'),(2,'admin','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_ADMIN'),(3,'george','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-16 10:21:34
