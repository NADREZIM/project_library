CREATE DATABASE IF NOT EXISTS `liba`;

USE `liba`;

CREATE TABLE IF NOT EXISTS `user` (
   `id_user` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(45) DEFAULT NULL,
   `login` varchar(45) DEFAULT NULL,
   `password` varchar(45) DEFAULT NULL,
   `birthday` date DEFAULT NULL,
   PRIMARY KEY (`id_user`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS `book` (
   `id_book` int(11) NOT NULL AUTO_INCREMENT,
   `author` varchar(45) DEFAULT NULL,
   `title` varchar(45) DEFAULT NULL,
   `count` int(11) DEFAULT NULL,
   PRIMARY KEY (`id_book`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS `report` (
   `idreport` int(11) NOT NULL AUTO_INCREMENT,
   `retbook` date DEFAULT NULL,
   `rent_book` timestamp(6) null DEFAULT NULL,
   `id_book` int(11) DEFAULT NULL,
   `author` varchar(45) DEFAULT NULL,
   `title` varchar(45) DEFAULT NULL,
   `count` int(11) DEFAULT NULL,
   `id_user` int(11) DEFAULT NULL,
   `name` varchar(45) DEFAULT NULL,
   `login` varchar(45) DEFAULT NULL,
   `password` varchar(45) DEFAULT NULL,
   `birthday` date DEFAULT NULL,
   PRIMARY KEY (`idreport`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS `user_with_books` (
   `userID` int(11) DEFAULT NULL,
   `bookID` int(11) DEFAULT NULL,
   `rent_book` timestamp(6) null DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS `role_data_base` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `role` varchar(45) DEFAULT 'user',
   `user_id` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

 INSERT INTO user(name,login,password,birthday)values ('Artuom_Borisov','nadrezim89','ingungh33','1989-10-04');
 INSERT INTO user(name,login,password,birthday)values ('Jack_Nilson','fallen22','sky33','1990-11-10');
 INSERT INTO role_data_base(role,user_id)values ('admin',1);
 INSERT INTO role_data_base(user_id)values (1);
 INSERT INTO role_data_base(user_id)values (2);
 INSERT INTO book(author,title,count)values ('Lev Tolstou','War and Piece',3);
 INSERT INTO book(author,title,count)values ('Roman Zlotnikov','Arvendal',4);
 INSERT INTO book(author,title,count)values ('Mira Grant','Korm',5);
 INSERT INTO book(author,title,count)values ('Joanne Rowling','Harry Potter',6);
 INSERT INTO book(author,title,count)values ('Oscar Wilde','The Duchess of Padua',7);
