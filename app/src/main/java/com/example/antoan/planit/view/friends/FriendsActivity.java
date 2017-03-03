package com.example.antoan.planit.view.friends;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.adapters.TabsNavigationAdapter;
import com.example.antoan.planit.view.friends.findFriends.FindFriendsContracts;
import com.example.antoan.planit.view.friends.friendList.FriendsListContract;
import com.example.antoan.planit.view.profile.ProfileContract;

import java.util.ArrayList;

import javax.inject.Inject;

public class FriendsActivity extends AppCompatActivity {

    @Inject
    ProfileContract.Presenter profilePresenter;

    @Inject
    FriendsListContract.Presenter friendListPresenter;

    @Inject
    FindFriendsContracts.Presenter findFriendsPresenter;
    private ViewPager pager;

    public static final String PROFILE_KEY = "PROFILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        this.injectDependancies();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_container,(Fragment)this.profilePresenter.getView())
                .commit();

        ArrayList<String> tabsNames = new ArrayList<>();
        tabsNames.add("Friends List");
        tabsNames.add("Find Friends");


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) friendListPresenter.getView());
        fragments.add((Fragment) findFriendsPresenter.getView());


        this.pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new TabsNavigationAdapter(getSupportFragmentManager(),tabsNames,fragments));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    private void injectDependancies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }
}
