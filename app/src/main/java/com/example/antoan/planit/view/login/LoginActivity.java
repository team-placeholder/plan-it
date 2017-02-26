package com.example.antoan.planit.view.login;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    public LoginContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.injectDependancies();


        getSupportFragmentManager().beginTransaction().replace(R.id.content_container,(Fragment)presenter.getView()).commit();
    }

    private void injectDependancies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the StartActivity
        moveTaskToBack(true);
    }
}
