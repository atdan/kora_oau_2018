package com.example.root.quiko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class PlansListActivity extends AppCompatActivity {

    CardView carInsure, healthInsure, houseInsure, lifeInsure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_list);


        carInsure = findViewById(R.id.car_insurance);
        healthInsure = findViewById(R.id.health_insurance);
        houseInsure = findViewById(R.id.house_insurance);
        lifeInsure = findViewById(R.id.life_insurance);


        carInsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PlansListActivity.this,CarDetailActivity.class);
                startActivity(intent);
            }
        });



    }
}