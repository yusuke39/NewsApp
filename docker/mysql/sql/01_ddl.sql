CREATE TABLE `newsapp`.`admins` (
  `id` INT NOT NULL,
  `name` VARCHAR(188) NOT NULL,
  `email` VARCHAR(188) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 CREATE TABLE IF NOT EXISTS `newsapp`.`articles` (
    `id` INT NOT NULL,
    `heading` VARCHAR(45) NOT NULL,
    `image` MEDIUMBLOB NOT NULL,
    `contents` TEXT NOT NULL,
    `genre_id` INT NOT NULL,
    `admin_id` INT NOT NULL,
    CONSTRAINT fk_admin_id
        FOREIGN KEY admin_id(admin_id) REFERENCES admins(id),
    CONSTRAINT fk_genre_id
        FOREIGN KEY genre_id(genre_id) REFERENCES genre(id),
    PRIMARY KEY (`id`))
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 CREATE TABLE IF NOT EXISTS `newsapp`.`genre` (
   `id` INT NOT NULL,
   `genrename` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`id`))
   ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



 CREATE TABLE IF NOT EXISTS `newsapp`.`users` (
   `id` INT NOT NULL,
   `name` VARCHAR(45) NOT NULL,
   `email` VARCHAR(45) NOT NULL,
   `password` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE INDEX `email_UNIQUE` (`email` ASC))
   ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
