package com.example.antoan.planit.view.friendProfile;

import android.graphics.Bitmap;

import com.data.models.PlanedEvent;

import java.util.List;

/**
 * Created by Antoan on 3/1/2017.
 */

public class FriendProfileContract {
    public interface View{

        void setPresenter(FriendProfileContract.Presenter presenter);


        void setProfileData(String email, String username, Bitmap avatar);

        void setUsersEvents(List<PlanedEvent> events);

        void hideShowButton();

        void displayNoEvents();

        void navigate(String eventId);
    }

    public interface Presenter{

        void start();

        FriendProfileContract.View getView();

        void setEmail(String email);

        void loadEvents();

        void navigate(int position);
    }
}
