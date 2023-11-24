package com.example.tictacduar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HistoryActivity extends AppCompatActivity {

    AppCompatButton back;

    List<Score> scoreList;

    ScoreDatabase scoreDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        scoreDB = Room.databaseBuilder(getApplicationContext(), ScoreDatabase.class, "ScoreDB").build();

        ExecutorService exe = Executors.newSingleThreadExecutor();

        exe.execute(new Runnable() {
            @Override
            public void run() {
                scoreList = scoreDB.getScoreDAO().getAllScore();
            }
        });

        StringBuilder strings = new StringBuilder();
        for(Score s : scoreList){
            strings.append(s.getPlayer1()).append(";").append(s.getPlayer2()).append(";").append(s.getTime()).append(";").append(s.getWinner()).append("_");
        }

        strings.toString();

        String complete = new String(strings);
        String[] baris = complete.split("_");
        TableLayout tbLayout = (TableLayout) findViewById(R.id.tbLayout);
        tbLayout.removeAllViews();

        for (int i = 0; i < baris.length; i++) {
            String bariss = baris[i];
            TableRow tbRow = new TableRow(HistoryActivity.this);
            tbRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            final String[] kolom = bariss.split(";");

            for (int j = 0; j < kolom.length; j++) {
                final String kolomm = kolom[j];
                TextView kmView = new TextView(HistoryActivity.this);
                kmView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                kmView.setTextColor(getResources().getColor(android.R.color.black));
                kmView.setText(String.format("%7s", kolomm));
                tbRow.addView(kmView);
            }
            tbLayout.addView(tbRow);
        }

        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });


    }
}
