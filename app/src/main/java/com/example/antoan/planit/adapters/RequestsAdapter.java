package com.example.antoan.planit.adapters;

import android.app.Activity;
import android.content.Context;
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
import com.example.antoan.planit.view.friendsRequest.FriendRequestsContract;

import java.util.List;

/**
 * Created by Antoan on 2/27/2017.
 */

public class RequestsAdapter extends ArrayAdapter<User> {
    private final Context context;
    private final int layoutId;
    private final List<User> data;
    private FriendRequestsContract.ISelectableButton selectable;

    public RequestsAdapter(Context context, int layoutResourceId, List<User> data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutId = layoutResourceId;
        this.data = data;
    }
    public void setSelectableButton(FriendRequestsContract.ISelectableButton selectable){
        this.selectable = selectable;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View row = inflater.inflate(layoutId, null);

        ImageView imageView = (ImageView)row.findViewById(R.id.iv_avatar);
        TextView tvUsername = (TextView) row.findViewById(R.id.tv_username);
        Button btnAddFriend = (Button) row.findViewById(R.id.btn_add_friend);
        btnAddFriend.setClickable(true);

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectable.OnSelectButton(position);
            }
        });

        return row;
    }
}
