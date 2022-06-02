package com.example.Alfa.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gifClient2", url = "${baseUri2}")
public interface DownloadGifByURL2 {

    @GetMapping(value = "{url}", consumes = MediaType.IMAGE_GIF_VALUE)
    byte[] downloadGif(@PathVariable String url);
}
