package com.minesweepergame;

import com.minesweepergame.cli.Cli;

/**
 * Entry point for the MinesweeperGame CLI application.
 */
public class MinesweeperMain {
    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.start();
    }
}
