package com.example.test.Spot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.test.InnerDataBase.DBHelper;
import com.example.test.R;
import com.example.test.Retrofitmanager.BadHygieneRestaurantModel;
import com.example.test.Retrofitmanager.QualityRestaurantModel;
import com.example.test.Retrofitmanager.RetrofitInstance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SpotListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private DBHelper dbHelper;
    private Call<List<BadHygieneRestaurantModel>> call;
    private List<BadHygieneRestaurantModel> result =new ArrayList<>();
    private String guName;
    private String term;
    private Date currentDate;
    private SimpleDateFormat format;
    private int intCurrentDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_list);

        Intent i = getIntent();
        guName = i.getStringExtra("area");
        term = i.getStringExtra("term");

        currentDate = new Date();
        format = new SimpleDateFormat("yyyymmdd");
        intCurrentDate = Integer.parseInt(format.format(currentDate));

        dbHelper = new DBHelper(SpotList.this, 1);


        call = RetrofitInstance.getApiService().getBadHygieneRestaurantModel(guName);
        call.enqueue(new Callback<List<BadHygieneRestaurantModel>>() {
            @Override
            public void onResponse(Call<List<BadHygieneRestaurantModel>> call, Response<List<BadHygieneRestaurantModel>> response) {
                result = response.body();
                recyclerView = findViewById(R.id.spotRecyclerView);
                linearLayoutManager = new LinearLayoutManager(SpotList.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new SpotListAdapter(result, intCurrentDate, term);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BadHygieneRestaurantModel>> call, Throwable t) {

            }
        });

    }
}