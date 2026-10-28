package sk.upjs.paz1a.tutoring.recap.exercise09;

/**
 * A square: area = side * side, perimeter = 4 * side.
 */
public class Square extends Shape {

    private final double side;

    /**
     * @throws IllegalArgumentException if side is zero or negative
     */
    public Square(double side) {
        super("Square");
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive, but was " + side);
        }
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double area() {
        // TODO: Implement this
        return 0.0;
    }

    @Override
    public double perimeter() {
        // TODO: Implement this
        return 0.0;
    }
}
