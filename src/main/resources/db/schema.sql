CREATE TABLE `USER` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(10) NOT NULL,
                        `email` VARCHAR(100) NOT NULL,
                        `salt` VARCHAR(100) NOT NULL,
                        `password` VARCHAR(100) NOT NULL,
                        `phone` VARCHAR(11) NOT NULL,
                        `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        KEY `email` (`email`)
);

CREATE TABLE `ADDRESS` (
                           `id` bigint NOT NULL,
                           `is_main` tinyint(1) NOT NULL,
                           `name` varchar(100) NOT NULL,
                           `user_id` bigint NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `address_user_id_idx` (`user_id`),
                           CONSTRAINT `address_user_id` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
);

CREATE TABLE `CATEGORY` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(10) NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE `PRODUCT` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `category_id` bigint NOT NULL,
                           `name` varchar(50) NOT NULL,
                           `price` bigint NOT NULL,
                           `main_img` varchar(300) NOT NULL,
                           `detail_img` varchar(300) NOT NULL,
                           `stock` bigint NOT NULL DEFAULT '0',
                           `score` float NOT NULL DEFAULT '0',
                           `delivery_fee` bigint NOT NULL DEFAULT '0',
                           `is_rocket` tinyint(1) NOT NULL DEFAULT '0',
                           `is_rocket_fresh` tinyint(1) NOT NULL DEFAULT '0',
                           `is_rocket_global` tinyint(1) NOT NULL DEFAULT '0',
                           PRIMARY KEY (`id`),
                           KEY `idx_name` (`name`)
);

CREATE TABLE `CART` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                        `product_id` BIGINT NOT NULL,
                        `product_num` INT NOT NULL,
                        `user_id` BIGINT NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `cart_user_id_idx` (`product_id`,`user_id`),
                        KEY `cart_user_id` (`user_id`),
                        CONSTRAINT `cart_product_id` FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`id`),
                        CONSTRAINT `cart_user_id` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
);

CREATE TABLE `COUPON` (
                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(10) NOT NULL,
                          `min_price` BIGINT NOT NULL,
                          `discount_price` BIGINT NOT NULL,
                          `product_id` BIGINT NOT NULL,
                          `expiration_time` TIMESTAMP NOT NULL, 
                          `max_count` BIGINT NOT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `USER_COUPON` (
                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                               `user_id` BIGINT NOT NULL,
                               `coupon_id` BIGINT NOT NULL,
                               `issued_count` INT DEFAULT '0',
                               `use_count` INT DEFAULT '0',
                               PRIMARY KEY (`id`)
);

CREATE TABLE `ORDER` (
                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                         `user_id` BIGINT NOT NULL,
                         `consumer_name` VARCHAR(10) NOT NULL,
                         `consumer_phone` VARCHAR(20) NOT NULL,
                         `receiver_name` VARCHAR(10) NOT NULL,
                         `receiver_address` VARCHAR(100) NOT NULL,
                         `receiver_phone` VARCHAR(20) NOT NULL,
                         `receiver_request` VARCHAR(20) NOT NULL,
                         `status` TINYINT(1) NOT NULL DEFAULT 0,
                         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `payment_id` VARCHAR(10) NOT NULL,
                         PRIMARY KEY (`id`)
);

CREATE TABLE `ORDER_PRODUCT` (
                                 `id` BIGINT NOT NULL AUTO_INCREMENT,
                                 `order_id` BIGINT NOT NULL,
                                 `product_id` BIGINT NOT NULL,
                                 `product_num` INT NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `order_product_order_id_idx` (`order_id`),
                                 KEY `order_procut_product_id_idx` (`product_id`),
                                 CONSTRAINT `order_procut_product_id` FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`id`),
                                 CONSTRAINT `order_product_order_id` FOREIGN KEY (`order_id`) REFERENCES `ORDER` (`id`)
);

CREATE TABLE `REVIEW` (
                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                          `user_id` BIGINT NOT NULL,
                          `product_id` BIGINT NOT NULL,
                          `img` VARCHAR(45) NOT NULL,
                          `content` TEXT(500) NOT NULL,
                          `help_num` INT NOT NULL,
                          `no_help_num` VARCHAR(45) NOT NULL,
                          `score` VARCHAR(45) NOT NULL,
                          `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`),
                          KEY `review_user_id_idx` (`user_id`),
                          KEY `review_product_id_idx` (`product_id`),
                          CONSTRAINT `review_product_id` FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`id`),
                          CONSTRAINT `review_user_id` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
);

CREATE TABLE `REVIEW_EVALUATION` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `review_id` int NOT NULL,
                               `user_id` int NOT NULL,
                               `is_help` tinyint(1) NOT NULL,
                               PRIMARY KEY (`id`)
);


CREATE TABLE `PAYMENT` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `type` INT NOT NULL,
                           `discount_price` BIGINT NOT NULL DEFAULT 0,
                           `total_price` BIGINT NOT NULL,
                           `status` TINYINT(1) NOT NULL DEFAULT 0,
                           `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `payment_order_id_idx` (`order_id`),
                           CONSTRAINT `payment_order_id` FOREIGN KEY (`order_id`) REFERENCES `ORDER` (`id`)
);
