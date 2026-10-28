package sk.upjs.paz1a.tutoring.recap.exercise09;

/**
 * A circle: area = pi * r * r, perimeter (circumference) = 2 * pi * r. Use {@link Math#PI}.
 */
public class Circle extends Shape {

    private final double radius;

    /**
     * @throws IllegalArgumentException if radius is zero or negative
     */
    public Circle(double radius) {
        super("Circle");
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive, but was " + radius);
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
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
