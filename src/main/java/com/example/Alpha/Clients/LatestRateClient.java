package com.example.Alpha.Clients;

import com.example.Alpha.Response.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "latest", url = "${currency.url}")
public interface LatestRateClient {
    @GetMapping(value = "/latest.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    CurrencyResponse getResponse(@RequestParam(value = "app_id") String appId);
}
