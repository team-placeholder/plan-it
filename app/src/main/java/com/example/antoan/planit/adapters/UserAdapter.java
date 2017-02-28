package com.example.antoan.planit.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antoan.planit.R;
import com.data.models.User;
import com.example.antoan.planit.utils.ImageHelper;
import com.example.antoan.planit.view.friends.findFriends.FindFriendsContracts;

import java.util.List;

/**
 * Created by Antoan on 2/25/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private final Context context;
    private final int layoutId;
    private final List<User> data;
    private FindFriendsContracts.ISelectableButton selectable;

    public UserAdapter(Context context, int layoutResourceId, List<User> data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutId = layoutResourceId;
        this.data = data;
    }

    public void setSelectableButton(FindFriendsContracts.ISelectableButton selectable){
        this.selectable = selectable;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View row = inflater.inflate(layoutId, null);

        ImageView imageView = (ImageView)row.findViewById(R.id.iv_avatar);
        TextView tvUsername = (TextView) row.findViewById(R.id.tv_username);
        ImageView ivAlreadyFriend = (ImageView)row.findViewById(R.id.iv_alreadyFriend);
        Button btnAddFriend = (Button) row.findViewById(R.id.btn_add_friend);
        btnAddFriend.setClickable(true);

        if(data.get(position).isFriend()){
            btnAddFriend.setVisibility(View.GONE);
        }else{

            ivAlreadyFriend.setVisibility(View.GONE);
            btnAddFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId() == R.id.btn_add_friend){
                       selectable.OnSelectButton(position);
                    }
                }
            });
        }


        ImageHelper imageHelper = new ImageHelper();
        Bitmap img = imageHelper.FromStringToBitmap(data.get(position).getAvatar());
        imageView.setImageBitmap(img);

        tvUsername.setText(data.get(position).getUsername());
        return row;
    }

}
