package com.example.antoan.planit.view.createEvent;

import android.app.Activity;
import android.app.FragmentManager;

import com.data.models.SimpleDate;
import com.example.antoan.planit.ui.MatirialTimePicker;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventContracts {
    public interface View{
        void setPresenter(Presenter presenter);

        void setDate(int year, int month, int day);


        void setMatirialTimePicker(MatirialTimePicker matirialTimePicker);
    }

    public interface Presenter{
        View getView();

        void start();
        void setDate(SimpleDate date);

    }
}
