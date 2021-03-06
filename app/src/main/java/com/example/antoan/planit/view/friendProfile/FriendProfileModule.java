package com.example.antoan.planit.view.friendProfile;

import com.data.services.EventsService;
import com.data.HttpData;
import com.data.models.User;
import com.example.antoan.planit.adapters.AdaptersFactory;
import com.example.antoan.planit.utils.ImageHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 3/1/2017.
 */
@Module
public class FriendProfileModule {
    @Provides
    FriendProfileContract.View provideFriendProfileView(){
        return new FriendProfileView();
    }

    @Provides
    FriendProfileContract.Presenter provideFriendProfilePresenter(FriendProfileContract.View view, HttpData<User> httpData, ImageHelper imageHelper, EventsService eventsService, AdaptersFactory adaptersFactory){
        return new FriendProfilePresenter(view,httpData,imageHelper, eventsService,adaptersFactory);
    }
}
