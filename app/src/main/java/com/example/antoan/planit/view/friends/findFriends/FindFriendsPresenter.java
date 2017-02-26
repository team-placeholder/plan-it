package com.example.antoan.planit.view.friends.findFriends;

import android.database.Cursor;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.SqlData.UserContract;
import com.example.antoan.planit.models.User;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/24/2017.
 */

public class FindFriendsPresenter implements FindFriendsContracts.Presenter {

    private final FindFriendsContracts.View view;
    private final AuthData authData;
    private final DbOperations db;
    private ArrayList<User> userArrayList;
    private String email;

    public FindFriendsPresenter(FindFriendsContracts.View view, AuthData authData, DbOperations db){
        this.view = view;
        this.db = db;
        this.authData = authData;
        this.getView().setPresenter(this);
    }
    @Override
    public FindFriendsContracts.View getView() {
        return this.view;
    }

    @Override
    public void start() {

    }

    @Override
    public void findFriends(String pattern) {
        if(pattern.length()<2){
            this.getView().notifyText("Your pattern word must be atleast 3 characters long!");
        }else {
            this.authData.findFriends(pattern).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<User[]>() {
                        @Override
                        public void accept(User[] users) throws Exception {
                            userArrayList = new ArrayList<User>(Arrays.asList(users));
                            getView().setUsers(userArrayList);
                        }
                    });
        }
    }

    @Override
    public void requestForFriends(final int position) {
        this.getCurrentUser();
        User currUser = new User("",this.email,"","");
        User[] users = new User[]{currUser,userArrayList.get(position)};
        this.authData.sendFriendRequest(users).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean){
                            userArrayList.remove(position);
                            getView().setUsers(userArrayList);
                        }

                    }
                });
    }

    private void getCurrentUser(){
        Cursor cursor = db.getCurrentUser();

        if(cursor.moveToFirst()){
            this.email = cursor.getString(cursor.getColumnIndex(UserContract.ResUserEntry.COLUMN_EMAIL));
        }
    }
}
