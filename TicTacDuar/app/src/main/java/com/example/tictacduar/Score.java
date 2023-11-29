package com.example.tictacduar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Score")
public class Score {

    @ColumnInfo(name="score_id")
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "player1")
    String player1;
    @ColumnInfo(name = "player2")
    String player2;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "status")
    String winner;

    @Ignore
    public Score(){

    }

    @Ignore
    public Score(String player1, String time, String winner){
        this.player1 = player1;
        this.time = time;
        this.winner = winner;
        this.id = 0;
    }

    public Score(String player1, String player2, String time, String winner){
        this.player1 = player1;
        this.player2 = player2;
        this.time = time;
        this.winner = winner;
        this.id = 0;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }
    public String getPlayer1() {
        return player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
    public String getPlayer2() {
        return player2;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
