package com.example.test.qualitySpot;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.InnerDataBase.DBHelper;
import com.example.test.R;
import com.example.test.Retrofitmanager.BadHygieneRestaurantModel;
import com.example.test.Retrofitmanager.QualityRestaurantModel;
import com.example.test.Retrofitmanager.RetrofitInstance;
import com.example.test.Spot.SpotList;
import com.example.test.Spot.SpotListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class qualitySpot extends AppCompatActivity {
    private RecyclerView recyclerView;
    private qualityAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Call<List<QualityRestaurantModel>> call;
    private List<QualityRestaurantModel> result = new ArrayList<>();
    private String guName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality);

        Intent i = getIntent();
        guName = i.getStringExtra("area");
        recyclerView = findViewById(R.id.dibRecyclerview);
        adapter = new qualityAdapter();
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        call = RetrofitInstance.getApiService().getCggcode(guName);
        call.enqueue(new Callback<List<QualityRestaurantModel>>() {
            @Override
            public void onResponse(Call<List<QualityRestaurantModel>> call, Response<List<QualityRestaurantModel>> response) {
                result = response.body();
                for (int i = 0; i < result.size(); i++) {
                    adapter.addItem(result.get(i).getUpsoNm() + "," +
                            result.get(i).getSiteAddrRd() + "," +
                            result.get(i).getGeEnYn());
                }
            }

            @Override
            public void onFailure(Call<List<QualityRestaurantModel>> call, Throwable t) {

            }
        });
    }
}