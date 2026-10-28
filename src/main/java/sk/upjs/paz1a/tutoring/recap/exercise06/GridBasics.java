package sk.upjs.paz1a.tutoring.recap.exercise06;

/**
 * Recap 06 - two-dimensional arrays.
 * <p>
 * A 2D array is an array of rows: {@code grid[r]} is row r (an int[] of its own) and
 * {@code grid[r][c]} is the value in row r, column c. Rows may have different lengths ("jagged"),
 * unless a method says the grid must be a rectangle. A {@code null} grid is rejected (already written
 * for you); a grid with no rows is a valid input.
 */
public class GridBasics {

    private GridBasics() {
        // only static methods here, so nobody needs to create a GridBasics object
    }

    /**
     * Returns the sum of all values: {{1, 2}, {3}} -> 6. A grid with no rows sums to 0.
     *
     * @throws IllegalArgumentException if grid is null
     */
    public static int sumAll(int[][] grid) {
        requireNonNull(grid);
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns a new array with the sum of every row: {{1, 2}, {3}, {}} -> {3, 3, 0}.
     *
     * @throws IllegalArgumentException if grid is null
     */
    public static int[] rowSums(int[][] grid) {
        requireNonNull(grid);
        // TODO: Implement this
        return null;
    }

    /**
     * Returns a new array with the sum of every column of a rectangular grid:
     * {{1, 2, 3}, {4, 5, 6}} -> {5, 7, 9}. A grid with no rows has no columns (empty array).
     * Hint: here the OUTER loop goes over the columns and the inner loop over the rows.
     *
     * @throws IllegalArgumentException if grid is null or its rows have different lengths
     */
    public static int[] columnSums(int[][] grid) {
        requireRectangular(grid);
        // TODO: Implement this
        return null;
    }

    /**
     * Returns a size x size multiplication table: the value in row r, column c is (r + 1) * (c + 1).
     * createMultiplicationTable(3) -> {{1, 2, 3}, {2, 4, 6}, {3, 6, 9}}.
     *
     * @throws IllegalArgumentException if size is zero or negative
     */
    public static int[][] createMultiplicationTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive, but was " + size);
        }
        // TODO: Implement this
        return null;
    }

    private static void requireNonNull(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("The grid must not be null");
        }
    }

    private static void requireRectangular(int[][] grid) {
        requireNonNull(grid);
        for (int r = 1; r < grid.length; r++) {
            if (grid[r].length != grid[0].length) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }
    }
}
