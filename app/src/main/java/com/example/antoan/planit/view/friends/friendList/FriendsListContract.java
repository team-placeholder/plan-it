package com.example.antoan.planit.view.friends.friendList;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendsListContract {
    public interface View{
        void setPresenter(FriendsListContract.Presenter presenter);
    }

    public interface Presenter{

        FriendsListContract.View getView();

        void start();
    }
}
