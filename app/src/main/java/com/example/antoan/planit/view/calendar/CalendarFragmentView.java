package com.example.antoan.planit.view.calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.models.PlannedEvent;
import com.example.antoan.planit.R;

import java.util.Calendar;
import java.util.Date;

public class CalendarFragmentView extends Fragment implements CalendarContracts.View, CalendarView.OnDateChangeListener {

    private CalendarContracts.Presenter presenter;
    private CalendarView calendarView;
    private Context ctx;
    private TextView tvMessage;
    private ArrayAdapter<PlannedEvent> eventsAdapter;
    private ListView lvEvents;

    public CalendarFragmentView() {
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
        this.ctx = root.getContext();
        this.calendarView = (CalendarView) root.findViewById(R.id.cv_events);
        this.tvMessage = (TextView) root.findViewById(R.id.tv_message);
        this.lvEvents = (ListView) root.findViewById(R.id.lv_events);

        this.calendarView.setFirstDayOfWeek(2);
        this.calendarView.setOnDateChangeListener(this);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(this.calendarView.getDate());
        this.tvMessage.setText(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH));
        this.presenter.getEventsForDay(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));


        return root;
    }

    @Override
    public void setPresenter(CalendarContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void notify(String message) {
        this.tvMessage.setText(message);
    }

    @Override
    public void setEvents(PlannedEvent[] events) {
        this.eventsAdapter =
                new ArrayAdapter<PlannedEvent>(ctx, -1, events) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = convertView;
                        if (view == null) {
                            LayoutInflater inflater = LayoutInflater.from(this.getContext());
                            view = inflater.inflate(R.layout.item_event, parent, false);
                        }

                        TextView tvStartTime= (TextView) view.findViewById(R.id.tv_event_start_time);
                        TextView tvTitle = (TextView) view.findViewById(R.id.tv_event_title);
                        PlannedEvent event = this.getItem(position);
                        String title = event.getTitle();
                        String startTime = event.getStart();

                        tvStartTime.setText(startTime);
                        tvTitle.setText(title);

                        return view;
                    }
                };

        this.lvEvents.setAdapter(this.eventsAdapter);
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        //this.tvMessage.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
        this.presenter.getEventsForDay(year, month + 1, dayOfMonth);
    }
}
