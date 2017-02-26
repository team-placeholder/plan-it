package com.example.antoan.planit.view.signup;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;

import javax.inject.Inject;

public class SignupActivity extends AppCompatActivity {

    @Inject
    public SignupContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.injectDependacies();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.content_container,(Fragment)this.presenter.getView()).commit();
    }

    private void injectDependacies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }

}
