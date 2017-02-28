package com.example.antoan.planit.view.calendar;

import com.data.EventsData;
import com.data.models.EventResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class CalendarPresenter implements CalendarContracts.Presenter {
    private final EventsData eventsData;
    private CalendarContracts.View view;

    @Inject
    public CalendarPresenter(CalendarContracts.View view, EventsData eventsData){
        this.view = view;
        this.eventsData = eventsData;
        this.getView().setPresenter(this);
    }

    @Override
    public CalendarContracts.View getView() {
        return this.view;
    }

    @Override
    public void getEventsForDay(int year, int month, int day) {
        this.eventsData.getDailyEvents(year,month,day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EventResponse>() {
                    @Override
                    public void accept(EventResponse response) throws Exception {
                        getView().notify(response.getMessage());
                        getView().setEvents(response.getEvents());
                    }
                });
    }
}
