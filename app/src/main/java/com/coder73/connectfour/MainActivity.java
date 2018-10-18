package com.coder73.connectfour;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements GameListener {
    Game _game = new Game(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void invalid() {
        //invalid play
    }

    @Override
    public Disc winner() {
        //winner
        return null;
    }
}
