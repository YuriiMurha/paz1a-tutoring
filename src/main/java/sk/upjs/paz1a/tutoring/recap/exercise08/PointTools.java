package sk.upjs.paz1a.tutoring.recap.exercise08;

/**
 * Static helpers that work with Point references.
 * <p>
 * Java passes a COPY of every argument to a method. For an object, that copy is a copy of the
 * reference: the method can change the object it points to, but assigning a new object to the
 * parameter changes nothing for the caller. The first test in PointToolsTest shows exactly this.
 */
public class PointTools {

    private PointTools() {
        // only static methods here, so nobody needs to create a PointTools object
    }

    /**
     * Moves every point in the array by (dx, dy). The caller sees the moved points, because the
     * array holds references to the very same objects.
     *
     * @throws IllegalArgumentException if points is null or contains null
     */
    public static void moveAll(Point[] points, int dx, int dy) {
        requireNoNulls(points);
        // TODO: Implement this
    }

    /**
     * Returns a NEW array that refers to the SAME Point objects (a shallow copy). Replacing a cell
     * of one array does not affect the other, but moving a point is visible through both.
     *
     * @throws IllegalArgumentException if points is null
     */
    public static Point[] shallowCopy(Point[] points) {
        requireNonNull(points);
        // TODO: Implement this
        return null;
    }

    /**
     * Returns a NEW array with NEW Point objects at the same positions (a deep copy). Afterwards
     * nothing done to one array or its points affects the other. Hint: Point.copy().
     *
     * @throws IllegalArgumentException if points is null or contains null
     */
    public static Point[] deepCopy(Point[] points) {
        requireNoNulls(points);
        // TODO: Implement this
        return null;
    }

    /**
     * Swaps the positions of two points: afterwards a is where b was, and b is where a was.
     * You cannot swap the caller's two VARIABLES (the method only has copies of the references),
     * but you can change the two OBJECTS they point to.
     *
     * @throws IllegalArgumentException if a or b is null
     */
    public static void swapCoordinates(Point a, Point b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Points must not be null");
        }
        // TODO: Implement this
    }

    private static void requireNonNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The array must not be null");
        }
    }

    private static void requireNoNulls(Point[] points) {
        requireNonNull(points);
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("The array contains null at index " + i);
            }
        }
    }
}
