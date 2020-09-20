package com.example.traderapp.portfolio;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.traderapp.services.StockDataService;
import com.example.traderapp.models.Item;
import com.example.traderapp.models.PortfolioResponse;
import com.example.traderapp.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PortfolioPresenter implements PortfolioContract.PortfolioPresenter {
    private PortfolioContract.PortfolioView portfolioView;
    String BaseUrl = "https://tbpilot.matriksdata.com/";

    public PortfolioPresenter(PortfolioContract.PortfolioView portfolioView) {
        this.portfolioView = portfolioView;
    }

    @Override
    public void getData(String id, String pass, String defaultAcc) {
        portfolioView.showLoader();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StockDataService service = retrofit.create(StockDataService.class);
        Call<PortfolioResponse> call = service.getPortfolioResponse("AN", 0, id, pass, defaultAcc, 4, 2);
        call.enqueue(new Callback<PortfolioResponse>() {
            @Override
            public void onResponse(@NonNull Call<PortfolioResponse> call, @NonNull Response<PortfolioResponse> response) {
                if (response.code() == 200) {

                    Log.i("Retro call in portf","successfully done.");

                    PortfolioResponse portfolioResponse = response.body();
                    Result result = portfolioResponse.getResult();

                    if(result.getState()){
                        List<Item> itemList = portfolioResponse.getItemList();
                        Double total=0.0;
                        for(int i=0;i<itemList.size();i++) {
                            double temp = itemList.get(i).getLastPx() * itemList.get(i).getQtyT2();
                            total += temp;
                        }
                        portfolioView.onSuccess(itemList,total);
                    }
                    else {
                        portfolioView.onError(result.getDescription());
                    }
                    portfolioView.hideLoader();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PortfolioResponse> call, @NonNull Throwable t) {
                portfolioView.hideLoader();
                Log.i("Retro call in portf","failed.");
                t.printStackTrace();
            }
        });
    }
}
