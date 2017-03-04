package com.example.antoan.planit.view.eventDetails;

import com.data.models.PlanedEvent;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class EventDetailsContracts {
    public interface View{
        void setPresenter(Presenter presenter);
        void notify(String message);

        void setEvent(PlanedEvent event);
    }

    public interface Presenter{
        View getView();
        void start();
        void setEventId(String id);
    }
}
