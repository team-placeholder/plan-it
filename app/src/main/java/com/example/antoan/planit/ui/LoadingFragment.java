package com.example.antoan.planit.ui;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.antoan.planit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment {

    private ProgressDialog loadingDialog;
    private Context context;
    private String message;

    public LoadingFragment() {
        // Required empty public constructor
    }

    public static LoadingFragment create(String message, Context context) {
        LoadingFragment fragment = new LoadingFragment();
        fragment.setContext(context);
        fragment.setMessage(message);
        return fragment;

    }

    @Override
    public void onDestroy() {
        if (this.loadingDialog != null) {
            this.loadingDialog.dismiss();
        }

        super.onDestroy();
    }

    public void show(){
        if(this.loadingDialog == null){
            this.loadingDialog = new ProgressDialog(this.context,R.style.AppTheme_Dark_Dialog);
            this.loadingDialog.setIndeterminate(true);
            this.loadingDialog.setMessage(this.message);
        }

        this.loadingDialog.show();
    }

    public void hide(){
        this.loadingDialog.hide();
    }


    private void setContext(Context context) {
        this.context = context;
    }

    private void setMessage(String msg) {
        this.message = msg;
    }

}

