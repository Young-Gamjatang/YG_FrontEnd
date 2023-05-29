package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchSelect extends AppCompatActivity {
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
    }
}
