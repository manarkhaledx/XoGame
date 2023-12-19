package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class XoActivity extends AppCompatActivity {
        TextView playerOneScoreTV;
        TextView playerTwoScoreTV;
        ConstraintLayout mainView;
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_xo);
            playerOneScoreTV=findViewById(R.id.tv_playerOneScore);
            playerTwoScoreTV=findViewById(R.id.tv_playerTwoScore);
            mainView=findViewById(R.id.xo_layout);
        }
        int counter=0;
        int player1score=0;
        int player2score=0;
        public void onPlayerClick(View view){
            Button clickedButton=((Button)view);
            if(!clickedButton.getText().toString().isEmpty())
                return;
            if(counter%2==0){
                clickedButton.setText("X");

                Resources.Theme theme = getTheme();
                int color = getResources().getColor(R.color.x_COLOR, theme);

                clickedButton.setTextColor(color);

            }else {
                clickedButton.setText("O");
                Resources.Theme theme = getTheme();
                int color = getResources().getColor(R.color.o_COLOR, theme);
                clickedButton.setTextColor(color);
            }
            counter++;
            if(checkWinner("X")){
                initBoard();
                player1score++;
                playerOneScoreTV.setText(player1score+"");

            }else if(checkWinner("O")){
                initBoard();
                player2score++;
                playerTwoScoreTV.setText(player2score+"");
            }else if(counter==9){
                initBoard();
            }
        }
    void initBoard() {

        for (int i = 0; i < 9; i++) {
            ((Button) mainView.findViewWithTag(i + "")).setText("");
        }
        counter = 0;
    }


    private boolean checkWinner(String playerSymbol){ //check who won
        for(int i=0;i<9;i+=3){//rows
            if(((Button)mainView.findViewWithTag(""+i))
                    .getText().toString().equals(playerSymbol)
                    && ((Button)mainView.findViewWithTag(""+(i+1)))
                    .getText().toString().equals(playerSymbol)
                    && ((Button)mainView.findViewWithTag(""+(i+2)))
                    .getText().toString().equals(playerSymbol)
            ){
                return true;
            }
        }for(int i=0;i<3;i++){//columns
            if(((Button)mainView.findViewWithTag(""+i))
                    .getText().toString().equals(playerSymbol)
                    && ((Button)mainView.findViewWithTag((i+3)+""))
                    .getText().toString().equals(playerSymbol)
                    && ((Button)mainView.findViewWithTag((i+6)+""))
                    .getText().toString().equals(playerSymbol)
            ){
                return true;
            }
        }if(((Button)mainView.findViewWithTag("0"))//diagonal
                .getText().toString().equals(playerSymbol)
                && ((Button)mainView.findViewWithTag((4)+""))
                .getText().toString().equals(playerSymbol)
                && ((Button)mainView.findViewWithTag((8)+""))
                .getText().toString().equals(playerSymbol)
        ){
            return true;
        }
        return ((Button) mainView.findViewWithTag("2")) //diagonal
                .getText().toString().equals(playerSymbol)
                && ((Button) mainView.findViewWithTag((4) + ""))
                .getText().toString().equals(playerSymbol)
                && ((Button) mainView.findViewWithTag((6) + ""))
                .getText().toString().equals(playerSymbol);
    }
}