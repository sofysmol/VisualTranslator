package com.example.sofysmo.visualtranslator.data.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by sofysmo on 07.08.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexTranslateResponse {
    public int code;
    public String lang;
    public ArrayList<String> text;
    @JsonCreator
    public YandexTranslateResponse(@JsonProperty(value = "code") int code,
                                   @JsonProperty(value = "lang") String lang,
                                   @JsonProperty(value = "text") ArrayList<String> text)
    {
        this.code = code;
        this.lang = lang;
        this.text = text;
    }

}
