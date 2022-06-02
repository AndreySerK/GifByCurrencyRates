package com.example.Alpha.Controllers;

import com.example.Alpha.Services.DownloadGifService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final DownloadGifService downloadGifService;

    @GetMapping(value = "/rates/{currency_code}")
    public ResponseEntity getGif(@PathVariable(name = "currency_code") String currencyCode) {
        try {
            byte[] gifBytes = downloadGifService.getGif(currencyCode);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(gifBytes);
        } catch (NullPointerException exception) {
            return ResponseEntity.badRequest().body("Запрашиваемая вами валюта не существует");
        }
    }
}
