package com.example.antoan.planit.view.friends.friendList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antoan.planit.R;
import com.example.antoan.planit.view.friends.friendList.FriendsListContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsListView extends Fragment implements FriendsListContract.View {


    private FriendsListContract.Presenter presenter;

    public FriendsListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_friends_view, container, false);

        return root;
    }

    @Override
    public void setPresenter(FriendsListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}