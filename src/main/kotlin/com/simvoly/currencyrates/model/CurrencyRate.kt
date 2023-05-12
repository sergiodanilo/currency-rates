package com.simvoly.currencyrates.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class CurrencyRate(
        var rate: BigDecimal,
        var timestamp: LocalDateTime
)