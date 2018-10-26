package com.coder73.connectfour;

public class Game {
    private final GameListener _listener;
    private Disc[][] _board;
    Disc _disc = Disc.NONE;
    Disc _winner = Disc.NONE;
    boolean _gameOver = false;
    public Disc getDisc() {

        return _disc;
    }

    public Disc getWinner() {
        return _winner;
    }

    public Game(GameListener listener) {
        _listener = listener;
    }

    public void newGame(){
        _board = new Disc[7][6];
        _disc = Disc.RED;
        _winner = Disc.NONE;
        _gameOver = false;

        for(int col = 0; col < 7; col++){
            for(int row = 0; row < 6; row ++) {
                _board[col][row] = Disc.NONE;
            }
        }
    }

    public boolean play(int column) {

        boolean result = false;

        if(_gameOver) {
            if(_listener != null) {
                _listener.gameOver(_winner);
            }
            return false;
        }

        for (int row = 0; row < 6; row ++) {
            if(_board[column][row] == Disc.NONE){

                _board[column][row] = _disc;

                if(isWinner(column,row)) {
                    _winner = _disc;
                    if(_listener != null) {
                        _listener.gameOver(_winner);
                        _gameOver = true;
                        result = true;
                    }
                }
                else {

                    if (_disc == Disc.RED) {
                        _disc = Disc.YELLOW;
                    } else {
                        _disc = Disc.RED;
                    }
                    result = true;
                }
                break;
            }
        }

        if (result == false) {
            if(_listener != null) {
                _listener.invalidMove();
            }
        }

        return result;
    }

    private boolean isWinner(int column, int row) {
        boolean result = checkRow(row) || checkColumn(column) || checkDiag(column, row);
        return result;
    }

    private boolean checkDiag(int row, int column) {
        return false;
    }

    private boolean checkColumn(int col) {
        int count = 0;
        for (int row = 0; row < 6; row++) {
            if(_board[col][row] == _disc) {
                count++;
                if(count ==4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean checkRow(int row) {
        int count = 0;
        for (int col = 0; col < 7; col++ ){
            if(_board[col][row] == _disc) {
                count++;
                if(count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}
