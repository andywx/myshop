CREATE DATABASE myshop;

USE myshop;

CREATE TABLE memberrichfaceskitchensink (
	id INT NOT NULL AUTO_INCREMENT,
	email VARCHAR(255) NOT NULL,
	name VARCHAR(25) NOT NULL,
	phone_number VARCHAR(12) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO memberrichfaceskitchensink VALUES (0, 'John Smith', 'john.smith@mailinator.com', '2125551212');

SELECT * FROM memberrichfaceskitchensink;

-- ----------------------------------------
-- Rank table
-- ----------------------------------------

CREATE TABLE rank (
	id			TINYINT	AUTO_INCREMENT,
	name		VARCHAR(20)	NOT NULL,
	score		INT		NOT NULL,
	discount	DECIMAL(3, 2)	NOT NULL,
	CONSTRAINT rank_pk PRIMARY KEY(id)
);

INSERT INTO rank(name, score, discount) VALUES('1等', 0, 0.90);
INSERT INTO rank(name, score, discount) VALUES('2等', 100, 0.85);
INSERT INTO rank(name, score, discount) VALUES('3等', 500, 0.80);
INSERT INTO rank(name, score, discount) VALUES('4等', 1000, 0.75);
INSERT INTO rank(name, score, discount) VALUES('5等', 5000, 0.70);

SELECT * FROM rank;

-- ----------------------------------------
-- Customer table
-- ----------------------------------------

CREATE TABLE customer (
	username		VARCHAR(20),
	password		VARCHAR(150)	NOT NULL,
	real_name	VARCHAR(20),
	gender		CHAR(1)			DEFAULT 'M',
	birthday		DATE,
	question		VARCHAR(50),
	answer		VARCHAR(50),
	rank_id		TINYINT			DEFAULT 1,
	score			INT				DEFAULT 0,
	mobile			VARCHAR(20),
	email			VARCHAR(50),
	province		VARCHAR(20),
	city			VARCHAR(40),
	county		VARCHAR(40),
	address		VARCHAR(100),
	zipcode		CHAR(6),
	CONSTRAINT customer_pk PRIMARY KEY(username),
	CONSTRAINT customer_rank_fk FOREIGN KEY(rank_id)
		REFERENCES rank(id)
);

INSERT INTO customer VALUES(
	'zhangsan',
	MD5('123456'),
	'张三', 'M',
	'1994-02-19',
	'最喜欢的数字', '2',
	1, 0,
	'13012345678',
	'zhangs@abc.com',
	'天津', '天津市', '南开区',
	'天津-天津市-南开区-卫津路92号',
	'300072');

INSERT INTO customer VALUES(
	'guest',
	MD5('123456'),
	'李先生', 'M',
	'1992-02-19',
	'最喜欢的数字', '2',
	1, 0,
	'13112345678',
	'mrlee@abc.com',
	'天津', '天津市', '南开区',
	'天津-天津市-南开区-天津大学',
	'300072');

INSERT INTO customer VALUES(
	'abc',
	MD5('123456'),
	'陈小红', 'M',
	'1993-02-19',
	'最喜欢的数字', '2',
	1, 0,
	'13212345678',
	'chenxiaohong@abc.com',
	'天津', '天津市', '南开区',
	'天津-天津市-南开区-天津大学',
	'300072');

SELECT * FROM customer;

CREATE TABLE user_roles (
  id		  INT	AUTO_INCREMENT,
  username VARCHAR(20) NOT NULL,
  rolename VARCHAR(20) NOT NULL,
  CONSTRAINT user_roles_pk PRIMARY KEY(id),
  CONSTRAINT user_roles_fk FOREIGN KEY(username)
		REFERENCES customer(username)  
);

INSERT INTO user_roles VALUES (0, 'zhangsan', 'USER');
INSERT INTO user_roles VALUES (0, 'guest', 'USER');
INSERT INTO user_roles VALUES (0, 'abc', 'ADMIN');
INSERT INTO user_roles VALUES (0, 'zhangsa', 'USER');

SELECT * FROM user_roles;

-- ----------------------------------------
-- Province table
-- ----------------------------------------

CREATE TABLE province (
	province_id	CHAR(6),
	name		VARCHAR(20)	NOT NULL,
	CONSTRAINT province_pk PRIMARY KEY(province_id)
);

-- INSERT INTO province VALUES ('110000', '北京市');
-- INSERT INTO province VALUES ('120000', '天津市');

SELECT * FROM province;

DELETE FROM province;

-- ----------------------------------------
-- City table
-- ----------------------------------------

CREATE TABLE city (
	city_id	CHAR(6),
	name		VARCHAR(40)	NOT NULL,
	parent_id	CHAR(6)		NOT NULL,
	CONSTRAINT city_pk PRIMARY KEY(city_id),
	CONSTRAINT city_province_fk FOREIGN KEY(parent_id)
		REFERENCES province(province_id)
);

-- INSERT INTO city VALUES ('110100', '市辖区', '110000');
-- INSERT INTO city VALUES ('110200', '县', '110000');
-- INSERT INTO city VALUES ('120100', '市辖区', '120000');
-- INSERT INTO city VALUES ('120200', '县', '120000');

SELECT * FROM city;

DELETE FROM city;

-- ----------------------------------------
-- County table
-- ----------------------------------------

CREATE TABLE county (
	county_id	CHAR(6),
	name		VARCHAR(40)	NOT NULL,
	parent_id	CHAR(6)		NOT NULL,
	CONSTRAINT county_pk PRIMARY KEY(county_id),
	CONSTRAINT county_city_fk FOREIGN KEY(parent_id)
		REFERENCES city(city_id)
);

-- INSERT INTO county VALUES ('110101', '东城区', '110100');
-- INSERT INTO county VALUES ('110102', '西城区', '110100');
-- INSERT INTO county VALUES ('110103', '崇文区', '110100');
-- INSERT INTO county VALUES ('110104', '宣武区', '110100');
-- INSERT INTO county VALUES ('110105', '朝阳区', '110100');
-- INSERT INTO county VALUES ('110106', '丰台区', '110100');
-- INSERT INTO county VALUES ('110107', '石景山区', '110100');
-- INSERT INTO county VALUES ('110108', '海淀区', '110100');
-- INSERT INTO county VALUES ('110109', '门头沟区', '110100');
-- INSERT INTO county VALUES ('110111', '房山区', '110100');
-- INSERT INTO county VALUES ('110112', '通州区', '110100');
-- INSERT INTO county VALUES ('110113', '顺义区', '110100');
-- INSERT INTO county VALUES ('110114', '昌平区', '110100');
-- INSERT INTO county VALUES ('110115', '大兴区', '110100');
-- INSERT INTO county VALUES ('110116', '怀柔区', '110100');
-- INSERT INTO county VALUES ('110117', '平谷区', '110100');
-- INSERT INTO county VALUES ('110228', '密云县', '110200');
-- INSERT INTO county VALUES ('110229', '延庆县', '110200');
-- INSERT INTO county VALUES ('120101', '和平区', '120100');
-- INSERT INTO county VALUES ('120102', '河东区', '120100');
-- INSERT INTO county VALUES ('120103', '河西区', '120100');
-- INSERT INTO county VALUES ('120104', '南开区', '120100');
-- INSERT INTO county VALUES ('120105', '河北区', '120100');
-- INSERT INTO county VALUES ('120106', '红桥区', '120100');
-- INSERT INTO county VALUES ('120107', '塘沽区', '120100');
-- INSERT INTO county VALUES ('120108', '汉沽区', '120100');
-- INSERT INTO county VALUES ('120109', '大港区', '120100');
-- INSERT INTO county VALUES ('120110', '东丽区', '120100');
-- INSERT INTO county VALUES ('120111', '西青区', '120100');
-- INSERT INTO county VALUES ('120112', '津南区', '120100');
-- INSERT INTO county VALUES ('120113', '北辰区', '120100');
-- INSERT INTO county VALUES ('120114', '武清区', '120100');
-- INSERT INTO county VALUES ('120115', '宝坻区', '120100');
-- INSERT INTO county VALUES ('120221', '宁河县', '120200');
-- INSERT INTO county VALUES ('120223', '静海县', '120200');
-- INSERT INTO county VALUES ('120225', '蓟　县', '120200');

SELECT * FROM county;

DELETE FROM county;

LOAD DATA INFILE 'C:\\xinwang\\workspace_infosys\\myshop\\data\\province.txt'
INTO TABLE province FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

LOAD DATA INFILE 'C:\\xinwang\\workspace_infosys\\myshop\\data\\city.txt'
INTO TABLE city FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';

LOAD DATA INFILE 'C:\\xinwang\\workspace_infosys\\myshop\\data\\county.txt'
INTO TABLE county FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';


-- ----------------------------------------
-- Login table
-- ----------------------------------------
CREATE TABLE login
(
	id		      INT		AUTO_INCREMENT,
	username	   VARCHAR(20)	NOT NULL,
	ip_addr	   VARCHAR(15)	NOT NULL,
	login_time	DATETIME	NOT NULL,
	CONSTRAINT login_pk PRIMARY KEY(id),
	CONSTRAINT login_customer_fk FOREIGN KEY(username)
		REFERENCES customer(username)
);

INSERT INTO login VALUES (0, 'zhangsan', '127.0.0.1', CURRENT_TIMESTAMP);
INSERT INTO login VALUES (0, 'guest', '127.0.0.1', CURRENT_TIMESTAMP);
INSERT INTO login VALUES (0, 'abc', '127.0.0.1', CURRENT_TIMESTAMP);

-- ----------------------------------------
-- Category table
-- ----------------------------------------
CREATE TABLE category
(
	id		         INT			   AUTO_INCREMENT,
	name		      VARCHAR(20)		NOT NULL,
	description		VARCHAR(100),
	CONSTRAINT category_pk PRIMARY KEY(id)
);

INSERT INTO category VALUES (0, 'Apple', 'iPhone mobile phone');
INSERT INTO category VALUES (0, 'Samsung', 'Samsung mobile phone');
INSERT INTO category VALUES (0, 'Huawei', 'Huawei mobile phone');
INSERT INTO category VALUES (0, 'Lenovo', 'Lenovo laptop');

-- ----------------------------------------
-- Product table
-- ----------------------------------------
CREATE TABLE product
(
	id		         INT		AUTO_INCREMENT,
	category_id	   INT		NOT NULL,
	image		      VARCHAR(20),	
	name		      VARCHAR(20)	NOT NULL,
	market_price	NUMERIC(20, 2)	NOT NULL, -- DECIMAL
	is_on_sale	   BOOLEAN	DEFAULT 0 NOT NULL, -- TINYINT(1)
	discount	      NUMERIC(3, 2)	DEFAULT 1,
	pub_date	      DATE	NOT NULL,
	stock_count	   INT		NOT NULL,
	sale_count	   INT		DEFAULT 0	NOT NULL,
	summary	      VARCHAR(100)	NOT NULL,
	detail	      TEXT, -- CLOB Character Large OBject ; BLOB Binary Large OBject
	CONSTRAINT  product_pk PRIMARY KEY(id),
	CONSTRAINT  product_category_fk FOREIGN KEY(category_id) REFERENCES category(id)
);

INSERT INTO product VALUES(0, 1, '1.jpg', 'Apple iPhone 6',
	821.95, 0, 1, CURRENT_DATE, 10, 0, 'Apple iPhone 6, Gold, 64 GB', 'Built on 64-bit desktop-class architecture, the new A8 chip delivers more power, even while driving a larger display. Other Features include: Wi-Fi 802.11 a/b/g/n/ac, dual-band, Wi-Fi hotspot, Bluetooth: v4.0, A2DP, USB: v2.0, GPS: with A-GPS, GLONASS, Browser: HTML (Safari), Messaging: iMessage, SMS (threaded view), MMS, Email, Push Email, Built-in rechargeable lithium-ion Battery, Talktime: Up to 14 Hours (3G), Standby: Up to 250 Hours (3G)');
INSERT INTO product VALUES(0, 1, '2.jpg', 'iPhone 5',
	329.00, 1, 0.9, CURRENT_DATE, 5, 0, 'iPhone 5 16GB Black A1428 GSM FACTORY UNLOCKED', '4-inch Retina display A6 chip, iOS 6 and iCloud 8.0MP iSight camera All-new EarPods and improved audio Unlocked cell phones are compatible with GSM carriers like AT&T and T-Mobile as well as with GSM SIM cards (e.g. H20 and select prepaid carriers). Unlocked cell phones will not work with CDMA Carriers like Sprint, Verizon, Boost or Virgin.Packaging not sealed to verify content UPC# 85909-530175 Model Number# A1428');
INSERT INTO product VALUES(0, 2, '3.jpg', 'Samsung Note 4',
	729.99, 0, 1, CURRENT_DATE, 20, 0, 'Samsung Galaxy Note 4, Charcoal Black 32GB (Verizon Wireless)', 'Our 5.7-inch Quad HD Super AMOLED display features more pixels per inch than any other display we’ve made. That means images are clearer, brighter and more captivating than ever before. And now you can capture video in the same high resolution as the display so your personal videos look better than ever before.');
INSERT INTO product VALUES(0, 2, '4.jpg', 'Samsung Galaxy Grand',
	172.18, 1, 0.8, CURRENT_DATE, 15, 0, 'Samsung Galaxy Grand Prime G530H/DS Factory Unlocked Phone - Retail Packaging - Gray', 'The GALAXY Grand Prime has a 5 MP front camera with an ultra-wide view angle of 85 degrees. Other Features include: Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot, Bluetooth: v4.0, A2DP, USB: micro USB v2.0, GPS: with A-GPS, Beidou, Browser: HTML5, Messaging: SMS (threaded view), MMS, Email, Push Mail, IM, Li-Ion 2600 mAh Battery, Talk time: (2G) / Up to 17 Hours (3G)');
INSERT INTO product VALUES(0, 3, '5.jpg', 'Huawei Ascend G7',
	425.00, 1, 0.9, CURRENT_DATE, 15, 0, 'Huawei Ascend G7 GSM Unlocked Smartphone 5.5 inch 2GB + 16GB', 'OS: Android 4.4, EMUI 3.0 CPU: MSM8916 Quad Core 1.2GHz Storage: RAM 2GB + ROM 16GB, TF card up to 32G (not included) Display: 5.5 inch IPS LTPS Screen Capacitive Screen 1280 x 720 pixels Camera: Front 5.0MP, Back 13MP AF BSI F2.0, with Flash) ');
INSERT INTO product VALUES(0, 3, '6.jpg', 'Huawei Ascend P6',
	161.99, 0, 1, CURRENT_DATE, 3, 0, 'Huawei Ascend P6 Unlocked smartphone 1.5GHz Quad core K3V2E 6.18mm Thickness', '6.18mm Thickness 1.5GHz Quad core K3V2E Android 4.2 ( Emotion UI 1.6 ) 1280*720 incell screen Packing Included Cell Phone Micro USB cable Charger Li Battery earphone');
INSERT INTO product VALUES(0, 4, '7.jpg', 'Lenovo Z50 Laptop',
	593.99, 0, 1, CURRENT_DATE, 50, 0, 'Lenovo Z50 Laptop Computer - 59436279 - Black - 4th Generation Intel Core', 'It''s about balance, not compromise. The IdeaPad Z Series Laptops are solid, stylish, portable PCs. If you''re looking for a laptop with a unique mix of entertainment features, modern style, and affordable pricing, look no further. Z Series laptops are ideal for study, entertainment, online chats, and everyday use. They run on powerful multi-core processors from Intel, so they deliver smart, efficient multitasking and reliable on-demand performance - just what you are looking for in an entertainment laptop. What''s more, Z Series laptops sport contemporary styling and come in a range of vivid, colorful designs. Whether you''re looking for an entertainment and social networking device or shopping for a laptop with the best multimedia features you can afford, the IdeaPad Z Series is a good choice.');
INSERT INTO product VALUES(0, 4, '8.jpg', 'Lenovo G50 15.6-Inch',
	339.99, 1, 0.9, CURRENT_DATE, 30, 0, 'Lenovo G50 15.6-Inch Laptop (Core i3, 6 GB, 500 GB)', 'The affordable Lenovo G50 notebook is less than 1" slim, but packed with features like an integrated DVD drive, the latest processor and HD graphics. Plus, stereo speakers coupled with Dolby audio certification make the G50 good for everyday multimedia applications while on the go or at home.');

SELECT * FROM product;
DELETE FROM product;

ALTER TABLE product CHANGE image image VARCHAR(200);


-- ----------------------------------------
-- Order status table
-- ----------------------------------------
CREATE TABLE order_status
(
	id		   INT	AUTO_INCREMENT,
	name		VARCHAR(20)	NOT NULL,
	CONSTRAINT order_status_pk PRIMARY KEY(id)
);

INSERT INTO order_status VALUES (0, '未下达');
INSERT INTO order_status VALUES (0, '待处理');
INSERT INTO order_status VALUES (0, '配货');
INSERT INTO order_status VALUES (0, '等待货款');
INSERT INTO order_status VALUES (0, '发货');
INSERT INTO order_status VALUES (0, '已完成');

-- ----------------------------------------
-- Payment method table
-- ----------------------------------------
CREATE TABLE payment_method
(
	id		   INT	AUTO_INCREMENT,
	name		VARCHAR(20)	NOT NULL,
	CONSTRAINT payment_method_pk PRIMARY KEY(id)
);

INSERT INTO payment_method VALUES (0, '货到付款');
INSERT INTO payment_method VALUES (0, '邮局汇款');

-- ----------------------------------------
-- Send method table
-- ----------------------------------------
CREATE TABLE send_method
(
	id		   INT	AUTO_INCREMENT,
	name		VARCHAR(20)	NOT NULL,
	CONSTRAINT send_method_pk PRIMARY KEY(id)
);

INSERT INTO send_method VALUES (0, '送货');
INSERT INTO send_method VALUES (0, '普通包裹');
INSERT INTO send_method VALUES (0, '特快传递');

-- ----------------------------------------
-- Order table
-- ----------------------------------------
CREATE TABLE `order`
(
	id			      INT		AUTO_INCREMENT,
	customer_id 	VARCHAR(20)		NOT NULL,
	status_id		INT	NOT NULL,
	price			   NUMERIC(10, 2)	NOT NULL,
	payment_method_id	INT	NOT NULL,
	send_method_id		INT	NOT NULL,
	place_time		DATETIME	NOT NULL,
	send_time		DATETIME,
	invoice_title		VARCHAR(50)	NOT NULL,
	invoice_item		VARCHAR(20)	NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY(id),
	CONSTRAINT order_customer_fk FOREIGN KEY(customer_id)
		REFERENCES customer(username),
	CONSTRAINT order_order_status_fk FOREIGN KEY(status_id)
		REFERENCES order_status(id),
	CONSTRAINT order_payment_method_fk FOREIGN KEY(payment_method_id)
		REFERENCES payment_method(id),
	CONSTRAINT order_send_method_fk FOREIGN KEY(send_method_id)
		REFERENCES send_method(id)
);

INSERT INTO `order` VALUES (0, 'zhangsan',
	2, 1443.99, 1, 1, CURRENT_TIMESTAMP, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), 'Tianjin University', 'electronic products');

-- ----------------------------------------
-- Item table
-- ----------------------------------------
CREATE TABLE item
(
	id		      INT		AUTO_INCREMENT,
	order_id	   INT		NOT NULL,
	product_id	INT		NOT NULL,
	`count`		INT	   DEFAULT 1	NOT NULL,
	price		   NUMERIC(10, 2)	NOT NULL,
	CONSTRAINT item_pk PRIMARY KEY(id),
	CONSTRAINT item_order_fk FOREIGN KEY(order_id)
		REFERENCES `order`(id),
	CONSTRAINT item_product_fk FOREIGN KEY(product_id)
		REFERENCES product(id)
);

INSERT INTO item VALUES(0, 1, 5, 2, 850.00);
INSERT INTO item VALUES(0, 1, 7, 1, 593.99);

-- ----------------------------------------
-- Select queries
-- ----------------------------------------
SELECT * FROM rank;
SELECT * FROM customer;
SELECT * FROM user_roles;
SELECT * FROM login;
SELECT * FROM category;
SELECT * FROM product;
SELECT * FROM order_status;
SELECT * FROM payment_method;
SELECT * FROM send_method;
SELECT * FROM `order`;
SELECT * FROM item;
SELECT * FROM province;
SELECT * FROM city;
SELECT * FROM county;
