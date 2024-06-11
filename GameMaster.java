package com.example.xeno;

import android.app.Application;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//グローバル変数で各値を格納しておくApplication継承クラス
public class GameMaster extends Application {
    // ゲームマスターの言葉を格納する変数
    String gm;

    //1Pの手札のカードの情報の変数
    ImageView p1_card1, p1_card2;
    //2Pの手札のカードの情報の変数
    ImageView p2_card1, p2_card2;
    //1Pの手札の1枚目のカードの変数(実際に置く(X,Y)の座標)
    float p1_card1X, p1_card1Y, p1_card2X, p1_card2Y;
    //2Pの手札の1枚目のカードの変数(実際に置く(X,Y)の座標)
    float p2_card1X, p2_card1Y, p2_card2X, p2_card2Y;

    ImageView temp;

    //1Pの墓地の情報の変数(実際に置く(X,Y)の座標)<空白のカード枠を作ってカードをそこに置くイメージ>
    float discard1X, discard1Y;
    //2Pの墓地の情報の変数(実際に置く(X,Y)の座標)<空白のカード枠を作ってカードをそこに置くイメージ>
    float discard2X, discard2Y;

    // カードの情報の変数(各カード)(X,Y)座標
    static float juvenile1X, juvenile1Y, soldier1X, soldier1Y, fortuneteller1X, fortuneteller1Y,
            maiden1X, maiden1Y, death1X, death1Y, noble1X, noble1Y, scholar1X, scholar1Y, spirit1X, spirit1Y,
            emperorX, emperorY, heroX, heroY;
    static float juvenile2X, juvenile2Y, soldier2X, soldier2Y, fortuneteller2X, fortuneteller2Y,
            maiden2X, maiden2Y, death2X, death2Y, noble2X, noble2Y, scholar2X, scholar2Y, spirit2X, spirit2Y;

    // 状態を表す変数
    static int click = 0;
    static List<ImageView> list = new ArrayList<ImageView>(); //山札のカードリスト
    static List<ImageView> player1_cards = new ArrayList<ImageView>();//1Pのカードリスト
    static List<ImageView> player2_cards = new ArrayList<ImageView>();//2Pのカードリスト
    ImageView reincarnationCard;
    static int turn = 0;
    static int countJuvenile = 0;
    static int countNoble = 0;
    static Map<ImageView, Integer> card_Map = new HashMap<>(); //兵士の効果発動時に使用

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
