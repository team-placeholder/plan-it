package com.example.antoan.planit.view.friends.friendList;

import com.data.AuthData;
import com.data.models.User;
import com.example.antoan.planit.view.friends.friendList.FriendsListContract;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FriendsListPresenter implements FriendsListContract.Presenter {

    private final FriendsListContract.View view;
    private final AuthData authData;
    private ArrayList<User> userArrayList;

    public FriendsListPresenter(FriendsListContract.View view, AuthData authData){
        this.view = view;
        this.authData = authData;
        this.getView().setPresenter(this);
    }
    @Override
    public FriendsListContract.View getView() {
        return this.view;
    }

    @Override
    public void start() {
        this.authData.getFriends().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User[]>() {
                    @Override
                    public void accept(User[] users) throws Exception {
                        userArrayList = new ArrayList<User>(Arrays.asList(users));
                        getView().setFriends(userArrayList);
                    }
                });
    }

    @Override
    public void navigateToFriendProfile(int position) {
        getView().navigateToFriendProfile(userArrayList.get(position).getEmail());
    }
}
