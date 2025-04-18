package com.bankaccountsystem.kotlinproject

import java.time.LocalDateTime

data class Transaction (
    val type: TransactionType,
    val amount: Double,
    val timestamp: LocalDateTime
){
}

enum class TransactionType {
    DEPOSIT,
    WITHDRAW
}