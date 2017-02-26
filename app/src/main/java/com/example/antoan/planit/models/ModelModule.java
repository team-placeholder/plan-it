package com.example.antoan.planit.models;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoan on 2/18/2017.
 */
@Module
public class ModelModule {
    @Provides
    Class<User> provideUserType(){
        return User.class;
    }

    @Provides
    Class<User[]> provideUserTypeArray(){
        return User[].class;
    }

    @Provides
    Class<Password> providePasswordType(){return Password.class;}

    @Provides
    Class<Password[]>providePasswordTypeArray(){return Password[].class;}

    @Provides
    ApiUrl<User> provideUserApiUrl(@Named("baseApiUrl") String baseApiUrl){
        return new ApiUrl<>(baseApiUrl,"users");
    }

    @Provides
    ApiUrl<Password> providePasswordApiUrl(@Named("baseApiUrl") String baseApiUrl){
        return new ApiUrl<>(baseApiUrl,"profile/password");
    }
}
