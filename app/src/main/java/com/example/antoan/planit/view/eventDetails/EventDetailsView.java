package com.example.antoan.planit.view.eventDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;

public class EventDetailsView extends Fragment implements EventDetailsContracts.View {


    private EventDetailsContracts.Presenter presenter;

    public EventDetailsView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_event_details_view, container, false);


        return root;
    }

    @Override
    public void setPresenter(EventDetailsContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}
