package com.simvoly.currencyrates.service

import com.simvoly.currencyrates.exception.CurrencyNotFoundException
import com.simvoly.currencyrates.model.CurrencyRate
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class CurrencyRateService {

    fun calculate(from: String, to: String): CurrencyRate {
        val rate = getRates(from, to)
        return CurrencyRate(rate, LocalDateTime.now())
    }

    fun getRates(from: String, to: String): BigDecimal {
        val restTemplate = RestTemplate()
        val fooResourceUrl = "https://open.er-api.com/v6/latest/${from}"
        val response = restTemplate.getForObject(fooResourceUrl, String::class.java)

        val jsonParser = JSONParser()
        val jsonResponse: JSONObject?
        try {
            jsonResponse = jsonParser.parse(response) as JSONObject
        } catch (e: ParseException) {
            throw CurrencyNotFoundException("Error trying to decode response")
        }

        if (jsonResponse["result"] == "error" ) {
            throw CurrencyNotFoundException("You may input a valid currency on from parameter!")
        }

        jsonResponse.let {
            val rates = it["rates"] as JSONObject
            if (rates[to] == null) {
                throw CurrencyNotFoundException("You may input a valid currency on to parameter!")
            }
            val rate = rates[to] as Double
            return BigDecimal.valueOf(rate)
        }
    }

}