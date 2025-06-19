-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS bookstore;

-- Use the created database
USE bookstore;

-- Drop tables if they already exist to start fresh
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS book_request;
DROP TABLE IF EXISTS book_offer;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS user_favorite_authors;
DROP TABLE IF EXISTS user_prefer_book_categories;
DROP TABLE IF EXISTS user_profile;
DROP TABLE IF EXISTS book_category;
DROP TABLE IF EXISTS book_author;

-- Create the users table
CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(255) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    role VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- Create the book_category table
CREATE TABLE book_category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
);

-- Create the book_author table
CREATE TABLE book_author (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the book table
CREATE TABLE book (
    book_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) DEFAULT NULL,
    author_id INT,
    category_id INT,
    summary TEXT,
    PRIMARY KEY (book_id),
    FOREIGN KEY (author_id) REFERENCES book_author(author_id),
    FOREIGN KEY (category_id) REFERENCES book_category(category_id)
);

-- Create the book_offer table
CREATE TABLE book_offer (
    offer_id INT NOT NULL AUTO_INCREMENT,
    book_id INT,
    owner_id INT,
    description TEXT,
    PRIMARY KEY (offer_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- Create the book_request table
CREATE TABLE book_request (
    request_id INT NOT NULL AUTO_INCREMENT,
    book_id INT,
    requester_id INT,
    PRIMARY KEY (request_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (requester_id) REFERENCES users(id)
);

-- Create the user_profile table
CREATE TABLE user_profile (
    username VARCHAR(255) PRIMARY KEY,
    full_name VARCHAR(255),
    address VARCHAR(255),
    age INT,
    phone_number VARCHAR(255)
);

-- Create the user_prefer_book_categories table
CREATE TABLE user_prefer_book_categories (
    user_id VARCHAR(255),
    category_id INT,
    PRIMARY KEY (user_id, category_id),
    FOREIGN KEY (user_id) REFERENCES user_profile(username),
    FOREIGN KEY (category_id) REFERENCES book_category(category_id)
);

-- Create the user_favorite_authors table
CREATE TABLE user_favorite_authors (
    user_id VARCHAR(255),
    author_id INT,
    PRIMARY KEY (user_id, author_id),
    FOREIGN KEY (user_id) REFERENCES user_profile(username),
    FOREIGN KEY (author_id) REFERENCES book_author(author_id)
);

-- Insert data into the users table
INSERT INTO users (user_name, password, role) VALUES
('john_doe', 'password123', 'admin'),
('jane_smith', 'password456', 'user'),
('sam_jones', 'password789', 'user');

-- Insert data into the book_category table
INSERT INTO book_category (category_name) VALUES
('Fiction'),
('Non-Fiction'),
('Science'),
('Technology');

-- Insert data into the book_author table
INSERT INTO book_author (name) VALUES
('Author One'),
('Author Two'),
('Author Three');

-- Insert data into the book table
INSERT INTO book (title, author_id, category_id, summary) VALUES
('Book One', 1, 1, 'Summary of book one.'),
('Book Two', 2, 2, 'Summary of book two.'),
('Book Three', 3, 3, 'Summary of book three.');

-- Insert data into the book_offer table
INSERT INTO book_offer (book_id, owner_id, description) VALUES
(1, 1, 'Offer description for book one.'),
(2, 2, 'Offer description for book two.');

-- Insert data into the book_request table
INSERT INTO book_request (book_id, requester_id) VALUES
(1, 2),
(2, 3);

-- Insert data into the user_profile table
INSERT INTO user_profile (username, full_name, address, age, phone_number) VALUES
('john_doe', 'John Doe', '123 Main St', 30, '555-1234'),
('jane_smith', 'Jane Smith', '456 Elm St', 25, '555-5678'),
('sam_jones', 'Sam Jones', '789 Oak St', 28, '555-9012');

-- Insert data into the user_prefer_book_categories table
INSERT INTO user_prefer_book_categories (user_id, category_id) VALUES
('john_doe', 1),
('jane_smith', 2),
('sam_jones', 3);

-- Insert data into the user_favorite_authors table
INSERT INTO user_favorite_authors (user_id, author_id) VALUES
('john_doe', 1),
('jane_smith', 2),
('sam_jones', 3);
