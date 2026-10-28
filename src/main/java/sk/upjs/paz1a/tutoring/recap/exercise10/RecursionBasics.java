package sk.upjs.paz1a.tutoring.recap.exercise10;

/**
 * Recap 10 - recursion, on numbers and on linked lists.
 * <p>
 * Solve every method RECURSIVELY: no {@code for}, {@code while} or {@code do} loops, no streams.
 * A test reads this file and fails if it finds a loop.
 * <p>
 * A linked list is recursive by itself: a list is either empty ({@code null}) or a first node
 * followed by a shorter list ({@code head.getNext()}). So the base case is usually
 * {@code head == null}, and the recursive step works on {@code head.getNext()}.
 */
public class RecursionBasics {

    private RecursionBasics() {
        // only static methods here, so nobody needs to create a RecursionBasics object
    }

    /**
     * Returns base to the power of exponent: power(2, 10) -> 1024, power(5, 0) -> 1.
     * Base case: anything to the power of 0 is 1.
     *
     * @throws IllegalArgumentException if exponent is negative
     */
    public static long power(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("exponent must not be negative, but was " + exponent);
        }
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns the sum of the decimal digits of n: 1234 -> 10, 0 -> 0.
     * Hint: n % 10 is the last digit, n / 10 is the rest.
     *
     * @throws IllegalArgumentException if n is negative
     */
    public static int sumDigits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative, but was " + n);
        }
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns the number of nodes: [3] -> [1] -> [4] gives 3, the empty list (null) gives 0.
     */
    public static int length(Node head) {
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns the sum of all values in the list; the empty list gives 0.
     */
    public static int sum(Node head) {
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns true if some node of the list holds target; the empty list contains nothing.
     */
    public static boolean contains(Node head, int target) {
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the largest value in the list. Base case: a list with a single node.
     *
     * @throws IllegalArgumentException if the list is empty (head is null)
     */
    public static int max(Node head) {
        if (head == null) {
            throw new IllegalArgumentException("An empty list has no maximum");
        }
        // TODO: Implement this
        return 0;
    }
}
