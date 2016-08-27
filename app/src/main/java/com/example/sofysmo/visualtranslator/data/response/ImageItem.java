package com.example.sofysmo.visualtranslator.data.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sofysmo on 26.08.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageItem{
    private int id;
    private List<DisplaySize> displaySizes;
    @JsonCreator
    public ImageItem(
            @JsonProperty(value = "id", required = true) int id,
            @JsonProperty(value = "display_sizes", required = true) List<DisplaySize> displaySizes)
    {
        this.displaySizes = displaySizes;
        this.id = id;
    }
    public int getId(){return id;}
    public List<DisplaySize> getDisplaySizes(){return displaySizes;}
}
