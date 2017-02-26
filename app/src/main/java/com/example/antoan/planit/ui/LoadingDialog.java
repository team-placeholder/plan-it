package com.example.antoan.planit.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

import com.example.antoan.planit.R;

import javax.inject.Inject;

/**
 * Created by Antoan on 2/19/2017.
 */

public class LoadingDialog {

    private final String loadingMessage;
    private  ProgressDialog loadingDialog;
    private final Context context;
    @Inject
    public LoadingDialog(Context context, String loadingMessage){
        this.loadingMessage = loadingMessage;
        this.context = context;
    }

    public void show(){
        if(this.loadingDialog == null){

            this.loadingDialog = new ProgressDialog(this.context,R.style.AppTheme_Dark_Dialog);
            this.loadingDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
            this.loadingDialog.setIndeterminate(true);
            this.loadingDialog.setMessage(this.loadingMessage);
        }

        this.loadingDialog.show();
    }

    public void hide(){
        if(loadingDialog !=null){
            this.loadingDialog.hide();
        }

    }

    public void dismiss(){ this.loadingDialog.dismiss(); }
}
