package com.example.sofysmo.visualtranslator.api.GettyImages;

import android.media.Image;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.sofysmo.visualtranslator.api.APIWrapper;
import com.example.sofysmo.visualtranslator.data.response.GettyImagesResponse;
import com.example.sofysmo.visualtranslator.exception.APIResponseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.auth.Credentials;

/**
 * Created by sofysmo on 21.08.16.
 */
public class GettyImagesRestAPIWrapper implements ImagerAPIWrapper {
    private String url = "https://api.gettyimages.com/v3/search/images";
    private APIWrapper apiWrapper = new APIWrapper();
    private ObjectMapper mapper = new ObjectMapper();
    private Random random = new Random();
    private Credentials credentials;

    public GettyImagesRestAPIWrapper(@NonNull String apiKey)
    {
        Map<String,String> headerProperty = new HashMap<>();
        headerProperty.put("Api-Key", apiKey);
        //headerProperty.put("Authorization", "Bearer "+apiSecret);
        apiWrapper.setHeaderProperty(headerProperty);
    }

    @Override
    public String getImage(@NonNull String phrase) throws IOException,APIResponseException {
        RequestParams params = new RequestParams();
        params.put("phrase", phrase);
        params.put("page", "1");
        params.put("page_size", "1");
        GettyImagesResponse response = mapper.readValue(apiWrapper.get(url, params), GettyImagesResponse.class);
        return response.getImages().get(0).getDisplaySizes().get(0).getUri();
    }
}
