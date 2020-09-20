package com.example.traderapp.portfolio;

import com.example.traderapp.models.Item;

import java.util.List;

public class PortfolioContract {

    public interface PortfolioView{
        // methods here
        void showLoader();
        void hideLoader();
        void onSuccess(List<Item> itemList, Double total);
        void onError(String msg);
    }

    public interface PortfolioPresenter{
        void getData(String id, String pass, String defaultAcc);
    }
}
