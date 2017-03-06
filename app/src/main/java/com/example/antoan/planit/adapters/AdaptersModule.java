package com.example.antoan.planit.adapters;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 3/6/2017.
 */
@Module
public class AdaptersModule {

    @Provides
    AdaptersFactory provideAdaptersFactory(){
        return new AdaptersFactory();
    }
}
