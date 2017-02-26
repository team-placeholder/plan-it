package com.example.antoan.planit.data;

import android.content.Context;

import com.example.antoan.planit.models.ApiUrl;
import com.example.antoan.planit.models.Password;
import com.example.antoan.planit.models.ResponsePair;
import com.example.antoan.planit.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Antoan on 2/21/2017.
 */

public class AuthData {

    private final String baseApiUrl;
    private final OkHttpClient client;
    private final Gson gson;
    private Context context;

    @Inject
    public AuthData(String baseApiUrl, Context context){
        this.baseApiUrl = baseApiUrl;
        this.client = new OkHttpClient();
        this.context = context;
        this.gson = new Gson();
    }
    public Observable<Boolean> updatePassword(final Password item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("oldPassword",item.getOldPassword());
                jsonObject.addProperty("newPassword",item.getNewPassword());
                Ion.with(context).load("PUT",baseApiUrl+"profile/password").addHeader("Content-Type", "application/json").setJsonObjectBody(jsonObject).asJsonObject().withResponse()
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {
                                String res = gson.toJson(result.getRequest());
                                if(res == "Successfully changed your password"){
                                    e.onNext(true);
                                }else{
                                    e.onNext(false);
                                }
                            }
                        });


            }
        });
    }


    public Observable<Boolean> updateAvatar(final User item){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                String json = gson.toJson(item);

                Request request = buildRequestWithBody(json,baseApiUrl+"profile/profile-picture", HttpData.HttpMethods.PUT);

                Response res = client.newCall(request).execute();

                if(res.code() == 201){
                    e.onNext(true);
                }else {
                    e.onNext(false);
                }
            }
        });
    }


    public Observable<Boolean> register(final User item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                String json = gson.toJson(item);
                Request request = buildRequestWithBody(json,baseApiUrl+"register", HttpData.HttpMethods.POST);
                Response res = client.newCall(request).execute();

                if(res.code() == 201) {


                    e.onNext(true);
                }else{
                    e.onNext(false);;
                }
            }
        });
    }

    public Observable<ResponsePair> authorize(final User item) {
        return Observable.create(new ObservableOnSubscribe<ResponsePair>() {
            @Override
            public void subscribe(final ObservableEmitter<ResponsePair> e) throws Exception {


                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("email",item.getEmail());
                jsonObject.addProperty("password",item.getPassword());
                jsonObject.addProperty("username",item.getEmail());
                Ion.with(context).load("POST",baseApiUrl+"authenticate").addHeader("Content-Type", "application/json").setJsonObjectBody(jsonObject).asJsonObject().withResponse()
                 .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                     @Override
                     public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {
                         User user = gson.fromJson(result.getResult(),User.class);
                         String code = result.getHeaders().toString();
                         ResponsePair responsePair = new ResponsePair(200,user);
                         e.onNext(responsePair);
                     }
                 });

            }
        });
    }

    public Observable<Boolean> cleanNewRequest(final User item){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                String json = gson.toJson(item);
                Request request = buildRequestWithBody(json,baseApiUrl+"profile/requests", HttpData.HttpMethods.PUT);

                Response res = client.newCall(request).execute();
                if(res.code()== 201){
                    e.onNext(true);
                }else{
                    e.onNext(false);
                }
            }
        });
    }

    public Observable<User[]> findFriends(final String pattern){
        return Observable.create(new ObservableOnSubscribe<User[]>() {
            @Override
            public void subscribe(ObservableEmitter<User[]> e) throws Exception {
                 Request request = new Request.Builder().url(baseApiUrl+"users/search/"+pattern).get().build();

                 Response res = client.newCall(request).execute();

                User[] items = gson.fromJson(res.body().string(),User[].class);

                e.onNext(items);

            }
        });
    }

    public Observable<Boolean> sendFriendRequest(final User[] users){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                String json = gson.toJson(users);
                Request request = buildRequestWithBody(json,baseApiUrl+"profile/send-request", HttpData.HttpMethods.POST);

                Response response = client.newCall(request).execute();
                if(response.code()== 201){
                    e.onNext(true);
                }else{
                    e.onNext(false);
                }

            }
        });
    }



    private Request buildRequestWithBody(String json,String url, HttpData.HttpMethods type) {

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),json);

        Request.Builder request = new Request.Builder().url(url);

        switch (type){
            case POST:
                request = request.post(body);
                break;
            case PUT:
                request = request.put(body);
                break;
            case DELETE:
                request = request.delete(body);
                break;
        }


        return  request.build();
    }
}
