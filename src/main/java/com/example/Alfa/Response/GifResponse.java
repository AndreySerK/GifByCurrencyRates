package com.example.Alfa.Response;

import com.example.Alfa.DTO.GifObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GifResponse {
    private GifObject data;
}
