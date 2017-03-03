package com.example.antoan.planit.view.home;

import com.example.antoan.planit.view.home.listEventsAsCreator.EventAsCreatorModule;
import com.example.antoan.planit.view.home.listEventsAsParticipant.EventsAsParticipantModule;

import dagger.Module;

/**
 * Created by Antoan on 3/3/2017.
 */
@Module(includes = {EventAsCreatorModule.class, EventsAsParticipantModule.class})
public class HomeModule {
}
