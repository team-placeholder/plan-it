package com.example.antoan.planit.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antoan.planit.R;
import com.example.antoan.planit.models.User;
import com.example.antoan.planit.utils.ImageHelper;

import java.util.List;

/**
 * Created by Antoan on 2/25/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private final Context context;
    private final int layoutId;
    private final List<User> data;

    public UserAdapter(Context context, int layoutResourceId, List<User> data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View row = inflater.inflate(layoutId, null);

        ImageView imageView = (ImageView)row.findViewById(R.id.iv_avatar);
        TextView tvEmail = (TextView) row.findViewById(R.id.tv_emai);
        TextView tvUsername = (TextView) row.findViewById(R.id.tv_username);

        ImageHelper imageHelper = new ImageHelper();
        Bitmap img = imageHelper.FromStringToBitmap(data.get(position).getAvatar());
        imageView.setImageBitmap(img);

        tvEmail.setText(data.get(position).getEmail());
        tvUsername.setText(data.get(position).getUsername());
        return row;
    }
}
