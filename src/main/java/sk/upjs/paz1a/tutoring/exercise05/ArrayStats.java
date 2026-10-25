package sk.upjs.paz1a.tutoring.exercise05;

/**
 * Exercise 05 - one-dimensional arrays.
 * <p>
 * The input checks (guard clauses) are already written for you: study them, then replace every
 * {@code // TODO} with your own loop. Do not use {@code java.util.Arrays} or streams - the point is
 * to practise walking through an array index by index.
 */
public class ArrayStats {

    private ArrayStats() {
        // only static methods here, so nobody needs to create an ArrayStats object
    }

    /**
     * Returns the arithmetic mean, e.g. {1, 2, 3, 4} -> 2.5.
     * <p>
     * Watch out for two classic traps: integer division ({@code 5 / 2 == 2}) and {@code int}
     * overflow when the sum gets bigger than {@link Integer#MAX_VALUE}.
     *
     * @throws IllegalArgumentException if {@code numbers} is null or empty
     */
    public static double calculateAverage(int[] numbers) {
        requireNonEmpty(numbers);
        // TODO: Implement this
        return 0.0;
    }

    /**
     * Returns the smallest element, e.g. {4, -2, 7} -> -2.
     *
     * @throws IllegalArgumentException if {@code numbers} is null or empty
     */
    public static int findMin(int[] numbers) {
        requireNonEmpty(numbers);
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns true if no element is greater than the element right after it.
     * Equal neighbors are fine: {1, 2, 2, 5} -> true, {1, 3, 2} -> false.
     * An array with a single element is sorted.
     *
     * @throws IllegalArgumentException if {@code numbers} is null or empty
     */
    public static boolean isSortedAscending(int[] numbers) {
        requireNonEmpty(numbers);
        // TODO: Implement this
        return false;
    }

    /**
     * Returns a NEW array with only the values greater than zero, in their original order,
     * e.g. {3, -1, 0, 7} -> {3, 7}. The result has exactly as many elements as there are positive
     * values (no trailing zeros); if there are none, it is an empty array, not null.
     * The input array must stay unchanged.
     * <p>
     * Hint: count the positive values first, then create the result array and fill it.
     *
     * @throws IllegalArgumentException if {@code numbers} is null or empty
     */
    public static int[] filterPositive(int[] numbers) {
        requireNonEmpty(numbers);
        // TODO: Implement this
        return null;
    }

    private static void requireNonEmpty(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("The array must not be null or empty");
        }
    }
}
