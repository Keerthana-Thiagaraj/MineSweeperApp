package com.minesweepergame.model;

/**
 * Represents a cell on the Minesweeper board.
 */
public class Cell {
    private boolean isMine;
    private boolean isUncovered;
    private int adjacentMines;

    /**
     * Constructs a new cell (not a mine, not uncovered, 0 adjacent mines).
     */
    public Cell() {
        this.isMine = false;
        this.isUncovered = false;
        this.adjacentMines = 0;
    }

    /**
     * Returns true if this cell is a mine.
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * Sets whether this cell is a mine.
     */
    public void setMine(boolean mine) {
        isMine = mine;
    }

    /**
     * Returns true if this cell is uncovered.
     */
    public boolean isUncovered() {
        return isUncovered;
    }

    /**
     * Uncovers this cell.
     */
    public void uncover() {
        isUncovered = true;
    }

    /**
     * Returns the number of adjacent mines.
     */
    public int getAdjacentMines() {
        return adjacentMines;
    }

    /**
     * Sets the number of adjacent mines.
     */
    public void setAdjacentMines(int count) {
        adjacentMines = count;
    }
}

