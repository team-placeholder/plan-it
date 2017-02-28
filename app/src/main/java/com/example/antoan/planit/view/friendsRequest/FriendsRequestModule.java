package com.example.antoan.planit.view.friendsRequest;

import com.data.AuthData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/24/2017.
 */
@Module
public class FriendsRequestModule {

    @Provides
    FriendRequestsContract.View provideFrReqView(){
        return new FriendRequestsView();
    }

    @Provides
    FriendRequestsContract.Presenter provideFrReqPresenter(FriendRequestsContract.View view, AuthData authData){
        return new FriendRequestsPresenter(view,authData);
    }
}
