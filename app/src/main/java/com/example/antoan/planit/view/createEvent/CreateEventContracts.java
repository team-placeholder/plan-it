package com.example.antoan.planit.view.createEvent;

import com.data.models.SimpleDate;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventContracts {
    public interface View{
        void setPresenter(Presenter presenter);

        void setDate(int year, int month, int day);
    }

    public interface Presenter{
        View getView();

        void start();
        void setDate(SimpleDate date);
    }
}
