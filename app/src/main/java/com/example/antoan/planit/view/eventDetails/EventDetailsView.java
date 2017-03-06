package com.example.antoan.planit.view.eventDetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.models.PlanedEvent;
import com.example.antoan.planit.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventDetailsView extends Fragment implements EventDetailsContracts.View, View.OnClickListener {


    private EventDetailsContracts.Presenter presenter;
    private TextView tvDate;
    private TextView tvTitle;
    private TextView tvEndTime;
    private TextView tvStartTime;
    private TextView tvDescription;
    private Button btnAlarm;
    private Context ctx;
    private Button btnJoinEvent;
    private ListView lvParticipants;
    private ArrayAdapter<String> adapter;
    private TextView tvCreator;

    public EventDetailsView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_event_details_view, container, false);
        this.ctx = root.getContext();
        this.tvDate = (TextView) root.findViewById(R.id.tv_date);
        this.tvTitle = (TextView) root.findViewById(R.id.tv_title);
        this.tvCreator = (TextView) root.findViewById(R.id.tv_creator);
        this.tvDescription = (TextView) root.findViewById(R.id.tv_description);
        this.tvStartTime = (TextView) root.findViewById(R.id.tv_start_time);
        this.tvEndTime = (TextView) root.findViewById(R.id.tv_end_time);
        this.btnAlarm = (Button) root.findViewById(R.id.btn_alarm);
        this.btnJoinEvent = (Button)root.findViewById(R.id.btn_join_event);
        this.lvParticipants = (ListView) root.findViewById(R.id.lv_participants);
        this.adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1,new ArrayList<String>());
        lvParticipants.setAdapter(this.adapter);

        this.btnAlarm.setOnClickListener(this);
        this.btnJoinEvent.setOnClickListener(this);
        this.presenter.start();

        return root;
    }

    @Override
    public void setPresenter(EventDetailsContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void notify(String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setEvent(PlanedEvent event) {
        this.tvDate.setText("Date: "+event.getDate().toString());
        this.tvTitle.setText(event.getTitle());
        this.tvDescription.setText(event.getDescription());
        this.tvStartTime.setText("Time: "+event.getStart());
        this.tvEndTime.setText(event.getEnd());
        this.tvCreator.setText("By "+event.getCreator());
        List<String> participants = Arrays.asList(event.getParticipants());
        this.adapter.addAll(participants);
    }

    @Override
    public void showJoinButton() {
        this.btnJoinEvent.setVisibility(View.VISIBLE);
        this.btnAlarm.setVisibility(View.GONE);
    }

    @Override
    public void hideJoinButton() {
        this.btnJoinEvent.setVisibility(View.GONE);
        this.btnAlarm.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_alarm:
                this.presenter.setAlarm(this.ctx);
                break;
            case R.id.btn_join_event:
                this.presenter.joinEvent();
                break;
        }

    }
}
