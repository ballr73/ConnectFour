package com.coder73.connectfour;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements GameListener {
    Game _game = new Game(this);
    android.support.v7.widget.GridLayout _gridLayout;
    LinearLayout _gameOverLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _gridLayout = findViewById(R.id.gridLayout);
        _gameOverLayout = findViewById(R.id.gameOverLayout);

        resetGame();
        setNextDiscImageView();
    }

    private void setNextDiscImageView() {
        ImageView nextDiscImageView = findViewById(R.id.nextDiscImageView);

        if(_game._disc == Disc.RED) {
         nextDiscImageView.setImageResource(R.drawable.red);
        }
        else {
            nextDiscImageView.setImageResource(R.drawable.yellow);
        }
    }

    @Override
    public void invalidMove() {
        Toast.makeText(this, "Invalid Move.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void gameOver(Disc winner) {
        Toast.makeText(this, "Game Over." + winner.toString() + " wins!", Toast.LENGTH_LONG).show();
        TextView gameOverTextView = findViewById(R.id.gameOverTextView);
        gameOverTextView.setText("Game Over!" + winner.toString() + " wins!");

        _gameOverLayout.setVisibility(View.VISIBLE);
    }

    public void playButtonOnClick(View view) {
        Disc disc = _game._disc;
        int col = Integer.parseInt(view.getTag().toString()); // get column from tag attribute

        if(_game.play(col)) {
            Toast.makeText(this, String.format("%s Playing column %d", disc.toString(), col), Toast.LENGTH_SHORT).show();
            ImageView imageView = (ImageView) view;
            imageView.setTranslationY(-1000f);

            if(disc == Disc.YELLOW) {
                imageView.setImageResource(R.drawable.yellow);
            }
            else {
                imageView.setImageResource(R.drawable.red);
            }

            imageView.animate().translationYBy(1000f).setDuration(300);

            setNextDiscImageView();
        }
    }

    public void newGameButtonOnClick(View view) {

        resetGame();
    }

    private void resetGame() {
        _gameOverLayout.setVisibility(View.INVISIBLE);
        _game.newGame();
        for(int x = 0; x < _gridLayout.getChildCount(); x++) {
            ImageView imageView = (ImageView) _gridLayout.getChildAt(x);
            imageView.setImageResource(R.color.colorPrimary);
        }
    }
}
