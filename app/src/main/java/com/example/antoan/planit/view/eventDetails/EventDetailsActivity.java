package com.example.antoan.planit.view.eventDetails;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.data.EventsData;
import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.view.profile.ProfileContract;

import javax.inject.Inject;

public class EventDetailsActivity extends AppCompatActivity {

    @Inject
    public ProfileContract.Presenter profilePresenter;

    @Inject
    public  EventDetailsContracts.Presenter eventDetailsPresenter;

    @Inject
    public EventsData eventsData;

    public static final String EVENT_KEY = "event";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        this.injectDependencies();
        String id = (String) this.getIntent().getSerializableExtra(EVENT_KEY);
        this.eventDetailsPresenter.setEventId(id);

        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.drawer_container,(Fragment)this.profilePresenter.getView())
                .replace(R.id.event_details_content_container, (Fragment) this.eventDetailsPresenter.getView())
                .commit();
    }

    private void injectDependencies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }
}
