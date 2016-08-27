package com.example.sofysmo.visualtranslator.api.Yandex;

import com.example.sofysmo.visualtranslator.api.APIWrapper;
import com.example.sofysmo.visualtranslator.data.response.YandexTranslateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sofysmo on 06.08.16.
 */
public class YandexRESTAPIWrapper implements TranslatorAPIWrapper {
    private Logger logger = LoggerFactory.getLogger(YandexRESTAPIWrapper.class);
    private String key;
    private APIWrapper apiWrapper = new APIWrapper();
    private ObjectMapper mapper = new ObjectMapper();
    private String host = "https://translate.yandex.net";
    public YandexRESTAPIWrapper(String key)
    {
        this.key = key;
    }

    @Override
    public String translate(String text, Locale localeFrom, Locale localeTo) throws IOException
    {
        RequestParams params = new RequestParams();
        params.put("key", key);
        params.put("lang", localeFrom.getLanguage() + "-" +localeTo.getLanguage());
        YandexTranslateResponse response = mapper.readValue(apiWrapper.post(host+"/api/v1.5/tr.json/translate", "text="+text, params),
                YandexTranslateResponse.class);
        return response.text.get(0);
    }
}
