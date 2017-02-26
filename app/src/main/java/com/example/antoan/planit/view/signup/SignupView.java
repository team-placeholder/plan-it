package com.example.antoan.planit.view.signup;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antoan.planit.R;
import com.example.antoan.planit.ui.LoadingDialog;
import com.example.antoan.planit.ui.LoadingFragment;
import com.example.antoan.planit.utils.InputValidator;
import com.example.antoan.planit.view.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupView extends Fragment implements SignupContract.View, View.OnClickListener {

    private EditText _nameText;
    private EditText _emailText;
    private EditText _passwordText;
    private EditText _reEnterPasswordText;
    private Button _signupButton;
    private TextView _loginLink;

    private SignupContract.Presenter presenter;
    private LoadingDialog loadingDialog;

    public SignupView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup_view, container, false);

        _nameText = (EditText) root.findViewById(R.id.input_name);
        _emailText = (EditText) root.findViewById(R.id.input_email);
        _passwordText = (EditText) root.findViewById(R.id.input_password);
        _reEnterPasswordText = (EditText) root.findViewById(R.id.input_reEnterPassword);
        _signupButton = (Button) root.findViewById(R.id.btn_signup);
        _loginLink = (TextView) root.findViewById(R.id.link_login);

        _signupButton.setOnClickListener(this);
        _loginLink.setOnClickListener(this);


        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(this.loadingDialog != null){
            this.loadingDialog.dismiss();
        }

    }

    @Override
    public void setPresenter(SignupContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onFailedSignup() {
        Toast.makeText(this.getContext(), "User with this email or username already exists", Toast.LENGTH_LONG).show();
        this.loadingDialog.hide();
        _signupButton.setEnabled(true);
    }

    @Override
    public void onSuccesSignup(String msg) {
        _signupButton.setEnabled(true);
        //setResult(RESULT_OK, null);
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this.getContext(),LoginActivity.class);
        startActivity(intent);
        this.loadingDialog.dismiss();
    }

    @Override
    public void setLoadingDialog(LoadingDialog loadingDialog) {
        this.loadingDialog = loadingDialog;
    }

    @Override
    public void setErrorEtName(String msg) {
        _nameText.setError(msg);
    }

    @Override
    public void setErrorEtEmail(String msg) {
        _emailText.setError(msg);
    }

    @Override
    public void setErrorEtPassword(String msg) {
        _passwordText.setError(msg);
    }

    @Override
    public void setErrorEtRePassword(String msg) {
        _reEnterPasswordText.setError(msg);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:
                this.loadingDialog.show();
                _signupButton.setEnabled(false);

                String name = _nameText.getText().toString();
                String email = _emailText.getText().toString();
                String password = _passwordText.getText().toString();
                String reEnterPassword = _reEnterPasswordText.getText().toString();

                if(this.presenter.validateInput(email,name,password,reEnterPassword)){
                    this.presenter.signup(email,name,password);
                }else{
                     this.onUnsuccesfullValidate();
                }
                break;
            case R.id.link_login:
                Intent intent = new Intent(this.getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void onUnsuccesfullValidate(){
        Toast.makeText(this.getContext(), "Sign up failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
        this.loadingDialog.hide();
    }
}
