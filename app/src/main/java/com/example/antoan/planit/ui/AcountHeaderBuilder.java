package com.example.antoan.planit.ui;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.antoan.planit.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import javax.inject.Inject;

/**
 * Created by Antoan on 2/22/2017.
 */

public class AcountHeaderBuilder {
    private  AccountHeaderBuilder accountHeaderBuilder;

    @Inject
    public AcountHeaderBuilder(){
        this.accountHeaderBuilder =  new AccountHeaderBuilder();
    }


    public AccountHeader getAcountHeader(Activity activity,String name, String email, Bitmap avatar){
      return  this.accountHeaderBuilder.withActivity(activity).withHeaderBackground(R.drawable.navigation_header_profile)
                .addProfiles(new ProfileDrawerItem().withName(name).withEmail(email).withIcon(avatar)).build();
    }
}
