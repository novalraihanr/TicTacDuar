package com.example.tictacduar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MultiGameActivity extends AppCompatActivity {

    TextView timerText;

    AppCompatButton back;

    String pemain1,pemain2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiingame);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        pemain1 = info.getString("namaPemain1");
        pemain2 = info.getString("namaPemain2");

        timerText = findViewById(R.id.timerr);
        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiGameActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}