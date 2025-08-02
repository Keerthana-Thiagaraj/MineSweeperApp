package com.minesweepergame.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BoardTest {
    @Test
    void testMineCountIsCorrect() {
        Board board = new Board(4, 3);
        int mineCount = 0;
        Cell[][] grid = board.getGrid();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (grid[i][j].isMine()) mineCount++;
            }
        }
        assertEquals(3, mineCount);
    }

    @Test
    void testUncoverNonMineCell() {
        Board board = new Board(3, 1);
        Cell[][] grid = board.getGrid();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (!grid[i][j].isMine()) {
                    assertFalse(grid[i][j].isUncovered());
                    board.uncover(i, j);
                    assertTrue(grid[i][j].isUncovered());
                    return;
                }
            }
        }
    }

    @Test
    void testUncoverMineCell() {
        Board board = new Board(3, 1);
        Cell[][] grid = board.getGrid();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (grid[i][j].isMine()) {
                    assertFalse(grid[i][j].isUncovered());
                    board.uncover(i, j);
                    assertTrue(grid[i][j].isUncovered());
                    return;
                }
            }
        }
    }

    @Test
    void testAllNonMineUncoveredWinCondition() {
        Board board = new Board(2, 1);
        Cell[][] grid = board.getGrid();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (!grid[i][j].isMine()) {
                    board.uncover(i, j);
                }
            }
        }
        assertTrue(board.allNonMineUncovered());
    }

    @Test
    void testDisplayBoard() {
        Board board = new Board(2, 1);
        String display = board.display();
        assertNotNull(display);
        // Accept either covered cells or mines in the initial display
        assertTrue(display.contains("."));
    }
}
