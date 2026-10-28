package sk.upjs.paz1a.tutoring.recap.exercise05;

/**
 * Recap 05, part 2 - more 1D array practice, ordered from easier (*) to harder (***).
 * <p>
 * The same rules as in {@link ArrayBasics}: a {@code null} array is rejected (already written for
 * you), but an empty array is a valid input. Methods that return an array always return a NEW array
 * and leave the input unchanged. Write your own loops - no {@code java.util.Arrays}, no streams.
 */
public class ArrayChallenges {

    private ArrayChallenges() {
        // only static methods here, so nobody needs to create an ArrayChallenges object
    }

    /**
     * (*) Returns the index of the first element equal to target, or -1 if there is none:
     * ({4, 8, 4}, 4) -> 0, ({4, 8, 4}, 5) -> -1.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static int indexOf(int[] numbers, int target) {
        requireNonNull(numbers);
        // TODO: Implement this
        return 0;
    }

    /**
     * (*) Returns a new array whose element i is the sum of numbers[0] up to numbers[i]:
     * {1, 2, 3, 4} -> {1, 3, 6, 10}.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static int[] runningSum(int[] numbers) {
        requireNonNull(numbers);
        // TODO: Implement this
        return null;
    }

    /**
     * (*) Returns true if the array reads the same from both ends: {1, 2, 1} -> true,
     * {1, 2} -> false. Empty and single-element arrays are palindromes.
     * Hint: compare the first with the last element, then move both indexes inwards.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static boolean isPalindrome(int[] numbers) {
        requireNonNull(numbers);
        // TODO: Implement this
        return false;
    }

    /**
     * (**) Returns a new array rotated to the left by steps positions; elements that fall off the
     * front come back at the end: ({1, 2, 3, 4, 5}, 2) -> {3, 4, 5, 1, 2}. Rotating by the
     * length gives the original order again, and steps may be larger than the length.
     * Hint: result[i] = numbers[(i + steps) % length] - but think about the empty array.
     *
     * @throws IllegalArgumentException if numbers is null or steps is negative
     */
    public static int[] rotateLeft(int[] numbers, int steps) {
        requireNonNull(numbers);
        if (steps < 0) {
            throw new IllegalArgumentException("steps must not be negative, but was " + steps);
        }
        // TODO: Implement this
        return null;
    }

    /**
     * (**) Returns the largest value that is strictly smaller than the maximum:
     * {4, 9, 2, 7} -> 7, {5, 1, 5, 3} -> 3 (both 5s are the maximum), {-2, -7} -> -7.
     * <p>
     * The null check is written for you; detecting arrays without a second largest value
     * ({}, {4} or {7, 7, 7}) is part of the exercise.
     *
     * @throws IllegalArgumentException if numbers is null or has fewer than two different values
     */
    public static int secondLargest(int[] numbers) {
        requireNonNull(numbers);
        // TODO: Implement this
        return 0;
    }

    /**
     * (**) Returns a new array without repeated values, keeping the first occurrence of each value
     * in the original order: {3, 1, 3, 2, 1} -> {3, 1, 2}. The result has no unused slots at the end.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static int[] removeDuplicates(int[] numbers) {
        requireNonNull(numbers);
        // TODO: Implement this
        return null;
    }

    /**
     * (***) Returns the length of the longest run of equal neighboring elements:
     * {1, 2, 2, 2, 3, 3} -> 3, {1, 2, 3} -> 1, {} -> 0.
     *
     * @throws IllegalArgumentException if numbers is null
     */
    public static int longestRun(int[] numbers) {
        requireNonNull(numbers);
        // TODO: Implement this
        return 0;
    }

    /**
     * (***) Merges two arrays that are each sorted in ascending order into one new sorted array with
     * all elements of both (duplicates are kept): ({1, 4, 7}, {2, 4, 9}) -> {1, 2, 4, 4, 7, 9}.
     * Walk through both arrays at the same time with two indexes - don't just join them and sort.
     *
     * @throws IllegalArgumentException if a or b is null
     */
    public static int[] mergeSorted(int[] a, int[] b) {
        requireNonNull(a);
        requireNonNull(b);
        // TODO: Implement this
        return null;
    }

    private static void requireNonNull(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("The array must not be null");
        }
    }
}
