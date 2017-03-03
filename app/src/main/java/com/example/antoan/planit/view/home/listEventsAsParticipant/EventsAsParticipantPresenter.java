package com.example.antoan.planit.view.home.listEventsAsParticipant;

/**
 * Created by Antoan on 3/3/2017.
 */

public class EventsAsParticipantPresenter implements EventsAsParticipantContract.Presenter {

    private final EventsAsParticipantContract.View view;

    public EventsAsParticipantPresenter(EventsAsParticipantContract.View view){
        this.view = view;
        this.getView().setPresenter(this);
    }
    @Override
    public EventsAsParticipantContract.View getView() {
        return this.view;
    }

    @Override
    public void start() {

    }
}
