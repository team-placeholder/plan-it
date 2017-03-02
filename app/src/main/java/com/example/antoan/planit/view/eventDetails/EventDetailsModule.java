package com.example.antoan.planit.view.eventDetails;

import com.data.EventsData;

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
    EventDetailsContracts.Presenter provideEventDetailsPresenter(EventDetailsContracts.View view, EventsData eventsData){
        return new EventDetailsPresenter(view, eventsData);
    }
}
