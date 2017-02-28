package com.example.antoan.planit.view.friendsRequest;

import com.data.AuthData;
import com.data.models.User;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendRequestsPresenter implements FriendRequestsContract.Presenter {

    private final FriendRequestsContract.View view;
    private final AuthData authData;
    private ArrayList<User> requestArrayList;

    public FriendRequestsPresenter(FriendRequestsContract.View view, AuthData authData){
        this.view = view;
        this.authData = authData;
        this.getView().setPresenter(this);
    }
    @Override
    public FriendRequestsContract.View getView() {
        return this.view;
    }

    @Override
    public void start() {

        this.authData.getFriendRequests().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User[]>() {
                    @Override
                    public void accept(User[] users) throws Exception {
                        requestArrayList = new ArrayList<User>(Arrays.asList(users));
                        getView().setRequests(requestArrayList);
                    }
                });
    }

    @Override
    public void acceptFriendRequest(Integer position) {

    }
}
