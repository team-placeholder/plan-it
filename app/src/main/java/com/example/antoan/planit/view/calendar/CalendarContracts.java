package com.example.antoan.planit.view.calendar;

import com.data.models.PlannedEvent;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class CalendarContracts {
    public interface View{
        void setPresenter(Presenter presenter);
        void notify(String message);
        void setEvents(PlannedEvent[] events);
    }

    public interface Presenter{
        View getView();
        void getEventsForDay(int year, int month, int day);
    }
}
