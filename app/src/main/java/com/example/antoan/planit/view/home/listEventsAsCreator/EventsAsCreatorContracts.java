package com.example.antoan.planit.view.home.listEventsAsCreator;

/**
 * Created by Antoan on 3/3/2017.
 */

public class EventsAsCreatorContracts {
    public interface View{
        void setPresenter(EventsAsCreatorContracts.Presenter presenter);
    }

    public interface Presenter{

        EventsAsCreatorContracts.View getView();

        void setEvents();

        void start();
    }
}
