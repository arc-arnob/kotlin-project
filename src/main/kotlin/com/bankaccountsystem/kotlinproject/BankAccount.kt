package com.bankaccountsystem.kotlinproject


class BankAccount(private val id: String) {
    private var balance: Double = 0.0;

    fun getBalance(): Double{
        return balance;
    }

    fun deposit(amount: Double){
        if (amount <= 0.0) {
            throw IllegalArgumentException("Deposit amount must be greater than zero")
        }
        balance+= amount
    }

    fun withdraw(amount: Double){
        if (amount <= 0.0){
            throw IllegalArgumentException("Withdraw amount must be greater than zero")
        }
        else if(amount > balance){
            throw IllegalArgumentException("Insufficient balance")
        }

        else{
            balance -= amount
        }

    }

}