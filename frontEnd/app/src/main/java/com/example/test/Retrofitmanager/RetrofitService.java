package com.example.test.Retrofitmanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  RetrofitService {

    @GET("query/cggcode")
    Call<List<QualityRestaurantModel>> getCggcode(@Query("guName") String guName);

    @GET("query/wrong/cggcode")
    Call<List<BadHygieneRestaurantModel>> getwrongcggcode(@Query("guName") String guName);


    @GET("search/wrong")
    Call<List<QualityRestaurantModel>> searchwrong(@Query("upsoName") String upsoName);


    @GET("search/restaurant")
    Call<List<QualityRestaurantModel>> searchres(@Query("upsoName") String upsoName);

    @GET("query/near")
    Call<List<QualityRestaurantModel>> nearres(@Query("guName")String guname,
                                               @Query("latitude")double lat,
                                               @Query("longitude")double lon);


    @GET("query/wrong/cggcode")
    Call<List<BadHygieneRestaurantModel>> getBadHygieneRestaurantModel(@Query("guName") String guName);
}
