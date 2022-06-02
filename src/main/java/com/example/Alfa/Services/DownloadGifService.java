package com.example.Alfa.Services;

import com.example.Alfa.Clients.*;
import com.example.Alfa.DTO.GifObject;
import com.example.Alfa.Response.GifResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadGifService {

    @Value("${gif.api_key}")
    private String gifApiKey;
    private String tag;
    private GifResponse gifResponse;

    private final GiffyClient giffyClient;
    private final CurrencyRateService currencyRateService;
    private final DownloadGifByURL0 downloadGifByURL0;
    private final DownloadGifByURL1 downloadGifByURL1;
    private final DownloadGifByURL2 downloadGifByURL2;
    private final DownloadGifByURL3 downloadGifByURL3;
    private final DownloadGifByURL4 downloadGifByURL4;


    public byte[] getGif(String requiredCurrency) {
        tag = currencyRateService.compareRates(requiredCurrency) ? "rich" : "broke";
        gifResponse = giffyClient.gifResponse(gifApiKey, tag);
        GifObject gifObject = gifResponse.getData();
        String url = gifObject.getImages().getOriginal().get("url");
        String baseUri = url.substring(0, 24);
        String downloadUri = url.substring(24, url.indexOf("?"));
        switch (baseUri) {
            case "https://media0.giphy.com" -> {
                return downloadGifByURL0.downloadGif(downloadUri);
            }
            case "https://media1.giphy.com" -> {
                return downloadGifByURL1.downloadGif(downloadUri);
            }
            case "https://media2.giphy.com" -> {
                return downloadGifByURL2.downloadGif(downloadUri);
            }
            case "https://media3.giphy.com" -> {
                return downloadGifByURL3.downloadGif(downloadUri);
            }
            case "https://media4.giphy.com" -> {
                return downloadGifByURL4.downloadGif(downloadUri);
            }
        }
        return null;
    }
}
