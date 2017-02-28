package com.example.antoan.planit.view.friendsRequest;

import com.data.models.User;

import java.util.List;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendRequestsContract {
    public interface ISelectableButton{
        void acceptFriendRequest(Integer position);

        void declineFriendRequest(Integer position);
    }
    public interface View extends FriendRequestsContract.ISelectableButton{
        void setPresenter(FriendRequestsContract.Presenter presenter);

        void setRequests(List<User> users);
    }

    public interface Presenter{

        FriendRequestsContract.View getView();

        void start();

        void acceptFriendRequest(Integer position);

        void declineFriendRequest(Integer position);
    }
}
