package com.example.sofysmo.visualtranslator.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by sofysmo on 07.08.16.
 */
public class RequestParams {
    List<NameValuePair> params = new ArrayList<>();
    public void put(String key, String value)
    {
        params.add(new BasicNameValuePair(key,value));
    }
    public String toString(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first) {
                first = false;
                result.append("?");
            }

            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
