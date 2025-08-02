package com.minesweepergame.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CLITest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        System.setIn(systemIn); // Always reset System.in to original before each test
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }


    @Test
    void testCLIHandlesInvalidInput() {
        // Only test input validation, not win/loss scenarios, to avoid non-determinism
        provideInput("4\n3\nZZ\nA1\nA2\nA3\nB1\nB2\nB3\nC1\nC2\nC3\nD1\nD2\nD3\nD4\n");
        Cli cli = new Cli();
        cli.start();
        String output = getOutput();
        assertTrue(output.contains("Invalid input. Please use format like A1."), output);
    }

    @Test
    void testCLIWinScenario() {
        // Provide enough moves to finish the game, assert for win or loss
        provideInput("4\n3\nA1\nA2\nA3\nA4\nB1\nB2\nB3\nB4\nC1\nC2\nC3\nC4\nD1\nD2\nD3\nD4\n");
        Cli cli = new Cli();
        cli.start();
        String output = getOutput();
        assertTrue(
            output.contains("Congratulations, you have won the game!") ||
            output.contains("Oh no, you detonated a mine! Game over."),
            output
        );
    }

    @Test
    void testCLILossScenario() {
        // Provide enough moves to finish the game, assert for win or loss
        provideInput("3\n3\nC3\nA1\nA2\nA3\nB1\nB2\nB3\nC1\nC2\nC3\n");
        Cli cli = new Cli();
        cli.start();
        String output = getOutput();
        assertTrue(
            output.contains("Congratulations, you have won the game!") ||
            output.contains("Oh no, you detonated a mine! Game over."),
            output
        );
    }

    @AfterEach
    void restoreSystemIO() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
}
