package com.example.antoan.planit.view.friendsRequest;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendRequestsContract {
    public interface View{
        void setPresenter(FriendRequestsContract.Presenter presenter);
    }

    public interface Presenter{

        FriendRequestsContract.View getView();

        void start();
    }
}
