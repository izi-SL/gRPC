READ ME FOR WALLET APP/CLIENT

Create Database
===============

*Run below commands in mySQL terminal

CREATE DATABASE IF NOT EXISTS walletDb;

USE walletDb;

CREATE USER 'wallet_admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON walletDb.* TO 'wallet_admin'@'localhost';

build JAR(s)
===========

Client :
Run ./gradleW clean build for build the client jar

Server :
Run ./gradleW clean build for build the server jar

Run wallet server
=================
java -jar build/libs/wallet-app-1.0-SNAPSHOT.jar 

Run wallet client
================
java -jar build/libs/wallet-client-1.0-SNAPSHOT.jar <users> <concurrent_threads_per_user> <rounds_per_thread>

Eg:- java -jar build/libs/wallet-client-1.0-SNAPSHOT.jar 1 3 2

Important Design Choices
========================

1) Use ExceuterServie framework for implement concurrent behaviour
2) Use Hibernate for generate database schema      


