package com.example.test.Retrofitmanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  RetrofitService {
    @GET("cggcode")
    Call<List<QualityRestaurantModel>> getCggcode(@Query("guName") String guName);

    @GET("wrong/cggcode")
    Call<List<QualityRestaurantModel>> getwrongcggcode(@Query("guName") String guName);

    @GET("URL")
    Call<List<BadHygieneRestaurantModel>> getBadHygieneRestaurantModel();
}
