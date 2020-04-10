CREATE DATABASE IF NOT EXISTS NewsApp;
use NewsApp;


CREATE TABLE IF NOT EXISTS `NewsApp`.`admins` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

 CREATE TABLE IF NOT EXISTS `NewsApp`.`articles` (
    `id` INT NOT NULL,
    `heading` VARCHAR(45) NOT NULL,
    `image` MEDIUMBLOB NOT NULL,
    `contents` TEXT NOT NULL,
    `genre` INT NOT NULL,
    `admins_id` INT NOT NULL,
    FOREIGN KEY(admins_id)
    REFERENCES admins(id),
    PRIMARY KEY (`id`));


 CREATE TABLE IF NOT EXISTS `NewsApp`.`users` (
     `id` INT NOT NULL,
     `name` VARCHAR(45) NOT NULL,
     `email` VARCHAR(45) NOT NULL,
     `password` VARCHAR(45) NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

 insert into admins (id,name,email,password) values (1,'平野祐介','yusuke@gmail.com','111');
 insert into admins (id,name,email,password) values (2,'平野レオ','reo@gmail.com','111');