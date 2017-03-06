package com.example.antoan.planit.data;

import android.content.Context;

import com.data.AuthData;
import com.data.Http;
import com.data.HttpData;
import com.data.SqlData.DbOperations;
import com.data.base.BaseData;
import com.data.base.BaseHttp;
import com.data.models.ApiUrl;
import com.data.models.Password;
import com.data.models.User;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/18/2017.
 */
@Module
public class DataModule {

    @Provides
    BaseData<User> provideHttpUserData(ApiUrl<User> apiUrl,Class<User> klass,Class<User[]> klassArray){
        return new HttpData<User>(apiUrl,klass,klassArray);
    }
    @Provides
    BaseData<Password> provideHttpPasswordData(ApiUrl<Password> apiUrl, Class<Password> klass, Class<Password[]> klassArray){
        return new HttpData<Password>(apiUrl,klass,klassArray);
    }

    @Provides
    AuthData provideAuthData(@Named("baseApiUrl")String baseApiUrl, Context context){
        return new AuthData(baseApiUrl,context);
    }
    @Provides
    DbOperations provideSqlDb(Context context){
        return new DbOperations(context);
    }

    @Provides
    BaseHttp provideHttp(){
        return new Http();
    }
}
