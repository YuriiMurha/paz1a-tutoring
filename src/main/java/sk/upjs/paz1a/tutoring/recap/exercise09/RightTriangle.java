package sk.upjs.paz1a.tutoring.recap.exercise09;

/**
 * A right triangle given by its two legs a and b: area = a * b / 2, and the perimeter is
 * a + b + the hypotenuse, where hypotenuse = sqrt(a * a + b * b) (Pythagoras, see recap 01).
 * Use {@link Math#sqrt(double)}.
 */
public class RightTriangle extends Shape {

    private final double legA;
    private final double legB;

    /**
     * @throws IllegalArgumentException if a leg is zero or negative
     */
    public RightTriangle(double legA, double legB) {
        super("Right triangle");
        if (legA <= 0 || legB <= 0) {
            throw new IllegalArgumentException("Legs must be positive, but were " + legA + " and " + legB);
        }
        this.legA = legA;
        this.legB = legB;
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
