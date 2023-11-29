package com.example.tictacduar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ScoreDAO {

    @Insert
    public void addScore(Score score);

    @Update
    public void updateScore(Score score);

    @Delete
    public void deleteScore(Score score);

    @Query("select * from score")
    public List<Score> getAllScore();

    @Query("select * from score where score_id==:score_id")
    public Score getScore(int score_id);
}
