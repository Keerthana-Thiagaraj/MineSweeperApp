package com.minesweepergame.cli;

import com.minesweepergame.model.Board;
import com.minesweepergame.model.Cell;
import com.minesweepergame.service.GameService;
import java.util.Scanner;

/**
 * Handles user interaction for the Minesweeper game.
 */
public class Cli {
    private final Scanner scanner = new Scanner(System.in);
    private GameService gameService;

    /**
     * Starts the Minesweeper CLI and game loop.
     */
    public void start() {
        System.out.println("Welcome to Minesweeper!\n");
        int size = promptInt("Enter the size of the grid (e.g. 4 for a 4x4 grid): \n");
        int maxMines = (int) Math.floor(size * size * 0.35);
        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
        int mines;
        do {
            mines = promptInt("");
            if (mines > maxMines) {
                System.out.println("Number of mines cannot exceed 35% of the total squares. Try again.");
            }
        } while (mines > maxMines);
        gameService = new GameService(size, mines);
        System.out.println("\nHere is your minefield:");
        printMinefield(false);
        while (!gameService.isGameOver()) {
            String input = promptSquare();
            int[] pos = parseInput(input, size);
            if (pos == null) {
                System.out.println("Invalid input. Please use format like A1.\n");
                continue;
            }
            int row = pos[0], col = pos[1];
            if (gameService.getBoard().isUncovered(row, col)) {
                System.out.println("This square is already revealed. Choose another.\n");
                continue;
            }
            gameService.uncover(row, col);
            if (gameService.getBoard().isMine(row, col)) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                System.out.println("Press any key to play again...");
                printMinefield(true);
                scanner.nextLine();
                return;
            } else {
                int adj = gameService.getBoard().getGrid()[row][col].getAdjacentMines();
                System.out.println("This square contains " + adj + " adjacent mines. \n");
                System.out.println("Here is your updated minefield:");
                printMinefield(false);
            }
        }
        System.out.println("Congratulations, you have won the game!");
        System.out.println("Press any key to play again...");
        scanner.nextLine();
    }

    private int promptInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + message);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private void printMinefield(boolean revealMines) {
        Board board = gameService.getBoard();
        int size = board.getSize();
        Cell[][] grid = board.getGrid();
        System.out.print("  ");
        for (int c = 1; c <= size; c++) System.out.print(c + " ");
        System.out.println();
        for (int r = 0; r < size; r++) {
            System.out.print((char)('A' + r) + " ");
            for (int c = 0; c < size; c++) {
                if (board.isUncovered(r, c)) {
                    if (board.isMine(r, c)) System.out.print("* ");
                    else {
                        int adj = grid[r][c].getAdjacentMines();
                        System.out.print(adj + " ");
                    }
                } else if (revealMines && board.isMine(r, c)) {
                    System.out.print("* ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private String promptSquare() {
        System.out.print("Select a square to reveal (e.g. A1): ");
        return scanner.next().trim();
    }

    private int[] parseInput(String input, int size) {
        if (input.length() < 2) return null;
        char rowChar = Character.toUpperCase(input.charAt(0));
        if (rowChar < 'A' || rowChar >= 'A' + size) return null;
        String colStr = input.substring(1);
        int col;
        try {
            col = Integer.parseInt(colStr) - 1;
        } catch (NumberFormatException e) {
            return null;
        }
        int row = rowChar - 'A';
        if (col < 0 || col >= size) return null;
        return new int[]{row, col};
    }
}
