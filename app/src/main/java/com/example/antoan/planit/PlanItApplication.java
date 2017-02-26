package com.example.antoan.planit;

import android.app.Application;
import android.graphics.Bitmap;

import com.example.antoan.planit.data.DataModule;
import com.example.antoan.planit.models.ModelModule;
import com.example.antoan.planit.ui.UiModule;
import com.example.antoan.planit.utils.UtilsModule;
import com.example.antoan.planit.view.ViewModule;
import com.example.antoan.planit.view.editProfile.EditProfileActivity;
import com.example.antoan.planit.view.editProfile.EditProfileModule;
import com.example.antoan.planit.view.friends.FriendsActivity;
import com.example.antoan.planit.view.friendsRequest.FriendRequestsActivity;
import com.example.antoan.planit.view.home.HomeActivity;
import com.example.antoan.planit.view.login.LoginActivity;
import com.example.antoan.planit.view.signup.SignupActivity;
import com.example.antoan.planit.view.start.StartActivity;

import dagger.Component;

/**
 * Created by Antoan on 2/18/2017.
 */

public class PlanItApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

      this.component = DaggerPlanItApplication_ApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Component(modules = {DataModule.class, ConfigModule.class,ApplicationModule.class, ViewModule.class, ModelModule.class, UiModule.class, UtilsModule.class, EditProfileModule.class})
    public interface ApplicationComponent {
        void inject(HomeActivity homeActivity);

        void inject(LoginActivity loginActivity);

        void inject(SignupActivity signupActivity);

        void inject(EditProfileActivity editProfileActivity);

        void inject(StartActivity startActivity);

        void inject(FriendRequestsActivity friendRequestsActivity);

        void inject(FriendsActivity friendsActivity);
    }
}
