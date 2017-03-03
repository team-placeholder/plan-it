package com.example.antoan.planit.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.astuetz.PagerSlidingTabStrip;
import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.data.SqlData.DbOperations;
import com.example.antoan.planit.adapters.TabsNavigationAdapter;
import com.example.antoan.planit.view.home.listEventsAsCreator.EventsAsCreatorContracts;
import com.example.antoan.planit.view.home.listEventsAsParticipant.EventsAsParticipantContract;
import com.example.antoan.planit.view.login.LoginActivity;
import com.example.antoan.planit.view.profile.ProfileContract;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    @Inject
    public ProfileContract.Presenter presenter;

    @Inject
    public EventsAsParticipantContract.Presenter eventsAsParticipantPresenter;

    @Inject
    EventsAsCreatorContracts.Presenter eventsAsCreatorPresenter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.injectDependacies();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_container,(Fragment)this.presenter.getView()).commit();

        ArrayList<String> tabsNames = new ArrayList<>();
        tabsNames.add("Your events");
        tabsNames.add("Joined events");


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) eventsAsCreatorPresenter.getView());
        fragments.add((Fragment) eventsAsParticipantPresenter.getView());


        this.pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new TabsNavigationAdapter(getSupportFragmentManager(),tabsNames,fragments));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }

    private void injectDependacies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
