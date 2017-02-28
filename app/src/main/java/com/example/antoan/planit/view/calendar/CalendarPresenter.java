package com.example.antoan.planit.view.calendar;

import javax.inject.Inject;

/**
 * Created by hristov on 28.2.2017 г..
 */

public class CalendarPresenter implements CalendarContracts.Presenter {
    private CalendarContracts.View view;

    @Inject
    public CalendarPresenter(CalendarContracts.View view){
        this.view = view;
        this.getView().setPresenter(this);
    }

    @Override
    public CalendarContracts.View getView() {
        return this.view;
    }
}
