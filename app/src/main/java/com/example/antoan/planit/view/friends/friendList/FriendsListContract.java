package com.example.antoan.planit.view.friends.friendList;

import com.data.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendsListContract {
    public interface View{
        void setPresenter(FriendsListContract.Presenter presenter);

        void setFriends(List<User> userArrayList);

        void notifyText(String email);

        void navigateToFriendProfile(String email);
    }

    public interface Presenter{

        FriendsListContract.View getView();

        void start();

        void navigateToFriendProfile(int position);
    }
}
