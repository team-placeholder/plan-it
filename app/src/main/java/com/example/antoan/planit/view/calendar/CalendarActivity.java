package com.example.antoan.planit.view.calendar;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.data.EventsData;
import com.data.models.SimpleDate;
import com.example.antoan.planit.PlanItApplication;
import com.example.antoan.planit.R;
import com.example.antoan.planit.utils.ICanNavigateActivity;
import com.example.antoan.planit.view.createEvent.CreateEventActivity;
import com.example.antoan.planit.view.eventDetails.EventDetailsActivity;
import com.example.antoan.planit.view.profile.ProfileContract;

import javax.inject.Inject;

public class CalendarActivity extends AppCompatActivity implements ICanNavigateActivity{

    @Inject
    public ProfileContract.Presenter profilePresenter;

    @Inject
    public CalendarContracts.Presenter calendarPresenter;

    @Inject
    public EventsData eventsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.injectDependencies();

        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.drawer_container,(Fragment)this.profilePresenter.getView())
                .replace(R.id.calendar_content_container, (Fragment) this.calendarPresenter.getView())
                .commit();

    }

    private void injectDependencies() {
        ((PlanItApplication)this.getApplication()).getComponent().inject(this);
    }


    @Override
    public void navigate(Object obj) {
        if (obj instanceof String){
            String eventId = (String) obj;
            Intent intent = new Intent(this, EventDetailsActivity.class);

            intent.putExtra(EventDetailsActivity.EVENT_KEY, eventId);
            this.startActivity(intent);
        }

        if (obj instanceof SimpleDate){
            SimpleDate date = (SimpleDate) obj;
            Intent intent = new Intent(this, CreateEventActivity.class);

            intent.putExtra(CreateEventActivity.EVENT_DATE_KEY, date);
            this.startActivity(intent);
        }
    }
}
