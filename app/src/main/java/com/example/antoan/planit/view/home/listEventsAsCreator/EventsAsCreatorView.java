package com.example.antoan.planit.view.home.listEventsAsCreator;


import android.icu.util.Calendar;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsAsCreatorView extends Fragment implements EventsAsCreatorContracts.View {


    private EventsAsCreatorContracts.Presenter presenter;


    public EventsAsCreatorView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_events_as_creator_view, container, false);

        return root;
    }

    @Override
    public void setPresenter(EventsAsCreatorContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}
