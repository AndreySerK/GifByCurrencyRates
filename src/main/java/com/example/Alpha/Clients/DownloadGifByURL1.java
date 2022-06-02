package com.example.Alpha.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gifClient1", url = "${baseUri1}")
public interface DownloadGifByURL1 {

    @GetMapping(value = "{url}", consumes = MediaType.IMAGE_GIF_VALUE)
    byte[] downloadGif(@PathVariable String url);
}
