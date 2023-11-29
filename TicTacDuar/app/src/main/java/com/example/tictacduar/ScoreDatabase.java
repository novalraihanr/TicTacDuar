package com.example.tictacduar;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class},version = 1)
public abstract class ScoreDatabase extends RoomDatabase {
    public abstract ScoreDAO getScoreDAO();
}
