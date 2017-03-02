package com.example.antoan.planit.view.eventDetails;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class EventDetailsContracts {
    public interface View{
        void setPresenter(Presenter presenter);

    }

    public interface Presenter{
        View getView();
        void start();
    }
}
