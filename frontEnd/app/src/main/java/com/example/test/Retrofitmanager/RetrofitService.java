package com.example.test.Retrofitmanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  RetrofitService {
    @GET("URL")
    Call<List<QualityRestaurantModel>> getQualityRestaurantModel();

    @GET("URL")
    Call<List<BadHygieneRestaurantModel>> getBadHygieneRestaurantModel();
}
