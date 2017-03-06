package com.data;

import android.content.Context;

import com.data.base.BaseHttp;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by hristov on 6.3.2017 г..
 */

public class Http implements BaseHttp {

    public Http() {
    }

    public void getJson(Context context, String url, final jsonCallback callback) {
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        callback.onCompleted(e,result);
                    }
                });
    }

    @Override
    public void postJson(Context context, String url, JsonObject json, final jsonCallback callback) {
        Ion.with(context)
                .load(url)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        callback.onCompleted(e,result);
                    }
                });
    }
}
