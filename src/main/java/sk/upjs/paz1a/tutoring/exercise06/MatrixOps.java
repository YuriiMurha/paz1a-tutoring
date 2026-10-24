package sk.upjs.paz1a.tutoring.exercise06;

/**
 * Exercise 06 - two-dimensional arrays (matrices).
 * <p>
 * A matrix is an array of rows: {@code matrix.length} is the number of rows and
 * {@code matrix[0].length} is the number of columns.
 * <p>
 * This time the input checks are your job too: the {@code @throws} tag of every method says which
 * inputs it must reject with an {@link IllegalArgumentException}.
 */
public class MatrixOps {

    private MatrixOps() {
        // only static methods here, so nobody needs to create a MatrixOps object
    }

    /**
     * Returns a NEW matrix whose rows are the columns of the input. The input is a rectangle (all
     * rows have the same length), so a 2x3 matrix becomes 3x2:
     * <pre>
     * {{1, 2, 3},        {{1, 4},
     *  {4, 5, 6}}   ->    {2, 5},
     *                     {3, 6}}
     * </pre>
     * The input must stay unchanged.
     *
     * @throws IllegalArgumentException if {@code matrix} is null or has no rows
     */
    public static int[][] transpose(int[][] matrix) {
        // TODO: Implement this
        return null;
    }

    /**
     * Returns the largest sum of a single row. Rows may have different lengths (an empty row has
     * sum 0), and all the sums may be negative: {{-5, -1}, {-3, -2}} -> -5.
     *
     * @throws IllegalArgumentException if {@code matrix} is null or has no rows
     */
    public static int maxRowSum(int[][] matrix) {
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns true if the matrix is square and {@code matrix[i][j] == matrix[j][i]} for every
     * {@code i} and {@code j} - in other words, it equals its own transpose.
     * A matrix that is not square is never symmetric.
     *
     * @throws IllegalArgumentException if {@code matrix} is null or has no rows
     */
    public static boolean isSymmetric(int[][] matrix) {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns a NEW {@code size x size} identity matrix: 1 on the main diagonal, 0 everywhere else.
     *
     * @throws IllegalArgumentException if {@code size} is zero or negative
     */
    public static int[][] createIdentityMatrix(int size) {
        // TODO: Implement this
        return null;
    }
}
