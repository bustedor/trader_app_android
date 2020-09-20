package com.example.traderapp.services;

import com.example.traderapp.models.LoginResponse;
import com.example.traderapp.models.PortfolioResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockDataService {
    @GET("9999/Integration.aspx?")
        //?MsgType=AN&CustomerNo=0&Username=proje&Password=proje&AccountID=804587&Ex-changeID=4&OutputType=2
    Call<LoginResponse> getLoginResponse(@Query("MsgType") String msgType,
                                         @Query("CustomerNo") Integer customerNo,
                                         @Query("Username") String username,
                                         @Query("Password") String password ,
                                         @Query("AccountID") String accountId,
                                         @Query("ExchangeID") Integer exchangeId,
                                         @Query("OutputType") Integer outputType);
    @GET("9999/Integration.aspx?")
    Call<PortfolioResponse> getPortfolioResponse(@Query("MsgType") String msgType,
                                                 @Query("CustomerNo") Integer customerNo,
                                                 @Query("Username") String username,
                                                 @Query("Password") String password ,
                                                 @Query("AccountID") String accountId,
                                                 @Query("ExchangeID") Integer exchangeId,
                                                 @Query("OutputType") Integer outputType);

}
