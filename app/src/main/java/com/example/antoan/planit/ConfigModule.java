package com.example.antoan.planit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/18/2017.
 */
@Module
public class ConfigModule {
    @Provides
    @Named("baseApiUrl")
    String provideBaseApiUrl(){
        return  "http://192.168.0.100:3000/api/";
        //return "http://192.168.0.102:3000/api/";
    }
}
