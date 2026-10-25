package sk.upjs.paz1a.tutoring.recap.exercise04;

/**
 * Recap 04 - methods and scope: a small unit calculator and string processor.
 * <p>
 * Split every task into small methods with clear names. A variable declared inside a method (a
 * local variable) exists only while that method runs, so pass values between methods as parameters
 * and return values, not through fields.
 * <p>
 * The private helper methods below are part of the exercise: implement them first, then build the
 * public methods out of them.
 */
public class MethodDecomposition {

    public static final int MIN_PASSWORD_LENGTH = 8;

    private MethodDecomposition() {
        // only static methods here, so nobody needs to create a MethodDecomposition object
    }

    /**
     * Returns true if the password has at least 8 characters, at least one digit and at least one
     * uppercase letter. Examples: "Secret123" -> true, "secret123" -> false (no uppercase letter).
     *
     * @throws IllegalArgumentException if password is null
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }
        // TODO: Implement this (use containsDigit and containsUppercase)
        return false;
    }

    /**
     * Returns true if at least one character of text is a digit.
     * Hint: {@link Character#isDigit(char)}.
     */
    private static boolean containsDigit(String text) {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns true if at least one character of text is an uppercase letter.
     * Hint: {@link Character#isUpperCase(char)}.
     */
    private static boolean containsUppercase(String text) {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the greatest common divisor of a and b, using Euclid's algorithm: while b is not 0,
     * replace the pair (a, b) with (b, a % b); when b reaches 0, a is the answer.
     * Examples: (12, 18) -> 6, (17, 5) -> 1, (0, 5) -> 5.
     *
     * @throws IllegalArgumentException if a or b is negative, or if both are 0
     */
    public static int greatestCommonDivisor(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Numbers must not be negative, but got " + a + " and " + b);
        }
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("gcd(0, 0) is not defined");
        }
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns the body mass index: weight divided by height squared (kilograms and metres).
     * Example: 70 kg and 1.75 m -> 22.86 (approximately).
     *
     * @throws IllegalArgumentException if weightKg or heightMeters is zero or negative
     */
    public static double calculateBMI(double weightKg, double heightMeters) {
        if (weightKg <= 0 || heightMeters <= 0) {
            throw new IllegalArgumentException("Weight and height must be positive, but got "
                    + weightKg + " and " + heightMeters);
        }
        // TODO: Implement this (use square)
        return 0.0;
    }

    /**
     * Returns value * value.
     */
    private static double square(double value) {
        // TODO: Implement this
        return 0.0;
    }
}
