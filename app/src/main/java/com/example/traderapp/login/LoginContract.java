package com.example.traderapp.login;

import com.example.traderapp.models.LoginResponse;

public class LoginContract {
    // view interface
    public interface LoginViewContract {
        void showLoader();
        void hideLoader();
        void clear();
        void onSuccess(LoginResponse loginResponse);
        void onErrorLogin(String msg);

    }

    //presenter interface
    public interface PresenterContract{
        void doLogin(String id, String pass);
    }
}

