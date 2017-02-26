package com.example.antoan.planit.view.friends.friendList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/24/2017.
 */
@Module
public class FriendsListModule {

    @Provides
    FriendsListContract.View provideFriendsView(){
        return new FriendsListView();
    }

    @Provides
    FriendsListContract.Presenter provideFriends(FriendsListContract.View view){
        return new FriendsListPresenter(view);
    }
}
