package com.example.antoan.planit.view.editProfile.changePassword;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antoan.planit.R;
import com.example.antoan.planit.view.calendar.CalendarActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordView extends Fragment implements ChangePasswordContract.View, View.OnClickListener {


    private ChangePasswordContract.Presenter presenter;
    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etReNewPassword;
    private Button btnUpdatePass;

    public ChangePasswordView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_change_password_view, container, false);

        this.etOldPassword = (EditText) root.findViewById(R.id.et_old_pass);
        this.etNewPassword = (EditText) root.findViewById(R.id.et_new_pass);
        this.etReNewPassword = (EditText) root.findViewById(R.id.et_re_new_pass);
        this.btnUpdatePass = (Button)root.findViewById(R.id.btn_change_pass);

        this.btnUpdatePass.setOnClickListener(this);

        return root;
    }

    @Override
    public void setPresenter(ChangePasswordContract.Presenter presenter) {
         this.presenter = presenter;
    }

    @Override
    public void setErrorOnEtReNewPassword(String msg) {
        this.etReNewPassword.setError(msg);
    }

    @Override
    public void setErrorOnEtNewPassword(String msg) {
        this.etNewPassword.setError(msg);
    }

    @Override
    public void setErrorOnEtOldPassword(String msg) {
        this.etOldPassword.setError(msg);
    }


    @Override
    public void notifyOnServerResponse(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigate() {
        Intent intent = new Intent(this.getContext(), CalendarActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_change_pass:
                this.onPressedBtnChangePassword();
        }
    }

    private void onPressedBtnChangePassword() {

        String oldPass = etOldPassword.getText().toString();
        String newPass = etNewPassword.getText().toString();
        String newRePass = etReNewPassword.getText().toString();
        Boolean isValid = this.presenter.validateInput(oldPass,newPass,newRePass);
        if(isValid){
            this.presenter.updatePassword(oldPass,newPass);
        }
    }

}
