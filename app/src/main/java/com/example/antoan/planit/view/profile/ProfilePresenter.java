package com.example.antoan.planit.view.profile;

import android.database.Cursor;
import android.graphics.Bitmap;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.SqlData.UserContract;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.User;
import com.example.antoan.planit.ui.AcountHeaderBuilder;
import com.example.antoan.planit.ui.DrawerBuilder;
import com.example.antoan.planit.ui.DrawerListener;
import com.example.antoan.planit.utils.ImageHelper;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/20/2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter{

    private final ProfileContract.View view;
    private final BaseData<User> httpData;
    private final List<IDrawerItem> drawerItems;
    private final DbOperations db;
    private final ImageHelper imgHelper;
    private final DrawerListener drawerListener;
    private final AuthData authData;
    private String email;
    private User currUser;

    @Inject
    public ProfilePresenter(ProfileContract.View view,
                            BaseData<User> httpData,
                            List<IDrawerItem> drawerItems,
                            DbOperations db,
                            ImageHelper imgHelper,
                            AcountHeaderBuilder acountHeaderBuilder,
                            DrawerBuilder drawerBuilder,
                            DrawerListener drawerListener,
                            AuthData authData
                            ){
        this.view = view;
        this.httpData = httpData;
        this.drawerItems = drawerItems;
        this.drawerListener = drawerListener;
        this.authData = authData;
        this.db = db;
        this.imgHelper = imgHelper;
        this.getView().setDrawerLister(drawerListener);
        this.getView().setAccountHeaderBuilder(acountHeaderBuilder);
        this.getView().setDrawerBuilder(drawerBuilder);
        this.getView().setPresenter(this);
        this.getView().setDrawerItems(this.drawerItems);
        this.getView().setOnDrawerItemClickListener();

    }
    @Override
    public void start() {
        this.getCurrentUser();
        this.httpData.getById(this.email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        currUser = user;
                        Bitmap avatar = imgHelper.FromStringToBitmap(user.getAvatar());
                        getView().setupAccountHeader(user.getUsername(),user.getEmail(),avatar);
                        getView().setupDrawer();
                        getView().setNewRequests(3,user.getNewRequest());

                    }
                });

    }

    @Override
    public ProfileContract.View getView() {
        return this.view;
    }

    @Override
    public void logout() {
        db.clearUsers();
    }

    @Override
    public void clearNewRequests(final int indetifier, final int value) {
        this.authData.cleanNewRequest(this.currUser).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isCleared) throws Exception {
                if(isCleared){
                    getView().setNewRequests(indetifier,value);
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
