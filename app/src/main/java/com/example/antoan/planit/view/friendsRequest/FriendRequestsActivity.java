package com.example.antoan.planit.view.friendsRequest;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.view.profile.ProfileContract;

import javax.inject.Inject;

public class FriendRequestsActivity extends AppCompatActivity {

    @Inject
    public FriendRequestsContract.Presenter presenter;

    @Inject
    public ProfileContract.Presenter profilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_request);

        this.injectDependancies();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_container,(Fragment)this.profilePresenter.getView())
                .replace(R.id.content_container,(Fragment)this.presenter.getView()).commit();

      /*  this.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_container,(Fragment)this.profilePresenter.getView()).commit();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.content_container,(Fragment)this.presenter.getView()).commit();*/
    }

    private void injectDependancies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }
}
