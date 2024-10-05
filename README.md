# Project Management System (PoisePMS)

## Overview
The **Project Management System (PoisePMS)** is a Java application designed to manage projects and associated personnel using JDBC for database interactions. This system enables users to perform various operations such as adding, updating, finalizing, and deleting projects, as well as retrieving relevant project data.

## Features
- **Database Interaction**: Read and write data about projects and associated personnel directly from a database (no text files).
- **Project Management**:
  - Add new projects to the database.
  - Update existing project information.
  - Delete projects and associated personnel.
  - Finalize projects by marking them as completed and recording the completion date.
  - Retrieve all projects that need to be completed.
  - Find projects that are past their due date.
  - Search for projects by project number or project name.
 
 ## Requirements
 - Java Development Kit (JDK) 8 or higher
- JDBC driver for your database (e.g., MySQL Connector/J for MySQL databases)
- A database server (e.g., MySQL, PostgreSQL)

## Database Setup

To set up the database using the provided SQL dump file:

1. Create a new database:

   ```sql
   CREATE DATABASE poisePMS;

2. Import the SQL dump:
For MySQL:
mysql -u username -p poisePMS < path/to/your_dump_file.sql

3. Verify the import by checking the tables and data:
USE poisePMS; -- For MySQL
-- or
\c poisePMS; -- For PostgreSQL
SHOW TABLES; -- MySQL
SELECT * FROM your_table_name; -- To check data

## Installation
Clone the Repository:
git clone https://github.com/yourusername/poisePMS.git
cd poisePMS

Add the JDBC Driver:
Ensure you have the JDBC driver in your project's build path.

## Usage
1. Run the application from your IDE 
2. Follow the on-screen instructions to manage projects and associated personnel.

3. ## Author
[Ari Hecht](https://github.com/Arihecht)









