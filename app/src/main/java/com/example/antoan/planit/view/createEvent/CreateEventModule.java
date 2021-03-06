package com.example.antoan.planit.view.createEvent;

import com.data.services.EventsService;
import com.example.antoan.planit.ui.MaterialTimePicker;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hristov on 2.3.2017 г..
 */

@Module
public class CreateEventModule {
    @Provides
    CreateEventContracts.View provideCreateEventView(){
        return new CreateEventView();
    }

    @Provides
    CreateEventContracts.Presenter provideCalendarPresenter(CreateEventContracts.View view, EventsService eventsService, MaterialTimePicker materialTimePicker){
        return new CreateEventPresenter(view, eventsService, materialTimePicker);
    }
}
