package sk.upjs.paz1a.tutoring.recap.exercise05;

/**
 * Recap 05 - one-dimensional array fundamentals.
 * <p>
 * A {@code null} array is a programming error, so every method rejects it (already written for you).
 * An EMPTY array, however, is a perfectly valid input here: every method has a natural answer for
 * it (sum 0, nothing found, ...). Compare this with exercise 05 of the main course, where the
 * average or the minimum of an empty array makes no sense and is rejected.
 * <p>
 * Write your own loops - no {@code java.util.Arrays}, no streams.
 */
public class ArrayBasics {

    private ArrayBasics() {
        // only static methods here, so nobody needs to create an ArrayBasics object
    }

    /**
     * Returns the sum of all elements: {1, 2, 3, 4} -> 10. The sum of an empty array is 0.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static int sumArray(int[] numbers) {
        requireNonNull(numbers);
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns true if at least one element equals target. An empty array contains nothing.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static boolean containsValue(int[] numbers, int target) {
        requireNonNull(numbers);
        // TODO: Implement this
        return false;
    }

    /**
     * Returns a NEW array with the elements in reverse order: {1, 2, 3} -> {3, 2, 1}.
     * The input array stays unchanged; an empty array gives a new empty array.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static int[] reverseArray(int[] numbers) {
        requireNonNull(numbers);
        // TODO: Implement this
        return null;
    }

    /**
     * Returns how many elements equal target: ({1, 2, 2, 3, 2}, 2) -> 3.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static int countOccurrences(int[] numbers, int target) {
        requireNonNull(numbers);
        // TODO: Implement this
        return 0;
    }

    private static void requireNonNull(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("The array must not be null");
        }
    }
}
