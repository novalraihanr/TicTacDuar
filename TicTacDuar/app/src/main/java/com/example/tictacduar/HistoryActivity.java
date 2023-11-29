package com.example.tictacduar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    AppCompatButton back;

    List<Score> scoreList;

    ScoreDatabase scoreDB;
    RecyclerView recyclerView;
    ScoreAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        scoreDB = Room.databaseBuilder(getApplicationContext(), ScoreDatabase.class, "ScoreDB").allowMainThreadQueries().build();
        List<Score> scoreLists = scoreDB.getScoreDAO().getAllScore();


        adapter = new ScoreAdapter(this, scoreLists);
        recyclerView.setAdapter(adapter);


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
