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
    public void acceptFriendRequest(final Integer position) {
         this.authData.acceptFriendRequest(requestArrayList.get(position))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<Boolean>() {
                     @Override
                     public void accept(Boolean isSuccesful) throws Exception {
                         if(isSuccesful){
                             getView().notifyText("Succesfully added into your friend list");
                             requestArrayList.remove((int)position);
                             getView().setRequests(requestArrayList);
                         }else{
                             getView().notifyText("There was a problem with your request");
                         }
                     }
                 });
    }

    @Override
    public void declineFriendRequest(final Integer position) {

        this.authData.declineFriendRequest(requestArrayList.get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isSuccesful) throws Exception {
                        if(isSuccesful){
                            getView().notifyText("Succesfully declined friend request");
                            requestArrayList.remove((int)position);
                            getView().setRequests(requestArrayList);
                        }else{
                            getView().notifyText("There was a problem with your request");
                        }
                    }
                });
    }
}
