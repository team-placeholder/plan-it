package com.example.antoan.planit.view.calendar;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class CalendarContracts {
    public interface View{
        void setPresenter(Presenter presenter);
    }

    public interface Presenter{
        View getView();
    }
}
