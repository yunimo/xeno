package com.example.xeno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;

//実際のゲーム進行を行うクラス
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView gamemaster;
    private ImageView juvenile1, soldier1, fortuneteller1, maiden1, death1, noble1, scholar1, spirit1, emperor, hero;
    private ImageView juvenile2, soldier2, fortuneteller2, maiden2, death2, noble2, scholar2, spirit2;
    private ImageView deck, reverseCard1, reverseCard2;

    //1Pの手札の変数(実際に置く(X,Y)の座標)<空白のカード枠を作ってカードをそこに置くイメージ>
    float p1_hand1X, p1_hand1Y, p1_hand2X, p1_hand2Y;
    //2Pの手札の変数(実際に置く(X,Y)の座標)<空白のカード枠を作ってカードをそこに置くイメージ>
    float p2_hand1X, p2_hand1Y, p2_hand2X, p2_hand2Y;

    // 状態を表す変数
    static boolean continue_game = true; //ゲームを続けるかどうかの判定
    static boolean fortuneteller_counter = false; //占い師の効果を発動するか
    static boolean maiden_counter = false; //乙女の効果を発動するか
    static int player_win_counter = 0; //勝敗判定⇒1:勝利,2:引き分け,3:敗北
    
    private GameMaster g; //GameMasterクラスのインスタンス化

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        g = (GameMaster) getApplication();
        
        //タッチイベントのリスナー登録
        gamemaster = findViewById(R.id.gamemaster);
        deck = findViewById(R.id.deck);
        deck.setOnClickListener(this);
        juvenile1 = findViewById(R.id.juvenile1);
        juvenile1.setOnClickListener(this);
        juvenile2 = findViewById(R.id.juvenile2);
        juvenile2.setOnClickListener(this);
        soldier1 = findViewById(R.id.soldier1);
        soldier1.setOnClickListener(this);
        soldier2 = findViewById(R.id.soldier2);
        soldier2.setOnClickListener(this);
        fortuneteller1 = findViewById(R.id.fortuneteller1);
        fortuneteller1.setOnClickListener(this);
        fortuneteller2 = findViewById(R.id.fortuneteller2);
        fortuneteller2.setOnClickListener(this);
        maiden1 = findViewById(R.id.maiden1);
        maiden1.setOnClickListener(this);
        maiden2 = findViewById(R.id.maiden2);
        maiden2.setOnClickListener(this);
        death1 = findViewById(R.id.death1);
        death1.setOnClickListener(this);
        death2 = findViewById(R.id.death2);
        death2.setOnClickListener(this);
        noble1 = findViewById(R.id.noble1);
        noble1.setOnClickListener(this);
        noble2 = findViewById(R.id.noble2);
        noble2.setOnClickListener(this);
        scholar1 = findViewById(R.id.scholar1);
        scholar1.setOnClickListener(this);
        scholar2 = findViewById(R.id.scholar2);
        scholar2.setOnClickListener(this);
        spirit1 = findViewById(R.id.spirit1);
        spirit1.setOnClickListener(this);
        spirit2 = findViewById(R.id.spirit2);
        spirit2.setOnClickListener(this);
        emperor = findViewById(R.id.emperor);
        emperor.setOnClickListener(this);
        hero = findViewById(R.id.hero);
        hero.setOnClickListener(this);
        reverseCard1 = findViewById(R.id.card1);
        reverseCard1.setOnClickListener(this);
        reverseCard2 = findViewById(R.id.card2);
        reverseCard2.setOnClickListener(this);

        //ImageViewとカードナンバーを結ぶ
        g.card_Map.put(juvenile1, 1);
        g.card_Map.put(juvenile2, 1);
        g.card_Map.put(soldier1, 2);
        g.card_Map.put(soldier2, 2);
        g.card_Map.put(fortuneteller1, 3);
        g.card_Map.put(fortuneteller2, 3);
        g.card_Map.put(maiden1, 4);
        g.card_Map.put(maiden2, 4);
        g.card_Map.put(death1, 5);
        g.card_Map.put(death2, 5);
        g.card_Map.put(noble1, 6);
        g.card_Map.put(noble2, 6);
        g.card_Map.put(scholar1, 7);
        g.card_Map.put(scholar2, 7);
        g.card_Map.put(spirit1, 8);
        g.card_Map.put(spirit2, 8);
        g.card_Map.put(emperor, 9);
        g.card_Map.put(hero, 10);

        //手札の空白のカード枠の初期値
        p1_hand1X = 150;
        p1_hand1Y = 1400;
        p1_hand2X = 550;
        p1_hand2Y = 1400;
        p2_hand1X = 150;
        p2_hand1Y = 100;
        p2_hand2X = 550;
        p2_hand2Y = 100;

        //墓地の空白のカード枠の初期値
        g.discard1X = 0;
        g.discard1Y = 1000;
        g.discard2X = 0;
        g.discard2Y = 500;

        //ゲーム開始時はカードを枠外へ配置(見えないようにする)
        juvenile1.setX(-1000);
        juvenile1.setY(0);
        juvenile2.setX(-1000);
        juvenile2.setY(0);
        soldier1.setX(-1000);
        soldier1.setY(0);
        soldier2.setX(-1000);
        soldier2.setY(0);
        fortuneteller1.setX(-1000);
        fortuneteller1.setY(0);
        fortuneteller2.setX(-1000);
        fortuneteller2.setY(0);
        maiden1.setX(-1000);
        maiden1.setY(0);
        maiden2.setX(-1000);
        maiden2.setY(0);
        death1.setX(-1000);
        death1.setY(-0);
        death2.setX(-1000);
        death2.setY(0);
        noble1.setX(-1000);
        noble1.setY(0);
        noble2.setX(-1000);
        noble2.setY(0);
        scholar1.setX(-1000);
        scholar1.setY(0);
        scholar2.setX(-1000);
        scholar2.setY(0);
        spirit1.setX(-1000);
        spirit1.setY(0);
        spirit2.setX(-1000);
        spirit2.setY(0);
        emperor.setX(-1000);
        emperor.setY(0);
        hero.setX(-1000);
        hero.setY(0);
        reverseCard1.setX(-1000);
        reverseCard2.setY(-1000);

        //各カードの(X,Y)座標を取得
        g.juvenile1X = juvenile1.getX();
        g.juvenile1Y = juvenile1.getY();
        g.juvenile2X = juvenile2.getX();
        g.juvenile2Y = juvenile2.getY();
        g.soldier1X = soldier1.getX();
        g.soldier1Y = soldier1.getY();
        g.soldier2X = soldier2.getX();
        g.soldier2Y = soldier2.getY();
        g.fortuneteller1X = fortuneteller1.getX();
        g.fortuneteller1Y = fortuneteller1.getY();
        g.fortuneteller2X = fortuneteller2.getX();
        g.fortuneteller2Y = fortuneteller2.getY();
        g.maiden1X = maiden1.getX();
        g.maiden1Y = maiden1.getY();
        g.maiden2X = maiden2.getX();
        g.maiden2Y = maiden2.getY();
        g.death1X = death1.getX();
        g.death1Y = death1.getY();
        g.death2X = death2.getX();
        g.death2Y = death2.getY();
        g.noble1X = noble1.getX();
        g.noble1Y = noble1.getY();
        g.noble2X = noble2.getX();
        g.noble2Y = noble2.getY();
        g.scholar1X = scholar1.getX();
        g.scholar1Y = scholar1.getY();
        g.scholar2X = scholar2.getX();
        g.scholar2Y = scholar2.getY();
        g.spirit1X = spirit1.getX();
        g.spirit1Y = spirit1.getY();
        g.spirit2X = spirit2.getX();
        g.spirit2Y = spirit2.getY();
        g.emperorX = emperor.getX();
        g.emperorY = emperor.getY();
        g.heroX = hero.getX();
        g.heroY = hero.getY();

        //山札にカードを追加
        g.list.add(juvenile1);
        g.list.add(juvenile2);
        g.list.add(soldier1);
        g.list.add(soldier2);
        g.list.add(fortuneteller1);
        g.list.add(fortuneteller2);
        g.list.add(maiden1);
        g.list.add(maiden2);
        g.list.add(death1);
        g.list.add(death2);
        g.list.add(noble1);
        g.list.add(noble2);
        g.list.add(scholar1);
        g.list.add(scholar2);
        g.list.add(spirit1);
        g.list.add(spirit2);
        g.list.add(emperor);
        g.list.add(hero);

        // 山札内のカードをシャッフルする
        Collections.shuffle(g.list);

        //1Pと2Pにカードを一枚配る
        g.player1_cards.add(g.list.get(0));
        g.list.remove(0);
        g.player2_cards.add(g.list.get(0));
        g.list.remove(0);

        //転生札のセット
        g.reincarnationCard = g.list.get(0);
        g.list.remove(0);

        //1Pのカードリストの1番目を取得
        g.p1_card1 = g.player1_cards.get(0);
        //2Pのカードリストの1番目を取得
        g.p2_card1 = g.player2_cards.get(0);

        //1Pのカードリストの1番目のカードの(X,Y)座標を取得
        g.p1_card1X = g.p1_card1.getX();
        g.p1_card1Y = g.p1_card1.getY();

        //2Pのカードリストの1番目のカードの(X,Y)座標を取得
        g.p2_card1X = g.p2_card1.getX();
        g.p2_card1Y = g.p2_card1.getY();

        //1Pの手札をカード枠にセット
        g.p1_card1X = p1_hand1X;
        g.p1_card1Y = p1_hand1Y;
        //2Pの手札をカード枠にセット
        g.p2_card1X = p2_hand1X;
        g.p2_card1Y = p2_hand1Y;

        //山札を表示
        deck.setX(700);
        deck.setY(700);

        //手札を表示
        g.p1_card1.setX(g.p1_card1X);
        g.p1_card1.setY(g.p1_card1Y);

        //ゲームマスターの初期文
        gamemaster.setText("XENOを開始します");
    }

    /*1.カード選択時
     *2.soldierの効果発動時
     * onClickイベント処理*/
    @Override
    public void onClick(View v) {
        //画像を一番上に重ね合わせる
        ViewGroup p = (ViewGroup) v.getParent();
        p.removeView(v);
        p.addView(v);

        if (g.click == 0) { //ドロー時
            if (v.getId() == R.id.deck) {
                g.click = 1;
                //ターン経過を加算
                g.turn += 1;
                //カードをドロー
                Draw();
                Display();
            }
        } else if (g.click == 1) { //使用カード選択時
            switch (v.getId()) { //カード選択時
                case R.id.juvenile1:
                    // クリック処理
                    g.click = 2;
                    g.temp = juvenile1;
                    setCard();
                    Juvenile();
                    break;
                case R.id.juvenile2:
                    // クリック処理
                    g.click = 2;
                    g.temp = juvenile2;
                    setCard();
                    Juvenile();
                    break;
                case R.id.soldier1:
                    // クリック処理
                    g.click = 2;
                    g.temp = soldier1;
                    setCard();
                    Soldier();
                    break;
                case R.id.soldier2:
                    // クリック処理
                    g.click = 2;
                    g.temp = soldier2;
                    setCard();
                    Soldier();
                    break;
                case R.id.fortuneteller1:
                    // クリック処理
                    g.click = 2;
                    g.temp = fortuneteller1;
                    setCard();
                    FortuneTeller();
                    break;
                case R.id.fortuneteller2:
                    // クリック処理
                    g.click = 2;
                    g.temp = fortuneteller2;
                    setCard();
                    FortuneTeller();
                    break;
                case R.id.maiden1:
                    // クリック処理
                    g.click = 2;
                    g.temp = maiden1;
                    setCard();
                    Maiden();
                    break;
                case R.id.maiden2:
                    // クリック処理
                    g.click = 2;
                    g.temp = maiden2;
                    setCard();
                    Maiden();
                    break;
                case R.id.death1:
                    // クリック処理
                    g.click = 2;
                    g.temp = death1;
                    setCard();
                    Death();
                    break;
                case R.id.death2:
                    // クリック処理
                    g.click = 2;
                    g.temp = death2;
                    setCard();
                    Death();
                    break;
                case R.id.noble1:
                    // クリック処理
                    g.click = 2;
                    g.temp = noble1;
                    setCard();
                    Noble();
                    break;
                case R.id.noble2:
                    // クリック処理
                    g.click = 2;
                    g.temp = noble2;
                    setCard();
                    Noble();
                    break;
                case R.id.scholar1:
                    // クリック処理
                    g.click = 2;
                    g.temp = scholar1;
                    setCard();
                    Scholar();
                    break;
                case R.id.scholar2:
                    // クリック処理
                    g.click = 2;
                    g.temp = scholar2;
                    setCard();
                    Scholar();
                    break;
                case R.id.spirit1:
                    // クリック処理
                    g.click = 2;
                    g.temp = spirit1;
                    setCard();
                    Spirit();
                    break;
                case R.id.spirit2:
                    // クリック処理
                    g.click = 2;
                    g.temp = spirit2;
                    setCard();
                    Spirit();
                    break;
                case R.id.emperor:
                    // クリック処理
                    g.click = 2;
                    g.temp = emperor;
                    setCard();
                    Emperor();
                    break;
                default:
                    break;
            }
            Display();
        } else if (g.click == 2) { //ターンエンド時
            if (v.getId() == R.id.deck) {

                //ゲームマスターの言葉を更新
                g.gm = "相手に端末を渡してください";

                g.click = 0;

                //手札を消す
                g.p1_card1.setX(-1000);
                g.p2_card1.setX(-1000);

                //各カードの(X,Y)を更新
                juvenile1.setX(g.juvenile1X);
                juvenile1.setY(g.juvenile1Y);
                juvenile2.setX(g.juvenile2X);
                juvenile2.setY(g.juvenile2Y);
                soldier1.setX(g.soldier1X);
                soldier1.setY(g.soldier1Y);
                soldier2.setX(g.soldier2X);
                soldier2.setY(g.soldier2Y);
                fortuneteller1.setX(g.fortuneteller1X);
                fortuneteller1.setY(g.fortuneteller1Y);
                fortuneteller2.setX(g.fortuneteller2X);
                fortuneteller2.setY(g.fortuneteller2Y);
                maiden1.setX(g.maiden1X);
                maiden1.setY(g.maiden1Y);
                maiden2.setX(g.maiden2X);
                maiden2.setY(g.maiden2Y);
                death1.setX(g.death1X);
                death1.setY(g.death1Y);
                death2.setX(g.death2X);
                death2.setY(g.death2Y);
                noble1.setX(g.noble1X);
                noble1.setY(g.noble1Y);
                noble2.setX(g.noble2X);
                noble2.setY(g.noble2Y);
                scholar1.setX(g.scholar1X);
                scholar1.setY(g.scholar1Y);
                scholar2.setX(g.scholar2X);
                scholar2.setY(g.scholar2Y);
                spirit1.setX(g.spirit1X);
                spirit1.setY(g.spirit1Y);
                spirit2.setX(g.spirit2X);
                spirit2.setY(g.spirit2Y);
                emperor.setX(g.emperorX);
                emperor.setY(g.emperorY);
                hero.setX(g.heroX);
                hero.setY(g.heroY);

                finalButtle();//最終決戦へ
            }
        } else if (g.click == 3) { //少年・死神・皇帝のカード効果発動時
            switch (v.getId()) { //ターン反転してる
                case R.id.juvenile1:
                    // クリック処理
                    g.click = 2;
                    g.countJuvenile = 1;
                    g.temp = juvenile1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.juvenile2:
                    // クリック処理
                    g.click = 2;
                    g.countJuvenile = 1;
                    g.temp = juvenile2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.soldier1:
                    // クリック処理
                    g.click = 2;
                    g.temp = soldier1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.soldier2:
                    // クリック処理
                    g.click = 2;
                    g.temp = soldier2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.fortuneteller1:
                    // クリック処理
                    g.click = 2;
                    g.temp = fortuneteller1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.fortuneteller2:
                    // クリック処理
                    g.click = 2;
                    g.temp = fortuneteller2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.maiden1:
                    // クリック処理
                    g.click = 2;
                    g.temp = maiden1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.maiden2:
                    // クリック処理
                    g.click = 2;
                    g.temp = maiden2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.death1:
                    // クリック処理
                    g.click = 2;
                    g.temp = death1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.death2:
                    // クリック処理
                    g.click = 2;
                    g.temp = death2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.noble1:
                    // クリック処理
                    g.click = 2;
                    g.countNoble = 1;
                    g.temp = noble1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.noble2:
                    // クリック処理
                    g.click = 2;
                    g.countNoble = 1;
                    g.temp = noble2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.scholar1:
                    // クリック処理
                    g.click = 4;
                    g.temp = scholar1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "賢者の効果<道ずれ>/山札をタッチしてください";
                    break;
                case R.id.scholar2:
                    // クリック処理
                    g.click = 4;
                    g.temp = scholar2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "賢者の効果<道ずれ>/山札をタッチしてください";
                    break;
                case R.id.spirit1:
                    // クリック処理
                    g.click = 2;
                    g.temp = spirit1;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.spirit2:
                    // クリック処理
                    g.click = 2;
                    g.temp = spirit2;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.emperor:
                    // クリック処理
                    g.click = 2;
                    g.temp = emperor;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "山札をタッチしてターンを終了してください";
                    break;
                case R.id.hero:
                    // クリック処理
                    g.click = 4;
                    publicExecution();//皇帝の公開処刑チェック
                    g.temp = hero;
                    setCard();
                    //ゲームマスターの言葉を更新
                    g.gm = "英雄の効果<転生>/山札をタッチしてください";
                    break;
                case R.id.card1://一枚目の裏向きカード選択
                    // クリック処理
                    g.click = 2;
                    if (g.turn % 2 == 1) {
                        g.temp = g.p1_card1;
                    } else {
                        g.temp = g.p2_card1;
                    }
                    //画像を一番上に重ね合わせる
                    p.removeView(g.temp);
                    p.addView(g.temp);
                    setCard();
                    reverseCard1.setX(-1000);
                    reverseCard2.setX(-1000);
                    if(g.temp == scholar1 || g.temp == scholar2 || g.temp == hero){
                        g.click = 4;
                        //ゲームマスターの言葉を更新
                        g.gm = "英雄の効果<転生>/山札をタッチしてください";
                    }
                    break;
                case R.id.card2://二枚目の裏向きカード選択
                    // クリック処理
                    g.click = 2;
                    if (g.turn % 2 == 1) {
                        g.temp = g.p1_card2;
                    } else {
                        g.temp = g.p2_card2;
                    }
                    //画像を一番上に重ね合わせる
                    p.removeView(g.temp);
                    p.addView(g.temp);
                    setCard();
                    reverseCard1.setX(-1000);
                    reverseCard2.setX(-1000);
                    if(g.temp == scholar1 || g.temp == scholar2 || g.temp == hero){
                        g.click = 4;
                        //ゲームマスターの言葉を更新
                        g.gm = "英雄の効果<転生>/山札をタッチしてください";
                    }
                    break;
                default:
                    break;
            }
            g.turn++;//元のターンにもどす
            Display();
        } else if(g.click == 4) {//賢者・英雄の効果発動時
            g.click = 2;
            g.turn++;
            if (g.list.size() > 0) { //山札があれば
                if (v.getId() == R.id.deck) { //山札タッチする
                    if (g.turn % 2 == 1) {
                        //賢者・英雄が捨てられたとき、残りの手札も捨てる
                        if(g.temp == scholar1 || g.temp == scholar2) {
                            g.p1_card1 = g.player1_cards.get(0);
                            g.temp = g.p1_card1;
                            //画像を一番上に重ね合わせる
                            p.removeView(g.temp);
                            p.addView(g.temp);
                            setCard();
                            //1Pがカードを1枚ドロー
                            g.player1_cards.add(g.list.get(0));
                            g.list.remove(0);
                            //1Pの手札を取得
                            g.p1_card1 = g.player1_cards.get(0);
                        } else if(g.temp == hero){
                            g.p1_card1 = g.player1_cards.get(0);
                            g.temp = g.p1_card1;
                            //画像を一番上に重ね合わせる
                            p.removeView(g.temp);
                            p.addView(g.temp);
                            setCard();
                            //転生札を取得
                            g.player1_cards.add(g.reincarnationCard);
                            //1Pの手札を取得
                            g.p1_card1 = g.player1_cards.get(0);
                        }
                    } else {
                        //賢者・英雄が捨てられたとき、残りの手札も捨てる
                        if (g.temp == scholar1 || g.temp == scholar2) {
                            g.p2_card1 = g.player2_cards.get(0);
                            g.temp = g.p2_card1;
                            //画像を一番上に重ね合わせる
                            p.removeView(g.temp);
                            p.addView(g.temp);
                            setCard();
                            //2Pがカードを1枚ドロー
                            g.player2_cards.add(g.list.get(0));
                            g.list.remove(0);
                            //2Pの手札を取得
                            g.p2_card1 = g.player2_cards.get(0);
                        } else if(g.temp == hero){
                            g.p2_card1 = g.player2_cards.get(0);
                            g.temp = g.p2_card1;
                            //画像を一番上に重ね合わせる
                            p.removeView(g.temp);
                            p.addView(g.temp);
                            setCard();
                            //転生札を取得
                            g.player2_cards.add(g.reincarnationCard);
                            //2Pの手札を取得
                            g.p2_card1 = g.player2_cards.get(0);
                        }
                    }
                    g.turn++;//元のターンにもどす
                }
                g.gm = "山札をタッチしてターンを終了してください";

                //各カードを表示
                juvenile1.setX(g.juvenile1X);
                juvenile1.setY(g.juvenile1Y);
                juvenile2.setX(g.juvenile2X);
                juvenile2.setY(g.juvenile2Y);
                soldier1.setX(g.soldier1X);
                soldier1.setY(g.soldier1Y);
                soldier2.setX(g.soldier2X);
                soldier2.setY(g.soldier2Y);
                fortuneteller1.setX(g.fortuneteller1X);
                fortuneteller1.setY(g.fortuneteller1Y);
                fortuneteller2.setX(g.fortuneteller2X);
                fortuneteller2.setY(g.fortuneteller2Y);
                maiden1.setX(g.maiden1X);
                maiden1.setY(g.maiden1Y);
                maiden2.setX(g.maiden2X);
                maiden2.setY(g.maiden2Y);
                death1.setX(g.death1X);
                death1.setY(g.death1Y);
                death2.setX(g.death2X);
                death2.setY(g.death2Y);
                noble1.setX(g.noble1X);
                noble1.setY(g.noble1Y);
                noble2.setX(g.noble2X);
                noble2.setY(g.noble2Y);
                scholar1.setX(g.scholar1X);
                scholar1.setY(g.scholar1Y);
                scholar2.setX(g.scholar2X);
                scholar2.setY(g.scholar2Y);
                spirit1.setX(g.spirit1X);
                spirit1.setY(g.spirit1Y);
                spirit2.setX(g.spirit2X);
                spirit2.setY(g.spirit2Y);
                emperor.setX(g.emperorX);
                emperor.setY(g.emperorY);
                hero.setX(g.heroX);
                hero.setY(g.heroY);

                //手札を表示
                if (g.turn % 2 == 1) { //1Pのターン時
                    g.p1_card1.setX(g.p1_card1X);
                    g.p1_card1.setY(g.p1_card1Y);
                } else {
                    g.p2_card1.setX(g.p2_card1X);
                    g.p2_card1.setY(g.p2_card1Y);
                }
            } else {
                g.gm = "山札がないのでカード効果無効";
            }
        }

        //ゲームマスターの言葉を更新
        gamemaster.setText(g.gm);

    }

    public void finalButtle() {
        if (g.list.size() == 0) { //山札がなくなった時
            if (g.player1_cards.size() == 1 && g.player2_cards.size() == 1) {
                continue_game = false;
            }
        }
        if (!continue_game) { //勝敗判定
            //1Pの手札を取得
            g.p1_card1 = g.player1_cards.get(0);
            //2Pの手札を取得
            g.p2_card1 = g.player2_cards.get(0);
            //カード名をカードナンバーに変更
            Integer p1_num = g.card_Map.get(g.p1_card1);
            Integer p2_num = g.card_Map.get(g.p2_card1);
            //switch文で勝敗判定
            switch (p1_num.compareTo(p2_num)) {
                case 1://プレイヤーの手札のカードの数字がCPUの手札のカードの数字より大きい場合
                    player_win_counter = 1;
                    break;
                case 0://プレイヤーの手札のカードの数字とCPUの手札のカードの数字が同じ場合
                    player_win_counter = 2;
                    break;
                case -1://プレイヤーの手札のカードの数字がCPUの手札のカードの数字より小さい場合
                    player_win_counter = 3;
                    break;
            }
            // 結果画面へ
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("WINNER", player_win_counter);
            startActivity(intent);
        }
    }

    //カードを表示するメソッド，占い師の効果もあり
    public void Display() {
        //各カードを表示
        juvenile1.setX(g.juvenile1X);
        juvenile1.setY(g.juvenile1Y);
        juvenile2.setX(g.juvenile2X);
        juvenile2.setY(g.juvenile2Y);
        soldier1.setX(g.soldier1X);
        soldier1.setY(g.soldier1Y);
        soldier2.setX(g.soldier2X);
        soldier2.setY(g.soldier2Y);
        fortuneteller1.setX(g.fortuneteller1X);
        fortuneteller1.setY(g.fortuneteller1Y);
        fortuneteller2.setX(g.fortuneteller2X);
        fortuneteller2.setY(g.fortuneteller2Y);
        maiden1.setX(g.maiden1X);
        maiden1.setY(g.maiden1Y);
        maiden2.setX(g.maiden2X);
        maiden2.setY(g.maiden2Y);
        death1.setX(g.death1X);
        death1.setY(g.death1Y);
        death2.setX(g.death2X);
        death2.setY(g.death2Y);
        noble1.setX(g.noble1X);
        noble1.setY(g.noble1Y);
        noble2.setX(g.noble2X);
        noble2.setY(g.noble2Y);
        scholar1.setX(g.scholar1X);
        scholar1.setY(g.scholar1Y);
        scholar2.setX(g.scholar2X);
        scholar2.setY(g.scholar2Y);
        spirit1.setX(g.spirit1X);
        spirit1.setY(g.spirit1Y);
        spirit2.setX(g.spirit2X);
        spirit2.setY(g.spirit2Y);
        emperor.setX(g.emperorX);
        emperor.setY(g.emperorY);
        hero.setX(g.heroX);
        hero.setY(g.heroY);

        //手札を表示
        if (g.turn % 2 == 1) { //1Pのターン時
            g.p1_card1.setX(g.p1_card1X);
            g.p1_card1.setY(g.p1_card1Y);
            g.p1_card2.setX(g.p1_card2X);
            g.p1_card2.setY(g.p1_card2Y);
            if (fortuneteller_counter) {
                //2Pの手札を表示
                g.p2_card1.setX(g.p2_card1X);
                g.p2_card1.setY(g.p2_card1Y);
            }
        } else if (g.turn % 2 == 0) { //2Pのターン時
            g.p2_card1.setX(g.p2_card1X);
            g.p2_card1.setY(g.p2_card1Y);
            g.p2_card2.setX(g.p2_card2X);
            g.p2_card2.setY(g.p2_card2Y);
            if (fortuneteller_counter) {
                //1Pの手札を表示
                g.p1_card1.setX(g.p1_card1X);
                g.p1_card1.setY(g.p1_card1Y);
            }
        }
        fortuneteller_counter = false;
    }

    public void DisplayReverseCard() {
        //各カードを表示
        juvenile1.setX(g.juvenile1X);
        juvenile1.setY(g.juvenile1Y);
        juvenile2.setX(g.juvenile2X);
        juvenile2.setY(g.juvenile2Y);
        soldier1.setX(g.soldier1X);
        soldier1.setY(g.soldier1Y);
        soldier2.setX(g.soldier2X);
        soldier2.setY(g.soldier2Y);
        fortuneteller1.setX(g.fortuneteller1X);
        fortuneteller1.setY(g.fortuneteller1Y);
        fortuneteller2.setX(g.fortuneteller2X);
        fortuneteller2.setY(g.fortuneteller2Y);
        maiden1.setX(g.maiden1X);
        maiden1.setY(g.maiden1Y);
        maiden2.setX(g.maiden2X);
        maiden2.setY(g.maiden2Y);
        death1.setX(g.death1X);
        death1.setY(g.death1Y);
        death2.setX(g.death2X);
        death2.setY(g.death2Y);
        noble1.setX(g.noble1X);
        noble1.setY(g.noble1Y);
        noble2.setX(g.noble2X);
        noble2.setY(g.noble2Y);
        scholar1.setX(g.scholar1X);
        scholar1.setY(g.scholar1Y);
        scholar2.setX(g.scholar2X);
        scholar2.setY(g.scholar2Y);
        spirit1.setX(g.spirit1X);
        spirit1.setY(g.spirit1Y);
        spirit2.setX(g.spirit2X);
        spirit2.setY(g.spirit2Y);
        emperor.setX(g.emperorX);
        emperor.setY(g.emperorY);
        hero.setX(g.heroX);
        hero.setY(g.heroY);

        //手札を表示
        if (g.turn % 2 == 1) { //1Pのターン時
            reverseCard1.setX(p1_hand1X);
            reverseCard1.setY(p1_hand1Y);
            reverseCard2.setX(p1_hand2X);
            reverseCard2.setY(p1_hand2Y);
        } else if (g.turn % 2 == 0) { //2Pのターン時
            reverseCard1.setX(p2_hand1X);
            reverseCard1.setY(p2_hand1Y);
            reverseCard2.setX(p2_hand2X);
            reverseCard2.setY(p2_hand2Y);
        }
    }

    //Draw時に使うメソッド
    public void Draw() {
        if (g.turn % 2 == 1) { //1Pのターン時
            //1Pがカードを1枚ドロー
            g.player1_cards.add(g.list.get(0));
            g.list.remove(0);
            //1Pの手札を取得
            g.p1_card1 = g.player1_cards.get(0);
            g.p1_card2 = g.player1_cards.get(1);
            //1Pの手札のカード枠にセット
            g.p1_card1X = p1_hand1X;
            g.p1_card1Y = p1_hand1Y;
            g.p1_card2X = p1_hand2X;
            g.p1_card2Y = p1_hand2Y;
        } else { //2Pのターン時
            //2Pがカードを1枚ドロー
            g.player2_cards.add(g.list.get(0));
            g.list.remove(0);
            //2Pの手札を取得
            g.p2_card1 = g.player2_cards.get(0);
            g.p2_card2 = g.player2_cards.get(1);
            //2Pの手札のカード枠にセット
            g.p2_card1X = p2_hand1X;
            g.p2_card1Y = p2_hand1Y;
            g.p2_card2X = p2_hand2X;
            g.p2_card2Y = p2_hand2Y;
        }
        //ゲームマスターの言葉を更新
        g.gm = "使用するカードを選択してください";
    }

    //g.tempを送ればそのカードを手札から墓地における
    public void setCard() { //選択されたカードを手札から墓地へ置く
        if (g.turn % 2 == 1) { //1Pのターン時
            //墓地の空白のカード枠のX座標を60ずらす
            g.discard1X += 60;
            g.player1_cards.remove(g.temp);
            if (g.p1_card1 == g.temp) {
                //1Pの墓地のカード枠にセット
                g.p1_card1X = g.discard1X;
                g.p1_card1Y = g.discard1Y;
            } else if (g.p1_card2 == g.temp) {
                //1Pの墓地のカード枠にセット
                g.p1_card2X = g.discard1X;
                g.p1_card2Y = g.discard1Y;
            }
            if (g.temp == juvenile1) {
                g.juvenile1X = g.discard1X;
                g.juvenile1Y = g.discard1Y;
            } else if (g.temp == juvenile2) {
                g.juvenile2X = g.discard1X;
                g.juvenile2Y = g.discard1Y;
            } else if (g.temp == soldier1) {
                g.soldier1X = g.discard1X;
                g.soldier1Y = g.discard1Y;
            } else if (g.temp == soldier2) {
                g.soldier2X = g.discard1X;
                g.soldier2Y = g.discard1Y;
            } else if (g.temp == fortuneteller1) {
                g.fortuneteller1X = g.discard1X;
                g.fortuneteller1Y = g.discard1Y;
            } else if (g.temp == fortuneteller2) {
                g.fortuneteller2X = g.discard1X;
                g.fortuneteller2Y = g.discard1Y;
            } else if (g.temp == maiden1) {
                g.maiden1X = g.discard1X;
                g.maiden1Y = g.discard1Y;
            } else if (g.temp == maiden2) {
                g.maiden2X = g.discard1X;
                g.maiden2Y = g.discard1Y;
            } else if (g.temp == death1) {
                g.death1X = g.discard1X;
                g.death1Y = g.discard1Y;
            } else if (g.temp == death2) {
                g.death2X = g.discard1X;
                g.death2Y = g.discard1Y;
            } else if (g.temp == noble1) {
                g.noble1X = g.discard1X;
                g.noble1Y = g.discard1Y;
            } else if (g.temp == noble2) {
                g.noble2X = g.discard1X;
                g.noble2Y = g.discard1Y;
            }else if (g.temp == scholar1) {
                g.scholar1X = g.discard1X;
                g.scholar1Y = g.discard1Y;
            } else if (g.temp == scholar2) {
                g.scholar2X = g.discard1X;
                g.scholar2Y = g.discard1Y;
            } else if (g.temp == spirit1) {
                g.spirit1X = g.discard1X;
                g.spirit1Y = g.discard1Y;
            } else if (g.temp == spirit2) {
                g.spirit2X = g.discard1X;
                g.spirit2Y = g.discard1Y;
            } else if (g.temp == emperor) {
                g.emperorX = g.discard1X;
                g.emperorY = g.discard1Y;
            } else if (g.temp == hero) {
                g.heroX = g.discard1X;
                g.heroY = g.discard1Y;
            }
        } else { //2Pのターン時
            //墓地の空白のカード枠のX座標を60ずらす
            g.discard2X += 60;
            g.player2_cards.remove(g.temp);
            if (g.p2_card1 == g.temp) {
                //2Pの墓地のカード枠にセット
                g.p2_card1X = g.discard2X;
                g.p2_card1Y = g.discard2Y;
            } else if (g.p2_card2 == g.temp) {
                //1Pの墓地のカード枠にセット
                g.p2_card2X = g.discard2X;
                g.p2_card2Y = g.discard2Y;
            }
            if (g.temp == juvenile1) {
                g.juvenile1X = g.discard2X;
                g.juvenile1Y = g.discard2Y;
            } else if (g.temp == juvenile2) {
                g.juvenile2X = g.discard2X;
                g.juvenile2Y = g.discard2Y;
            } else if (g.temp == soldier1) {
                g.soldier1X = g.discard2X;
                g.soldier1Y = g.discard2Y;
            } else if (g.temp == soldier2) {
                g.soldier2X = g.discard2X;
                g.soldier2Y = g.discard2Y;
            } else if (g.temp == fortuneteller1) {
                g.fortuneteller1X = g.discard2X;
                g.fortuneteller1Y = g.discard2Y;
            } else if (g.temp == fortuneteller2) {
                g.fortuneteller2X = g.discard2X;
                g.fortuneteller2Y = g.discard2Y;
            } else if (g.temp == maiden1) {
                g.maiden1X = g.discard2X;
                g.maiden1Y = g.discard2Y;
            } else if (g.temp == maiden2) {
                g.maiden2X = g.discard2X;
                g.maiden2Y = g.discard2Y;
            } else if (g.temp == death1) {
                g.death1X = g.discard2X;
                g.death1Y = g.discard2Y;
            } else if (g.temp == death2) {
                g.death2X = g.discard2X;
                g.death2Y = g.discard2Y;
            } else if (g.temp == noble1) {
                g.noble1X = g.discard2X;
                g.noble1Y = g.discard2Y;
            } else if (g.temp == noble2) {
                g.noble2X = g.discard2X;
                g.noble2Y = g.discard2Y;
            }else if (g.temp == scholar1) {
                g.scholar1X = g.discard2X;
                g.scholar1Y = g.discard2Y;
            } else if (g.temp == scholar2) {
                g.scholar2X = g.discard2X;
                g.scholar2Y = g.discard2Y;
            } else if (g.temp == spirit1) {
                g.spirit1X = g.discard2X;
                g.spirit1Y = g.discard2Y;
            } else if (g.temp == spirit2) {
                g.spirit2X = g.discard2X;
                g.spirit2Y = g.discard2Y;
            } else if (g.temp == emperor) {
                g.emperorX = g.discard2X;
                g.emperorY = g.discard2Y;
            } else if (g.temp == hero) {
                g.heroX = g.discard2X;
                g.heroY = g.discard2Y;
            }
        }
    }

    public void Juvenile() {
        //少年の効果<革命>
        LABEL:if (g.countJuvenile == 0) {//1枚目の少年が出た時
            g.countJuvenile++;
            g.gm = "少年は力を蓄えている・・・";
        } else if (g.countJuvenile == 1) { //2枚目の少年が出た時
            g.countJuvenile++;
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }

            if (g.list.size() > 0) { //まだ山札にカードがある時
                g.click = 3;
                g.turn++;
                fortuneteller_counter = true;
                Draw();
                Display();
                g.gm = "少年の効果：<革命>/相手に捨てさせるカードを選んでください";
            } else { //山札がもうない時
                g.gm = "山札がないのでカード効果無効";
            }
        }
        maiden_counter = false;
    }

    public void Soldier() {
        LABEL:
        if (g.turn % 2 == 1) { //1Pのターン時
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            // カード選択画面へ
            Intent intent1 = new Intent(getApplicationContext(), Soldier_action.class);
            startActivity(intent1);

            // SharedPreferencesを取得
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            // グローバル変数の値を取得（キーとデフォルト値を指定）
            String designated_num = sharedPreferences.getString("key", "default value");


            //2Pの手札のカード名(ImageView)を取得
            g.p2_card1 = g.player2_cards.get(0);

            //カード名(ImageView)をカードナンバー(Integer)に変更
            Integer p2_num = g.card_Map.get(g.p2_card1);

            if(p2_num.equals(Integer.valueOf(designated_num))){
                player_win_counter = 1;
                // 結果画面へ
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("WINNER", player_win_counter);
                startActivity(intent);
            }
            //g.gm ="残念/山札をタッチしてください";
            g.gm = designated_num;
        } else { //2Pのターン時
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }

            // カード選択画面へ
            Intent intent1 = new Intent(getApplicationContext(), Soldier_action.class);
            startActivity(intent1);

            // SharedPreferencesを取得
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            // グローバル変数の値を取得（キーとデフォルト値を指定）
            String designated_num = sharedPreferences.getString("key", "default value");

            //1Pの手札のカード名(ImageView)を取得
            g.p1_card1 = g.player1_cards.get(0);

            //カード名(ImageView)をカードナンバー(Integer)に変更
            Integer p1_num = g.card_Map.get(g.p1_card1);

            if(p1_num.equals(Integer.valueOf(designated_num))){
                player_win_counter = 3;
                // 結果画面へ
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("WINNER", player_win_counter);
                startActivity(intent);
            }
            //g.gm ="残念/山札をタッチしてください";
            g.gm = designated_num;
        }
        maiden_counter = false;
    }

    public void FortuneTeller() {
        LABEL:
        if (g.turn % 2 == 1) { //1Pのターン時
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            //占い師の効果<透視>
            fortuneteller_counter = true;
            //2Pの手札を取得
            g.p2_card1 = g.player2_cards.get(0);
            //2Pの手札のカード枠にセット
            g.p2_card1X = p2_hand1X;
            g.p2_card1Y = p2_hand1Y;
            //ゲームマスターの言葉を更新
            g.gm = "2Pの手札を公開します";
        } else { //2Pのターン時
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            //占い師の効果<透視>
            fortuneteller_counter = true;
            //1Pの手札を取得
            g.p1_card1 = g.player1_cards.get(0);
            //1Pの手札のカード枠にセット
            g.p1_card1X = p1_hand1X;
            g.p1_card1Y = p1_hand1Y;
            //ゲームマスターの言葉を更新
            g.gm = "1Pの手札を公開します";
        }
        maiden_counter = false;
    }

    public void Maiden() {
        g.gm = "乙女の効果<守護>";
        maiden_counter = true;
    }

    public void Death() {
        //山札にカードがあるかの確認
        LABEL:
        if (g.list.size() > 0) {
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            g.click = 3;
            /*
             * 相手のターンにする
             * 相手にカードを引かせる→相手がカードを一枚ドロー
             * 相手の手札を一枚捨てる→相手がカードを一枚墓地へ置く
             * */
            g.turn++;
            //fortuneteller_counter = true;
            Draw();
            DisplayReverseCard();
            g.gm = "死神の効果：<疫病>/捨てるカードを選んでください";
        } else {
            g.gm = "山札がないのでカード効果無効";
        }
        maiden_counter = false;
    }

    public void Noble() {
        //貴族の効果<対面/対決>
        LABEL:
        if (g.countNoble == 0) {//1枚目の貴族が出た時
            g.countNoble++;
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            fortuneteller_counter = true;
            //1Pの手札を取得
            g.p1_card1 = g.player1_cards.get(0);
            //2Pの手札を取得
            g.p2_card1 = g.player2_cards.get(0);
            //1Pの手札のカード枠にセット
            g.p1_card1X = p1_hand1X;
            g.p1_card1Y = p1_hand1Y;
            //2Pの手札のカード枠にセット
            g.p2_card1X = p2_hand1X;
            g.p2_card1Y = p2_hand1Y;
            //ゲームマスターの言葉を更新
            g.gm = "貴族の効果<対面>：画面を共有してください";
        } else { //2枚目の貴族が出た時
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            //ゲームマスターの言葉を更新
            g.gm = "貴族の効果<対決>";
            //ゲーム終了
            continue_game = false;
            finalButtle();
        }
        maiden_counter = false;
    }

    public void Scholar(){
        g.gm = "賢者は戦いを見守っている";
    }

    public void Spirit() {
        LABEL:
        if (g.turn % 2 == 1) { //1Pのターン時
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            //精霊の効果<交換>
            g.player1_cards.add(g.player2_cards.get(0));
            g.player2_cards.add(g.player1_cards.get(0));
            g.player1_cards.remove(0);
            g.player2_cards.remove(0);
            //1Pのカードリストの1番目を取得
            g.p1_card1 = g.player1_cards.get(0);
            //2Pのカードリストの1番目を取得
            g.p2_card1 = g.player2_cards.get(0);
            //1Pの手札のカード枠にセット
            g.p1_card1X = p1_hand1X;
            g.p1_card1Y = p1_hand1Y;
            //2Pの手札のカード枠にセット
            g.p2_card1X = p2_hand1X;
            g.p2_card1Y = p2_hand1Y;
            //ゲームマスターの言葉を更新
            g.gm = "2Pと手札を交換します";
        } else { //2Pのターン時
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            //精霊の効果<交換>
            g.player1_cards.add(g.player2_cards.get(0));
            g.player2_cards.add(g.player1_cards.get(0));
            g.player1_cards.remove(0);
            g.player2_cards.remove(0);
            //1Pのカードリストの1番目を取得
            g.p1_card1 = g.player1_cards.get(0);
            //2Pのカードリストの1番目を取得
            g.p2_card1 = g.player2_cards.get(0);
            //1Pの手札のカード枠にセット
            g.p1_card1X = p1_hand1X;
            g.p1_card1Y = p1_hand1Y;
            //2Pの手札のカード枠にセット
            g.p2_card1X = p2_hand1X;
            g.p2_card1Y = p2_hand1Y;
            //ゲームマスターの言葉を更新
            g.gm = "1Pと手札を交換します";
        }
        maiden_counter = false;
    }

    public void Emperor() {
        //山札にカードがあるかの確認
        LABEL:
        if (g.list.size() > 0) {
            if (maiden_counter) { //乙女の効果<守護>のチェック
                g.gm = "<[守護]によりカード効果が無効となりました>";
                break LABEL;
            }
            g.click = 3;
            /*
             * 相手のターンにする
             * 相手にカードを引かせる→相手がカードを一枚ドロー
             * 相手の手札を一枚捨てる→相手がカードを一枚墓地へ置く
             * */
            g.turn++;
            fortuneteller_counter = true;
            Draw();
            Display();
            g.gm = "皇帝の効果：<公開処刑>/相手に捨てさせるカードを選んでください";
        } else {
            g.gm = "山札がないのでカード効果無効";
        }
        maiden_counter = false;
    }

    public void publicExecution() {
        if (g.temp == emperor) { //皇帝に公開処刑された時
            if (g.turn % 2 == 1) {
                player_win_counter = 3;
                // 結果画面へ
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("WINNER", player_win_counter);
                startActivity(intent);
            } else {
                player_win_counter = 1;
                // 結果画面へ
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("WINNER", player_win_counter);
                startActivity(intent);
            }
        }
    }
}