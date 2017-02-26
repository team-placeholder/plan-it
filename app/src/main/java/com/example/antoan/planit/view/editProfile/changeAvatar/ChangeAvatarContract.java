package com.example.antoan.planit.view.editProfile.changeAvatar;

import android.graphics.Bitmap;

import com.example.antoan.planit.utils.CheckPermiter;

/**
 * Created by Antoan on 2/20/2017.
 */

public class ChangeAvatarContract {
    public interface View{
        void setPresenter(ChangeAvatarContract.Presenter presenter);

        void setImage(Bitmap avatar);

        void setCheckPermiter(CheckPermiter checkPermiter);

        void navigate();

        void notifyMessage(String s);
    }

    public interface Presenter{
        void start();

        ChangeAvatarContract.View getView();

        void onCaptureImage(Bitmap thumbnail);

        void onSelectFromGallery(Bitmap thumbnail);

        void updateAvatar();

    }
}
