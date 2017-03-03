package com.example.antoan.planit.view.home.listEventsAsParticipant;

/**
 * Created by Antoan on 3/3/2017.
 */

public class EventsAsParticipantContract {
    public interface View{
        void setPresenter(EventsAsParticipantContract.Presenter presenter);
    }

    public interface Presenter{

        EventsAsParticipantContract.View getView();

        void start();
    }
}
