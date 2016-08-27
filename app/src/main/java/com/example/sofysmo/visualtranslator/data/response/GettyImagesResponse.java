package com.example.sofysmo.visualtranslator.data.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sofysmo on 21.08.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GettyImagesResponse {
    private int count;
    private List<ImageItem> images;
    @JsonCreator
    public GettyImagesResponse(@JsonProperty(value = "result_count", required = true) int count,
                               @JsonProperty(value = "images", required = true) List<ImageItem> images)
    {
        this.count = count;
        this.images = images;
    }
    public int getCount(){return count;}
    public List<ImageItem> getImages(){return images;}

}
