package com.example.Alfa.Clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gifClient0", url = "${baseUri0}")
public interface DownloadGifByURL0 {

    @GetMapping(value = "{url}", consumes = MediaType.IMAGE_GIF_VALUE)
    byte[] downloadGif(@PathVariable String url);
}

