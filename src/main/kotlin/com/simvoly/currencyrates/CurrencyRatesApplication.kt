package com.simvoly.currencyrates

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CurrencyRatesApplication

fun main(args: Array<String>) {
	runApplication<CurrencyRatesApplication>(*args)
}
