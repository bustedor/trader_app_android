package com.example.traderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traderapp.login.LoginContract;
import com.example.traderapp.login.LoginPresenter;
import com.example.traderapp.R;
import com.example.traderapp.models.LoginResponse;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginViewContract {

    private LoginContract.PresenterContract presenter;
    private TextView idTextView;
    private TextView passTextView;

    ProgressDialog progress;

    public void hideHint(final TextView myEditText,final String hint){
        myEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    myEditText.setHint(getString(R.string.empty));
                else
                    myEditText.setHint(hint);
            }
        });
    }

    public void clicked(View view){
        //Hide keyboard
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);

        presenter.doLogin(idTextView.getText().toString(),passTextView.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        idTextView   =  findViewById(R.id.editText);
        passTextView =  findViewById(R.id.editText3);
        presenter = new LoginPresenter(LoginActivity.this);

        hideHint(idTextView,getString(R.string.kullanıcı_adı));
        hideHint(passTextView,getString(R.string.sifre));

        progress = new ProgressDialog(this);
        progress.setTitle(getString(R.string.giris));
        progress.setMessage(getString(R.string.bekleyiniz));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

    }

    @Override
    public void showLoader(){
        if (progress != null) {
            progress.show();
        }
    }

    @Override
    public void hideLoader(){
        if (progress != null)
            progress.dismiss();
    }

    @Override
    public void onSuccess(LoginResponse loginResponse){
        // redirect to PortfolioActivity
        Intent myIntent = new Intent(getBaseContext(),   PortfolioActivity.class);
        myIntent.putExtra("id",idTextView.getText().toString());
        myIntent.putExtra("pass",passTextView.getText().toString());
        myIntent.putExtra("DefaultAccount",loginResponse.getDefaultAccount());
        startActivity(myIntent);
    }

    @Override
    public void onErrorLogin(String msg){
        // display error message
        Toast toast = Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_LONG);
        toast.show();
        clear();
    }

    @Override
    public void clear() {
        idTextView.setText(getString(R.string.empty));
        passTextView.setText(getString(R.string.empty));
    }
}
