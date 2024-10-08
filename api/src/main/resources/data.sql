-- Brand
INSERT INTO brand (id, name) VALUES 
(1, 'A'),
(2, 'B'),
(3, 'C'),
(4, 'D'),
(5, 'E'),
(6, 'F'),
(7, 'G'),
(8, 'H'),
(9, 'I');

-- Category
INSERT INTO category (id, name) VALUES 
(1, '상의'),
(2, '아우터'),
(3, '바지'),
(4, '스니커즈'),
(5, '가방'),
(6, '모자'),
(7, '양말'),
(8, '액세서리');

-- Product
INSERT INTO product (id, name, brand_id, category_id, price) VALUES
(1, 'test1', 1, 1, 11200),
(2, 'test2', 1, 2, 5500),
(3, 'test3', 1, 3, 4200),
(4, 'test4', 1, 4, 9000),
(5, 'test5', 1, 5, 2000),
(6, 'test6', 1, 6, 1700),
(7, 'test7', 1, 7, 1800),
(8, 'test8', 1, 8, 2300),

(9, 'test9', 2, 1, 10500),
(10, 'test10', 2, 2, 5900),
(11, 'test11', 2, 3, 3800),
(12, 'test12', 2, 4, 9100),
(13, 'test13', 2, 5, 2100),
(14, 'test14', 2, 6, 2000),
(15, 'test15', 2, 7, 2000),
(16, 'test16', 2, 8, 2200),

(17, 'test17', 3, 1, 10000),
(18, 'test18', 3, 2, 6200),
(19, 'test19', 3, 3, 3300),
(20, 'test20', 3, 4, 9200),
(21, 'test21', 3, 5, 2200),
(22, 'test22', 3, 6, 1900),
(23, 'test23', 3, 7, 2200),
(24, 'test24', 3, 8, 2100),

(25, 'test25', 4, 1, 10100),
(26, 'test26', 4, 2, 5100),
(27, 'test27', 4, 3, 3000),
(28, 'test28', 4, 4, 9500),
(29, 'test29', 4, 5, 2500),
(30, 'test30', 4, 6, 1500),
(31, 'test31', 4, 7, 2400),
(32, 'test32', 4, 8, 2000),

(33, 'test33', 5, 1, 10700),
(34, 'test34', 5, 2, 5000),
(35, 'test35', 5, 3, 3800),
(36, 'test36', 5, 4, 9900),
(37, 'test37', 5, 5, 2300),
(38, 'test38', 5, 6, 1800),
(39, 'test39', 5, 7, 2100),
(40, 'test40', 5, 8, 2100),

(41, 'test41', 6, 1, 11200),
(42, 'test42', 6, 2, 7200),
(43, 'test43', 6, 3, 4000),
(44, 'test44', 6, 4, 9300),
(45, 'test45', 6, 5, 2100),
(46, 'test46', 6, 6, 1600),
(47, 'test47', 6, 7, 2300),
(48, 'test48', 6, 8, 1900),

(49, 'test49', 7, 1, 10500),
(50, 'test50', 7, 2, 5800),
(51, 'test51', 7, 3, 3900),
(52, 'test52', 7, 4, 9000),
(53, 'test53', 7, 5, 2200),
(54, 'test54', 7, 6, 1700),
(55, 'test55', 7, 7, 2100),
(56, 'test56', 7, 8, 2000),

(57, 'test57', 8, 1, 10800),
(58, 'test58', 8, 2, 6300),
(59, 'test59', 8, 3, 3100),
(60, 'test60', 8, 4, 9700),
(61, 'test61', 8, 5, 2100),
(62, 'test62', 8, 6, 1600),
(63, 'test63', 8, 7, 2000),
(64, 'test64', 8, 8, 2000),

(65, 'test65', 9, 1, 11400),
(66, 'test66', 9, 2, 6700),
(67, 'test67', 9, 3, 3200),
(68, 'test68', 9, 4, 9500),
(69, 'test69', 9, 5, 2400),
(70, 'test70', 9, 6, 1700),
(71, 'test71', 9, 7, 1700),
(72, 'test72', 9, 8, 2400);