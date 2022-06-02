package com.example.Alpha.Clients;

import com.example.Alpha.Response.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gifSearchClient", url = "${gif.url}")
public interface GiffyClient {
    @GetMapping(value = "/random")
    GifResponse gifResponse (@RequestParam(value = "api_key") String apiKey,
                             @RequestParam String tag);
}
