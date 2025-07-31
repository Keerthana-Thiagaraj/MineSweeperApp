package com.minesweepergame.service;

import com.minesweepergame.model.Board;

/**
 * Service layer for Minesweeper game logic.
 */
public class GameService {
    private final Board board;
    private boolean gameOver = false;
    private boolean win = false;

    /**
     * Constructs a new GameService with the given board size and mine count.
     */
    public GameService(int size, int mineCount) {
        this.board = new Board(size, mineCount);
    }

    /**
     * Uncovers a cell and updates game state.
     */
    public void uncover(int row, int col) {
        if (gameOver || board.isUncovered(row, col)) return;
        if (board.isMine(row, col)) {
            board.uncover(row, col);
            gameOver = true;
            win = false;
        } else {
            board.uncover(row, col);
            if (board.allNonMineUncovered()) {
                gameOver = true;
                win = true;
            }
        }
    }

    /**
     * Returns true if the game is over.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns true if the player has won.
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Returns the game board.
     */
    public Board getBoard() {
        return board;
    }
}

