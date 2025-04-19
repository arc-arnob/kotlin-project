package com.bankaccountsystem.kotlinproject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class BankAccountTest {
//    @Test
//    fun applyDepositTransactionShouldIncreaseBalance() {
//        val account = BankAccount("123")
//        account.applyTransaction(DepositTransaction(200.0))
//        assertEquals(200.0, account.getBalance())
//    }
//
//    @Test
//    fun applyWithdrawTransactionShouldDecreaseBalance() {
//        val account = BankAccount("123")
//        account.applyTransaction(DepositTransaction(200.0))
//        account.applyTransaction(WithdrawTransaction(50.0))
//        assertEquals(150.0, account.getBalance())
//    }
//    @Test
//    fun applyWithdrawTransactionShouldFailIfInsufficientFunds() {
//        val account = BankAccount("123")
//        val exception = assertThrows<IllegalArgumentException> {
//            account.applyTransaction(WithdrawTransaction(50.0))
//        }
//        assertEquals("Insufficient balance", exception.message)
//    }
//
//    @Test
//    fun `Transfer Funds should decrease senders balance and should increase receiver's balance`(){
//        val senderAccount = BankAccount("1234")
//        val receiverAccount = BankAccount("3456")
//        senderAccount.applyTransaction(DepositTransaction(100.0))
//
//        senderAccount.applyTransaction(SendMoneyTransaction(100.0, receiverAccount, senderAccount));
//        assertEquals(100.0, receiverAccount.getBalance())
//        assertEquals(0.0, senderAccount.getBalance())
//
//    }
    private val processor = TransactionProcessor()

    @Test
    fun `deposit should increase balance`() {
        val acct = BankAccount("A1")
        processor.process(acct, DepositTransaction(150.0))
        assertEquals(150.0, acct.getBalance())
    }

    @Test
    fun `withdraw should decrease balance`() {
        val acct = BankAccount("A2")
        processor.process(acct, DepositTransaction(200.0))
        processor.process(acct, WithdrawTransaction(75.0))
        assertEquals(125.0, acct.getBalance())
    }

    @Test
    fun `withdraw beyond balance should fail`() {
        val acct = BankAccount("A3")
        val ex = assertThrows<IllegalArgumentException> {
            processor.process(acct, WithdrawTransaction(50.0))
        }
        assertEquals("Insufficient balance", ex.message)
    }

    @Test
    fun `transfer should move funds between accounts`() {
        val sender = BankAccount("S1")
        val receiver = BankAccount("R1")

        processor.process(sender, DepositTransaction(500.0))
        processor.process(sender, SendMoneyTransaction(200.0, receiver, sender))

        assertEquals(300.0, sender.getBalance())
        assertEquals(200.0, receiver.getBalance())
    }

    @Test
    fun `transfer beyond balance should fail`() {
        val sender = BankAccount("S2")
        val receiver = BankAccount("R2")

        val ex = assertThrows<IllegalArgumentException> {
            processor.process(sender, SendMoneyTransaction(100.0, receiver, sender))
        }
        assertEquals("Insufficient balance", ex.message)
    }
}