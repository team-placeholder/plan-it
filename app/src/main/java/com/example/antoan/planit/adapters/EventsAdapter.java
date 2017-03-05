package com.example.antoan.planit.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.data.models.PlanedEvent;
import com.example.antoan.planit.R;


import java.util.List;


/**
 * Created by Antoan on 3/5/2017.
 */

public class EventsAdapter extends ArrayAdapter<PlanedEvent> {

    private final Context context;
    private final int layoutId;
    private final List<PlanedEvent> data;

    public EventsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<PlanedEvent> objects) {
        super(context, resource, objects);
        this.context= context;
        this.layoutId = resource;
        this.data = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(layoutId, parent, false);
        }
        TextView tvStartTime= (TextView) view.findViewById(R.id.tv_event_start_time);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_event_title);
        TextView tvEndTime = (TextView) view.findViewById(R.id.tv_event_end_time);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_event_date);
        PlanedEvent event = data.get(position);
        String title = event.getTitle();
        String startTime = event.getStart();
        String endTime = event.getEnd();
        String date = ""+event.getDate().getYear()+"/"+event.getDate().getMonth()+"/"+event.getDate().getDay();

        tvStartTime.setText(startTime);
        tvTitle.setText(title);
        tvEndTime.setText(endTime);
        tvDate.setText(date);

        return view;
    }
}
