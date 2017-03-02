package com.example.antoan.planit.view.createEvent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;

public class CreateEventView extends Fragment implements CreateEventContracts.View {

    private CreateEventContracts.Presenter presenter;

    public CreateEventView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_event_view, container, false);
    }

    @Override
    public void setPresenter(CreateEventContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}
