package com.example.Alpha;

import com.example.Alpha.Clients.HistoricalRateClient;
import com.example.Alpha.Clients.LatestRateClient;
import com.example.Alpha.Response.CurrencyResponse;
import com.example.Alpha.Services.CurrencyRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AlphaApplicationTests {

    private String yesterday;
    Map<String, Double> todayRates;
    Map<String, Double> yesterdayRates;

    @Value("${currency.api_key}")
    private String apiKey;

    @MockBean
    private HistoricalRateClient historicalRateClient;
    @MockBean
    private LatestRateClient latestRateClient;

    @Autowired
    private CurrencyRateService currencyRateService;


    @BeforeEach
    public void init() {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);
        yesterday = dateFormat.format(cal.getTime());

        yesterdayRates = new HashMap<>();
        yesterdayRates.put("EUR", 1.0);
        yesterdayRates.put("GBP", 3.0);

        todayRates = new HashMap<>();
        todayRates.put("EUR", 2.0);
        todayRates.put("GBP", 1.0);
    }

    @Test
    void compareRatesTest() {
        Mockito.when(latestRateClient.getResponse(apiKey))
                .thenReturn(new CurrencyResponse(todayRates));

        Mockito.when(historicalRateClient.getResponse(yesterday, apiKey))
                .thenReturn(new CurrencyResponse(yesterdayRates));

        boolean isIncreasedEUR = currencyRateService.compareRates("EUR");
        boolean isIncreasedGBP = currencyRateService.compareRates("GBP");

        assertFalse(isIncreasedGBP);
        assertTrue(isIncreasedEUR);
    }
}
