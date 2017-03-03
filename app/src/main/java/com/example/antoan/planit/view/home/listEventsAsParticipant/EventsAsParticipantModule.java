package com.example.antoan.planit.view.home.listEventsAsParticipant;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 3/3/2017.
 */
@Module
public class EventsAsParticipantModule {

    @Provides
    EventsAsParticipantContract.View provideEventsAsParticipantView(){
        return new EventsAsParticipantView();
    }

    @Provides
    EventsAsParticipantContract.Presenter provideEventsAsParticipantPresenter(EventsAsParticipantContract.View view){
        return new EventsAsParticipantPresenter(view);
    }
}
