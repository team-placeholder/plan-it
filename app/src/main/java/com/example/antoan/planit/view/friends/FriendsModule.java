package com.example.antoan.planit.view.friends;

import com.example.antoan.planit.view.friends.findFriends.FindFriendsModule;
import com.example.antoan.planit.view.friends.friendList.FriendsListModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/24/2017.
 */
@Module(includes = {FindFriendsModule.class, FriendsListModule.class})
public class FriendsModule {
}
