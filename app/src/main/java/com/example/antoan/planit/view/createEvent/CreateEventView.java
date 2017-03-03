package com.example.antoan.planit.view.createEvent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.antoan.planit.R;

public class CreateEventView extends Fragment implements CreateEventContracts.View {

    private CreateEventContracts.Presenter presenter;
    private TextView tvDate;

    public CreateEventView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create_event_view, container, false);
        this.tvDate = (TextView) root.findViewById(R.id.tv_date);
        this.presenter.start();
        return root;
    }

    @Override
    public void setPresenter(CreateEventContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDate(int year, int month, int day) {
        int realMonth = month+1;
        String date = "Your creating event for "+year+"/"+realMonth+"/"+day;
        this.tvDate.setText(date);

    }
}
