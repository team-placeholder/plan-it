package com.example.antoan.planit.view.editProfile.changePassword;

/**
 * Created by Antoan on 2/20/2017.
 */

public class ChangePasswordContract {
    public interface View{
        void setPresenter(ChangePasswordContract.Presenter presenter);

        void setErrorOnEtReNewPassword(String msg);

        void setErrorOnEtNewPassword(String msg);

        void setErrorOnEtOldPassword(String msg);

        void notifyOnServerResponse(String msg);

        void navigate();
    }
    public interface Presenter{

        void start();

        ChangePasswordContract.View getView();

        Boolean validateInput(String oldPass, String newPass, String newRePass);

        void updatePassword(String oldPass, String newPass);


    }
}
