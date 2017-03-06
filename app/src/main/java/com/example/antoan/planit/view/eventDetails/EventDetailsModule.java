package com.example.antoan.planit.view.eventDetails;

import com.data.services.EventsService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hristov on 2.3.2017 г..
 */

@Module
public class EventDetailsModule {
    @Provides
    EventDetailsContracts.View provideEventDetailsView(){
        return new EventDetailsView();
    }

    @Provides
    EventDetailsContracts.Presenter provideEventDetailsPresenter(EventDetailsContracts.View view, EventsService eventsService){
        return new EventDetailsPresenter(view, eventsService);
    }
}
