package com.example.antoan.planit.models;

import javax.inject.Inject;

/**
 * Created by Antoan on 2/18/2017.
 */

public class ApiUrl<T> {
    private final String baseApiUrl;
    private  String suffix;
    @Inject
    public ApiUrl(String baseApiUrl, String suffix){
        this.baseApiUrl = baseApiUrl;
        this.suffix = suffix;
    }


    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getApiUrl(){
        return this.baseApiUrl+suffix;
    }
    public String getSuffix(){
        return this.suffix;
    }
}
