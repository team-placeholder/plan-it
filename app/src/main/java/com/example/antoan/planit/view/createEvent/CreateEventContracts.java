package com.example.antoan.planit.view.createEvent;

import com.data.models.SimpleDate;
import com.example.antoan.planit.ui.MaterialTimePicker;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventContracts {
    public interface View{
        void setPresenter(Presenter presenter);

        void setDate(SimpleDate date);

        void setMaterialTimePicker(MaterialTimePicker materialTimePicker);

        void notify(String message);

        void navigateToCalendar();

        void setErrorEtTitle(String msg);

        void setErrorEtDescription(String msg);

        void setErrorEtStart(String msg);

        void setErrorEtEnd(String msg);

        void notifyText(String msg);
    }

    public interface Presenter{
        View getView();

        void start();

        void setDate(SimpleDate date);

        void createEvent(String title, String description, String start, String end);

        boolean validateInput(String title, String description, String start, String end);
    }
}
