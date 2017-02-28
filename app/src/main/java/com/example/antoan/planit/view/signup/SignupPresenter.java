package com.example.antoan.planit.view.signup;

import com.data.AuthData;
import com.data.SqlData.DbOperations;
import com.data.models.User;
import com.example.antoan.planit.ui.LoadingDialog;
import com.example.antoan.planit.utils.InputValidator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/19/2017.
 */

public class SignupPresenter implements SignupContract.Presenter {

    private final SignupContract.View view;
    private final AuthData httpData;
    private final DbOperations db;
    private final LoadingDialog loadingDialog;

    public SignupPresenter(SignupContract.View view, AuthData httpData, DbOperations db, LoadingDialog loadingDialog){
        this.view = view;
        this.loadingDialog = loadingDialog;
        this.httpData = httpData;
        this.db = db;
        this.getView().setPresenter(this);
        this.getView().setLoadingDialog(this.loadingDialog);
    }
    @Override
    public SignupContract.View getView() {
        return this.view;
    }

    @Override
    public void start() {

    }

    @Override
    public void signup(final String email, String username, String password) {
        User user = new User(username,email,password,"");
        httpData.register(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isRegistered) throws Exception {
                        if(!isRegistered){
                            getView().onFailedSignup();
                        }else {
                            getView().onSuccesSignup("Succesfully registerd");
                            db.AddEmail(email);
                        }
                    }
                });
    }

    @Override
    public boolean validateInput(String email, String name, String password, String reEnterPassword) {

        Boolean valid = true;
        if (!InputValidator.IsInValidLenghtRange(name,3,10)) {

            this.getView().setErrorEtName("at least 3 characters and maximum 10 characters");
            valid = false;
        }else{
            this.getView().setErrorEtName(null);
        }

        if (!InputValidator.IsValidEmail(email)) {

            this.getView().setErrorEtEmail("enter a valid email address");
            valid = false;
        }else{
            this.getView().setErrorEtEmail(null);
        }

        if (!InputValidator.IsInValidLenghtRange(password,4,10)) {

            this.getView().setErrorEtPassword("between 4 and 10 alphanumeric characters");
            valid = false;
        }else {
            this.getView().setErrorEtPassword(null);
        }

        if (!InputValidator.IsInValidLenghtRange(reEnterPassword,4,10)) {
            this.getView().setErrorEtRePassword("between 4 and 10 alphanumeric characters");
            valid = false;
        }else if(!(reEnterPassword.equals(password))){

            this.getView().setErrorEtRePassword("Password Do not match");
            valid = false;
        }
        else{

            this.getView().setErrorEtRePassword(null);
        }

        return valid;
    }


}
