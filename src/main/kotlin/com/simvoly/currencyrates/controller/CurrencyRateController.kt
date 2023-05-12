package com.simvoly.currencyrates.controller;

import com.simvoly.currencyrates.exception.CurrencyNotFoundException
import com.simvoly.currencyrates.model.CurrencyRate
import com.simvoly.currencyrates.service.CurrencyRateService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/currency-rates")
class CurrencyRateController (
        private val service: CurrencyRateService
) {

    @GetMapping("/{from}/{to}")
    fun get(
            @PathVariable("from") from: String,
            @PathVariable("to") to: String
    ): CurrencyRate {
        return service.calculate(from, to)
    }

    @ExceptionHandler(CurrencyNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSuchElementFoundException(
            exception: CurrencyNotFoundException
    ): String? {
        return exception.message
    }

}
