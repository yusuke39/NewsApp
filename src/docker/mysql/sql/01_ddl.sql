CREATE TABLE `NewsApp`.`admins` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);


CREATE TABLE `NewsApp`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);


CREATE TABLE `NewsApp`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genre_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `genre_name_UNIQUE` (`genre_name` ASC) VISIBLE);

CREATE TABLE `NewsApp`.`articles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `image` LONGBLOB NULL,
  `content` MEDIUMTEXT NOT NULL,
  `genre_id` INT NOT NULL,
  `admin_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `genre_id_idx` (`genre_id` ASC) VISIBLE,
  INDEX `admin_id_idx` (`admin_id` ASC) VISIBLE,
  CONSTRAINT `genre_id`
    FOREIGN KEY (`genre_id`)
    REFERENCES `NewsApp`.`genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `admin_id`
    FOREIGN KEY (`admin_id`)
    REFERENCES `NewsApp`.`admins` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
