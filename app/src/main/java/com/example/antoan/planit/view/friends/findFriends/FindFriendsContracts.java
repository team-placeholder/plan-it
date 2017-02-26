package com.example.antoan.planit.view.friends.findFriends;

import com.example.antoan.planit.models.User;
import com.example.antoan.planit.view.friends.friendList.FriendsListContract;

import java.util.List;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FindFriendsContracts {
    public interface View{
        void setPresenter(FindFriendsContracts.Presenter presenter);

        void notifyText(String msg);

        void setUsers(List<User> users);
    }

    public interface Presenter{

        FindFriendsContracts.View getView();

        void start();

        void findFriends(String pattern);

        void requestForFriends(int position);
    }
}
