package com.coder73.connectfour;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements GameListener {
    SoundPool _soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    Game _game = new Game(this);
    android.support.v7.widget.GridLayout _gridLayout;
    LinearLayout _gameOverLayout;
    private int _dropSoundId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _dropSoundId = _soundPool.load(this, R.raw.drop, 1);
        _gridLayout = findViewById(R.id.gridLayout);
        _gameOverLayout = findViewById(R.id.gameOverLayout);

        resetGame();
        setNextDiscImageView();
    }

    private void setNextDiscImageView() {
        ImageView nextDiscImageView = findViewById(R.id.nextDiscImageView);

        if(_game.getDisc() == Disc.RED) {
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

        updateScore();

        Log.i(this.getClass().getName(),  "Game Over." + winner.toString() + " wins!");

        TextView gameOverTextView = findViewById(R.id.gameOverTextView);
        gameOverTextView.setText("Game Over! " + winner.toString() + " wins!");

        _gameOverLayout.setAlpha(0f);
        _gameOverLayout.setVisibility(View.VISIBLE);
        _gameOverLayout.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null);
    }

    private void updateScore() {
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(String.format("Score: Red %d, Yellow %d", _game.getPlayer1Score(), _game.getPlayer2Score()));
    }

    public void playButtonOnClick(View view) {

        Disc disc = _game.getDisc();
        int col = Integer.parseInt(view.getTag().toString()); // get column from tag attribute

        int row = _game.play(col);

        if(row != -1) {

            Log.i(this.getClass().getName(), String.format("%s Playing column %d", disc.toString(), col));

            ImageView imageView = (ImageView) _gridLayout.getChildAt((_gridLayout.getColumnCount() * row) + col);
            imageView.setTranslationY(-1000f);

            if(disc == Disc.YELLOW) {
                imageView.setImageResource(R.drawable.yellow);
            }
            else {
                imageView.setImageResource(R.drawable.red);
            }

            imageView.animate().translationYBy(1000f).setDuration(300);
            _soundPool.play(_dropSoundId, 1, 1, 0, 0, 1);
            setNextDiscImageView();
        }
    }

    public void newGameButtonOnClick(View view) {
        resetGame();
    }

    private void resetGame() {
        _gameOverLayout.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        _gameOverLayout.setVisibility(View.INVISIBLE);
                    }
                });

        _game.newGame();
        for(int x = 0; x < _gridLayout.getChildCount(); x++) {
            ImageView imageView = (ImageView) _gridLayout.getChildAt(x);
            imageView.setImageResource(R.color.colorPrimary);
        }
    }

    public void resetScoreButtonClick(View view) {
        _game.resetScore();
        updateScore();
    }
}
