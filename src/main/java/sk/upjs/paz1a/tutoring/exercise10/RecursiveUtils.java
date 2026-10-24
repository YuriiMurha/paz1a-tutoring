package sk.upjs.paz1a.tutoring.exercise10;

/**
 * Exercise 10 - recursion.
 * <p>
 * Solve every method RECURSIVELY: no {@code for}, {@code while} or {@code do} loops, no streams and
 * no {@code StringBuilder.reverse()}. A test reads this file and fails if it finds a loop.
 * <p>
 * The recipe for every method:
 * <ol>
 *     <li><b>Base case</b> - the smallest input you can answer immediately (0, 1, an empty string...).</li>
 *     <li><b>Recursive step</b> - call the method on a smaller input and build on its answer.</li>
 * </ol>
 */
public class RecursiveUtils {

    private RecursiveUtils() {
        // only static methods here, so nobody needs to create a RecursiveUtils object
    }

    /**
     * Returns n! = 1 * 2 * ... * n, where 0! = 1. Only 0..20 are allowed, because 21! no longer
     * fits into a {@code long}.
     *
     * @throws IllegalArgumentException if n is negative or greater than 20
     */
    public static long factorial(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("n must be between 0 and 20, but was " + n);
        }
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns the string backwards: "hello" -> "olleh".
     * Hint: {@code str.substring(1)} is the string without its first character.
     *
     * @throws IllegalArgumentException if str is null
     */
    public static String reverseString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The string must not be null");
        }
        // TODO: Implement this
        return null;
    }

    /**
     * Returns how many decimal digits n has: 0 -> 1, 7 -> 1, 42 -> 2, 12345 -> 5.
     * Hint: {@code n / 10} removes the last digit.
     *
     * @throws IllegalArgumentException if n is negative
     */
    public static int countDigits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative, but was " + n);
        }
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns true if str reads the same forwards and backwards ("racecar", "abba"). The empty
     * string and a single character are palindromes. The comparison is exact: "Anna" is NOT a
     * palindrome, because 'A' and 'a' are different characters.
     * Hint: compare the first and the last character, then check what is between them.
     *
     * @throws IllegalArgumentException if str is null
     */
    public static boolean isPalindrome(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The string must not be null");
        }
        // TODO: Implement this
        return false;
    }
}
