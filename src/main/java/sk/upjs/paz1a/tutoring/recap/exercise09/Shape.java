package sk.upjs.paz1a.tutoring.recap.exercise09;

/**
 * Recap 09 - inheritance and polymorphism (dynamic dispatch).
 * <p>
 * Every shape has a name, an area and a perimeter, but each kind of shape computes them
 * differently, so area() and perimeter() are abstract. isLargerThan() is written only ONCE, here,
 * and still works for every subclass: when it calls area(), Java runs the area() of the actual
 * object - a Circle's for a circle, a Square's for a square. That is dynamic dispatch.
 */
public abstract class Shape {

    private final String name;

    protected Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double area();

    public abstract double perimeter();

    /**
     * Returns true if this shape's area is strictly larger than the other shape's area.
     *
     * @throws IllegalArgumentException if other is null
     */
    public boolean isLargerThan(Shape other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare with a null shape");
        }
        // TODO: Implement this
        return false;
    }
}
