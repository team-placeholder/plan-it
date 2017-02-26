package com.example.antoan.planit.data;

import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.ApiUrl;
import com.example.antoan.planit.models.ResponsePair;
import com.example.antoan.planit.models.User;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.internal.subscriptions.BooleanSubscription;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Antoan on 2/18/2017.
 */

public class HttpData<T> implements BaseData<T> {
    private final ApiUrl<T> apiUrl;
    private final Class<T> klassSingle;
    private final Class<T[]> klassArray;
    private final OkHttpClient client;
    private final Gson gson;
    private String suffixUrl;

    @Inject
    public HttpData(ApiUrl<T> apiUrl, Class<T> klassSingle, Class<T[]> klassArray){
        this.apiUrl = apiUrl;
        this.suffixUrl = apiUrl.getSuffix();
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.klassSingle = klassSingle;
        this.klassArray = klassArray;
    }
    @Override
    public Observable<T[]> getAll() {
        return Observable.create(new ObservableOnSubscribe<T[]>() {
            @Override
            public void subscribe(ObservableEmitter<T[]> e) throws Exception {
                Request request = buildGetRequest();

                Response response = client.newCall(request).execute();
                if(response.code() == 200){
                    T[] items = parseArrayItems(response.body().string());

                    e.onNext(items);
                }else
                {
                    e.onNext(null);
                }
            }


        });
    }

    private T[] parseArrayItems(String body) {
        T[] items = this.gson.fromJson(body,this.klassArray);
        return items;
    }

    @Override
    public Observable<T> getById(final Object id) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                Request request = buildGetRequestWithId(id);

                Response response = client.newCall(request).execute();
                if(response.code() == 200){
                    T items = parseSingle(response.body().string());

                    e.onNext(items);
                }else
                {
                    e.onNext(null);
                }
            }
        });

    }

    @Override
    public Observable<T> add(final T item) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                Request request = buildRequestWithBody(item,apiUrl.getApiUrl(),HttpMethods.POST);

                Response res = client.newCall(request).execute();

                if(res.code() == 200) {
                    T item = parseSingle(res.body().string());

                    e.onNext(item);
                }else{
                    e.onNext(null);;
                }
            }
        });
    }
    @Override
    public Observable<ResponsePair> authorize(final T item) {
        return Observable.create(new ObservableOnSubscribe<ResponsePair>() {
            @Override
            public void subscribe(ObservableEmitter<ResponsePair> e) throws Exception {
                apiUrl.setSuffix("authenticate");
                Request request = buildRequestWithBody(item,apiUrl.getApiUrl(),HttpMethods.POST);
                apiUrl.setSuffix(suffixUrl);
                Response res = client.newCall(request).execute();

                if(res.code() == 200) {
                    User item = gson.fromJson(res.body().string(),User.class);

                    e.onNext(new ResponsePair(res.code(),item));
                }else{
                    User item = null;
                    e.onNext(new ResponsePair(res.code(),item));;
                }
            }
        });
    }

    @Override
    public Observable<Boolean> register(final T item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                apiUrl.setSuffix("register");
                Request request = buildRequestWithBody(item,apiUrl.getApiUrl(),HttpMethods.POST);
                apiUrl.setSuffix(suffixUrl);
                Response res = client.newCall(request).execute();

                if(res.code() == 201) {


                    e.onNext(true);
                }else{
                    e.onNext(false);;
                }
            }
        });
    }

    @Override
    public Observable<Boolean> updateAvatar(final T item){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                apiUrl.setSuffix("profile/profile-picture");
                Request request = buildRequestWithBody(item,apiUrl.getApiUrl(),HttpMethods.PUT);
                apiUrl.setSuffix(suffixUrl);
                Response res = client.newCall(request).execute();

                if(res.code() == 201){
                    e.onNext(true);
                }else {
                    e.onNext(false);
                }
            }
        });
    }

    @Override
    public Observable<Boolean> updatePassword(final T item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {

                Request request = buildRequestWithBody(item,apiUrl.getApiUrl(),HttpMethods.PUT);
                Response res = client.newCall(request).execute();

                if(res.code() == 201){
                    e.onNext(true);
                }else {
                    e.onNext(false);
                }
            }
        });
    }


    private T parseSingle(String json) {
        T item = gson.fromJson(json,this.klassSingle);
        return item;
    }

    private Request buildRequestWithBody(T object,String url, HttpMethods type) {
        String json  = gson.toJson(object);
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

    private Request buildGetRequest(){
        return new Request.Builder().url(this.apiUrl.getApiUrl()).build();
    }

    private Request buildGetRequestWithId(Object id){
        return new Request.Builder().url(this.apiUrl.getApiUrl()+"/"+id).build();
    }



    enum HttpMethods{
        POST,
        PUT,
        DELETE
    }

}
