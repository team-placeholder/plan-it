package com.example.antoan.planit.view.eventDetails;

import com.data.EventsData;
import com.data.models.EventResponse;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class EventDetailsPresenter implements  EventDetailsContracts.Presenter {
    private final EventsData eventsData;
    private EventDetailsContracts.View view;
    private String eventId;

    @Inject
    public EventDetailsPresenter(EventDetailsContracts.View view, EventsData eventsData) {
        this.view = view;
        this.getView().setPresenter(this);
        this.eventsData = eventsData;
    }

    @Override
    public EventDetailsContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        eventsData.getEventById(eventId).subscribe(new Consumer<EventResponse>() {
            @Override
            public void accept(EventResponse eventResponse) throws Exception {
                getView().notify(eventResponse.getMessage());
                getView().setEvent(eventResponse.getEvent());
            }
        });
    }

    @Override
    public void setEventId(String id) {
        this.eventId = id;
    }
}
