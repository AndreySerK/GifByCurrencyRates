package com.example.Alfa.Services;

import com.example.Alfa.Clients.HistoricalRateClient;
import com.example.Alfa.Clients.LatestRateClient;
import com.example.Alfa.Response.CurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    @Value("${currency.api_key}")
    private String currencyKey;

    private final HistoricalRateClient historicalRateClient;
    private final LatestRateClient latestRateClient;

    public Boolean compareRates(String requiredCurrency) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, -1);
        String yesterdayDate = dateFormat.format(currentDate.getTime());
        CurrencyResponse todayCurrencyResponse = latestRateClient.getResponse(currencyKey);
        CurrencyResponse yesterdayCurrencyResponse = historicalRateClient.getResponse(yesterdayDate, currencyKey);
        Map<String, Double> todayCurrencyRates = todayCurrencyResponse.getRates();
        Map<String, Double> yesterdayCurrencyRates = yesterdayCurrencyResponse.getRates();
        double todayRateUSDtoRequiredCurrency = todayCurrencyRates.get(requiredCurrency.toUpperCase());
        double yesterdayRateUSDtoRequiredCurrency = yesterdayCurrencyRates.get(requiredCurrency.toUpperCase());
        return todayRateUSDtoRequiredCurrency > yesterdayRateUSDtoRequiredCurrency;
    }
}
