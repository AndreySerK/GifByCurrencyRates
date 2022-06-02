package com.example.Alpha.Clients;

import com.example.Alpha.Response.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "historical", url = "${currency.url}")
public interface HistoricalRateClient {
    @GetMapping(value = "/historical/{date}.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    CurrencyResponse getResponse(@PathVariable String date,
                                 @RequestParam(value = "app_id") String appId);
}

