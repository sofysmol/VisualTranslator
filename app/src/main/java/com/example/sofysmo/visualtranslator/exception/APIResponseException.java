package com.example.sofysmo.visualtranslator.exception;

/**
 * Created by sofysmo on 07.08.16.
 */
public class APIResponseException extends RuntimeException{
    int code;
    String reason;

    public APIResponseException(String url, int code, String reason) {
        super("Url: "+ url +", code: " + code + "message: " + reason);
        this.code = code;
        this.reason = reason;
    }
}
