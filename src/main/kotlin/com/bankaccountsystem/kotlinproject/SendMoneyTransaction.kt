package com.bankaccountsystem.kotlinproject


class SendMoneyTransaction(override val amount: Double, private val to: BankAccount, private val from: BankAccount): Transaction {
    override fun applyTo(balance: Double): Double {
        if(from.getBalance() < amount){
            throw IllegalArgumentException("Insufficient Funds")
        }
        from.applyTransaction(WithdrawTransaction(amount))
        to.applyTransaction(DepositTransaction(amount))

        return from.getBalance()
    }
}