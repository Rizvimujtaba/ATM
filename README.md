# 🏦 Java ATM System with MySQL Integration

A full-stack banking application that simulates an Automated Teller Machine (ATM). This project demonstrates Core Java concepts, Object-Oriented Programming (OOP), and database connectivity using JDBC and MySQL.

## 🚀 Features
* **User Registration:** Create new bank accounts with a unique User ID and PIN.
* **Secure Login:** Authentication against a MySQL database.
* **Persistent Data:** Balances and account details are saved permanently in the database.
* **Banking Operations:** * Check Balance
    * Deposit Money (Real-time database update)
    * Withdraw Money (Includes insufficient funds validation)

## 🛠️ Technology Stack
* **Language:** Java (JDK 17+)
* **Database:** MySQL 8.0
* **Connectivity:** JDBC (Java Database Connectivity)
* **IDE:** VS Code / Eclipse / IntelliJ

---

## ⚙️ Setup & Installation

### 1. Database Setup
Before running the code, you must set up the MySQL database. Open your MySQL Command Line or Workbench and run these commands:

```sql
-- Create the Database
CREATE DATABASE atm_db;

-- Select the Database
USE atm_db;

-- Create the Accounts Table
CREATE TABLE accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    pin VARCHAR(10) NOT NULL,
    user_id VARCHAR(50),
    balance DECIMAL(10,2),
    ifsc VARCHAR(20)
);

-- (Optional) Insert a test user manually
INSERT INTO accounts VALUES ('123456', '1234', 'TestUser', 5000.00, 'IFSC001');