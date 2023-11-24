package com.example.tictacduar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScoreScreen extends AppCompatActivity {

    String player1, player2, winner, time;
    TextView win;
    AppCompatButton back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winscreen);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        player1 = info.getString("pemain1");
        player2 = info.getString("pemain2");
        winner = info.getString("winner");
        time = info.getString("time");

        String winnertxt = winner + " is the winner";

        win = findViewById(R.id.status);
        win.setText(winner + " Win");

        back = findViewById(R.id.bckButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ScoreScreen.this, MainMenu.class);
                startActivity(in);
            }
        });
    }
}
