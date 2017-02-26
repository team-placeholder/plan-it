package com.example.antoan.planit.view.signup;

import android.widget.EditText;

import com.example.antoan.planit.ui.LoadingDialog;

/**
 * Created by Antoan on 2/19/2017.
 */

public class SignupContract {
    public interface View{
        void setPresenter(SignupContract.Presenter presenter);

        void onFailedSignup();

        void onSuccesSignup(String msg);

        void setLoadingDialog(LoadingDialog loadingDialog);

        void setErrorEtName(String msg);

        void setErrorEtEmail(String msg);

        void setErrorEtPassword(String msg);

        void setErrorEtRePassword(String msg);

    }

    public interface Presenter{
        SignupContract.View getView();

        void start();


        void signup(String email, String username, String password);

        boolean validateInput(String email, String name, String password, String reEnterPassword);
    }
}
