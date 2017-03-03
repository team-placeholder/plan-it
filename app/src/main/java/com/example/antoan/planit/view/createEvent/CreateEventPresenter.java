package com.example.antoan.planit.view.createEvent;

import com.data.EventsData;
import com.data.models.PlanedEvent;
import com.data.models.SimpleDate;
import com.example.antoan.planit.ui.MaterialTimePicker;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventPresenter implements CreateEventContracts.Presenter {
    private final EventsData eventsData;
    private final MaterialTimePicker materialTimePicker;
    private CreateEventContracts.View view;
    private SimpleDate date;


    @Inject
    public CreateEventPresenter(CreateEventContracts.View view, EventsData eventsData, MaterialTimePicker materialTimePicker){
        this.view=view;
        this.materialTimePicker = materialTimePicker;
        this.getView().setPresenter(this);
        this.getView().setMaterialTimePicker(this.materialTimePicker);
        this.eventsData = eventsData;
    }

    @Override
    public CreateEventContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        this.getView().setDate(date);
    }

    @Override
    public void setDate(SimpleDate date) {
        this.date = date;
    }

    @Override
    public void createEvent(String title, String description, String start, String end) {
        PlanedEvent event = new PlanedEvent(end, start,description,title,this.date);
        this.eventsData.createEvent(event).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                getView().notify(s);
                getView().navigateToCalendar();
            }
        });
    }
}
