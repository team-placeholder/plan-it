package com.example.antoan.planit.view.home.listEventsAsCreator;

/**
 * Created by Antoan on 3/3/2017.
 */

public class EventsAsCreatorPresenter implements EventsAsCreatorContracts.Presenter {

    private final EventsAsCreatorContracts.View view;

    public EventsAsCreatorPresenter(EventsAsCreatorContracts.View view){
        this.view = view;
        this.getView().setPresenter(this);

    }
    @Override
    public EventsAsCreatorContracts.View getView() {
        return this.view;
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void start() {

    }
}
