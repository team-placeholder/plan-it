package com.example.antoan.planit.view.calendar;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.view.profile.ProfileContract;

import javax.inject.Inject;

public class CalendarActivity extends AppCompatActivity {

    @Inject
    public ProfileContract.Presenter profilePresenter;

    @Inject
    public CalendarContracts.Presenter calendarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.injectDependacies();

        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.drawer_container,(Fragment)this.profilePresenter.getView())
                .replace(R.id.calendar_content_container, (Fragment) this.calendarPresenter.getView())
                .commit();

    }

    private void injectDependacies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }
}
