package com.bankaccountsystem.kotlinproject

class WithdrawTransaction(override val amount: Double): Transaction {
    override fun applyTo(balance: Double): Double {
        if (amount > balance) {
            throw IllegalArgumentException("Insufficient balance")
        }
        return balance - amount;
    }
}