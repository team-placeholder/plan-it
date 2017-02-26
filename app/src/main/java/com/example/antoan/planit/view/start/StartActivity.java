package com.example.antoan.planit.view.start;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.view.home.HomeActivity;
import com.example.antoan.planit.view.login.LoginActivity;

import javax.inject.Inject;

public class StartActivity extends AppCompatActivity {

    @Inject
    public DbOperations db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.injectDependacies();

        Cursor cursor = this.db.getCurrentUser();

        if(cursor.getCount()>0){
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        this.finish();
    }

    private void injectDependacies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }
}
