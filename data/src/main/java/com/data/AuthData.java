package com.data;

import android.content.Context;

import com.data.models.Password;
import com.data.models.ResponseMessage;
import com.data.models.ResponsePair;
import com.data.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Antoan on 2/21/2017.
 */

public class AuthData {

    private final String baseApiUrl;
    private final OkHttpClient client;
    private final Gson gson;
    private Context context;

    @Inject
    public AuthData(String baseApiUrl, Context context) {
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
                jsonObject.addProperty("oldPassword", item.getOldPassword());
                jsonObject.addProperty("newPassword", item.getNewPassword());
                //Ion.with(context).load("PUT", baseApiUrl + "profile/password").addHeader("Content-Type", "application/json").setJsonObjectBody(jsonObject).asJsonObject().withResponse()
                buildIonRequestWithBody(jsonObject,"profile/password", HttpData.HttpMethods.PUT)
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {
                                ResponseMessage resMsg = gson.fromJson(result.getResult().toString(), ResponseMessage.class);
                                e.onNext(resMsg.getSuccesful());

                            }
                        });


            }
        });
    }


    public Observable<Boolean> updateAvatar(final User item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("avatar", item.getAvatar());
                buildIonRequestWithBody(jsonObject,"profile/profile-picture", HttpData.HttpMethods.PUT)
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {
                                ResponseMessage responseMessage = gson.fromJson(result.getResult().toString(), ResponseMessage.class);
                                e.onNext(responseMessage.getSuccesful());
                            }
                        });
            }
        });
    }


    public Observable<Boolean> register(final User item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
             /*   String json = gson.toJson(item);
                Request request = buildRequestWithBody(json, baseApiUrl + "register", HttpData.HttpMethods.POST);
                Response res = client.newCall(request).execute();

                if (res.code() == 201) {


                    e.onNext(true);
                } else {
                    e.onNext(false);
                    ;
                }*/

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email",item.getEmail());
                jsonObject.addProperty("username",item.getUsername());
                jsonObject.addProperty("password",item.getPassword());

                buildIonRequestWithBody(jsonObject,"register", HttpData.HttpMethods.POST)
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {
                                ResponseMessage responseMessage = gson.fromJson(result.getResult().toString(),ResponseMessage.class);
                                e.onNext(responseMessage.getSuccesful());
                            }
                        });
            }
        });
    }

    public Observable<ResponsePair> authorize(final User item) {
        return Observable.create(new ObservableOnSubscribe<ResponsePair>() {
            @Override
            public void subscribe(final ObservableEmitter<ResponsePair> e) throws Exception {


                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("email", item.getEmail());
                jsonObject.addProperty("password", item.getPassword());
                jsonObject.addProperty("username", item.getEmail());
                buildIonRequestWithBody(jsonObject, "authenticate", HttpData.HttpMethods.POST)
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {

                                if (result.getResult() != null) {
                                    User user = gson.fromJson(result.getResult(), User.class);
                                    ResponsePair responsePair = new ResponsePair(200, user);
                                    e.onNext(responsePair);
                                } else {
                                    ResponsePair responsePair = new ResponsePair(401, null);
                                    e.onNext(responsePair);
                                }

                            }
                        });

            }
        });
    }

    public Observable<User> getProfile(){
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(final ObservableEmitter<User> e) throws Exception {
                buildIonGetRequestReturningJsonObject("/profile/get-user-info")
                        .setCallback(new FutureCallback<Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, Response<JsonObject> result) {
                                User user = gson.fromJson(result.getResult().toString(),User.class);
                                e.onNext(user);
                            }
                        });
            }
        });
    }

    public Observable<Boolean> cleanNewRequest(final User item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                /*String json = gson.toJson(item);
                Request request = buildRequestWithBody(json, baseApiUrl + "profile/requests", HttpData.HttpMethods.PUT);

                Response res = client.newCall(request).execute();
                if (res.code() == 201) {
                    e.onNext(true);
                } else {
                    e.onNext(false);
                }*/
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email",item.getEmail());
                jsonObject.addProperty("username",item.getUsername());
                buildIonRequestWithBody(jsonObject,"profile/requests", HttpData.HttpMethods.PUT)
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {
                                ResponseMessage responseMessage = gson.fromJson(result.getResult().toString(),ResponseMessage.class);
                                e.onNext(responseMessage.getSuccesful());
                            }
                        });

            }
        });
    }

    public Observable<User[]> findFriends(final String pattern) {
        return Observable.create(new ObservableOnSubscribe<User[]>() {
            @Override
            public void subscribe(final ObservableEmitter<User[]> e) throws Exception {
              /*  Request request = new Request.Builder().url(baseApiUrl + "users/search/" + pattern).get().build();

                Response res = client.newCall(request).execute();

                User[] items = gson.fromJson(res.body().string(), User[].class);

                e.onNext(items);*/

                buildIonGetRequestReturningJsonArray("users/search/" + pattern)
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonArray>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonArray> result) {
                                User[] items = gson.fromJson(result.getResult().toString(), User[].class);
                                e.onNext(items);
                            }
                        });
            }
        });
    }

    public Observable<Boolean> sendFriendRequest(final User user) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
              /*  String json = gson.toJson(user);
                Request request = buildRequestWithBody(json, baseApiUrl + "profile/send-request", HttpData.HttpMethods.POST);

                Response response = client.newCall(request).execute();
                if (response.code() == 201) {
                    e.onNext(true);
                } else {
                    e.onNext(false);
                }*/
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email",user.getEmail());
                buildIonRequestWithBody(jsonObject,"profile/send-request", HttpData.HttpMethods.POST)
                        .setCallback(new FutureCallback<com.koushikdutta.ion.Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, com.koushikdutta.ion.Response<JsonObject> result) {
                                ResponseMessage responseMessage = gson.fromJson(result.getResult().toString(),ResponseMessage.class);
                                e.onNext(responseMessage.getSuccesful());
                            }
                        });

            }
        });
    }

    public Observable<Boolean> acceptFriendRequest(final User user){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email",user.getEmail());
                jsonObject.addProperty("username",user.getUsername());
                jsonObject.addProperty("avatar",user.getAvatar());
                buildIonRequestWithBody(jsonObject,"profile/add-friend", HttpData.HttpMethods.POST)
                        .setCallback(new FutureCallback<Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, Response<JsonObject> result) {
                                ResponseMessage responseMessage = gson.fromJson(result.getResult().toString(),ResponseMessage.class);
                                e.onNext(responseMessage.getSuccesful());
                            }
                        });

            }
        });
    }

    public Observable<Boolean> declineFriendRequest(final User user){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email",user.getEmail());
                jsonObject.addProperty("username",user.getUsername());
                jsonObject.addProperty("avatar",user.getAvatar());
                buildIonRequestWithBody(jsonObject,"profile/deny-request", HttpData.HttpMethods.PUT)
                        .setCallback(new FutureCallback<Response<JsonObject>>() {
                            @Override
                            public void onCompleted(Exception ex, Response<JsonObject> result) {
                                ResponseMessage responseMessage = gson.fromJson(result.getResult().toString(),ResponseMessage.class);
                                e.onNext(responseMessage.getSuccesful());
                            }
                        });

            }
        });
    }

    public Observable<User[]> getFriendRequests(){
        return Observable.create(new ObservableOnSubscribe<User[]>() {
            @Override
            public void subscribe(final ObservableEmitter<User[]> e) throws Exception {
                buildIonGetRequestReturningJsonArray("profile/get-requests")
                        .setCallback(new FutureCallback<Response<JsonArray>>() {
                            @Override
                            public void onCompleted(Exception ex, Response<JsonArray> result) {
                                User[] users = gson.fromJson(result.getResult().toString(),User[].class);
                                e.onNext(users);
                            }
                        });
            }
        });
    }

    public Observable<User[]> getFriends(){
        return Observable.create(new ObservableOnSubscribe<User[]>() {
            @Override
            public void subscribe(final ObservableEmitter<User[]> e) throws Exception {
                buildIonGetRequestReturningJsonArray("profile/friends")
                        .setCallback(new FutureCallback<Response<JsonArray>>() {
                            @Override
                            public void onCompleted(Exception ex, Response<JsonArray> result) {
                                User[] friends = gson.fromJson(result.getResult().toString(),User[].class);
                                e.onNext(friends);
                            }
                        });
            }
        });
    }


    private Request buildRequestWithBody(String json, String url, HttpData.HttpMethods type) {

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        Request.Builder request = new Request.Builder().url(url);

        switch (type) {
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

        return request.build();
    }

    private Future<com.koushikdutta.ion.Response<JsonObject>> buildIonRequestWithBody(JsonObject jsonObject, String suffixUrl, HttpData.HttpMethods type) {

        String HttpMethod = null;

        switch (type) {
            case POST:
                HttpMethod = "POST";
                break;
            case PUT:
                HttpMethod = "PUT";
                break;
            case DELETE:
                HttpMethod = "DELETE";
                break;
        }


        return Ion.with(context).load(HttpMethod, baseApiUrl + suffixUrl).addHeader("Content-Type", "application/json").setJsonObjectBody(jsonObject).asJsonObject().withResponse();
    }

    private Future<com.koushikdutta.ion.Response<JsonArray>> buildIonGetRequestReturningJsonArray(String suffixUrl) {

        return Ion.with(context).load(baseApiUrl + suffixUrl).asJsonArray().withResponse();
    }

    private Future<com.koushikdutta.ion.Response<JsonObject>> buildIonGetRequestReturningJsonObject(String suffixUrl) {

        return Ion.with(context).load(baseApiUrl + suffixUrl).asJsonObject().withResponse();
    }

}
