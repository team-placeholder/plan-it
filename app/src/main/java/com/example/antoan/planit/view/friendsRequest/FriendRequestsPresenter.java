package com.example.antoan.planit.view.friendsRequest;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendRequestsPresenter implements FriendRequestsContract.Presenter {

    private final FriendRequestsContract.View view;

    public FriendRequestsPresenter(FriendRequestsContract.View view){
        this.view = view;
        this.getView().setPresenter(this);
    }
    @Override
    public FriendRequestsContract.View getView() {
        return this.view;
    }

    @Override
    public void start() {

    }
}
