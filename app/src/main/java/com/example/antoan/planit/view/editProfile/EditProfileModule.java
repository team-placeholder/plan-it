package com.example.antoan.planit.view.editProfile;

import com.example.antoan.planit.view.editProfile.changeAvatar.ChangeAvatarModule;
import com.example.antoan.planit.view.editProfile.changePassword.ChangePasswordModule;

import dagger.Module;

/**
 * Created by Antoan on 2/20/2017.
 */
@Module(includes = {ChangeAvatarModule.class, ChangePasswordModule.class})
public class EditProfileModule {
}
