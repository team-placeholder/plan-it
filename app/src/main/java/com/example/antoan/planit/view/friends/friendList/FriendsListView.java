package com.example.antoan.planit.view.friends.friendList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.data.models.User;
import com.example.antoan.planit.R;
import com.example.antoan.planit.adapters.UserAdapter;
import com.example.antoan.planit.view.friends.friendList.FriendsListContract;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsListView extends Fragment implements FriendsListContract.View, AdapterView.OnItemClickListener {


    private FriendsListContract.Presenter presenter;
    private ListView lvUsers;
    private UserAdapter adapter;

    public FriendsListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_friends_view, container, false);

        this.lvUsers = (ListView) root.findViewById(R.id.lv_users);
        this.adapter = new UserAdapter(this.getContext(),R.layout.user_row,new ArrayList<User>());
        this.lvUsers.setOnItemClickListener(this);
        lvUsers.setAdapter(this.adapter);
        this.presenter.start();
        return root;
    }

    @Override
    public void setPresenter(FriendsListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setFriends(List<User> userArrayList) {
        this.adapter.clear();
        this.adapter.addAll(userArrayList);
    }

    @Override
    public void notifyText(String email) {
        Toast.makeText(this.getContext(),email,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.presenter.navigateToFriendProfile(position);
    }
}
