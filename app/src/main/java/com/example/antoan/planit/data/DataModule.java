package com.example.antoan.planit.data;

import android.content.Context;

import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.ApiUrl;
import com.example.antoan.planit.models.Password;
import com.example.antoan.planit.models.User;

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

}
