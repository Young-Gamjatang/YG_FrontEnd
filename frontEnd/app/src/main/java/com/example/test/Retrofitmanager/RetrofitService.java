package com.example.test.Retrofitmanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("URL/{latitude}")
    Call<QualityRestaurantModel> getLatitude(@Path("latitude") Double latitude);

    @GET("URL/{longitude}")
    Call<QualityRestaurantModel> getLongitude(@Path("longitude") Double longitude);

    @GET("URL/{upsoSno}")
    Call<QualityRestaurantModel> getUpsoSno(@Path("upsoSno") String upsoSno);

    @GET("URL/{cggCode}")
    Call<QualityRestaurantModel> getCggCode(@Path("cggCode") String cggCode);

    @GET("URL/{sntCobNm}")
    Call<QualityRestaurantModel> getSntCobNm(@Path("sntCobNm") String sntCobNm);

    @GET("URL/{upsoNm}")
    Call<QualityRestaurantModel> getUpsoNm(@Path("upsoNm") String upsoNm);

    @GET("URL/{sntUptaeNm}")
    Call<QualityRestaurantModel> getSntUptaeNm(@Path("sntUptaeNm") String sntUptaeNm);

    @GET("URL/{siteAddrRd}")
    Call<QualityRestaurantModel> getSiteAddrRd(@Path("siteAddrRd") String siteAddrRd);

    @GET("URL/{bdngJisgFlrNum}")
    Call<QualityRestaurantModel> getBdngJisgFlrNum(@Path("bdngJisgFlrNum") String bdngJisgFlrNum);

    @GET("URL/{bdngUnderFlrNum}")
    Call<QualityRestaurantModel> getBdngUnderFlrNum(@Path("bdngUnderFlrNum") String bdngUnderFlrNum);

    @GET("URL/{geEhYn}")
    Call<QualityRestaurantModel> getGeEhYn(@Path("geEhYn") String geEhYn);

}
