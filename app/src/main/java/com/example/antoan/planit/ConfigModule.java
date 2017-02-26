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
       return  "http://192.168.198.129:3000/api/";
    }
}
