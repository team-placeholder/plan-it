package com.example.antoan.planit.view.start;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.data.AuthData;
import com.data.SqlData.DbOperations;
import com.data.SqlData.UserContract;
import com.data.models.ResponsePair;
import com.data.models.User;
import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.ui.LoadingDialog;
import com.example.antoan.planit.view.calendar.CalendarActivity;
import com.example.antoan.planit.view.login.LoginActivity;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class StartActivity extends AppCompatActivity {

    @Inject
    public DbOperations db;

    @Inject
    public AuthData authData;

    @Inject
    public LoadingDialog loadingDialog;
    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.injectDependencies();
        this.loadingDialog.show();
        Cursor cursor = this.db.getCurrentUser();
        cursor.moveToFirst();

        this.activity = this;

        if(cursor.getCount()>0){
            String email = cursor.getString(cursor.getColumnIndex(UserContract.ResUserEntry.COLUMN_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(UserContract.ResUserEntry.COLUMN_PASSWORD));
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            authData.authorize(user).subscribe(new Consumer<ResponsePair>() {
                @Override
                public void accept(ResponsePair responsePair) throws Exception {
                    if (responsePair.getStatusCode() < 400){
                        navigateCalendar(activity);
                    }
                    else{
                        navigateLogin(activity);
                    }
                    loadingDialog.hide();
                    loadingDialog.dismiss();
                    finish();
                }
            });

        }else{
            this.loadingDialog.hide();
            this.loadingDialog.dismiss();
            navigateLogin(this.activity);
            this.finish();
        }
    }

    private void injectDependencies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }

    private void navigateLogin(AppCompatActivity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    private void navigateCalendar(AppCompatActivity activity){
        Intent intent = new Intent(activity, CalendarActivity.class);
        startActivity(intent);
    }
}
