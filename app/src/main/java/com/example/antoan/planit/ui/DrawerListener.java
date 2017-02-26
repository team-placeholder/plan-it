package com.example.antoan.planit.ui;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import com.example.antoan.planit.view.editProfile.EditProfileActivity;
import com.example.antoan.planit.view.home.HomeActivity;
import com.example.antoan.planit.view.login.LoginActivity;
import com.example.antoan.planit.view.profile.ProfileContract;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by Antoan on 2/22/2017.
 */

public class DrawerListener {

    private ProfileContract.Logoutable presenter;

    public DrawerListener(){

    }


    public Intent initDrawer(Context ctx, IDrawerItem drawerItem){
        Intent intent = null;
        switch ((int)drawerItem.getIdentifier()){
            case 1:
                intent = new Intent(ctx,HomeActivity.class);

                break;
            case 2:
                intent = new Intent(ctx,EditProfileActivity.class);

                break;
            case 3:
                presenter.logout();
                intent = new Intent(ctx, LoginActivity.class);
                break;

        }
        return intent;
    }

    public void setPresenter(ProfileContract.Logoutable presenter) {
        this.presenter = presenter;
    }
}
