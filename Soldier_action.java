package com.example.xeno;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Soldier_action extends AppCompatActivity implements View.OnClickListener{

    private ImageView juvenile, soldier, fortuneteller, maiden, death, noble, scholar, spirit, emperor, hero;

    private GameMaster g;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_action);

        g = (GameMaster) getApplication();
        
        //タッチイベントのリスナー登録
        juvenile = findViewById(R.id.juvenile1);
        juvenile.setOnClickListener(this);
        soldier = findViewById(R.id.soldier1);
        soldier.setOnClickListener(this);
        fortuneteller = findViewById(R.id.fortuneteller1);
        fortuneteller.setOnClickListener(this);
        maiden = findViewById(R.id.maiden1);
        maiden.setOnClickListener(this);
        death = findViewById(R.id.death1);
        death.setOnClickListener(this);
        noble = findViewById(R.id.noble1);
        noble.setOnClickListener(this);
        scholar = findViewById(R.id.scholar1);
        scholar.setOnClickListener(this);
        spirit = findViewById(R.id.spirit1);
        spirit.setOnClickListener(this);
        emperor = findViewById(R.id.emperor);
        emperor.setOnClickListener(this);
        hero = findViewById(R.id.hero);
        hero.setOnClickListener(this);

        //各カードに座標をセット
        juvenile.setX(100);
        juvenile.setY(100);
        soldier.setX(400);
        soldier.setY(100);
        fortuneteller.setX(700);
        fortuneteller.setY(100);
        maiden.setX(100);
        maiden.setY(600);
        death.setX(400);
        death.setY(600);
        noble.setX(700);
        noble.setY(600);
        scholar.setX(100);
        scholar.setY(1100);
        spirit.setX(400);
        spirit.setY(1100);
        emperor.setX(700);
        emperor.setY(1100);
        hero.setX(400);
        hero.setY(1600);
    }

    int designated_num = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) { //カード選択時
            case R.id.juvenile1:
                // クリック処理
                designated_num = 1;
                break;
            case R.id.soldier1:
                // クリック処理
                designated_num = 2;
                break;
            case R.id.fortuneteller1:
                // クリック処理
                designated_num = 3;
                break;
            case R.id.maiden1:
                // クリック処理
                designated_num = 4;
                break;
            case R.id.death1:
                // クリック処理
                designated_num = 5;
                break;
            case R.id.noble1:
                // クリック処理
                designated_num = 6;
                break;
            case R.id.scholar1:
                // クリック処理
                designated_num = 7;
                break;
            case R.id.spirit1:
                designated_num = 8;
                break;
            case R.id.emperor:
                // クリック処理
                designated_num = 9;
                break;
            case R.id.hero:
                // クリック処理
                designated_num = 10;
                break;
            default:
                break;
        }
        // SharedPreferencesを取得
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // SharedPreferences.Editorを取得
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // グローバル変数を更新
        editor.putString("key", String.valueOf(designated_num)); // ここで適切なキーと値を設定
        // 変更を保存
        editor.apply();
        // アクティビティを終了
        finish();
    }
}