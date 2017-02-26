package com.example.antoan.planit.view.editProfile.changePassword;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.Password;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/20/2017.
 */
@Module
public class ChangePasswordModule {
    @Provides
    ChangePasswordContract.View provideChangePasswordView(){
        return new ChangePasswordView();
    }

    @Provides
    ChangePasswordContract.Presenter provideChangePasswordPresenter(ChangePasswordContract.View view, AuthData httpData, DbOperations db){
        return new ChangePasswordPresenter(view,httpData,db);
    }
}
