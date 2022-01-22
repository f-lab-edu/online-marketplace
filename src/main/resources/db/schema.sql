CREATE TABLE `USER` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(10) NOT NULL,
                        `email` varchar(100) NOT NULL,
                        `salt` varchar(100) NOT NULL,
                        `password` varchar(100) NOT NULL,
                        `phone` varchar(11) NOT NULL,
                        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
                            `id` bigint NOT NULL,
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
                        `id` bigint NOT NULL,
                        `product_id` bigint NOT NULL,
                        `product_num` int NOT NULL,
                        `user_id` bigint NOT NULL,
                        `is_regular_delivery` tinyint(1) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `cart_user_id_idx` (`product_id`,`user_id`),
                        KEY `cart_user_id` (`user_id`),
                        CONSTRAINT `cart_product_id` FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`id`),
                        CONSTRAINT `cart_user_id` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
);

CREATE TABLE `COUPON` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `name` varchar(10) NOT NULL,
                          `min_price` bigint NOT NULL,
                          `discount_price` bigint NOT NULL,
                          `product_id` bigint NOT NULL,
                          `expiration_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `USER_COUPON` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `user_id` bigint NOT NULL,
                               `coupon_id` bigint NOT NULL,
                               `use_status` tinyint(1) NOT NULL DEFAULT '0',
                               PRIMARY KEY (`id`)
);

CREATE TABLE `ORDER` (
                         `id` bigint NOT NULL,
                         `user_id` bigint NOT NULL,
                         `receiver_name` varchar(10) NOT NULL,
                         `recevier_address` varchar(100) NOT NULL,
                         `receiver_phone` varchar(10) NOT NULL,
                         `receiver_request` varchar(20) NOT NULL,
                         `status` tinyint(1) NOT NULL DEFAULT '0',
                         `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`)
);

CREATE TABLE `ORDER_PRODUCT` (
                                 `id` bigint NOT NULL,
                                 `order_id` bigint NOT NULL,
                                 `product_id` bigint NOT NULL,
                                 `product_num` int NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `order_product_order_id_idx` (`order_id`),
                                 KEY `order_procut_product_id_idx` (`product_id`),
                                 CONSTRAINT `order_procut_product_id` FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`id`),
                                 CONSTRAINT `order_product_order_id` FOREIGN KEY (`order_id`) REFERENCES `ORDER` (`id`)
);

CREATE TABLE `REVIEW` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `user_id` bigint NOT NULL,
                          `product_id` bigint NOT NULL,
                          `content` text NOT NULL,
                          `help_num` int NOT NULL DEFAULT '0',
                          `no_help_num` int NOT NULL DEFAULT '0',
                          `score` int NOT NULL,
                          `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `type` int NOT NULL,
                           `order_id` bigint NOT NULL,
                           `discount_price` bigint NOT NULL DEFAULT '0',
                           `total_price` bigint NOT NULL,
                           `status` int NOT NULL DEFAULT '0',
                           `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `payment_order_id_idx` (`order_id`),
                           CONSTRAINT `payment_order_id` FOREIGN KEY (`order_id`) REFERENCES `ORDER` (`id`)
);