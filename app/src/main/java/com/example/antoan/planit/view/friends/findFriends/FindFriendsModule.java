package com.example.antoan.planit.view.friends.findFriends;

import com.data.AuthData;
import com.data.SqlData.DbOperations;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/24/2017.
 */
@Module
public class FindFriendsModule {

    @Provides
    FindFriendsContracts.View provideFindFriendView(){
        return new FindFriendsView();
    }

    @Provides
    FindFriendsContracts.Presenter provideFindFriendPresenter(FindFriendsContracts.View view, AuthData authData, DbOperations db){
        return new FindFriendsPresenter(view,authData, db);
    }
}
