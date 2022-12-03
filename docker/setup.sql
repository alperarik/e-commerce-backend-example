CREATE DATABASE IF NOT EXISTS `order-service`;
CREATE DATABASE IF NOT EXISTS `inventory-service`;
CREATE USER 'admin'@'%' IDENTIFIED BY 'admin';
GRANT ALL ON `order-service`.* TO 'admin'@'%';
GRANT ALL ON `inventory-service`.* TO 'admin'@'%';