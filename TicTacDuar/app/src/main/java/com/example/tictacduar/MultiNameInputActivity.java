package com.example.tictacduar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MultiNameInputActivity extends AppCompatActivity {

    EditText pemain1, pemain2;
    AppCompatButton start, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multinameinput);

        pemain1 = findViewById(R.id.namapemain1);
        pemain2 = findViewById(R.id.namapemain2);
        start = findViewById(R.id.mulai);
        back = findViewById(R.id.backButton);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp1 = pemain1.getText().toString().trim();
                String temp2 = pemain2.getText().toString().trim();

                Bundle info = new Bundle();
                Intent intent = new Intent(MultiNameInputActivity.this ,MultiGameActivity.class);
                info.putString("namaPemain1", temp1);
                info.putString("namaPemain2", temp2);
                intent.putExtras(info);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiNameInputActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });


    }
}