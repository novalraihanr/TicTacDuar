package com.example.tictacduar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SingleNameInputActivity extends AppCompatActivity {

    EditText pemain;
    AppCompatButton start, back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlenameinput);

        pemain = findViewById(R.id.pemainnama);
        start = findViewById(R.id.mulai);
        back = findViewById(R.id.backButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = pemain.getText().toString().trim();

                Bundle info = new Bundle();
                Intent intent = new Intent(SingleNameInputActivity.this, SingleGameActivity.class);

                info.putString("namaPemain", temp);
                intent.putExtras(info);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleNameInputActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}