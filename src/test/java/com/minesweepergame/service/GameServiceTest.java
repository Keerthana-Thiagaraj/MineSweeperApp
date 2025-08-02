package com.minesweepergame.service;

import com.minesweepergame.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    @Test
    void testGameWin() {
        GameService gameService = new GameService(2, 1);;
        Board board = gameService.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (!board.isMine(i, j)) {
                    gameService.uncover(i, j);
                }
            }
        }
        assertTrue(gameService.isGameOver());
        assertTrue(gameService.isWin());
    }

    @Test
    void testGameLoss() {
        GameService gameService = new GameService(2, 1);
        Board board = gameService.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.isMine(i, j)) {
                    gameService.uncover(i, j);
                    assertTrue(gameService.isGameOver());
                    assertFalse(gameService.isWin());
                    return;
                }
            }
        }
    }

    @Test
    void testUncoverAlreadyUncoveredCellDoesNothing() {
        GameService gameService = new GameService(2, 1);
        Board board = gameService.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (!board.isMine(i, j)) {
                    gameService.uncover(i, j);
                    boolean prevGameOver = gameService.isGameOver();
                    boolean prevWin = gameService.isWin();
                    gameService.uncover(i, j);
                    assertEquals(prevGameOver, gameService.isGameOver());
                    assertEquals(prevWin, gameService.isWin());
                    return;
                }
            }
        }
    }

    @Test
    void testGameNotOverInitially() {
        GameService gameService = new GameService(2, 1);
        assertFalse(gameService.isGameOver());
        assertFalse(gameService.isWin());
    }
}
