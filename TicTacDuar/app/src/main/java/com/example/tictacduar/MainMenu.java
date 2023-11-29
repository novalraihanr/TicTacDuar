package com.example.tictacduar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    Button spButton, mpButton, hisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spButton = findViewById(R.id.spButton);
        mpButton = findViewById(R.id.mpButton);
        hisButton = findViewById(R.id.hisButton);

        mpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainMenu.this, MultiNameInputActivity.class);
                startActivity(in);
            }
        });

        spButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainMenu.this, SingleNameInputActivity.class);
                startActivity(in);
            }
        });

        hisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainMenu.this, HistoryActivity.class);
                startActivity(in);
            }
        });


    }



}
