package com.example.antoan.planit.view.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;

public class CalendarView extends Fragment implements CalendarContracts.View{

    private CalendarContracts.Presenter presenter;

    public CalendarView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calendar_view, container, false);


        return root;
    }

    @Override
    public void setPresenter(CalendarContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}
