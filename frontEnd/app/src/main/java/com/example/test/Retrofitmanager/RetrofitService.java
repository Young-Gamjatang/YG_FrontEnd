package com.example.test.Retrofitmanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {


    @GET("resturant")//그룹 리스트 조회
    Call<ReslistModel>reslist(@Body ResModel resModel);



    @GET("push/{tokens}")
    Call<String> insertToken(@Path("tokens") String tokens);

}
