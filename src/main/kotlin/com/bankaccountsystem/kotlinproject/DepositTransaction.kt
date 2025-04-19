package com.bankaccountsystem.kotlinproject

class DepositTransaction(override val amount: Double): Transaction {
    override fun processWith(account: BankAccount, processor: TransactionProcessor) {
        processor.processDeposit(account, this)
    }
}