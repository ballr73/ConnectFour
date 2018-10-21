package com.coder73.connectfour;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements GameListener {
    Game _game = new Game(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        _game.newGame();
    }

    public void playButtonOnClick(View view) {
        Disc disc = _game._disc;
        int col = Integer.parseInt(view.getTag().toString());
        Toast.makeText(this, String.format("%s Playing column %d", disc.toString(), col), Toast.LENGTH_SHORT).show();


        if(_game.play(col)) {
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
}
