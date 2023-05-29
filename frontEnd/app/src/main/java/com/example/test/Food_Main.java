package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Food_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);
        /**Maps넘어가는 코드 - 절대 절대 절대 건들지 말 것**/
        LinearLayout btn_maps = findViewById(R.id.btn_maps);
        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food_Main.this, Maps.class);
                startActivity(i);
            }
        });
        /**Maps넘어가는 코드 - 절대 절대 절대 건들지 말 것**/


        LinearLayout DibsButton = (LinearLayout) findViewById(R.id.dibs_button_main);

        DibsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food_Main.this ,Dibs.class);
                startActivity(intent);
            }
        });

        LinearLayout SearchButton = (LinearLayout) findViewById(R.id.search_button_main);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food_Main.this, SearchSelect.class);
                startActivity(intent);
            }
        });

    }

}