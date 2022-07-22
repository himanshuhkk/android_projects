package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button [] bnb;
    int [][] winarray = {{0,1,2} , {3,4,5} , {6,7,8} ,
            {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}  };
    int[] gamepos = {2,2,2,2,2,2,2,2,2};
    private  int cntr = 0;
    TextView tvpos;
    Button bnplay , bnreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnplay = findViewById(R.id.bnplay);
        bnreset = findViewById(R.id.bnreset);
        bnb = new Button[9];
        bnb[0] = findViewById(R.id.bnb0);
        bnb[1] = findViewById(R.id.bnb1);
        bnb[2] = findViewById(R.id.bnb2);
        bnb[3] = findViewById(R.id.bnb3);
        bnb[4] = findViewById(R.id.bnb4);
        bnb[5] = findViewById(R.id.bnb5);
        bnb[6] = findViewById(R.id.bnb6);
        bnb[7] = findViewById(R.id.bnb7);
        bnb[8] = findViewById(R.id.bnb8);
        for(int i = 0 ; i<9 ; i++){
            bnb[i].setOnClickListener(this);
        }
        LockBtns();
        bnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bnplay.setEnabled(false);
                ResetGamePos();
                UnlockBtns();
            }
        });
        bnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bnplay.setEnabled(true);
                tvpos.setText("");
                tvpos.setVisibility(View.GONE);
                clearControls();
            }
        });
        tvpos = findViewById(R.id.tvpos);
        tvpos.setVisibility(View.GONE);
        clearControls();
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        view.setEnabled(false);
        Button bn = (Button) view;
        String txt = bn.getText().toString();
        Integer tg = Integer.parseInt(bn.getTag().toString());
        if(txt.equals("")){
            bn.setText("0");
            gamepos[tg] = 1;
            cntr++;
        }
        GetNextMove();
    }
    private void GetNextMove(){
        int min = 0 ;
        int max = 8;
        int bno = new Random().nextInt(max - min) + min;
        String tg = bnb[bno].getTag().toString();
        String txt = bnb[bno].getText().toString();

        if(tg != String.valueOf(bno) && txt.equals("")){
            bnb[bno].setText("X");
            bnb[bno].setEnabled(false);
            cntr++;
            gamepos[bno] = 2;
            CheckWinner();
        }
        else{
            if(cntr < 8){
                GetNextMove();
            }
            else{
                bnb[bno].setText("X");
                tvpos.setText("Draw");
                tvpos.setVisibility(View.VISIBLE);
                LockBtns();
                cntr = 0;

            }
        }
    }
    private void CheckWinner(){
        for(int[] winner : winarray){
            if(gamepos[winner[0]] == gamepos[winner[1]] &&
               gamepos[winner[1]] == gamepos[winner[2]] && gamepos[winner[0]] != 2){
                if(gamepos[winner[0]]==0) tvpos.setText("Computer win");
                else
                {
                    tvpos.setText("You Win");
                }
                tvpos.setVisibility(View.VISIBLE);
                LockBtns();
            }
        }
    }
    private void clearControls(){
        for(int i = 0; i<9; i++){
            bnb[i].setText("");
        }
    }
    private void LockBtns(){
        for(int i = 0 ; i<9;i++){
            bnb[i].setEnabled(false);
        }
    }
    private void UnlockBtns(){
        for(int i = 0 ; i<9;i++){
            bnb[i].setEnabled(true);
        }
    }
    private void ResetGamePos(){
        for(int i = 0 ; i<9;i++){
            gamepos[i] = 2;
        }
    }
}