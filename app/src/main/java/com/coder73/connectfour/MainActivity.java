package com.coder73.connectfour;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements GameListener {
    Game _game = new Game(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Random random = new Random();
        int col = random.nextInt((6 - 0) + 1) + 0;
        Toast.makeText(this, String.format("%s Playing column %d", _game.getDisc().toString(), col), Toast.LENGTH_SHORT).show();
        _game.play(col);
    }
}
