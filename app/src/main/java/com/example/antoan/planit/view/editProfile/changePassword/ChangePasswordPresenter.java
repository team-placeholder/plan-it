package com.example.antoan.planit.view.editProfile.changePassword;

import android.database.Cursor;

import com.example.antoan.planit.data.AuthData;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.data.SqlData.UserContract;
import com.example.antoan.planit.data.base.BaseData;
import com.example.antoan.planit.models.Password;
import com.example.antoan.planit.utils.InputValidator;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoan on 2/20/2017.
 */

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter{

    private final ChangePasswordContract.View view;
    private final AuthData httpData;
    private final DbOperations db;
    private String email;

    @Inject
    public ChangePasswordPresenter(ChangePasswordContract.View view, AuthData httpData, DbOperations db){
        this.view = view;
        this.httpData = httpData;
        this.db = db;
        this.getView().setPresenter(this);

    }
    @Override
    public void start() {

    }

    @Override
    public ChangePasswordContract.View getView() {
        return this.view;
    }

    @Override
    public Boolean validateInput(String oldPass, String newPass, String newRePass) {
        Boolean isValid = true;
        if(!(newPass.equals(newRePass))){

            this.getView().setErrorOnEtReNewPassword("Password Do not match!");
            isValid = false;
        }else{
            this.getView().setErrorOnEtReNewPassword(null);
        }
        if (!InputValidator.IsInValidLenghtRange(newPass,4,10)){

            this.getView().setErrorOnEtNewPassword("between 4 and 10 alphanumeric characters");
            isValid = false;
        }
        else{
            this.getView().setErrorOnEtNewPassword(null);
        }
        if(!InputValidator.IsInValidLenghtRange(oldPass,4,10)){
            this.getView().setErrorOnEtOldPassword("between 4 and 10 alphanumeric characters");
            isValid = false;
        }
        else {
            this.getView().setErrorOnEtOldPassword(null);
        }

        return isValid;

    }

    @Override
    public void updatePassword(String oldPass, String newPass) {
        this.getCurrentUser();
        this.httpData.updatePassword(new Password(this.email,oldPass,newPass))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isUpdated) throws Exception {
                if(isUpdated) {
                    getView().notifyOnServerResponse("Successfully updated password.");
                    getView().navigate();
                }else{
                    getView().notifyOnServerResponse("Problem with updating password occured.");
                }
            }
        });


    }



    private void getCurrentUser(){
        Cursor cursor = db.getCurrentUser();

        if(cursor.moveToFirst()){
            this.email = cursor.getString(cursor.getColumnIndex(UserContract.ResUserEntry.COLUMN_EMAIL));
        }
    }

}
