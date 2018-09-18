CREATE DATABASE IF NOT EXISTS walletDb;

USE walletDb;

CREATE USER 'wallet_admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON walletDb.* TO 'wallet_admin'@'localhost';