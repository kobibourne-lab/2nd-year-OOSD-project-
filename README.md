Online Library Management System

Java desktop application for managing library items, users and orders using MySQL.

Overview

This project was developed as an individual software development project. The application provides a graphical interface for managing items, users and orders, with the data stored in a MySQL database.

Features

Manage library items

Add items

Update items

Delete items

Display item records

Manage users

Add users

Update users

Delete users

Display user records

Manage orders

Create orders

Update orders

Delete orders

Display order records

Store and retrieve data using MySQL

Separate Java classes for different item types including books, DVDs and games

Technologies

Java

MySQL

JDBC

Java GUI

Structure

The project uses an object-oriented structure for library items. Item is used as the base class, with Book, DVD and Game extending it.

The application also separates database operations into CRUD classes for items, users and orders.

Database

The application connects to a MySQL database containing tables for:

Items

Users

Orders

Orders store information such as the user, item, order type, order date and return date.

What I Worked On

This was an individual project focused on building a Java application that communicates with a relational database. It gave me experience with object-oriented programming, JDBC, SQL and CRUD operations.


Notes

The database connection uses a local MySQL database, so the application requires MySQL and the appropriate JDBC driver to run.
