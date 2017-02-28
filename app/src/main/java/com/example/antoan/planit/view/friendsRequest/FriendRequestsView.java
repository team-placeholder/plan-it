package com.example.antoan.planit.view.friendsRequest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.antoan.planit.R;
import com.example.antoan.planit.adapters.RequestsAdapter;
import com.data.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendRequestsView extends Fragment implements FriendRequestsContract.View {


    private FriendRequestsContract.Presenter presenter;
    private RequestsAdapter adapter;


    public FriendRequestsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_friend_requests_view, container, false);
        ListView requests = (ListView) root.findViewById(R.id.lv_users);
        this.adapter = new RequestsAdapter(this.getContext(),R.layout.request_row,new ArrayList<User>());
        this.adapter.setSelectableButton(this);
        requests.setAdapter(this.adapter);

        this.presenter.start();
        return  root;
    }

    @Override
    public void setPresenter(FriendRequestsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setRequests(List<User> users) {
        this.adapter.clear();
        this.adapter.addAll(users);
    }

    @Override
    public void notifyText(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void acceptFriendRequest(Integer position) {
        this.presenter.acceptFriendRequest(position);
    }

    @Override
    public void declineFriendRequest(Integer position) {
        this.presenter.declineFriendRequest(position);
    }
}
