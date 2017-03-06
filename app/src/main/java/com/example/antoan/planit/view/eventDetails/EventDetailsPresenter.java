package com.example.antoan.planit.view.eventDetails;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.data.services.EventsService;
import com.data.models.EventResponse;
import com.data.models.PlanedEvent;
import com.example.antoan.planit.view.alarmReceiver.AlarmReceiverActivity;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class EventDetailsPresenter implements  EventDetailsContracts.Presenter {
    private final EventsService eventsService;
    private EventDetailsContracts.View view;
    private String eventId;
    private PlanedEvent event;

    @Inject
    public EventDetailsPresenter(EventDetailsContracts.View view, EventsService eventsService) {
        this.view = view;
        this.getView().setPresenter(this);
        this.eventsService = eventsService;
    }

    @Override
    public EventDetailsContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        eventsService.getEventById(eventId).subscribe(new Consumer<EventResponse>() {

            @Override
            public void accept(EventResponse eventResponse) throws Exception {
                event = eventResponse.getEvent();
                if (event ==  null){
                    getView().notify("Event can not be found!");
                }
                else {
                    if(!event.getUserIn()){
                        getView().showJoinButton();
                    }
                    getView().notify(eventResponse.getMessage());
                    getView().setEvent(eventResponse.getEvent());
                }
            }
        });
    }

    @Override
    public void setEventId(String id) {
        this.eventId = id;
    }

    @Override
    public void setAlarm(Context ctx) {

        int year = this.event.getDate().getYear();
        int month = this.event.getDate().getMonth() - 1;
        int day = this.event.getDate().getDay();

        String[] times = this.event.getStart().split(":");
        Integer hour  = Integer.valueOf(times[0]);
        int minutes = Integer.valueOf(times[1]);

        String message = this.event.getTitle();

        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minutes);
        cal.add(Calendar.SECOND,5);

        Intent intent = new Intent(ctx, AlarmReceiverActivity.class);

        intent.putExtra(AlarmReceiverActivity.MESSAGE_KEY, message);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx,
                12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent );

        this.getView().notify("Alarm Set.");
    }

    @Override
    public void joinEvent() {
        this.eventsService.joinEvent(eventId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean){
                            getView().hideJoinButton();
                            getView().notify("Succesfully joined");
                        }else{
                            getView().notify("Try again later");
                        }
                    }
                });
    }
}
