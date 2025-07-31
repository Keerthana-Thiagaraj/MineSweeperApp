package com.minesweepergame.model;

import java.util.Random;
import com.minesweepergame.model.Cell;

/**
 * Represents the Minesweeper game board and manages mine placement and uncovering logic.
 */
public class Board {
    private final int size;
    private final int mineCount;
    private final Cell[][] grid;
    private boolean[][] visited;

    /**
     * Constructs a new Board with the given size and mine count.
     * @param size Board size (NxN)
     * @param mineCount Number of mines
     */
    public Board(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.grid = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
        placeMines();
        calculateAdjacents();
    }

    /**
     * Randomly places mines on the board.
     */
    private void placeMines() {
        Random rand = new Random();
        int placed = 0;
        while (placed < mineCount) {
            int r = rand.nextInt(size);
            int c = rand.nextInt(size);
            if (!grid[r][c].isMine()) {
                grid[r][c].setMine(true);
                placed++;
            }
        }
    }

    /**
     * Calculates the number of adjacent mines for each cell.
     */
    private void calculateAdjacents() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!grid[i][j].isMine()) {
                    grid[i][j].setAdjacentMines(countAdjacentMines(i, j));
                }
            }
        }
    }

    /**
     * Counts adjacent mines for a given cell.
     */
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int r = row + dr, c = col + dc;
                if (r >= 0 && r < size && c >= 0 && c < size && grid[r][c].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Uncovers a cell and recursively uncovers adjacent empty cells.
     * @return true if uncovering was successful
     */
    public boolean uncover(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size || grid[row][col].isUncovered()) {
            return false;
        }
        visited = new boolean[size][size];
        uncoverRecursive(row, col);
        return true;
    }

    private void uncoverRecursive(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size || visited[row][col] || grid[row][col].isUncovered()) {
            return;
        }
        grid[row][col].uncover();
        visited[row][col] = true;
        if (grid[row][col].getAdjacentMines() == 0 && !grid[row][col].isMine()) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr != 0 || dc != 0) {
                        uncoverRecursive(row + dr, col + dc);
                    }
                }
            }
        }
    }

    public boolean isMine(int row, int col) {
        return grid[row][col].isMine();
    }

    public boolean isUncovered(int row, int col) {
        return grid[row][col].isUncovered();
    }

    /**
     * Checks if all non-mine cells are uncovered (win condition).
     */
    public boolean allNonMineUncovered() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!grid[i][j].isMine() && !grid[i][j].isUncovered()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a string representation of the board for display.
     * @param revealMines If true, reveals all mines
     */
    public String display(boolean revealMines) {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int c = 0; c < size; c++) sb.append(c + " ");
        sb.append("\n");
        for (int r = 0; r < size; r++) {
            sb.append(r + " | ");
            for (int c = 0; c < size; c++) {
                if (grid[r][c].isUncovered()) {
                    if (grid[r][c].isMine()) sb.append("*");
                    else if (grid[r][c].getAdjacentMines() > 0) sb.append(grid[r][c].getAdjacentMines());
                    else sb.append(" ");
                } else if (revealMines && grid[r][c].isMine()) {
                    sb.append("*");
                } else {
                    sb.append(".");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the board (hides mines).
     */
    public String display() {
        return display(false);
    }

    public int getSize() {
        return size;
    }

    // Add getter for grid
    public Cell[][] getGrid() {
        return grid;
    }
}
