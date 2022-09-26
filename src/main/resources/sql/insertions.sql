USE `cash_register`;

INSERT INTO `role` (`id`, `name`)
VALUES
(1, 'admin'),
(2, 'cashier'),
(3, 'senior cashier'),
(4, 'commodity expert');


INSERT INTO `user` (`id`, `login`, `email`, `password`, `role_id`)
VALUES
(1, 'admin', 'admin@admin', 'admin', 1),
(2, 'Alex', 'alex@alex', '1111', 1),
(3, 'Bob', 'b@b', '2222', 2),
(4, 'Clode', 'c@c', '3333', 3),
(5, 'Den', 'd@d', '4444', 4);

INSERT INTO `unit` (`name`)
VALUES
('кг'),
('шт');

INSERT INTO `in_out` (`value`)
VALUES
('IN'),
('OUT');


INSERT INTO `product` (`id`, `name`, `unit_name`, `price`)
VALUES
(1, 'мінеральна вода 1л','шт', 15.50),
(2, 'чай Greenfield','шт', 160.00),
(3, 'цукор','кг', 25.00),
(4, 'сосиски', 'кг', 120.00),
(5, 'молоко 1л', 'шт', 35.80),
(6,'cellphone', 'шт', 1023.56),
(7,'glasses', 'шт', 25.38),
(8,'helicopter', 'шт', 100000.0),
(9,'Leopard', 'шт', 2000000.5),
(10,'M142 HIMARS', 'шт', 3500000.0),
(11,'pencil', 'шт', 15.45),
(12,'пиво', 'шт', 31.0),
(13,'сіль', 'кг', 20.0),
(14,'олія Олейна 1,0 л', 'шт', 60.35),
(15,'шампунь', 'шт', 45.88);

INSERT INTO `warehouse` (`id`, `quantity`, `product_id`)
VALUES
(1, 30, 1),
(2, 5, 2),
(3, 40, 3),
(4, 10, 4),
(5, 20, 5),
(6, 30, 6),
(7, 5, 7),
(8, 40, 8),
(9, 10, 9),
(10, 20, 10),
(11, 30, 11),
(12, 5, 12),
(13, 40, 13),
(14, 10, 14);

INSERT INTO `order` (`id`, `user_id`, `time`, `totalAmount`, `direction`)
VALUES
( 1, 3, '2022-09-15 13:15:25', 521.5, 'OUT'),
( 2, 3, '2022-09-15 14:20:25', 625, 'OUT'),
( 3, 3, '2022-09-16 11:03:15', 1640.16, 'OUT'),
( 4, 3, '2022-09-17 09:45:50', 19800003.5, 'OUT'),
( 5, 3, '2022-09-17 10:33:18', 348.63, 'OUT');

INSERT INTO `order_details` (`id`, `order_id`, `product_id`, `quantity`, `price`)
VALUES
(01, 1, 1, 13, 15.50),
(02, 1, 2,  2, 160.00),
(03, 2, 3, 25, 25.00),
(04, 3, 4,  8, 120.00),
(05, 3, 5, 11, 35.80),
(06, 3, 6,  3, 1023.56),
(07, 3, 7,  2, 25.38),
(08, 4, 8, 23, 100000.0),
(09, 4, 9, 7, 2000000.5),
(10, 4,10, 1, 3500000.0),
(11, 5,11, 15, 15.45),
(12, 5,12, 1, 31.0),
(13, 5,13, 2, 20.0),
(14, 5,14, 1, 45.88);

INSERT INTO `order` (`id`, `user_id`, `time`, `totalAmount`, `direction`)
VALUES
( 6, 5, '2022-09-15 16:35:25', 0, 'IN'),
( 7, 5, '2022-09-17 13:23:18', 0, 'IN');

INSERT INTO `order_details` (`id`, `order_id`, `product_id`, `quantity`, `price`)
VALUES
(15, 1, 8,  4, 800000),
(16, 1, 9,  8, 1500000),
(17, 1,10, 2, 2500000),
(18, 2, 1,  8, 10),
(19, 2, 2, 11, 100.80),
(20, 2, 3,  3, 18.56),
(21, 2, 4,  2, 90.38),
(22, 4, 5, 23, 20.0);
