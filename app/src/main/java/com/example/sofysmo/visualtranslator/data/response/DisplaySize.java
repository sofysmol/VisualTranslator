package com.example.sofysmo.visualtranslator.data.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sofysmo on 26.08.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisplaySize{
    private String uri;
    @JsonCreator
    public DisplaySize(@JsonProperty(value = "uri", required = true)String uri)
    {
        this.uri = uri;
    }
    public String getUri(){return uri;}
}