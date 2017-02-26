package com.example.antoan.planit;

import android.content.Context;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/19/2017.
 */
@Module
public class ApplicationModule {
    private final Context context;

    @Inject
    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return this.context;
    }
}
