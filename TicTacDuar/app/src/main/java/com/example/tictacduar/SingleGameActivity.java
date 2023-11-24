package com.example.tictacduar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SingleGameActivity extends AppCompatActivity {

    TextView timerText;

    AppCompatButton back;

    boolean isActive = true;

    String playername;
    int player = 0;
    int cpu = 1;
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

            img.setImageResource(R.drawable.x);
            if(counter != 9){
                cpuTurn();
            }
        }
        checkWin();
    }

    public void cpuTurn(){
        int chosenImage;
        ImageView img;

        do {
            chosenImage = new Random().nextInt(9);
            img = findViewById(getResources().getIdentifier("grid" + chosenImage, "id", getPackageName()));
        }while(gameState[chosenImage] != 2);

        counter++;
        if(counter == 9){
            isActive = false;
        }

        gameState[chosenImage] = cpu;
        img.setImageResource(R.drawable.o);
        checkWin();
    }

    public void checkWin(){
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
//                        winnerStr = playername + " telah menang";
//                    }else {
//                        winnerStr = "CPU telah menang";
//                    }
//
//                    TextView status = findViewById(R.id.status);
//                    status.setText(winnerStr);
                    timerTask.cancel();
                    Bundle info = new Bundle();
                    Intent in = new Intent(SingleGameActivity.this, ScoreScreen.class);

                    String winnerStr;

                    if(gameState[winCondition[0]] == 0){
                        winnerStr = playername;
                    }else {
                        winnerStr = "CPU";
                    }

                    info.putString("pemain1", playername);
                    info.putString("pemain2", "CPU");
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
        counter = 0;
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
        status.setText("Tic Tac Duar");
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleingame);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        playername = info.getString("namaPemain");

        timerText = findViewById(R.id.timerr);
        back = findViewById(R.id.backButton);

        timer = new Timer();

        startTimer();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SingleGameActivity.this, MainMenu.class);
                        startActivity(intent);
                    }
                });
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