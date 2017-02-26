package com.example.antoan.planit.view.signup;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.User;
import com.example.antoan.planit.ui.LoadingDialog;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/19/2017.
 */
@Module
public class SignupModule {
    @Provides
    SignupContract.View provideSignupView(){
        return new SignupView();
    }

    @Provides
    SignupContract.Presenter provideSignupPresenter(SignupContract.View view, AuthData httpData, DbOperations db, LoadingDialog loadingDialog){
        return new SignupPresenter(view,httpData,db,loadingDialog);
    }
}
