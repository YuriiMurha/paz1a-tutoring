package sk.upjs.paz1a.tutoring.recap.exercise09;

/**
 * Static helpers that work with any shapes through the Shape type only - no {@code instanceof}
 * and no casts. They keep working for shape types that do not even exist yet.
 */
public class ShapeTools {

    private ShapeTools() {
        // only static methods here, so nobody needs to create a ShapeTools object
    }

    /**
     * Returns the sum of the areas of all shapes; an empty array gives 0.
     *
     * @throws IllegalArgumentException if shapes is null or contains null
     */
    public static double totalArea(Shape[] shapes) {
        requireNoNulls(shapes);
        // TODO: Implement this
        return 0.0;
    }

    /**
     * Returns the shape with the largest area (on a tie, the first one); an empty array gives null.
     * Hint: isLargerThan() is already there to help you.
     *
     * @throws IllegalArgumentException if shapes is null or contains null
     */
    public static Shape findLargest(Shape[] shapes) {
        requireNoNulls(shapes);
        // TODO: Implement this
        return null;
    }

    /**
     * Returns the names of the shapes in order, separated by ", ": "Circle, Square".
     * An empty array gives an empty string.
     *
     * @throws IllegalArgumentException if shapes is null or contains null
     */
    public static String listNames(Shape[] shapes) {
        requireNoNulls(shapes);
        // TODO: Implement this
        return null;
    }

    private static void requireNoNulls(Shape[] shapes) {
        if (shapes == null) {
            throw new IllegalArgumentException("The array must not be null");
        }
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == null) {
                throw new IllegalArgumentException("The array contains null at index " + i);
            }
        }
    }
}
