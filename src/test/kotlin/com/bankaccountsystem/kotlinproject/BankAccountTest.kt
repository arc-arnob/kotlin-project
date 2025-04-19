package com.bankaccountsystem.kotlinproject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class BankAccountTest {
    @Test
    fun applyDepositTransactionShouldIncreaseBalance() {
        val account = BankAccount("123")
        account.applyTransaction(DepositTransaction(200.0))
        assertEquals(200.0, account.getBalance())
    }

    @Test
    fun applyWithdrawTransactionShouldDecreaseBalance() {
        val account = BankAccount("123")
        account.applyTransaction(DepositTransaction(200.0))
        account.applyTransaction(WithdrawTransaction(50.0))
        assertEquals(150.0, account.getBalance())
    }
    @Test
    fun applyWithdrawTransactionShouldFailIfInsufficientFunds() {
        val account = BankAccount("123")
        val exception = assertThrows<IllegalArgumentException> {
            account.applyTransaction(WithdrawTransaction(50.0))
        }
        assertEquals("Insufficient balance", exception.message)
    }

    @Test
    fun `Transfer Funds should decrease senders balance and should increase receiver's balance`(){
        val senderAccount = BankAccount("1234")
        val receiverAccount = BankAccount("3456")
        senderAccount.applyTransaction(DepositTransaction(100.0))

        senderAccount.applyTransaction(SendMoneyTransaction(100.0, receiverAccount, senderAccount));
        assertEquals(100.0, receiverAccount.getBalance())
        assertEquals(0.0, senderAccount.getBalance())

    }
}