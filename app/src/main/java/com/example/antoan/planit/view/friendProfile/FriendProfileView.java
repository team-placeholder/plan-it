package com.example.antoan.planit.view.friendProfile;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.data.models.PlanedEvent;
import com.example.antoan.planit.R;
import com.example.antoan.planit.adapters.AdaptersFactory;
import com.example.antoan.planit.adapters.EventsAdapter;
import com.example.antoan.planit.view.eventDetails.EventDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendProfileView extends Fragment implements FriendProfileContract.View, View.OnClickListener, AdapterView.OnItemClickListener {


    private FriendProfileContract.Presenter presenter;
    private TextView tvUsername;
    private TextView tvEmail;
    private ImageView profileImg;
    private Button btnShow;
    private EventsAdapter adapter;
    private ListView lvEvents;
    private TextView tvNoEvents;
    private AdaptersFactory adapterFactory;

    public FriendProfileView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_friend_profile_view, container, false);
        this.tvUsername = (TextView) root.findViewById(R.id.tv_username);
        this.tvEmail = (TextView) root.findViewById(R.id.tv_email);
        this.profileImg = (ImageView)root.findViewById(R.id.im_avatar);
        this.btnShow = (Button) root.findViewById(R.id.btn_show);
        this.lvEvents = (ListView) root.findViewById(R.id.lv_user_events);
        this.tvNoEvents = (TextView)root.findViewById(R.id.tv_no_events);
        this.adapter = this.adapterFactory.CreateEventsAdapter(this.getContext(),R.layout.item_event,new ArrayList<PlanedEvent>());
        this.lvEvents.setAdapter(adapter);
        this.presenter.start();

        this.lvEvents.setOnItemClickListener(this);
        this.btnShow.setOnClickListener(this);


        return root;
    }

    @Override
    public void setPresenter(FriendProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProfileData(String email, String username, Bitmap avatar) {
        this.tvUsername.setText(username);
        this.tvEmail.setText(email);
        this.profileImg.setImageBitmap(avatar);
    }

    @Override
    public void setUsersEvents(List<PlanedEvent> events) {
          this.adapter.clear();
          this.adapter.addAll(events);
    }

    @Override
    public void hideShowButton() {
        this.btnShow.setVisibility(View.GONE);
    }

    @Override
    public void displayNoEvents() {
        this.tvNoEvents.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigate(String eventId) {
        Intent intent = new Intent(this.getContext(),EventDetailsActivity.class);
        intent.putExtra(EventDetailsActivity.EVENT_KEY,eventId);
        startActivity(intent);
    }

    @Override
    public void setAdapterFactory(AdaptersFactory adapterFactory) {
        this.adapterFactory = adapterFactory;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:
                this.presenter.loadEvents();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.presenter.navigate(position);
    }
}
