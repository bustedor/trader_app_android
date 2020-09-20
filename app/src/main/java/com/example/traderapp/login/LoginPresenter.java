package com.example.traderapp.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.traderapp.R;
import com.example.traderapp.services.StockDataService;
import com.example.traderapp.models.LoginResponse;
import com.example.traderapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements LoginContract.PresenterContract {

    private LoginContract.LoginViewContract loginViewContract;
    String BaseUrl = "https://tbpilot.matriksdata.com/";

    public LoginPresenter (LoginContract.LoginViewContract loginViewContract)
    {
        this.loginViewContract = loginViewContract;
    }

    @Override
    public void doLogin(String id, String pass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StockDataService service = retrofit.create(StockDataService.class);

        loginViewContract.showLoader();
        Call<LoginResponse> call = service.getLoginResponse("A", 0, id, pass, "0", 4, 2);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.code() == 200) {
                    Log.i("Retrofit call","successfully done.");

                    LoginResponse loginResponse = response.body();
                    Result result = loginResponse.getResult();

                    if(result.getState()) {
                        loginViewContract.onSuccess(loginResponse);
                    }
                    else {
                        loginViewContract.onErrorLogin(result.getDescription());
                    }
                }
                loginViewContract.hideLoader();
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                loginViewContract.hideLoader();
                Log.i("Retrofit call","failed.");
                t.printStackTrace();
                loginViewContract.onErrorLogin("Veri alınamadı...");
            }
        });
    }

}
