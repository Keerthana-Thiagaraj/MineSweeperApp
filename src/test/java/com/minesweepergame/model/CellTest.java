package com.minesweepergame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testCellDefaults() {
        Cell cell = new Cell();
        assertFalse(cell.isMine());
        assertFalse(cell.isUncovered());
        assertEquals(0, cell.getAdjacentMines());
    }

    @Test
    void testSetMine() {
        Cell cell = new Cell();
        cell.setMine(true);
        assertTrue(cell.isMine());
        cell.setMine(false);
        assertFalse(cell.isMine());
    }

    @Test
    void testUncover() {
        Cell cell = new Cell();
        assertFalse(cell.isUncovered());
        cell.uncover();
        assertTrue(cell.isUncovered());
    }

    @Test
    void testSetAdjacentMines() {
        Cell cell = new Cell();
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines());
    }
}
