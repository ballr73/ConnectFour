package com.coder73.connectfour;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameTests {
    @Test
    public void WhenPlayDiscIsCorrect() {
        //Given
        Game game = new Game(null);

        //when
        game.newGame();
        game.play(0);
        Disc disc1 = game.getDisc();
        game.play(0);
        Disc disc2 = game.getDisc();

        //Then
        assertEquals(Disc.YELLOW, disc1);
        assertEquals(Disc.RED, game.getDisc());
    }
    @Test
    public void WhenColumnIsFullThenDiscIsSame() {

        //Given
        Game game = new Game(null);
        game.newGame();
        for(int x = 0; x < 6; x++) {
            game.play(0);
        }

        Disc expected = game.getDisc();

        //When

        game.play(0);
        Disc actual = game.getDisc();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void WhenRedHasFourInColumnWinnerIsRed() {
        //Given
        Game game = new Game(null);
        game.newGame();
        game.play(0);
        game.play(1);
        game.play(0);
        game.play(1);
        game.play(0);
        game.play(1);
        game.play(0);

        //when
        Disc actual = game.getWinner();

        //Then
        assertEquals(Disc.RED, actual);

    }

    @Test
    public void WhenYellowHasFourInColumnWinnerIsYellow() {
        //Given
        Game game = new Game(null);
        game.newGame();
        game.play(0);
        game.play(1);
        game.play(0);
        game.play(1);
        game.play(0);
        game.play(1);
        game.play(2);
        game.play(1);

        //when
        Disc actual = game.getWinner();

        //Then
        assertEquals(Disc.YELLOW, actual);

    }
    @Test
    public void WhenRedHasFourInRowWinnerIsRed() {
        //Given
        Game game = new Game(null);
        game.newGame();
        game.play(0);
        game.play(0);
        game.play(1);
        game.play(0);
        game.play(2);
        game.play(0);
        game.play(3);

        //when
        Disc actual = game.getWinner();

        //Then
        assertEquals(Disc.RED, actual);

    }
    @Test
    public void WhenYellowHasFourInRowWinnerIsYellow() {
        //Given
        Game game = new Game(null);
        game.newGame();
        game.play(0);
        game.play(1);
        game.play(1);
        game.play(2);
        game.play(2);
        game.play(3);
        game.play(3);
        game.play(4);

        //when
        Disc actual = game.getWinner();

        //Then
        assertEquals(Disc.YELLOW, actual);

    }

    @Test
    public void WhenRedHasFourInDiagWinnerIsRed() {
        //Given
        Game game = new Game(null);
        game.newGame();
        game.play(0);
        game.play(1);
        game.play(1);
        game.play(2);
        game.play(3);
        game.play(2);
        game.play(2);
        game.play(3);
        game.play(3);
        game.play(4);
        game.play(3);

        //when
        Disc actual = game.getWinner();

        //Then
        assertEquals(Disc.RED, actual);
    }
    @Test
    public void WhenYellowHasFourInDiagWinnerIsYellow() {
        //Given
        Game game = new Game(null);
        game.newGame();
        game.play(1);
        game.play(0);
        game.play(2);
        game.play(1);
        game.play(2);
        game.play(2);
        game.play(3);
        game.play(3);
        game.play(3);
        game.play(3);

        //when
        Disc actual = game.getWinner();

        //Then
        assertEquals(Disc.YELLOW, actual);
    }

    @Test
    public void WhenRedHasFourInDiagDownWinnerIsRed() {
        //Given
        Game game = new Game(null);
        game.newGame();
        game.play(3);
        game.play(2);
        game.play(2);
        game.play(1);
        game.play(1);
        game.play(0);
        game.play(1);
        game.play(0);
        game.play(0);
        game.play(1);
        game.play(0);

        //when
        Disc actual = game.getWinner();

        //Then
        assertEquals(Disc.RED, actual);
    }
}