package com.example.antoan.planit.view.calendar;

import com.data.services.EventsService;
import com.example.antoan.planit.adapters.AdaptersFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hristov on 28.2.2017 г..
 */

@Module
public class CalendarModule {
    @Provides
    CalendarContracts.View provideCalendarView(){
        return new CalendarFragmentView();
    }

    @Provides
    CalendarContracts.Presenter provideCalendarPresenter(CalendarContracts.View view, EventsService eventsService, AdaptersFactory adaptersFactory){
        return new CalendarPresenter(view, eventsService,adaptersFactory);
    }
}
