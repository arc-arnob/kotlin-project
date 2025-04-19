package com.bankaccountsystem.kotlinproject

import java.time.LocalDateTime

interface Transaction {
    val amount: Double
    fun applyTo(balance: Double): Double
}

