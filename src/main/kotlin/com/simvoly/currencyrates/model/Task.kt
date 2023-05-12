package com.simvoly.currencyrates.model

import java.time.LocalDateTime

data class Task(
        var id: Int,
        var description: String,
        var priority: Int,
        var timestamp: LocalDateTime
)