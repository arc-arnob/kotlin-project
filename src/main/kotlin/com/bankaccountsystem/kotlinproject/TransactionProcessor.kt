package com.bankaccountsystem.kotlinproject

class TransactionProcessor {
    fun process(account: BankAccount, transaction: Transaction) {
        transaction.processWith(account,this)
    }

    fun processDeposit(account: BankAccount, tx: DepositTransaction) {
        account.applyTransaction(tx)
    }

    fun processWithdraw(account: BankAccount, tx: WithdrawTransaction) {
        account.applyTransaction(tx)
    }

    fun processTransfer(tx: SendMoneyTransaction) {
        process(tx.from, WithdrawTransaction(tx.amount))
        process(tx.to, DepositTransaction(tx.amount))
    }
}