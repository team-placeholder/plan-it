package com.example.antoan.planit.view.calendar;

import com.data.services.EventsService;
import com.data.models.EventResponse;
import com.data.models.PlanedEvent;
import com.data.models.SimpleDate;
import com.example.antoan.planit.utils.ICanNavigateActivity;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class CalendarPresenter implements CalendarContracts.Presenter {
    private final EventsService eventsService;
    private CalendarContracts.View view;
    private PlanedEvent[] events;
    private SimpleDate selectedDate;

    @Inject
    public CalendarPresenter(CalendarContracts.View view, EventsService eventsService){
        this.view = view;
        this.eventsService = eventsService;
        this.getView().setPresenter(this);
    }

    @Override
    public CalendarContracts.View getView() {
        return this.view;
    }

    @Override
    public void getEventsForDay(int year, int month, int day) {
        this.eventsService.getDailyEvents(year,month,day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EventResponse>() {
                    @Override
                    public void accept(EventResponse response) throws Exception {
                        getView().notify(response.getMessage());
                        events = response.getEvents();
                        getView().setEvents(events);
                    }
                });
    }

    @Override
    public String getEventId(int position) {
        return this.events[position].getId();
    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {
        this.selectedDate = new SimpleDate(year,month,dayOfMonth);
    }

    @Override
    public SimpleDate getSelectedDate() {
        return this.selectedDate;
    }

    @Override
    public void navigateCreate(){
        ICanNavigateActivity activity = (ICanNavigateActivity) this.getView().getActivity();
        activity.navigate(this.getSelectedDate());
    }
}
