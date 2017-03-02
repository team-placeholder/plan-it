package com.example.antoan.planit.view.createEvent;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventContracts {
    public interface View{
        void setPresenter(Presenter presenter);
    }

    public interface Presenter{
        View getView();

    }
}
