package com.bankaccountsystem.kotlinproject

import java.lang.IllegalArgumentException

class FraudDetectionProcessor(
    private val delegate: TransactionProcessor,
    private val maxAllowed: Double
) {
    fun process(account: BankAccount, transaction: Transaction){
        if(transaction is SendMoneyTransaction && transaction.amount > maxAllowed){
            throw IllegalArgumentException("Blocked: cannot send more than $maxAllowed in one transfer")
        }
        delegate.process(account, transaction)
    }
}