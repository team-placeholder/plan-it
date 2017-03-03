package com.example.antoan.planit.view.createEvent;

import com.data.EventsData;
import com.data.models.SimpleDate;

import javax.inject.Inject;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventPresenter implements CreateEventContracts.Presenter {
    private final EventsData eventsData;
    private CreateEventContracts.View view;
    private SimpleDate date;

    @Inject
    public CreateEventPresenter(CreateEventContracts.View view, EventsData eventsData){
        this.view=view;
        this.getView().setPresenter(this);
        this.eventsData = eventsData;
    }

    @Override
    public CreateEventContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        this.getView().setDate(date.getYear(),date.getMonth(),date.getDay());
    }

    @Override
    public void setDate(SimpleDate date) {
        this.date = date;
    }
}
