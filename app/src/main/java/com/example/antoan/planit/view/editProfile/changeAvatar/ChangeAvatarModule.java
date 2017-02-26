package com.example.antoan.planit.view.editProfile.changeAvatar;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.User;
import com.example.antoan.planit.utils.CheckPermiter;
import com.example.antoan.planit.utils.ImageHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/20/2017.
 */
@Module
public class ChangeAvatarModule {
    @Provides
    ChangeAvatarContract.View provideChangeAvatarView(){
        return new ChangeAvatarView();
    }

    @Provides
    ChangeAvatarContract.Presenter provideChangerAvatarPresenter(ChangeAvatarContract.View view,
                                                                 BaseData<User> httpData,
                                                                 AuthData authData,
                                                                 DbOperations db,
                                                                 ImageHelper imgHelper,
                                                                 CheckPermiter checkPermiter){
        return new ChangeAvatarPresenter(view,httpData, authData,db,imgHelper,checkPermiter);
    }
}
