package sk.upjs.paz1a.tutoring.recap.exercise08;

/**
 * Recap 08 - references and objects: a point on a grid that can move.
 * <p>
 * Point objects are MUTABLE: moveTo and moveBy change the object itself, so every variable, array
 * cell or parameter that refers to that object sees the change.
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Moves this point to the position (x, y). (Already done - use it as a model for moveBy.)
     */
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves this point by an offset: x increases by dx and y by dy.
     */
    public void moveBy(int dx, int dy) {
        // TODO: Implement this
    }

    /**
     * Returns a NEW point at the same position. Moving the copy must not move this point.
     */
    public Point copy() {
        // TODO: Implement this
        return null;
    }

    /**
     * Returns true if other is at the same position as this point. Note the difference from
     * {@code ==}: for two Point variables, {@code a == b} asks "is it the SAME object?",
     * not "is it the same position?".
     *
     * @throws IllegalArgumentException if other is null
     */
    public boolean hasSameCoordinates(Point other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare with a null point");
        }
        // TODO: Implement this
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
