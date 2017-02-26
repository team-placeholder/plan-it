package com.example.antoan.planit.models;

/**
 * Created by Antoan on 2/19/2017.
 */

public class ResponsePair {

    private final int statusCode;
    private User data;

    public ResponsePair(int statusCode, User data){
        this.statusCode = statusCode;
        this.data = data;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public User getData() {
        return data;
    }
}
