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

    ScoreDatabase scoreDB;

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



//        RoomDatabase.Callback Call = new RoomDatabase.Callback() {
//            @Override
//            public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                super.onCreate(db);
//            }
//
//            @Override
//            public void onOpen(@NonNull SupportSQLiteDatabase db) {
//                super.onOpen(db);
//            }
//        };

        scoreDB = Room.databaseBuilder(getApplicationContext(), ScoreDatabase.class, "ScoreDB").build();

        Score s1 = new Score(player1, player2, time, winnertxt);

        ExecutorService exe = Executors.newSingleThreadExecutor();

        exe.execute(new Runnable() {
            @Override
            public void run() {
                scoreDB.getScoreDAO().addScore(s1);
            }
        });

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
