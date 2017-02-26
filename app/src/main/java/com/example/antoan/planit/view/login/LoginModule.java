package com.example.antoan.planit.view.login;

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
public class LoginModule {
    @Provides
    LoginContract.View provideLoginView(){
        return new  LoginView();
    }

    @Provides
    LoginContract.Presenter provideSignupPresenter(LoginContract.View view, AuthData httpData, DbOperations db, LoadingDialog loadingDialog){
        return new LoginPresenter(view,httpData,db,loadingDialog);
    }
}
