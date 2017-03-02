package com.example.antoan.planit.view.createEvent;

import com.data.EventsData;

import javax.inject.Inject;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventPresenter implements CreateEventContracts.Presenter {
    private final EventsData eventsData;
    private CreateEventContracts.View view;

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
}
