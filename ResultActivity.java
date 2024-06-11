package com.example.xeno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//結果画面を出力するクラス
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView winner = findViewById(R.id.winner);

        int player_win_counter = getIntent().getIntExtra("WINNER", 0);

        switch (player_win_counter){
            case 1:
                winner.setText("1Pの勝利です");
                break;
            case 2:
                winner.setText("引き分けです");
                break;
            case 3:
                winner.setText("2Pの勝利です");
                break;
        }
    }
}