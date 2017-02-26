package com.example.antoan.planit.view.login;

import android.database.Cursor;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.SqlData.UserContract;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.ResponsePair;
import com.example.antoan.planit.models.User;
import com.example.antoan.planit.ui.LoadingDialog;
import com.example.antoan.planit.utils.InputValidator;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/18/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final DbOperations db;
    private final LoginContract.View view;
    private final AuthData httpData;
    private final LoadingDialog loadingDialog;
    private ArrayList<String> emails;

    public LoginPresenter(LoginContract.View view, AuthData httpData, DbOperations db, LoadingDialog loadingDialog){
        this.db = db;
        this.loadingDialog = loadingDialog;
        this.view = view;
        this.httpData =httpData;
        this.getView().setPresenter(this);
    }
    @Override
    public void start() {
        this.setEmails();
       this.getView().setEmails(emails);
        this.getView().setLoadingDialog(this.loadingDialog);
    }

    @Override
    public LoginContract.View getView() {
        return this.view;
    }

    @Override
    public void setEmails() {
        Cursor cursor = db.getEmailsContent();
        this.emails = new ArrayList<>();

        while (cursor.moveToNext()) {
            String currentEmail = cursor.getString(cursor.getColumnIndex(UserContract.EmailEntry.COLUMN_EMAIL));
            emails.add(currentEmail);
        }
    }

    @Override
    public Boolean validateInput(String email, String password) {
        boolean valid = true;



        if (!InputValidator.IsValidEmail(email)) {
            getView().setErrorTvEmail("enter a valid email address");
            valid = false;
        }else {
            getView().setErrorTvEmail(null);
        }

        if (!InputValidator.IsInValidLenghtRange(password,4,10)) {
            getView().setErrorEtPassword("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            getView().setErrorEtPassword(null);
        }

        return valid;
    }

    @Override
    public void requestLogin(String email, String password) {
        User user = new User("",email,password,"");
        this.httpData.authorize(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponsePair>() {
                    @Override
                    public void accept(ResponsePair res) throws Exception {
                        if(res.getStatusCode() != 200){
                            getView().onFailedLogin();

                        }
                        else {
                            if (!emails.contains(res.getData().getEmail())) {
                                db.AddEmail(res.getData().getEmail());
                            }

                            db.addCurUser(res.getData().getEmail(), res.getData().getUsername(), res.getData().getPassword());

                            getView().succesfulLogin();
                        }
                    }
                });
    }

}
