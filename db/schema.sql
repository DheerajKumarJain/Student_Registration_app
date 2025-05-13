CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    rollno VARCHAR(50),
    class VARCHAR(20),
    email VARCHAR(100),
    contact VARCHAR(15),
    gender VARCHAR(10),
    department VARCHAR(50)
);
