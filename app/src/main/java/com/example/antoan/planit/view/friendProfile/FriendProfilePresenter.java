package com.example.antoan.planit.view.friendProfile;

import android.graphics.Bitmap;

import com.data.HttpData;
import com.data.models.User;
import com.example.antoan.planit.utils.ImageHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 3/1/2017.
 */

public class FriendProfilePresenter implements FriendProfileContract.Presenter {

    private final FriendProfileContract.View view;
    private final HttpData httpData;
    private final ImageHelper imgHelper;
    private String email;
    private User currUser;

    public FriendProfilePresenter(FriendProfileContract.View view, HttpData httpData, ImageHelper imgHelper){
        this.view = view;
        this.httpData = httpData;
        this.imgHelper = imgHelper;
        getView().setPresenter(this);
    }
    @Override
    public void start() {

        this.httpData.getById(this.email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        currUser = user;
                        Bitmap avatar = imgHelper.FromStringToBitmap(user.getAvatar());
                        getView().setProfileData(user.getEmail(),user.getUsername(),avatar);


                    }
                });
    }

    @Override
    public FriendProfileContract.View getView() {
        return this.view;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
