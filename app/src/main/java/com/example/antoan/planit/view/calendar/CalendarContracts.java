package com.example.antoan.planit.view.calendar;

import android.support.v4.app.FragmentActivity;

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
        FragmentActivity getActivity();
    }

    public interface Presenter{
        View getView();
        void getEventsForDay(int year, int month, int day);

        String getEventId(int position);

        void setDate(int year, int month, int dayOfMonth);

        SimpleDate getSelectedDate();
        void navigateCreate();
    }
}
