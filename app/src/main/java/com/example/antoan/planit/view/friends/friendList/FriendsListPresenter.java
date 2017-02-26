package com.example.antoan.planit.view.friends.friendList;

import com.example.antoan.planit.view.friends.friendList.FriendsListContract;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendsListPresenter implements FriendsListContract.Presenter {

    private final FriendsListContract.View view;

    public FriendsListPresenter(FriendsListContract.View view){
        this.view = view;
        this.getView().setPresenter(this);
    }
    @Override
    public FriendsListContract.View getView() {
        return this.view;
    }

    @Override
    public void start() {

    }
}
