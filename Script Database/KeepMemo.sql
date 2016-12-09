/*
SQLyog Community v12.3.2 (64 bit)
MySQL - 5.6.17 : Database - diary
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`diary` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `diary`;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `commentid` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `publish_at` datetime NOT NULL,
  `postid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `comment` */

/*Table structure for table `friend` */

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `listid` bigint(20) NOT NULL AUTO_INCREMENT,
  `friendid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `status` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`listid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `friend` */

insert  into `friend`(`listid`,`friendid`,`userid`,`status`) values 
(1,6,5,1),
(2,5,6,1),
(3,5,12,1),
(4,12,5,1),
(5,6,12,1),
(6,12,6,1),
(7,11,12,2),
(8,12,11,3),
(9,5,13,2),
(10,13,5,3);

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `postid` bigint(20) NOT NULL,
  `title` varchar(500) NOT NULL,
  `content` longtext NOT NULL,
  `publish_at` datetime NOT NULL,
  `update_at` datetime NOT NULL,
  `status` smallint(6) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`postid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `post` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `hobby` varchar(100) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`userid`,`username`,`password`,`fullname`,`email`,`phone`,`hobby`) values 
(5,'admin','21232f297a57a5a743894a0e4a801fc3','admin','admin@gmail.com','0918218111',''),
(6,'namth27494','0df1aff32c69828f8ddb0f0497be9cee','Tr?n Nam','namth27494@gmail.com','',''),
(7,'aksdfksdnf','385fb4966ef82785e134815275d1e163','Tr?n NAm','a;sdjf;sdf','',''),
(8,'sldflasd','d93b3c8648c03a34b3062d3f8e22accd','Tr?n Nam','ládflsfd','',''),
(9,'àdsa','','Tr?n Nam',';asdjf;sdjf;','',''),
(10,'asdfdf','bd72e59f7cb71c8b25fe736820150773','Tr?n Nam','jasdfsdf','',''),
(11,'duongtran','3e48f1ce9f015cc59bd7bf0605681f28','Duong Tran','nam','',''),
(12,'nam','22c78aadb8d25a53ca407fae265a7154','Nam','namth27494@gmail.com','',''),
(13,'user 1','c4ca4238a0b923820dcc509a6f75849b','User','user@gmail.com','','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
