package com.example.tictacduar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MultiGameActivity extends AppCompatActivity {
    TextView timerText;
    AppCompatButton back;
    String pemain1,pemain2;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    private boolean gameStart = true;
    private int playerNum = 0;
    private int[] gameState = new int[9];
    private int[][] persyaratanMenang = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public int counter = 0;

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

        Arrays.fill(gameState, 2);
        startTimer();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiGameActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    public void Tapped(View view){
        if(!gameStart){
            gameReset(view);
            counter = 0;
        }

        if(counter == 9){
            gameStart = false;
        }

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = playerNum;

            if (playerNum == 0) {
                img.setImageResource(R.drawable.x_new);
                playerNum = 1;
            } else {
                img.setImageResource(R.drawable.o_new);
                playerNum = 0;
            }

            counter++;
            checkWin();
        }
    }


    public void checkWin() {
        for(int[] menang : persyaratanMenang){
            if(gameState[menang[0]] == gameState[menang[1]] && gameState[menang[1]] == gameState[menang[2]] && gameState[menang[0]] != 2){

                Bundle info = new Bundle();
                Intent in = new Intent(MultiGameActivity.this, ScoreScreen.class);

                String pemenang;

                if(gameState[menang[0]] == 0){
                    pemenang = pemain1;
                }else {
                    pemenang = pemain2;
                }

                info.putString("pemain1", pemain1);
                info.putString("pemain2", pemain2);
                info.putString("winner", pemenang);
                info.putString("time", timerText.getText().toString());
                in.putExtras(info);
                startActivity(in);
            }
        }

        if (counter == 9) {
            TextView status = findViewById(R.id.status);
            status.setText("Seri");
        }
    }

    public void gameReset(View view){
        Arrays.fill(gameState, 2);
        playerNum = 0;
        counter = 0;
        gameStart = true;
        for(int i=0; i<9; i++){
            ImageView img = findViewById(getResources().getIdentifier("grid" + i, "id", getPackageName()));
            img.setImageResource(0);
        }
        TextView status = findViewById(R.id.status);
        status.setText("Tic Tac Duar");
    }

    private  void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(time.toString());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}