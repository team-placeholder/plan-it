package com.example.antoan.planit.view.friendsRequest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendRequestsView extends Fragment implements FriendRequestsContract.View {


    private FriendRequestsContract.Presenter presenter;

    public FriendRequestsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_friend_requests_view, container, false);


        this.presenter.start();
        return  root;
    }

    @Override
    public void setPresenter(FriendRequestsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
