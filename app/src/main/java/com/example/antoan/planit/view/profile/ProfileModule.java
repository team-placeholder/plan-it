package com.example.antoan.planit.view.profile;

import com.data.AuthData;
import com.data.SqlData.DbOperations;
import com.data.base.BaseData;
import com.data.models.User;
import com.example.antoan.planit.ui.AcountHeaderBuilder;
import com.example.antoan.planit.ui.DrawerBuilder;
import com.example.antoan.planit.ui.DrawerListener;
import com.example.antoan.planit.utils.ImageHelper;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/20/2017.
 */
@Module
public class ProfileModule {

    @Provides
    ProfileContract.View provideProfileView(){
        return new ProfileView();
    }

    @Provides
    ProfileContract.Presenter provideProfilePresenter(ProfileContract.View view,
                                                      BaseData<User> httpData,
                                                      List<IDrawerItem> drawerItems,
                                                      DbOperations db,
                                                      ImageHelper imgHelper,
                                                      AcountHeaderBuilder acountHeaderBuilder,
                                                      DrawerBuilder drawerBuilder,
                                                      DrawerListener drawerListener,
                                                      AuthData authData){
        return new ProfilePresenter(view,httpData,drawerItems,db,imgHelper,acountHeaderBuilder,drawerBuilder,drawerListener,authData);

    }

    @Provides
    List<IDrawerItem> provideIDrawerItems(){
        ArrayList<IDrawerItem> items = new ArrayList<>();

        PrimaryDrawerItem homeItem = new PrimaryDrawerItem();
        homeItem.withIdentifier(1);
        homeItem.withName("Home");
        homeItem.withIcon(FontAwesome.Icon.faw_home);

        PrimaryDrawerItem settingsItem = new PrimaryDrawerItem();
        settingsItem.withIdentifier(2);
        settingsItem.withName("Profile setting");
        settingsItem.withIcon(GoogleMaterial.Icon.gmd_settings);

        PrimaryDrawerItem friendReqItem = new PrimaryDrawerItem();
        friendReqItem.withIdentifier(3);
        friendReqItem.withName("Friend requests");
        friendReqItem.withIcon(FontAwesome.Icon.faw_user_plus);

        PrimaryDrawerItem friendsItem = new PrimaryDrawerItem();
        friendsItem.withIdentifier(4);
        friendsItem.withName("Friends");
        friendsItem.withIcon(FontAwesome.Icon.faw_user);


        PrimaryDrawerItem logoutItem = new PrimaryDrawerItem();
        logoutItem.withIdentifier(5);
        logoutItem.withName("Logout");
        logoutItem.withIcon(FontAwesome.Icon.faw_arrow_circle_o_left);



        items.add(homeItem);
        items.add(settingsItem);
        items.add(friendReqItem);
        items.add(friendsItem);
        items.add(logoutItem);


        return items;
    }

    @Provides
    List<PrimaryDrawerItem> providePrimaryDrawerItems(){
        ArrayList<PrimaryDrawerItem> items = new ArrayList<>();

        PrimaryDrawerItem homeItem = new PrimaryDrawerItem();
        homeItem.withIdentifier(1);
        homeItem.withName("Home");
        homeItem.withIcon(FontAwesome.Icon.faw_home);

        PrimaryDrawerItem settingsItem = new PrimaryDrawerItem();
        settingsItem.withIdentifier(2);
        settingsItem.withName("Profile setting");
        settingsItem.withIcon(GoogleMaterial.Icon.gmd_settings);

        PrimaryDrawerItem friendReqItem = new PrimaryDrawerItem();
        friendReqItem.withIdentifier(3);
        friendReqItem.withName("Friend requests");
        friendReqItem.withIcon(FontAwesome.Icon.faw_user_plus);

        PrimaryDrawerItem friendsItem = new PrimaryDrawerItem();
        friendsItem.withIdentifier(4);
        friendsItem.withName("Friends");
        friendsItem.withIcon(FontAwesome.Icon.faw_user);

        PrimaryDrawerItem logoutItem = new PrimaryDrawerItem();
        logoutItem.withIdentifier(5);
        logoutItem.withName("Logout");
        logoutItem.withIcon(FontAwesome.Icon.faw_arrow_circle_o_left);



        items.add(homeItem);
        items.add(settingsItem);
        items.add(friendReqItem);
        items.add(friendsItem);
        items.add(logoutItem);


        return items;
    }


}
