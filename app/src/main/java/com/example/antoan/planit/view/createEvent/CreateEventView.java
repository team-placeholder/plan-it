package com.example.antoan.planit.view.createEvent;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.antoan.planit.R;
import com.example.antoan.planit.ui.MatirialTimePicker;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;


public class CreateEventView extends Fragment implements CreateEventContracts.View, View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private CreateEventContracts.Presenter presenter;
    private TextView tvDate;
    private EditText etTitle;
    private EditText etDescription;
    private EditText etStart;
    private Button btnSetStart;
    private Button btnCreateEvent;
    private MatirialTimePicker matirialTimePicker;

    public CreateEventView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create_event_view, container, false);
        this.tvDate = (TextView) root.findViewById(R.id.tv_date);
        this.etTitle = (EditText) root.findViewById(R.id.et_title);
        this.etDescription = (EditText) root.findViewById(R.id.et_description);
        this.etStart = (EditText) root.findViewById(R.id.et_start);
        this.btnSetStart = (Button) root.findViewById(R.id.btn_set_start);
        this.btnCreateEvent = (Button) root.findViewById(R.id.btn_create_event);
        this.presenter.start();

        btnCreateEvent.setOnClickListener(this);
        this.btnSetStart.setOnClickListener(this);
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


    @Override
    public void setMatirialTimePicker(MatirialTimePicker matirialTimePicker) {
        this.matirialTimePicker = matirialTimePicker;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_set_start:
                this.openMatirialTimePicker();
                break;
        }
    }

    private void openMatirialTimePicker() {
        Activity act = (Activity)this.getContext();
        this.matirialTimePicker.show(act,this);

    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
            String msg = ""+hourOfDay+":"+minute;
            this.etStart.setText(msg);
    }

}
