package com.example.antoan.planit.view.createEvent;

import com.data.services.EventsService;
import com.data.models.PlanedEvent;
import com.data.models.SimpleDate;
import com.example.antoan.planit.ui.MaterialTimePicker;
import com.example.antoan.planit.utils.InputValidator;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by hristov on 2.3.2017 г..
 */

public class CreateEventPresenter implements CreateEventContracts.Presenter {
    private final EventsService eventsService;
    private final MaterialTimePicker materialTimePicker;
    private CreateEventContracts.View view;
    private SimpleDate date;


    @Inject
    public CreateEventPresenter(CreateEventContracts.View view, EventsService eventsService, MaterialTimePicker materialTimePicker){
        this.view=view;
        this.materialTimePicker = materialTimePicker;
        this.getView().setPresenter(this);
        this.getView().setMaterialTimePicker(this.materialTimePicker);
        this.eventsService = eventsService;
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
        this.eventsService.createEvent(event).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean s) throws Exception {
                if(s){
                    getView().notifyText("Succesfully created new event");
                    getView().navigateToCalendar();
                }else{
                    getView().notifyText("There was a problem creating new event");
                }

            }
        });
    }

    @Override
    public boolean validateInput(String title, String description, String start, String end) {
        Boolean valid = true;
        if(!InputValidator.IsInValidLenghtRange(title,3,20)){
            this.getView().setErrorEtTitle("at least 3 characters and maximum 20 characters");
            valid = false;
        }
        else {
            this.getView().setErrorEtTitle(null);
        }

        if(!InputValidator.IsInValidLenghtRange(description,0,255)){
            this.getView().setErrorEtDescription("at least 5 characters and maximum 255 characters");
            valid = false;
        }
        else {
            this.getView().setErrorEtDescription(null);
        }
        if(!InputValidator.isValidTime(start)){
            this.getView().setErrorEtStart("Invalid time");
            valid = false;
        }else{
            this.getView().setErrorEtStart(null);
        }

        if(!InputValidator.isValidTime(end)){
            this.getView().setErrorEtEnd("Invalid time");
            valid = false;
        }else {
            this.getView().setErrorEtEnd(null);
        }
        if(!InputValidator.checkIfEndIsBiggerThanStar(start,end)){
            valid =false;
            this.getView().notifyText("Start time must be earlier End time");
            this.getView().setErrorEtStart("Start time must be earlier than End time");
        }else{
            this.getView().setErrorEtStart(null);
        }



        return valid;
    }
}
