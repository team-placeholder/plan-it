package com.data;

import android.content.Context;

import com.data.models.EventResponse;
import com.data.models.PlannedEvent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class EventsData {

    private final String baseApiUrl;
    private final Gson gson;
    private Context context;

    @Inject
    public EventsData(@Named("baseApiUrl")String baseApiUrl, Context context) {
        this.baseApiUrl = baseApiUrl;
        this.context = context;
        this.gson = new Gson();
    }

    public Observable<EventResponse> getDailyEvents(final int year, final int month, final int day) {
        return Observable.create(new ObservableOnSubscribe<EventResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<EventResponse> e) throws Exception {

                JsonObject json = new JsonObject();
                json.addProperty("year",year);
                json.addProperty("month",month);
                json.addProperty("day",day);

                Ion.with(context)
                        .load(baseApiUrl + "profile/events")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception ex, JsonObject result) {
                                if (result == null){
                                    e.onNext(new EventResponse("Unable to connect the server!",new PlannedEvent[0]));
                                }else {
                                    EventResponse response = gson.fromJson(result,EventResponse.class);
                                    e.onNext(response);
                                }
                            }
                        });
            }
        });
    }
}
