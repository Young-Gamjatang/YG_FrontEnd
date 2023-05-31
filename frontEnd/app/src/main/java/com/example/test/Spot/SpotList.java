package com.example.test.Spot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.test.InnerDataBase.DBHelper;
import com.example.test.R;
import com.example.test.Retrofitmanager.QualityRestaurantModel;
import com.example.test.Retrofitmanager.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SpotListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private DBHelper dbHelper;
    private Call<List<QualityRestaurantModel>> call;
    private List<QualityRestaurantModel> result =new ArrayList<>();
    private String guName;
    private ArrayList<String> body = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_list);

        recyclerView = findViewById(R.id.spotRecyclerView);
        adapter = new SpotListAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        dbHelper = new DBHelper(SpotList.this, 1);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        call = RetrofitInstance.getApiService().getCggcode(guName);
        call.enqueue(new Callback<List<QualityRestaurantModel>>() {
            @Override
            public void onResponse(Call<List<QualityRestaurantModel>> call, Response<List<QualityRestaurantModel>> response) {

                result = response.body();
                for(int i = 0 ; i < result.size(); i++)
                {
                    body.add(result.get(i).getUpsoName()+","+
                            result.get(i).getSiteAddrRd()+","+
                            result.get(i).getGeEnYn());
                    adapter.addItem(body.get(i));
                }

            }

            @Override
            public void onFailure(Call<List<QualityRestaurantModel>> call, Throwable t) {

            }
        });




    }
}