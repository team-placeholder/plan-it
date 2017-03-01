package com.example.antoan.planit.view.friendProfile;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.view.friends.FriendsActivity;
import com.example.antoan.planit.view.friends.friendList.FriendsListView;

import javax.inject.Inject;

public class FriendProfileActivity extends AppCompatActivity {

    @Inject
    public FriendProfileContract.Presenter presenter;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        this.injectDependancies();
        this.email = this.getIntent().getStringExtra(FriendsActivity.PROFILE_KEY);
        this.presenter.setEmail(this.email);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_container,(Fragment)presenter.getView()).commit();
    }

    private void injectDependancies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }
}
