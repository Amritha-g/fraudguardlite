[![Java](https://img.shields.io/badge/Java-17+-blue)](https://www.oracle.com/java/)
[![Project type](https://img.shields.io/badge/Project-Console%20App-lightgrey)]()
[![Status](https://img.shields.io/badge/Status-Completed-brightgreen)]()


# FraudGuard Lite 

**FraudGuard Lite** is a lightweight Java console application that simulates bank-style transactions and detects suspicious activity using a modular rule engine and risk scoring. 
It demonstrates core backend engineering skills: OOP design, pluggable rule modules (High Amount, Velocity, Blacklist), transaction validation, alerting and real-world fintech concepts .

---

## Features

- Create accounts with an initial balance  
- Transfer money between accounts (with validation)  
- Fraud engine with pluggable rules:
  - **HighAmountRule** — flags very large transfers
  - **VelocityRule** — flags many quick transfers from the same account
  - **BlacklistRule** — flags transfers to blacklisted receivers
- Rules return risk scores; total risk is compared with a threshold and stored as `FraudAlert` when exceeded
- Console UI for interaction and testing
- In-memory storage (simple, no external DB). Data is lost when the app exits.

---
---

## Architecture Overview

FraudGuard Lite is built using clean Object-Oriented Design with clear separation of concerns.  
The goal is to make the fraud detection logic modular, testable, and easy to extend.

### **1. Models (Data Objects)**  
- **Account** — holds account ID, owner name, and balance  
- **Transaction** — represents a transfer between accounts  
- **FraudAlert** — stores details of suspicious transactions  

---

### **2. Services (Business Logic)**  
- **AccountService** — creates accounts and manages balances  
- **TransactionService** — performs transfers and invokes the fraud engine  
- **AlertService** — stores and retrieves fraud alerts  

Each service is responsible for exactly one part of the workflow (single responsibility principle).

---

### **3. Fraud Engine (Pluggable Rules)**  
The fraud detection engine evaluates each successful transaction using rule modules:

- **HighAmountRule** → flags very large transfers  
- **VelocityRule** → flags too many rapid transfers from the same account  
- **BlacklistRule** → flags transfers to blacklisted recipients  

Each rule returns a **risk score** and **reason**.  
The Fraud Engine sums the scores and creates a `FraudAlert` if the total exceeds a threshold.

This design allows adding new rules without changing existing code → **Open/Closed Principle (OCP)**.

---

### **4. Console UI Layer**  
- **ConsoleUI** provides a menu-based interface for:
  - creating accounts  
  - doing transactions  
  - viewing fraud alerts  

The UI does not contain any business logic — it simply calls the services.

---

### **High-level Flow**
User Input (Console)
↓
ConsoleUI
↓
TransactionService → validates transfer
↓
FraudEngine → runs rule checks → risk scoring
↓
AlertService → stores alerts (if suspicious)
↓
Output shown to user 



## Project structure
fraudguardlite/
├─ src/
│ ├─ Account.java
│ ├─ AccountService.java
│ ├─ Transaction.java
│ ├─ TransactionService.java
│ ├─ FraudAlert.java
│ ├─ FraudRule.java
│ ├─ HighAmountRule.java
│ ├─ VelocityRule.java
│ ├─ BlacklistRule.java
│ ├─ FraudEngine.java
│ ├─ AlertService.java
│ ├─ ConsoleUI.java
│ ├─ AppConsole.java # runs the console UI
├─ bin/ # compiled classes (ignored in git)
├─ .gitignore
└─ README.md


---

## Screenshots

**Create accounts (menu + account creation):**  

![Create accounts](screenshots/console_menu.png)

**High-value transaction flagged as fraud:**  

![Fraud transaction](screenshots/fraud_transaction.png)

**Stored fraud alerts (view alerts):**  

![Alerts list](screenshots/alerts_list.png)
