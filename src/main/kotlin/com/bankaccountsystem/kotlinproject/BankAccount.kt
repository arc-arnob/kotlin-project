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
        balance = when (transaction) {
            is DepositTransaction    -> balance + transaction.amount
            is WithdrawTransaction   -> {
                if (transaction.amount > balance)
                    throw IllegalArgumentException("Insufficient balance")
                balance - transaction.amount
            }
            else -> balance
        }
        transactions += transaction
    }
}

