package com.example.antoan.planit.view.eventDetails;

import com.data.EventsData;

import javax.inject.Inject;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class EventDetailsPresenter implements  EventDetailsContracts.Presenter {
    private final EventsData eventsData;
    private EventDetailsContracts.View view;

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

    }
}
