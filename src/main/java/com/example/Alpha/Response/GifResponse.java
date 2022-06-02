package com.example.Alpha.Response;

import com.example.Alpha.DTO.GifObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GifResponse {
    private GifObject data;
}
