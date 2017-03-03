package com.example.antoan.planit.view.createEvent;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.data.EventsData;
import com.data.models.SimpleDate;
import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.view.profile.ProfileContract;

import javax.inject.Inject;

public class CreateEventActivity extends AppCompatActivity {

    @Inject
    public ProfileContract.Presenter profilePresenter;

    @Inject
    public CreateEventContracts.Presenter createEventPresenter;

    @Inject
    public EventsData eventsData;

    public static final String EVENT_DATE_KEY = "event-date";

    private SimpleDate date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        this.date =(SimpleDate) this.getIntent().getSerializableExtra(EVENT_DATE_KEY);
        this.injectDependencies();
        this.createEventPresenter.setDate(date);

        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.drawer_container,(Fragment)this.profilePresenter.getView())
                .replace(R.id.create_event_content_container, (Fragment) this.createEventPresenter.getView())
                .commit();
    }

    private void injectDependencies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }
}
