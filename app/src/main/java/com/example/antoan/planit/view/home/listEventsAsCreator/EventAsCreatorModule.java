package com.example.antoan.planit.view.home.listEventsAsCreator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 3/3/2017.
 */
@Module
public class EventAsCreatorModule {

    @Provides
    EventsAsCreatorContracts.View provideEventsAsCreatorView(){
        return new EventsAsCreatorView();
    }

    @Provides
    EventsAsCreatorContracts.Presenter provideEventsAsCreatorPresenter(EventsAsCreatorContracts.View view){
        return new EventsAsCreatorPresenter(view);
    }
}
