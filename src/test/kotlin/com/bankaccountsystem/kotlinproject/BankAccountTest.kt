package com.bankaccountsystem.kotlinproject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class BankAccountTest {
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

    @Test
    fun `Transfer beyond a threshold should be blocked`(){
        val safeProcessor = FraudDetectionProcessor(processor, 600.0)
        val sender = BankAccount("S2")
        val receiver = BankAccount("R2")
        val ex = assertThrows<IllegalArgumentException> {
            safeProcessor.process(sender, SendMoneyTransaction(650.0, receiver, sender))
        }
        assertEquals("Blocked: cannot send more than 600.0 in one transfer", ex.message)
    }

    @Test
    fun `Transfer less than a threshold should be not blocked`(){
        val safeProcessor = FraudDetectionProcessor(processor, 600.0)
        val sender = BankAccount("S2")
        val receiver = BankAccount("R2")
        val ex = assertThrows<IllegalArgumentException> {
            safeProcessor.process(sender, SendMoneyTransaction(599.0, receiver, sender))
        }
        assertEquals("Insufficient balance", ex.message)
    }
}