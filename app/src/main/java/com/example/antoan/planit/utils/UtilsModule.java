package com.example.antoan.planit.utils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/20/2017.
 */
@Module
public class UtilsModule {
    @Provides
    ImageHelper provideImageHelper(){
        return new ImageHelper();
    }

    @Provides
    CheckPermiter provideCheckPermiter(){
        return new CheckPermiter();
    }
}
