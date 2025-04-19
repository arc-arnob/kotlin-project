package com.bankaccountsystem.kotlinproject


class SendMoneyTransaction(override val amount: Double, val to: BankAccount, val from: BankAccount): Transaction {
    override fun processWith(account: BankAccount, processor: TransactionProcessor) {
        processor.processTransfer(this)
    }
}