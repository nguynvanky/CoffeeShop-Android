package com.example.coffeeshop.Services;
import com.example.coffeeshop.Contains.IpconfigLocalhost;
import com.example.coffeeshop.DTO.Category;
import com.example.coffeeshop.DTO.LoginRequest;
import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.DTO.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setLenient().create();
    // https://cfshop.rf.gd/API.php
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://"+ IpconfigLocalhost.IPCONFIG +"/doanketthucmon/CoffeeShop/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("API.php")
    Call<List<Product>> GetProducts(@Query("type") String type);
    @GET("API.php")
    Call<List<Category>> GetCategories(@Query("type") String type);
    @GET("API.php")
    Call<User> Login(@Query("type") String type,
                     @Query("username") String username,
                     @Query("password") String password);

//    @POST("API.php")
//    Call<User> Login(@Body LoginRequest body);

}
