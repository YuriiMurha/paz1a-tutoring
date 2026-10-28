package sk.upjs.paz1a.tutoring.recap.exercise06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridBasicsTest {

    // ---------- sumAll ----------

    @Test
    void sumOfRectangularGrid() {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6}
        };
        assertEquals(21, GridBasics.sumAll(grid));
    }

    @Test
    void sumOfJaggedGrid() {
        assertEquals(6, GridBasics.sumAll(new int[][]{{1, 2}, {3}, {}}),
                "Rows can have different lengths - use grid[r].length");
    }

    @Test
    void sumWithNegativeNumbers() {
        assertEquals(-4, GridBasics.sumAll(new int[][]{{-1, -2}, {3, -4}}));
    }

    @Test
    void sumOfOneByOneGrid() {
        assertEquals(7, GridBasics.sumAll(new int[][]{{7}}));
    }

    @Test
    void gridWithoutRowsSumsToZero() {
        assertEquals(0, GridBasics.sumAll(new int[0][]));
    }

    @Test
    void sumAllRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> GridBasics.sumAll(null));
    }

    // ---------- rowSums ----------

    @Test
    void rowSumsOfRectangularGrid() {
        assertArrayEquals(new int[]{6, 15}, GridBasics.rowSums(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    void rowSumsOfJaggedGrid() {
        assertArrayEquals(new int[]{3, 3, 0}, GridBasics.rowSums(new int[][]{{1, 2}, {3}, {}}));
    }

    @Test
    void rowSumsOfGridWithoutRowsIsEmpty() {
        int[] sums = GridBasics.rowSums(new int[0][]);
        assertNotNull(sums, "Return an empty array, not null");
        assertEquals(0, sums.length);
    }

    @Test
    void rowSumsRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> GridBasics.rowSums(null));
    }

    // ---------- columnSums ----------

    @Test
    void columnSumsOfRectangularGrid() {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6}
        };
        assertArrayEquals(new int[]{5, 7, 9}, GridBasics.columnSums(grid));
    }

    @Test
    void columnSumsOfSingleRow() {
        assertArrayEquals(new int[]{4, 5}, GridBasics.columnSums(new int[][]{{4, 5}}));
    }

    @Test
    void columnSumsOfSingleColumn() {
        assertArrayEquals(new int[]{6}, GridBasics.columnSums(new int[][]{{1}, {2}, {3}}));
    }

    @Test
    void columnSumsOfGridWithoutRowsIsEmpty() {
        int[] sums = GridBasics.columnSums(new int[0][]);
        assertNotNull(sums, "Return an empty array, not null");
        assertEquals(0, sums.length);
    }

    @Test
    void columnSumsRejectsNullAndJaggedGrid() {
        assertThrows(IllegalArgumentException.class, () -> GridBasics.columnSums(null));
        assertThrows(IllegalArgumentException.class, () -> GridBasics.columnSums(new int[][]{{1, 2}, {3}}));
    }

    // ---------- createMultiplicationTable ----------

    @Test
    void multiplicationTableOfSizeThree() {
        int[][] expected = {
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        };
        assertArrayEquals(expected, GridBasics.createMultiplicationTable(3));
    }

    @Test
    void multiplicationTableOfSizeOne() {
        assertArrayEquals(new int[][]{{1}}, GridBasics.createMultiplicationTable(1));
    }

    @Test
    void multiplicationTableOfSizeTenHasAllProducts() {
        int[][] table = GridBasics.createMultiplicationTable(10);
        assertNotNull(table);
        assertEquals(10, table.length);
        for (int r = 0; r < 10; r++) {
            assertEquals(10, table[r].length);
            for (int c = 0; c < 10; c++) {
                assertEquals((r + 1) * (c + 1), table[r][c], "Wrong value at [" + r + "][" + c + "]");
            }
        }
    }

    @Test
    void multiplicationTableRejectsZeroAndNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> GridBasics.createMultiplicationTable(0));
        assertThrows(IllegalArgumentException.class, () -> GridBasics.createMultiplicationTable(-2));
    }
}
