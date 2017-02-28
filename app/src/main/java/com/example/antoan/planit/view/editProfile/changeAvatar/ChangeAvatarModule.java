package com.example.antoan.planit.view.editProfile.changeAvatar;

import com.data.AuthData;
import com.data.SqlData.DbOperations;
import com.data.base.BaseData;
import com.data.models.User;
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
