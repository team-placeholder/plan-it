package com.example.antoan.planit.view.friendProfile;

import android.graphics.Bitmap;

/**
 * Created by Antoan on 3/1/2017.
 */

public class FriendProfileContract {
    public interface View{

        void setPresenter(FriendProfileContract.Presenter presenter);


        void setProfileData(String email, String username, Bitmap avatar);
    }

    public interface Presenter{

        void start();

        FriendProfileContract.View getView();

        void setEmail(String email);
    }
}
