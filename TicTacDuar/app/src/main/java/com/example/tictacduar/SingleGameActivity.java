package com.example.tictacduar;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    private boolean aktif = true;
    private String playername;
    private int playerNum = 0;
    private int cpuNum = 1;
    private int[] gameState = new int[9];
    private int[][] persyaratanMenang = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public int counter = 0;

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
        Arrays.fill(gameState, 2);

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

    public void Tapped(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!aktif){
            gameReset(view);
            counter = 0;
        }

        if(gameState[tappedImage] == 2){
            counter++;
            if(counter == 9){
                aktif = false;
            }

            gameState[tappedImage] = playerNum;

            img.setImageResource(R.drawable.x_new);
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
            aktif = false;
        }

        gameState[chosenImage] = cpuNum;
        img.setImageResource(R.drawable.o_new);
        checkWin();
    }

    public void checkWin(){
        if(counter > 4){
            for(int[] menang : persyaratanMenang){
                if(gameState[menang[0]] == gameState[menang[1]] && gameState[menang[1]] == gameState[menang[2]] && gameState[menang[0]] != 2){
                    timerTask.cancel();
                    Bundle info = new Bundle();
                    Intent in = new Intent(SingleGameActivity.this, ScoreScreen.class);

                    String pemenang;

                    if(gameState[menang[0]] == 0){
                        pemenang = playername;
                    }else {
                        pemenang = "CPU";
                    }

                    info.putString("pemain1", playername);
                    info.putString("pemain2", "CPU");
                    info.putString("winner", pemenang);
                    info.putString("time", timerText.getText().toString());
                    in.putExtras(info);
                    startActivity(in);
                }
            }

            if(counter == 9){
                TextView status = findViewById(R.id.status);
                status.setText("Seri");
            }
        }
    }

    public void gameReset(View view){
        Arrays.fill(gameState, 2);
        counter = 0;
        aktif = true;
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