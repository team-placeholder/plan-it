package com.example.antoan.planit.view.createEvent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.data.models.SimpleDate;
import com.example.antoan.planit.R;
import com.example.antoan.planit.ui.MaterialTimePicker;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;


public class CreateEventView extends Fragment implements CreateEventContracts.View, View.OnClickListener {

    private CreateEventContracts.Presenter presenter;
    private TextView tvDate;
    private EditText etTitle;
    private EditText etDescription;
    private EditText etStart;
    private ImageButton btnSetStart;
    private ImageButton btnSetEnd;
    private Button btnCreateEvent;
    private MaterialTimePicker materialTimePicker;
    private EditText etEnd;
    private Context ctx;
    private String dateMsg;

    public CreateEventView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create_event_view, container, false);
        this.ctx = root.getContext();
        this.tvDate = (TextView) root.findViewById(R.id.tv_date);
        this.etTitle = (EditText) root.findViewById(R.id.et_title);
        this.etDescription = (EditText) root.findViewById(R.id.et_description);
        this.etStart = (EditText) root.findViewById(R.id.et_start);
        this.etEnd = (EditText) root.findViewById(R.id.et_end);
        this.btnSetStart = (ImageButton) root.findViewById(R.id.btn_set_start);
        this.btnSetEnd = (ImageButton) root.findViewById(R.id.btn_set_end);
        this.btnCreateEvent = (Button) root.findViewById(R.id.btn_create_event);
        this.presenter.start();

        this.btnCreateEvent.setOnClickListener(this);
        this.btnSetStart.setOnClickListener(this);
        this.btnSetEnd.setOnClickListener(this);
        return root;
    }

    @Override
    public void setPresenter(CreateEventContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDate(SimpleDate date) {
        String message = "Your creating event for "+date.getYear()+"/"+date.getMonth()+"/"+date.getDay();
        this.tvDate.setText(message);

    }


    public void setMaterialTimePicker(MaterialTimePicker materialTimePicker) {
        this.materialTimePicker = materialTimePicker;
    }

    @Override
    public void notify(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_SHORT);
        Toast.makeText(ctx,message,Toast.LENGTH_SHORT);
    }

    @Override
    public void navigateToCalendar() {
        ((Activity)ctx).finish();
    }

    @Override
    public void setErrorEtTitle(String msg) {
        this.etTitle.setError(msg);
    }

    @Override
    public void setErrorEtDescription(String msg) {
        this.etDescription.setError(msg);
    }

    @Override
    public void setErrorEtStart(String msg) {
        this.etStart.setError(msg);
    }

    @Override
    public void setErrorEtEnd(String msg) {
        this.etEnd.setError(msg);
    }

    @Override
    public void notifyText(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_set_end:
                this.openTimePicker(this.etEnd);
                break;
            case R.id.btn_set_start:
                this.openTimePicker(this.etStart);
                break;
            case R.id.btn_create_event:
                String title = this.etTitle.getText().toString();
                String description  = this.etDescription.getText().toString();
                String start = this.etStart.getText().toString();
                String end = this.etEnd.getText().toString();
                if(this.presenter.validateInput(title,description,start,end)){
                    this.presenter.createEvent(title,description,start,end);
                }

                break;
        }
    }

    private void openTimePicker(final EditText editText) {
        this.materialTimePicker.show((Activity) ctx, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                String msg = ""+hourOfDay+":"+minute;
                editText.setText(msg);
            }
        });

    }





}
