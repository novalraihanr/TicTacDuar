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
    boolean isActive = true;

    TextView timerText;

    AppCompatButton back;

    String pemain1,pemain2;
    int player = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winConditions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public int counter = 0;

    Timer timer;
    TimerTask timerTask;
    Double time =0.0;

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!isActive){
            gameReset(view);
            counter = 0;
        }

        if(gameState[tappedImage] == 2){
            counter++;
            if(counter == 9){
                isActive = false;
            }

            gameState[tappedImage] = player;

            if(player == 0){
                img.setImageResource(R.drawable.x);
                player = 1;
                TextView status = findViewById(R.id.status);

                status.setText("Giliran " + pemain2);
            }else {
                img.setImageResource(R.drawable.o);
                player = 0;
                TextView status = findViewById(R.id.status);

                status.setText("Giliran " + pemain1);
            }
        }

        int flag = 0;

        if(counter > 4){
            for(int[] winCondition : winConditions){
                if(gameState[winCondition[0]] == gameState[winCondition[1]] && gameState[winCondition[1]] == gameState[winCondition[2]] && gameState[winCondition[0]] != 2){
//                    flag = 1;
//
//                    String winnerStr;
//
//                    isActive = false;
//                    if(gameState[winCondition[0]] == 0){
//                        winnerStr = pemain1 + " telah menang";
//                    }else {
//                        winnerStr = pemain2 + " telah menang";
//                    }
//
//                    TextView status = findViewById(R.id.status);
//                    status.setText(winnerStr);
                    Bundle info = new Bundle();
                    Intent in = new Intent(MultiGameActivity.this, ScoreScreen.class);

                    String winnerStr;

                    if(gameState[winCondition[0]] == 0){
                        winnerStr = pemain1;
                    }else {
                        winnerStr = pemain2;
                    }

                    info.putString("pemain1", pemain1);
                    info.putString("pemain2", pemain2);
                    info.putString("winner", winnerStr);
                    info.putString("time", timerText.getText().toString());
                    in.putExtras(info);
                    startActivity(in);
                }
            }

            if(counter == 9 && flag == 0){
                TextView status = findViewById(R.id.status);
                status.setText("Seri");
            }
        }
    }

    public void gameReset(View view){
        isActive = true;
        player = 0;
        Arrays.fill(gameState,2);

        ((ImageView) findViewById(R.id.grid0)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid1)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid2)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid3)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid4)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid5)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid6)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid7)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Giliran " + pemain1);

    }

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

        timer = new Timer();

        startTimer();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiGameActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    private  void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private String getTimerText(){
        int rounded = (int) Math.round(time);

        int second = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;

        return formatTime(second, minutes);
    }

    private String formatTime(int second, int minute){
        return String.format("%02d", minute) + " : " + String.format("%02d", second);
    }
}