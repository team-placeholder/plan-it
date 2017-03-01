package com.example.antoan.planit.view.friendProfile;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antoan.planit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendProfileView extends Fragment implements FriendProfileContract.View {


    private FriendProfileContract.Presenter presenter;
    private TextView tvUsername;
    private TextView tvEmail;
    private ImageView profileImg;

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
        this.presenter.start();

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
}
