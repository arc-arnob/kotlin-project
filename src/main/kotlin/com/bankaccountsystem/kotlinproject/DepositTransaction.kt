package com.bankaccountsystem.kotlinproject

class DepositTransaction(override val amount: Double): Transaction {
    override fun applyTo(balance: Double): Double {
        return balance + amount;
    }
}