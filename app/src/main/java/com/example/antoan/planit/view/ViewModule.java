package com.example.antoan.planit.view;

import com.example.antoan.planit.view.friends.FriendsModule;
import com.example.antoan.planit.view.friends.friendList.FriendsListModule;
import com.example.antoan.planit.view.friendsRequest.FriendsRequestModule;
import com.example.antoan.planit.view.login.LoginModule;
import com.example.antoan.planit.view.profile.ProfileModule;
import com.example.antoan.planit.view.signup.SignupModule;

import dagger.Module;

/**
 * Created by Antoan on 2/19/2017.
 */
@Module(includes = {LoginModule.class, SignupModule.class, ProfileModule.class, FriendsRequestModule.class, FriendsModule.class})
public class ViewModule {
}