package com.example.tictacduar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SingleGameActivity extends AppCompatActivity {

    TextView timerText;

    AppCompatButton back;

    String playername;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleingame);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        playername = info.getString("namaPemain");

        timerText = findViewById(R.id.timerr);
        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SingleGameActivity.this, MainMenu.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}