package sk.upjs.paz1a.tutoring.exercise06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOpsTest {

    // ---------- transpose ----------

    @Test
    void transposeTurnsRowsIntoColumns() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] expected = {
                {1, 4},
                {2, 5},
                {3, 6}
        };
        assertArrayEquals(expected, MatrixOps.transpose(matrix));
    }

    @Test
    void transposeOfSquareMatrix() {
        assertArrayEquals(new int[][]{{1, 3}, {2, 4}}, MatrixOps.transpose(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    void transposeOfSingleRowIsSingleColumn() {
        assertArrayEquals(new int[][]{{1}, {2}, {3}}, MatrixOps.transpose(new int[][]{{1, 2, 3}}));
    }

    @Test
    void transposeOfOneByOneMatrix() {
        assertArrayEquals(new int[][]{{7}}, MatrixOps.transpose(new int[][]{{7}}));
    }

    @Test
    void transposeReturnsNewMatrixAndKeepsInputUnchanged() {
        int[][] matrix = {{1, 2}, {3, 4}};
        int[][] result = MatrixOps.transpose(matrix);
        assertNotNull(result);
        assertNotSame(matrix, result, "Create a new matrix instead of changing the input");
        assertArrayEquals(new int[][]{{1, 2}, {3, 4}}, matrix);
    }

    @Test
    void transposeRejectsNullAndEmptyMatrix() {
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.transpose(null));
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.transpose(new int[0][0]));
    }

    // ---------- maxRowSum ----------

    @Test
    void maxRowSumPicksTheLargestRow() {
        int[][] matrix = {
                {1, 2, 3},   // 6
                {10, 0, -1}, // 9
                {4, 4, 0}    // 8
        };
        assertEquals(9, MatrixOps.maxRowSum(matrix));
    }

    @Test
    void maxRowSumWhenLastRowIsLargest() {
        assertEquals(10, MatrixOps.maxRowSum(new int[][]{{1, 1}, {0, 0}, {5, 5}}));
    }

    @Test
    void maxRowSumWithOnlyNegativeRows() {
        assertEquals(-5, MatrixOps.maxRowSum(new int[][]{{-5, -1}, {-3, -2}}),
                "Start with the sum of the first row, not with 0");
    }

    @Test
    void maxRowSumWithRowsOfDifferentLengths() {
        assertEquals(6, MatrixOps.maxRowSum(new int[][]{{1}, {1, 2, 3}, {}}));
    }

    @Test
    void maxRowSumOfOneByOneMatrix() {
        assertEquals(-4, MatrixOps.maxRowSum(new int[][]{{-4}}));
    }

    @Test
    void maxRowSumOfZeroMatrixIsZero() {
        assertEquals(0, MatrixOps.maxRowSum(new int[2][3]));
    }

    @Test
    void maxRowSumRejectsNullAndEmptyMatrix() {
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.maxRowSum(null));
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.maxRowSum(new int[0][0]));
    }

    // ---------- isSymmetric ----------

    @Test
    void symmetricMatrix() {
        int[][] matrix = {
                {1, 7, 3},
                {7, 4, 5},
                {3, 5, 6}
        };
        assertTrue(MatrixOps.isSymmetric(matrix));
    }

    @Test
    void nonSymmetricMatrix() {
        assertFalse(MatrixOps.isSymmetric(new int[][]{{1, 2}, {3, 1}}));
    }

    @Test
    void matrixDifferingOnlyInTheCornersIsNotSymmetric() {
        int[][] matrix = {
                {1, 0, 9},
                {0, 1, 0},
                {8, 0, 1}
        };
        assertFalse(MatrixOps.isSymmetric(matrix));
    }

    @Test
    void nonSquareMatrixIsNotSymmetric() {
        assertFalse(MatrixOps.isSymmetric(new int[][]{{1, 2, 3}, {2, 1, 4}}));
    }

    @Test
    void oneByOneMatrixIsSymmetric() {
        assertTrue(MatrixOps.isSymmetric(new int[][]{{5}}));
    }

    @Test
    void zeroMatrixIsSymmetric() {
        assertTrue(MatrixOps.isSymmetric(new int[3][3]));
    }

    @Test
    void isSymmetricRejectsNullAndEmptyMatrix() {
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.isSymmetric(null));
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.isSymmetric(new int[0][0]));
    }

    // ---------- createIdentityMatrix ----------

    @Test
    void identityMatrixOfSizeThree() {
        int[][] expected = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        assertArrayEquals(expected, MatrixOps.createIdentityMatrix(3));
    }

    @Test
    void identityMatrixOfSizeOne() {
        assertArrayEquals(new int[][]{{1}}, MatrixOps.createIdentityMatrix(1));
    }

    @Test
    void identityMatrixHasOnesOnlyOnTheDiagonal() {
        int size = 5;
        int[][] identity = MatrixOps.createIdentityMatrix(size);
        assertNotNull(identity);
        assertEquals(size, identity.length);
        for (int row = 0; row < size; row++) {
            assertEquals(size, identity[row].length);
            for (int col = 0; col < size; col++) {
                int expected = (row == col) ? 1 : 0;
                assertEquals(expected, identity[row][col], "Wrong value at [" + row + "][" + col + "]");
            }
        }
    }

    @Test
    void identityMatrixRejectsZeroAndNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.createIdentityMatrix(0));
        assertThrows(IllegalArgumentException.class, () -> MatrixOps.createIdentityMatrix(-3));
    }
}
