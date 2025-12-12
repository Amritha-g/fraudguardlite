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
│ └─ AppGui.java (optional) # if you created GUI earlier (not required)
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
