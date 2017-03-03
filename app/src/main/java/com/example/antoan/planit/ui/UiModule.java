package com.example.antoan.planit.ui;

import android.content.Context;
import android.widget.ListView;

import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/19/2017.
 */
@Module
public class UiModule {

    @Provides
    LoadingDialog provideLoadingDialog(Context context){
        return  new LoadingDialog(context,"Plaese Wait");
    }

    @Provides
    AcountHeaderBuilder provideAccountheaderBuilder(){
        return new AcountHeaderBuilder();
    }

    @Provides
    DrawerBuilder provideDrawerBuilder(List<PrimaryDrawerItem> drawerItems){
        return new DrawerBuilder(drawerItems);
    }

    @Provides
    DrawerListener provideDrawerListener(){
        return new DrawerListener();
    }

    @Provides
    MatirialTimePicker provideMatirialTimePicker(){
        return new MatirialTimePicker();
    }
}
