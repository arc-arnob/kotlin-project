package com.bankaccountsystem.kotlinproject

class WithdrawTransaction(override val amount: Double): Transaction {
    override fun processWith(account: BankAccount, processor: TransactionProcessor) {
        processor.processWithdraw(account, this)
    }
}