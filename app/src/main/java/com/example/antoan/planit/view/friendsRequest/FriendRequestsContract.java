package com.example.antoan.planit.view.friendsRequest;

import com.example.antoan.planit.models.User;

import java.util.List;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendRequestsContract {
    public interface ISelectableButton{
        void OnSelectButton(Integer position);
    }
    public interface View extends FriendRequestsContract.ISelectableButton{
        void setPresenter(FriendRequestsContract.Presenter presenter);

        void setRequests(List<User> users);
    }

    public interface Presenter{

        FriendRequestsContract.View getView();

        void start();

        void acceptFriendRequest(Integer position);
    }
}
