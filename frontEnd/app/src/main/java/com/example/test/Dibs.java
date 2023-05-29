package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.InnerDataBase.DBHelper;
import com.example.test.RoomDatabase.DibsAdapter;

public class Dibs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DibsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibs);

        LinearLayout HomeButton = findViewById(R.id.home_button_dibs);
        recyclerView = findViewById(R.id.dibRecyclerview);
        adapter = new DibsAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        dbHelper = new DBHelper(Dibs.this, 1);

        int size = dbHelper.getSplitResult().size();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        for(int i = 0; i < size; i++){
            adapter.addItem(dbHelper.getSplitResult().get(i));
        }

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Food_Main.class);
                startActivity(intent);
            }
        });
    }
}
