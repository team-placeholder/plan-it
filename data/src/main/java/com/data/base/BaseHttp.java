package com.data.base;

import android.content.Context;

import com.data.Http;
import com.google.gson.JsonObject;

/**
 * Created by hristov on 6.3.2017 г..
 */

public interface BaseHttp {
    void getJson(Context context, String url, final Http.jsonCallback callback);

    void postJson(Context context, String url,JsonObject json, final Http.jsonCallback callback);


    interface jsonCallback{
        void onCompleted(Exception ex, JsonObject result);
    }
}
