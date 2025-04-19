package com.bankaccountsystem.kotlinproject


class BankAccount(private val id: String) {
    private var balance: Double = 0.0;
    private val transactions = mutableListOf<Transaction>()

    fun getBalance(): Double{
        return balance;
    }

    fun getTransactions(): List<Transaction>  {
        return transactions.toList();
    }

    fun applyTransaction(transaction: Transaction){
        balance = transaction.applyTo(balance);
        transactions.add(transaction)
    }

}