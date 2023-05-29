package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QnA extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);

        ScrollView judge = findViewById(R.id.judge_scroll);
        ScrollView mobum = findViewById(R.id.mobum_scroll);

        judge.setVisibility(View.INVISIBLE);

        ImageView btn_mobum = findViewById(R.id.imageView2);

        btn_mobum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judge.setVisibility(View.INVISIBLE);
                mobum.setVisibility(View.VISIBLE);
            }
        });
        ImageView btn_judge = findViewById(R.id.imageView);

        btn_judge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judge.setVisibility(View.VISIBLE);
                mobum.setVisibility(View.INVISIBLE);
            }
        });
    }
}
