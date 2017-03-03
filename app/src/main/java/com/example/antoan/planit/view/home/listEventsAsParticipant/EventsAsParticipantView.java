package com.example.antoan.planit.view.home.listEventsAsParticipant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsAsParticipantView extends Fragment implements EventsAsParticipantContract.View{


    private EventsAsParticipantContract.Presenter presenter;

    public EventsAsParticipantView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_events_as_participant_view, container, false);

        return root;
    }

    @Override
    public void setPresenter(EventsAsParticipantContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
