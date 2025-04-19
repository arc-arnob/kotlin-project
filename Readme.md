# 🏦 Bank Account System (Kotlin + Spring Boot + Clean Code)

This project implements a basic bank account system using **Kotlin**, following **SOLID principles**, **Clean Code**, and **Test-Driven Development (TDD)**.

---

## ✅ Completed Problem Statements

### 1. Create a Bank Account

**Problem:**  
Design a `BankAccount` class that can be initialized with a unique account ID. A newly created account should start with a balance of `0.0`.

**Requirements:**
- Must accept an ID during creation
- Must expose a method `getBalance()` returning the current balance

---

### 2. Deposit Money

**Problem:**  
Implement a `deposit(amount: Double)` method to allow users to deposit money into their account.

**Requirements:**
- Increases account balance by the given amount
- Must throw `IllegalArgumentException` if the amount is `0` or negative

---

### 3. Withdraw Money

**Problem:**  
Implement a `withdraw(amount: Double)` method to allow users to withdraw money.

**Requirements:**
- Decreases account balance by the given amount
- Must throw `IllegalArgumentException` if:
    - The amount is `0` or negative
    - The account has insufficient funds

---

### 4. Track Transactions

**Problem:**  
Track all deposit and withdrawal actions using a `Transaction` interface and store them within the account.

**Requirements:**
- Create a `Transaction` interface with method `applyTo(balance: Double): Double`
- Implement `DepositTransaction` and `WithdrawTransaction`
- Maintain a list of transactions inside `BankAccount`
- Expose `getTransactions(): List<Transaction>` that is read-only from outside

---
### 5. Transfer Money Between Accounts

**Problem:**  
Design a `TransferTransaction` that allows transferring funds from one `BankAccount` to another using the existing `Transaction` interface system, without modifying `BankAccount`.

**Requirements:**
- Deducts funds from sender account
- Adds funds to receiver account
- Throws error if sender has insufficient balance
- Adds a transaction record to both accounts
- Follows **Open/Closed Principle**
- Implement using **TDD**

---

## 🧪 Testing Approach

- All features were developed using **Test-Driven Development (TDD)**
- Each problem statement has one or more associated unit tests
- Tests use **JUnit 5**

---
## 🚧 Problem In Progress

### 6: Introduce a Transaction Processor (SRP + DIP + OCP)
**Problem:**  
The `BankAccount` class is currently responsible for both holding state and applying different types of transactions. As more behaviors are introduced (e.g. sending money, fraud checks, logging), this violates **Single Responsibility Principle (SRP)** and becomes harder to maintain and extend.

**Goal:**  
Introduce a new `TransactionProcessor` class that handles applying transactions to accounts, separating that concern from the `BankAccount` itself.