package com.example.antoan.planit.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.view.login.LoginActivity;
import com.example.antoan.planit.view.profile.ProfileContract;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    @Inject
    public ProfileContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.injectDependacies();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_container,(Fragment)this.presenter.getView()).commit();

        Button btnLogout = (Button) this.findViewById(R.id.btn_logout);
        final DbOperations db = new DbOperations(this);
        final Context context = this;
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearUsers();
                Intent intent = new Intent(context,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void injectDependacies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
