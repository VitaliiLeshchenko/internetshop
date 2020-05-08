CREATE SCHEMA internet_shop;
USE internet_shop;
CREATE TABLE `internet_shop`.`products` (
                                            `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                            `name` VARCHAR(45) NOT NULL,
                                            `price` DECIMAL(11) NOT NULL,
                                            PRIMARY KEY (`id`),
                                            UNIQUE INDEX `idProducts_UNIQUE` (`id` ASC) VISIBLE);
