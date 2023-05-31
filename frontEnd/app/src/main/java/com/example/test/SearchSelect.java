package com.example.test;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Retrofitmanager.QualityRestaurantModel;
import com.example.test.Retrofitmanager.RetrofitInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSelect extends AppCompatActivity {

    int nCurrentPermission = 0;
    static final int PERMISSION_REQUEST = 0x0000001;
    retrofit2.Call<List<QualityRestaurantModel>> call;
    List<QualityRestaurantModel> result =new ArrayList<>();
    double cur_lat , cur_lon;
    private ListView listView = null;
    private ResListAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_select);

        LinearLayout BtJudge = findViewById(R.id.search_btn_judge);

        BtJudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchSelect.this, Order.class);
                startActivity(intent);
            }
        });

        LinearLayout BtMobum = findViewById(R.id.search_btn_mobum);

        BtMobum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchSelect.this, Region.class);
                startActivity(intent);
            }
        });
    //cur_lat과 cur_lon에 각각 위도 경도 값을 할당해줌
    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "앱 실행을 위해서는 위치 권한을 설정하셔야 합니다!", Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_REQUEST);
        }
    }
    Location loc_current = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

    cur_lat = loc_current.getLatitude();
    cur_lon = loc_current.getLongitude();


    Geocoder geocoder = new Geocoder(this);
    List<Address> citylist = null;
        try {
        citylist = geocoder.getFromLocation(cur_lat, cur_lon, 10);
    } catch (
    IOException e) {
        e.printStackTrace();
    }

    String mylocation = citylist.get(1).getSubLocality();
        Log.d("zz",mylocation + cur_lat+"/"+cur_lon);


    call = RetrofitInstance.getApiService().nearres(mylocation, cur_lat, cur_lon);

        call.enqueue(new Callback<List<QualityRestaurantModel>>() {

            @Override
        public void onResponse(Call<List<QualityRestaurantModel>> call, Response<List<QualityRestaurantModel>> response) {
            result = response.body();
            OkHttpClient client = new OkHttpClient();
            int requestCount = 5;
            CountDownLatch latch = new CountDownLatch(requestCount); // CountDownLatch 생성

            for (int i = 0; i < requestCount; i++) {
                final int index = i;

                if ((index == 1 || index == 3) && result.get(index) == null) {
                    // result.get(1)과 result.get(3)이 null인 경우에는 건너뜁니다.
                    latch.countDown(); // 호출이 완료되지 않았음을 알립니다.
                    continue;
                }

                Request request = new Request.Builder()
                        .url("https://dapi.kakao.com/v2/search/image?query=" + result.get(index).getUpsoName())
                        .addHeader("Authorization", "KakaoAK 6617914a7dca2526015fb098e3846fc6")
                        .build();
                client.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                        // 호출 실패 시 처리
                        latch.countDown(); // 호출이 완료되었음을 알립니다.
                    }

                    @Override
                    public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                        final String res = response.body().string();
                        String url = res.substring(res.indexOf("image_url") + 12, res.indexOf("thumbnail_url") - 3);



                        // null 체크 후 setUrl 호출
                        if (result.get(index) != null) {
                            result.get(index).setUrl(url);
                        }

                        latch.countDown(); // 호출이 완료되었음을 알립니다.

                        if (index == 4) {
                            try {
                                latch.await(); // 모든 호출이 완료될 때까지 대기합니다.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RecyclerView reslist = findViewById(R.id.resturant_list);
                                        LinearLayoutManager manager = new LinearLayoutManager(SearchSelect.this, LinearLayoutManager.VERTICAL, false);
                                        reslist.setLayoutManager(manager);
                                        adapter = new ResListAdapter(result);
                                        reslist.setAdapter(adapter);

                                        for (int i = 0; i < 5; i++) {
                                            if (result.get(i) != null) {
                                                Log.d("tag", "" + result.get(i).getUrl());
                                            }
                                        }
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        @Override
        public void onFailure(Call<List<QualityRestaurantModel>> call, Throwable t) {
            // 실패 시 처리

            Log.d("mmmm",t.getMessage());
        }
    });


}
}