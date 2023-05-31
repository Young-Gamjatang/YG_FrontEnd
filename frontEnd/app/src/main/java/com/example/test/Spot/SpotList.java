package com.example.test.Spot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

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
    private ArrayList<String> body = new ArrayList<>();
    private Date currentDate;
    private SimpleDateFormat format;
    private int intCurrentDate;
    private String[] admDipoYmd;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_list);

        Intent i = getIntent();
        guName = i.getStringExtra("area");
        term = i.getStringExtra("term");
        currentDate = new Date();
        format = new SimpleDateFormat("yyyymmdd");
        intCurrentDate = Integer.parseInt(format.format(currentDate));
        admDipoYmd = splitYmd(format.format(currentDate));

        recyclerView = findViewById(R.id.spotRecyclerView);
        adapter = new SpotListAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        dbHelper = new DBHelper(SpotList.this, 1);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        call = RetrofitInstance.getApiService().getBadHygieneRestaurantModel(guName);
        call.enqueue(new Callback<List<BadHygieneRestaurantModel>>() {
            @Override
            public void onResponse(Call<List<BadHygieneRestaurantModel>> call, Response<List<BadHygieneRestaurantModel>> response) {
                result = response.body();
                for(int i = 0; i < result.size(); i++){

                    switch(term){
                        case "1m":
                            if(admDipoYmd[1] == "01"){
                                if(Integer.parseInt(result.get(i).getAdmDispoYmd()) >= (intCurrentDate -= 8900)){
                                    adapter.addItem(result.get(i).getUpsoNm() + ","+
                                            result.get(i).getSiteAddrRd() + ","+
                                            result.get(i).getDispoCtnDt());
                                }
                            }else{
                                if(Integer.parseInt(result.get(i).getAdmDispoYmd()) >= (intCurrentDate -= 100)){
                                    adapter.addItem(result.get(i).getUpsoNm() + ","+
                                            result.get(i).getSiteAddrRd() + ","+
                                            result.get(i).getDispoCtnDt());
                                }
                            }
                            break;
                        case "3m":
                            if(admDipoYmd[1] == "03"){
                                if(Integer.parseInt(result.get(i).getAdmDispoYmd()) >= (intCurrentDate -= 9100)){
                                    adapter.addItem(result.get(i).getUpsoNm() + ","+
                                            result.get(i).getSiteAddrRd() + ","+
                                            result.get(i).getDispoCtnDt());
                                }
                            }else{
                                if(Integer.parseInt(result.get(i).getAdmDispoYmd()) >= (intCurrentDate -= 300)){
                                    adapter.addItem(result.get(i).getUpsoNm() + ","+
                                            result.get(i).getSiteAddrRd() + ","+
                                            result.get(i).getDispoCtnDt());
                                }
                            }
                            break;
                        case "6m":
                            if(admDipoYmd[1] == "06"){
                                if(Integer.parseInt(result.get(i).getAdmDispoYmd()) >= (intCurrentDate -= 9400)){
                                    adapter.addItem(result.get(i).getUpsoNm() + ","+
                                            result.get(i).getSiteAddrRd() + ","+
                                            result.get(i).getDispoCtnDt());
                                }
                            }else{
                                if(Integer.parseInt(result.get(i).getAdmDispoYmd()) >= (intCurrentDate -= 600)){
                                    adapter.addItem(result.get(i).getUpsoNm() + ","+
                                            result.get(i).getSiteAddrRd() + ","+
                                            result.get(i).getDispoCtnDt());
                                }
                            }
                            break;
                        case "1y":
                            if(Integer.parseInt(result.get(i).getAdmDispoYmd()) >= (intCurrentDate -= 1000)){
                                adapter.addItem(result.get(i).getUpsoNm() + ","+
                                        result.get(i).getSiteAddrRd() + ","+
                                        result.get(i).getDispoCtnDt());
                            }
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BadHygieneRestaurantModel>> call, Throwable t) {

            }
        });

    }

    public String[] splitYmd(String ymd){
        String year = ymd.substring(0,4);
        String month = ymd.substring(4,6);
        String day = ymd.substring(6,8);

        String[] admDispoYmd = {year, month, day};

        return admDispoYmd;
    }
}