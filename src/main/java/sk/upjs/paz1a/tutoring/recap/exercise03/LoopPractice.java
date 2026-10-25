package sk.upjs.paz1a.tutoring.recap.exercise03;

/**
 * Recap 03 - loops and iteration.
 * <p>
 * Use {@code for} when you know how many times to repeat, and {@code while} when you repeat until a
 * condition changes. Most loop bugs are off-by-one errors ({@code <} versus {@code <=}), so the tests
 * check the first and the last iteration.
 */
public class LoopPractice {

    private LoopPractice() {
        // only static methods here, so nobody needs to create a LoopPractice object
    }

    /**
     * Returns 1 + 2 + ... + n, computed with a loop. Examples: 5 -> 15, 0 -> 0.
     *
     * @throws IllegalArgumentException if n is negative
     */
    public static int sumNumbersUpTo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative, but was " + n);
        }
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns how many decimal digits number has, ignoring the minus sign:
     * 0 -> 1, 7 -> 1, 12345 -> 5, -123 -> 3.
     * Hint: {@code number / 10} removes the last digit - repeat until nothing is left.
     */
    public static int countDigits(int number) {
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns true if number is a prime: greater than 1 and divisible only by 1 and itself.
     * 0, 1 and negative numbers are not primes.
     * Hint: it is enough to try divisors d with d * d &lt;= number.
     */
    public static boolean isPrime(int number) {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns a triangle of stars with the given number of rows; row i (counting from 1) has i stars.
     * Rows are separated by {@code "\n"}, with no line break after the last row:
     * <pre>
     * generatePattern(3) returns "*\n**\n***", which prints as
     * *
     * **
     * ***
     * </pre>
     * Zero rows give an empty string. Build it with two nested loops (no {@code String.repeat}).
     *
     * @throws IllegalArgumentException if rows is negative
     */
    public static String generatePattern(int rows) {
        if (rows < 0) {
            throw new IllegalArgumentException("rows must not be negative, but was " + rows);
        }
        // TODO: Implement this
        return null;
    }
}
