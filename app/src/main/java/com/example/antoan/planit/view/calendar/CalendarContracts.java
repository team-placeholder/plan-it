package com.example.antoan.planit.view.calendar;

import com.data.models.PlanedEvent;
import com.data.models.SimpleDate;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class CalendarContracts {
    public interface View{
        void setPresenter(Presenter presenter);
        void notify(String message);
        void setEvents(PlanedEvent[] events);
    }

    public interface Presenter{
        View getView();
        void getEventsForDay(int year, int month, int day);

        String getEventId(int position);

        void setDate(int year, int month, int dayOfMonth);

        SimpleDate getSelectedDate();
    }
}
