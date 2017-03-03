package com.example.antoan.planit.view.createEvent;

import com.data.EventsData;
import com.example.antoan.planit.ui.MatirialTimePicker;

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
    CreateEventContracts.Presenter provideCalendarPresenter(CreateEventContracts.View view, EventsData eventsData, MatirialTimePicker matirialTimePicker){
        return new CreateEventPresenter(view,eventsData,matirialTimePicker);
    }
}
