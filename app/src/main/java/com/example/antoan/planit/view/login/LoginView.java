package com.example.antoan.planit.view.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antoan.planit.R;
import com.example.antoan.planit.data.SqlData.DbOperations;
import com.example.antoan.planit.ui.LoadingDialog;
import com.example.antoan.planit.ui.LoadingFragment;
import com.example.antoan.planit.utils.InputValidator;
import com.example.antoan.planit.view.home.HomeActivity;
import com.example.antoan.planit.view.signup.SignupActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginView extends Fragment implements LoginContract.View, View.OnClickListener {

    private AutoCompleteTextView tvEmail;
    private EditText etPassword;
    private Button loginButton;
    private TextView signupLink;

    private LoginContract.Presenter presenter;
    private LoadingDialog loadingDialog;

    public LoginView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_login_view, container, false);

        this.tvEmail = (AutoCompleteTextView) root.findViewById(R.id.input_email);
        this.etPassword = (EditText) root.findViewById(R.id.input_password);
        this.loginButton = (Button) root.findViewById(R.id.btn_login);
        this.signupLink = (TextView) root.findViewById(R.id.link_signup);

        this.loginButton.setOnClickListener(this);
        this.signupLink.setOnClickListener(this);

        this.presenter.start();

        return root;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
          this.presenter = presenter;
    }

    @Override
    public void setEmails(List<String> emailCollection) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, emailCollection);
        this.tvEmail.setAdapter(adapter);
    }

    @Override
    public void succesfulLogin() {
        loginButton.setEnabled(true);
        this.loadingDialog.dismiss();
        Intent intent = new Intent(this.getContext(), HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFailedLogin() {
        Toast.makeText(this.getContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
        this.loadingDialog.hide();
    }

    @Override
    public void setLoadingDialog(LoadingDialog loadingDialog) {
        this.loadingDialog = loadingDialog;
    }

    @Override
    public void setErrorTvEmail(String msg) {

        tvEmail.setError(msg);
    }

    @Override
    public void setErrorEtPassword(String msg) {

        etPassword.setError(msg);
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.btn_login:
                this.loginButton.setEnabled(false);
                if(this.presenter.validateInput(tvEmail.getText().toString(),etPassword.getText().toString())){
                    this.loadingDialog.show();
                    this.presenter.requestLogin(tvEmail.getText().toString(),etPassword.getText().toString());
                }
                else {
                    onFailedLogin();

                }
                break;
            case R.id.link_signup:
                Intent intent = new Intent(this.getContext(), SignupActivity.class);
                startActivityForResult(intent,0);
                break;

               //this.getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.loadingDialog.dismiss();
    }

}
