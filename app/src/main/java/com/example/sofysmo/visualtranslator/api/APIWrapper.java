package com.example.sofysmo.visualtranslator.api;

import com.example.sofysmo.visualtranslator.Constants;
import com.example.sofysmo.visualtranslator.exception.APIResponseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.*;
import com.loopj.android.http.RequestParams;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import cz.msebera.android.httpclient.NameValuePair;


/**
 * Created by sofysmo on 06.08.16.
 */
public class APIWrapper {
    private Logger log = LoggerFactory.getLogger(APIWrapper.class);

    private Map<String, String> headerParam;

    public APIWrapper()
    {
    }

    public void setHeaderProperty(Map<String,String> headerParam){
        this.headerParam = headerParam;
    }

    public String get(String req, RequestParams params) throws IOException {
        req = req + "?" + params.toString();
        URL url = new URL(req);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        beforConnect(conn);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        try {
            return connect(conn);
        }finally {
            conn.disconnect();
        }
    }

    public String post(String req, String data, RequestParams params) throws IOException {
        req = req + "?"+params.toString();
        URL url = new URL(req);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(
                conn.getOutputStream());
        out.write(data);
        out.close();

        try {
            return connect(conn);
        }finally {
            conn.disconnect();
        }
    }

    public String put(String req, String data,RequestParams params) throws IOException{
        req = req + "?"+params.toString();
        URL url = new URL(req);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(
                conn.getOutputStream());
        out.write(data);
        out.close();
        try {
            return connect(conn);

        }finally {
            conn.disconnect();
        }
    }

    public String delete(String req,RequestParams params) throws IOException {
        req = req + "?"+params.toString();
        URL url = new URL(req);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        beforConnect(conn);
        conn.setRequestMethod("DELETE");
        conn.setDoInput(true);
        try {
            return connect(conn);
        }finally {
            conn.disconnect();
        }
    }

    private void beforConnect(HttpURLConnection conn)
    {
        conn.setReadTimeout(Constants.TIMEOUT);
        conn.setConnectTimeout(Constants.TIMEOUT);
        for(String key : headerParam.keySet())
        conn.setRequestProperty(key,headerParam.get(key));
    }

    private String connect(HttpURLConnection conn) throws IOException, APIResponseException
    {
        InputStream in = null;
        try {
            conn.connect();
            int response = conn.getResponseCode();
            log.debug("Executed request " + conn.getURL().toString() + " with code " + response);
            if (response == 200) {
                in = new BufferedInputStream(conn.getInputStream());
                String str = IOUtils.toString(in, "UTF-8");
                return str;
            } else throw new APIResponseException(conn.getURL().toString(),
                    conn.getResponseCode(), conn.getResponseMessage());
        } finally {
            if(in!=null)
                in.close();
        }
    }

}
