package sk.upjs.paz1a.tutoring.recap.exercise07;

/**
 * Recap 07 - classes and encapsulation.
 * <p>
 * A rectangle keeps its width and height in private fields. The class invariant - both sides are
 * always positive - is protected by the constructor and by the setters (the checks are written for
 * you). Everything else is computed from the two fields, so there is no "area" field that could
 * get out of date when a side changes.
 */
public class Rectangle {

    private double width;
    private double height;

    /**
     * @throws IllegalArgumentException if width or height is zero or negative
     */
    public Rectangle(double width, double height) {
        requirePositive(width, "Width");
        requirePositive(height, "Height");
        // TODO: Implement this
    }

    public double getWidth() {
        // TODO: Implement this
        return 0.0;
    }

    public double getHeight() {
        // TODO: Implement this
        return 0.0;
    }

    /**
     * @throws IllegalArgumentException if width is zero or negative (the old width is kept)
     */
    public void setWidth(double width) {
        requirePositive(width, "Width");
        // TODO: Implement this
    }

    /**
     * @throws IllegalArgumentException if height is zero or negative (the old height is kept)
     */
    public void setHeight(double height) {
        requirePositive(height, "Height");
        // TODO: Implement this
    }

    /**
     * Returns width * height.
     */
    public double area() {
        // TODO: Implement this
        return 0.0;
    }

    /**
     * Returns the length of the border: 2 * (width + height).
     */
    public double perimeter() {
        // TODO: Implement this
        return 0.0;
    }

    /**
     * Returns true if the width and the height are equal.
     */
    public boolean isSquare() {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the rectangle as text in exactly this format: {@code Rectangle 3.0 x 4.5}
     * (build it with {@code +}).
     */
    @Override
    public String toString() {
        // TODO: Implement this
        return "";
    }

    private static void requirePositive(double value, String what) {
        if (value <= 0) {
            throw new IllegalArgumentException(what + " must be positive, but was " + value);
        }
    }
}
