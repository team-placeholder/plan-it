package com.data.services;

import android.content.Context;

import com.data.base.BaseHttp;
import com.data.models.EventResponse;
import com.data.models.PlanedEvent;
import com.data.models.ResponseMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EventsService {

    private final String baseApiUrl;
    private final Gson gson;
    private Context context;
    private BaseHttp http;

    @Inject
    public EventsService(@Named("baseApiUrl")String baseApiUrl, Context context, BaseHttp http) {
        this.baseApiUrl = baseApiUrl;
        this.context = context;
        this.gson = new Gson();
        this.http = http;
    }

    public Observable<EventResponse> getDailyEvents(final int year, final int month, final int day) {
        return Observable.create(new ObservableOnSubscribe<EventResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<EventResponse> e) throws Exception {

                JsonObject json = new JsonObject();
                json.addProperty("year",year);
                json.addProperty("month",month);
                json.addProperty("day",day);

                http.postJson(context, baseApiUrl + "profile/events", json, new BaseHttp.jsonCallback() {
                    @Override
                    public void onCompleted(Exception ex, JsonObject result) {
                        if (result == null){
                            e.onNext(new EventResponse("Unable to connect the server!",new PlanedEvent[0]));
                        }else {
                            EventResponse response = gson.fromJson(result,EventResponse.class);
                            e.onNext(response);
                        }
                    }
                });
            }
        });
    }

    public Observable<Boolean> createEvent(final PlanedEvent planedEvent) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {

                JsonObject json = (JsonObject)gson.toJsonTree(planedEvent);

                http.postJson(context, baseApiUrl + baseApiUrl + "events/create", json, new BaseHttp.jsonCallback() {
                    @Override
                    public void onCompleted(Exception ex, JsonObject result) {
                        ResponseMessage responseMessage = gson.fromJson(result,ResponseMessage.class);
                        e.onNext(responseMessage.getSuccesful());
                    }
                });
            }
        });
    }

    public Observable<EventResponse> getEventById(final String eventId) {
        return Observable.create(new ObservableOnSubscribe<EventResponse>() {

            @Override
            public void subscribe(final ObservableEmitter<EventResponse> e) throws Exception {

                http.getJson(context, (baseApiUrl + "events/" + eventId), new BaseHttp.jsonCallback() {
                    @Override
                    public void onCompleted(Exception ex, JsonObject result) {
                        if (result == null){
                            e.onNext(new EventResponse("Unable to connect the server!"));
                        }else {
                            EventResponse response = gson.fromJson(result,EventResponse.class);
                            e.onNext(response);
                        }
                    }
                });
            }
        });
    }

    public Observable<EventResponse> getUsersEvents(final String username){
        return Observable.create(new ObservableOnSubscribe<EventResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<EventResponse> e) throws Exception {

                http.getJson(context, (baseApiUrl+"friend/events/"+username), new BaseHttp.jsonCallback() {
                    @Override
                    public void onCompleted(Exception ex, JsonObject result) {
                        if (result == null){
                            e.onNext(new EventResponse("Unable to connect the server!"));
                        }else {
                            EventResponse response = gson.fromJson(result,EventResponse.class);
                            e.onNext(response);
                        }
                    }
                });
            }
        });
    }

    public Observable<Boolean> joinEvent(final String eventId){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id",eventId);
                Ion.with(context)
                        .load("PUT",baseApiUrl+"events/join")
                        .addHeader("Content-Type", "application/json").setJsonObjectBody(jsonObject)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception ex, JsonObject result) {
                                ResponseMessage responseMessage = gson.fromJson(result,ResponseMessage.class);
                                e.onNext(responseMessage.getSuccesful());
                            }
                        });
            }
        });
    }
}
