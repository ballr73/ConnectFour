package com.coder73.connectfour;

public class Game {

    private final GameListener _listener;
    private Disc[][] _board;
    private Disc _disc = Disc.NONE;
    private Disc _winner = Disc.NONE;
    private boolean _gameOver = false;

    public int getPlayer1Score() {
        return _player1Score;
    }

    public int getPlayer2Score() {
        return _player2Score;
    }

    int _player1Score = 0;
    int _player2Score = 0;

    public Disc getDisc() {

        return _disc;
    }

    Disc getWinner() {
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

    public int play(int column) {

        int result = -1;

        if(_gameOver) {
            if(_listener != null) {
                _listener.gameOver(_winner);
            }
            return -1;
        }

        for (int row = 5; row >= 0; row --) {
            if(_board[column][row] == Disc.NONE){

                _board[column][row] = _disc;
                result = row;
                if(isWinner(column,row)) {
                    _winner = _disc;
                    if(_listener != null) {
                        _listener.gameOver(_winner);
                        _gameOver = true;
                    }
                }
                else {

                    if (_disc == Disc.RED) {
                        _disc = Disc.YELLOW;
                    } else {
                        _disc = Disc.RED;
                    }
                }
                break;
            }
        }

        if (result == -1) {
            if(_listener != null) {
                _listener.invalidMove();
            }
        }

        return result;
    }

    private boolean isWinner(int column, int row) {
        boolean result = checkRow(row) || checkColumn(column) || checkDiagUp(column, row) || checkDiagDown(column, row);
        if(result){
            if(_winner == Disc.RED) {
                _player1Score++;
            }
            else {
                _player2Score++;
            }
        }
        return result;
    }

    private boolean checkDiagUp(int column, int row) {

        int startRow = row + column;
        if(startRow > 5)
            startRow = 5;

        int startColumn = column - (5 - row);
        if(startColumn < 0)
            startColumn = 0;

        int count = 0;
        while (startColumn <= 6 && startRow >= 0) {
            if(_board[startColumn][startRow] == _disc) {
                count++;
                if(count == 4){
                    return true;
                }
            }
            else {
                count = 0;
            }

            startRow--;
            startColumn++;
        }

        return false;
    }

    private boolean checkDiagDown(int column, int row) {
        int startRow = row - column;
        if(startRow < 0)
            startRow = 0;

        int startColumn = column - row;
        if(startColumn < 0)
            startColumn = 0;

        int count = 0;
        while (startColumn <= 6 && startRow <= 5 ) {
            if(_board[startColumn][startRow] == _disc) {
                count++;
                if(count == 4){
                    return true;
                }
            }
            else {
                count = 0;
            }

            startRow++;
            startColumn++;
        }

        return false;
    }

    private boolean checkColumn(int col) {
        int count = 0;
        for (int row = 0; row < 6; row++) {
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

    public void resetScore() {
        _player1Score = 0;
        _player2Score = 0;
    }
}
