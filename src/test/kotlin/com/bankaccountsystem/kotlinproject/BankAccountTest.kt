package com.bankaccountsystem.kotlinproject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class BankAccountTest {

    @Test
    fun newlyCreatedAccountHasZeroBalance() {
        val account = BankAccount("123")
        assertEquals(0.0, account.getBalance())
    }

    @Test
    fun depositShouldIncreaseBalance(){
        val account =  BankAccount("1234");
        account.deposit(100.00);
        assertEquals(100.0, account.getBalance())
    }

    @Test
    fun depositShouldFailForNegativeAmount() {
        val account = BankAccount("123")

        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            account.deposit(-50.0)
        }

        assertEquals("Deposit amount must be greater than zero", exception.message)
    }

    @Test
    fun withdrawShouldDecreaseBalance(){
        val account =  BankAccount("1234");
        account.deposit(100.00);
        account.withdraw(80.00)
        assertEquals(20.00, account.getBalance())
    }

    @Test
    fun withdrawShouldFailForNegativeAmount() {
        val account = BankAccount("123")

        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            account.withdraw(-50.0)
        }

        assertEquals("Withdraw amount must be greater than zero", exception.message)
    }
    @Test
    fun withdrawShouldFailForInsufficientFunds() {
        val account = BankAccount("123")

        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            account.withdraw(10.0)
        }

        assertEquals("Insufficient balance", exception.message)
    }
}