package com.example.antoan.planit.view.friends.findFriends;

import com.data.models.User;
import com.example.antoan.planit.adapters.AdaptersFactory;

import java.util.List;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FindFriendsContracts {
    public interface ISelectableButton{
        void OnSelectButton(Integer position);
    }
    public interface View extends  FindFriendsContracts.ISelectableButton{
        void setPresenter(FindFriendsContracts.Presenter presenter);

        void notifyText(String msg);

        void setUsers(List<User> users);

        void navigateToFriendProfile(String email);

        void setAdapterFactory(AdaptersFactory adapterFactory);
    }

    public interface Presenter{

        FindFriendsContracts.View getView();

        void start();

        void findFriends(String pattern);

        void requestForFriends(int position);

        void navigateToFriendProfile(int position);
    }
}
