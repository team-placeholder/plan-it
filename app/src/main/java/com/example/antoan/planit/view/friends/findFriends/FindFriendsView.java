package com.example.antoan.planit.view.friends.findFriends;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.antoan.planit.R;
import com.example.antoan.planit.adapters.AdaptersFactory;
import com.example.antoan.planit.adapters.UserAdapter;
import com.data.models.User;
import com.example.antoan.planit.view.friendProfile.FriendProfileActivity;
import com.example.antoan.planit.view.friends.FriendsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFriendsView extends Fragment implements FindFriendsContracts.View, View.OnClickListener, AdapterView.OnItemClickListener {


    private FindFriendsContracts.Presenter presenter;
    private Button btnFind;
    private EditText etSearch;
    private ListView lvUsers;
    private UserAdapter adapter;
    private List<User> userList;
    private AdaptersFactory adapterFactory;

    public FindFriendsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_find_friends_view, container, false);
        this.btnFind = (Button)root.findViewById(R.id.btn_find_friends);
        this.etSearch = (EditText) root.findViewById(R.id.et_search);
        this.lvUsers = (ListView) root.findViewById(R.id.lv_users);

        this.adapter = this.adapterFactory.createUserAdapter(this.getContext(),R.layout.user_row,new ArrayList<User>());
        this.adapter.setSelectableButton(this);
        this.lvUsers.setAdapter(this.adapter);
        this.btnFind.setOnClickListener(this);
        this.lvUsers.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void setPresenter(FindFriendsContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void notifyText(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUsers(List<User> users) {
        this.userList = users;
        this.adapter.clear();
        this.adapter.addAll(userList);
    }

    @Override
    public void navigateToFriendProfile(String email) {
        Intent intent = new Intent(this.getContext(), FriendProfileActivity.class);
        intent.putExtra(FriendsActivity.PROFILE_KEY,email);
        startActivity(intent);
    }

    @Override
    public void setAdapterFactory(AdaptersFactory adapterFactory) {
        this.adapterFactory = adapterFactory;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_find_friends:
                String pattern = etSearch.getText().toString();
                this.presenter.findFriends(pattern);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       this.presenter.navigateToFriendProfile(position);

    }

    @Override
    public void OnSelectButton(Integer position) {
        this.presenter.requestForFriends(position);
    }
}
