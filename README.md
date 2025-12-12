# FraudGuard Lite 🛡️

**FraudGuard Lite** is a small Java console application that simulates basic bank transactions and detects potentially fraudulent activity using modular rules and simple risk scoring. This project is designed as a learning/demo project to showcase Object-Oriented Design, rule-based detection logic, and an extendable code structure — suitable for a resume and interview discussion.

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
