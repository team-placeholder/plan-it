package com.example.antoan.planit.view.login;

import com.example.antoan.planit.ui.LoadingDialog;

import java.util.List;

/**
 * Created by Antoan on 2/18/2017.
 */

public class LoginContract {
    public interface View{
        void setPresenter(LoginContract.Presenter presenter);

        void setEmails(List<String> emailCollection);

        void succesfulLogin();

        void onFailedLogin();

        void setLoadingDialog(LoadingDialog loadingDialog);

        void setErrorTvEmail(String msg);

        void setErrorEtPassword(String msg);

    }

    public interface Presenter{
        void start();

        LoginContract.View getView();

        void setEmails();

        Boolean validateInput(String email, String password);

        void requestLogin(String email, String password);
    }
}
