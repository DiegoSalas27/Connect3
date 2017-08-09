package com.example.android.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red

    int activePlayer = 0;

    boolean gameActive = true;

    // 2 means unplayed

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2},
                                {3, 4, 5},
                                {6, 7, 8},
                                {0, 3, 6},
                                {1, 4, 7},
                                {2, 5, 8},
                                {0, 4, 8},
                                {2, 4, 6}};

    TextView tvGanador;
    Button btnReset;
    LinearLayout playAgainLayout;

    public void dropIn(View view) {
        //we use the element that was tapped on
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive){

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if(activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else{
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPosition : winningPositions){

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                   gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                   gameState[winningPosition[0]] != 2){

                    //someone has won

                    if(gameState[winningPosition[0]] == 0) {

                        gameActive = false;
                        playAgainLayout.setVisibility(View.VISIBLE);
                        tvGanador.setText("Yellow player won!");
                    }
                    else
                    {
                        gameActive = false;
                        playAgainLayout.setVisibility(View.VISIBLE);
                        tvGanador.setText("Red player won!");
                    }
                }
                else{

                    boolean gameIsOver = true;

                    for(int counterState : gameState){

                        if(counterState == 2){
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver){
                        gameActive = false;
                        playAgainLayout.setVisibility(View.VISIBLE);
                        tvGanador.setText("It's a draw!");
                    }
                }
            }
        }


    }
    public void resetGame(View view){

        playAgainLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        tvGanador = (TextView) findViewById(R.id.tvGanador);
        btnReset = (Button) findViewById(R.id.btnReset);

    }
}
