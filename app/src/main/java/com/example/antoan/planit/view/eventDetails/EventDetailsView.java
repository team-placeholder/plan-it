package com.example.antoan.planit.view.eventDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.data.models.PlanedEvent;
import com.example.antoan.planit.R;

public class EventDetailsView extends Fragment implements EventDetailsContracts.View {


    private EventDetailsContracts.Presenter presenter;
    private TextView tvDate;
    private TextView tvTitle;
    private TextView tvEndTime;
    private TextView tvStartTime;
    private TextView tvDescription;

    public EventDetailsView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_event_details_view, container, false);
        this.tvDate = (TextView) root.findViewById(R.id.tv_date);
        this.tvTitle = (TextView) root.findViewById(R.id.tv_title);
        this.tvDescription = (TextView) root.findViewById(R.id.tv_description);
        this.tvStartTime = (TextView) root.findViewById(R.id.tv_start_time);
        this.tvEndTime = (TextView) root.findViewById(R.id.tv_end_time);

        this.presenter.start();

        return root;
    }

    @Override
    public void setPresenter(EventDetailsContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void notify(String message) {
        this.tvDate.setText(message);
    }

    @Override
    public void setEvent(PlanedEvent event) {
        this.tvDate.setText(event.getDate().toString());
        this.tvTitle.setText(event.getTitle());
        this.tvDescription.setText(event.getDescription());
        this.tvStartTime.setText(event.getStart());
        this.tvEndTime.setText(event.getEnd());
    }
}
