package com.coder73.connectfour;

interface GameListener {

    void invalidMove();
    void gameOver(Disc winner);
}
