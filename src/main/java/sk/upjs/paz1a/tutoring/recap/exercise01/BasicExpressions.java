package sk.upjs.paz1a.tutoring.recap.exercise01;

/**
 * Recap 01 - variables, primitive types and operators.
 * <p>
 * Every method here needs only a line or two. The difficulty is in the details of the primitive
 * types: integer division, the remainder operator {@code %} with negative numbers, and the limited
 * range of {@code int}.
 */
public class BasicExpressions {

    /** Absolute zero - the lowest possible temperature - in degrees Fahrenheit. */
    public static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

    private BasicExpressions() {
        // only static methods here, so nobody needs to create a BasicExpressions object
    }

    /**
     * Converts degrees Fahrenheit to degrees Celsius using C = 5/9 * (F - 32).
     * Examples: 32 -> 0.0, 212 -> 100.0, -40 -> -40.0.
     * <p>
     * Careful: in Java {@code 5 / 9} is {@code 0}, because dividing two ints gives an int.
     *
     * @throws IllegalArgumentException if fahrenheit is below absolute zero (-459.67)
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        if (fahrenheit < ABSOLUTE_ZERO_FAHRENHEIT) {
            throw new IllegalArgumentException("Nothing is colder than absolute zero, but got " + fahrenheit);
        }
        // TODO: Implement this
        return 0.0;
    }

    /**
     * Returns a*a + b*b - by Pythagoras, the square of the hypotenuse of a right triangle whose legs
     * have lengths a and b. Example: (3, 4) -> 25.
     *
     * @throws IllegalArgumentException if a or b is negative (a length cannot be negative)
     */
    public static int calculateHypotenuseSquared(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Lengths must not be negative, but got " + a + " and " + b);
        }
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns true if number is even. Zero and negative even numbers such as -4 count as even.
     * Hint: the remainder operator {@code %}.
     */
    public static boolean isEven(int number) {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the last decimal digit of number as a value 0-9, also for negative numbers:
     * 123 -> 3, -123 -> 3, 120 -> 0.
     * <p>
     * Careful: in Java {@code -123 % 10} is {@code -3}, and {@code Math.abs(Integer.MIN_VALUE)} is
     * still negative, because +2147483648 does not fit into an int. The tests try both.
     */
    public static int extractLastDigit(int number) {
        // TODO: Implement this
        return 0;
    }
}
