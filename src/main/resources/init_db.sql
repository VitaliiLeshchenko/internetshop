CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idUsers_UNIQUE` (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
);
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`)
);
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `role_id_idx` (`role_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `role_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `roles_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
CREATE TABLE `shopping_carts` (
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `cart_id_UNIQUE` (`cart_id`),
  CONSTRAINT `cart_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`),
  KEY `orders_user_idx` (`user_id`),
  CONSTRAINT `orders_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `price` decimal(11,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idProducts_UNIQUE` (`id`)
);
CREATE TABLE `shopping_carts_products` (
  `cart_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  KEY `carts_cart_fk_idx` (`cart_id`),
  KEY `carts_product_fk_idx` (`product_id`),
  CONSTRAINT `carts_cart_fk` FOREIGN KEY (`cart_id`) REFERENCES `shopping_carts` (`cart_id`),
  CONSTRAINT `carts_product_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
);
CREATE TABLE `orders_products` (
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  KEY `orders_order_idx` (`order_id`),
  KEY `orders_product_idx` (`product_id`),
  CONSTRAINT `orders_order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `orders_products_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
);
