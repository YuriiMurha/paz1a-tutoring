package sk.upjs.paz1a.tutoring.exercise11;

/**
 * Exercise 11 - string puzzles, ordered from easier (*) to hard (***).
 * <p>
 * Allowed: everything in {@link String}, {@link StringBuilder}, {@link Character},
 * {@link Integer} and {@link Math}, plus arrays. NOT allowed: anything from {@code java.util}
 * (no lists, maps, {@code Arrays.sort}, {@code Stack}...), streams, {@code BigInteger} and
 * {@code BigDecimal}. A test reads this file and fails if it finds one of them. The point is to
 * build the tools yourself: counting arrays, a stack made from a {@code char[]}, column addition.
 * <p>
 * A {@code null} argument is rejected (already written for you); any other input check that is
 * part of the exercise is mentioned in the method's Javadoc. Write helper methods whenever a step
 * repeats - they make the hard puzzles much easier.
 */
public class StringPuzzles {

    private StringPuzzles() {
        // only static methods here, so nobody needs to create a StringPuzzles object
    }

    /**
     * (*) Returns the words of sentence in reverse order, separated by single spaces, without
     * spaces at the start or at the end. In the input, words are separated by one or more spaces:
     * "the quick brown fox" -> "fox brown quick the", "  hello   world " -> "world hello",
     * "   " -> "". Each word keeps its own characters: "Hello, World!" -> "World! Hello,".
     *
     * @throws IllegalArgumentException if sentence is null
     */
    public static String reverseWords(String sentence) {
        requireNonNull(sentence, "sentence");
        // TODO: Implement this
        return null;
    }

    /**
     * (*) Returns true if a and b are anagrams: they contain the same letters, each the same number
     * of times. Only the letters a-z count, upper and lower case are the same, and every other
     * character (spaces, punctuation, digits) is ignored: ("listen", "silent") -> true,
     * ("Dormitory", "Dirty room!") -> true, ("aab", "abb") -> false.
     * Hint: count the letters in an {@code int[26]}; {@code c - 'a'} turns a letter into 0-25.
     *
     * @throws IllegalArgumentException if a or b is null
     */
    public static boolean isAnagram(String a, String b) {
        requireNonNull(a, "a");
        requireNonNull(b, "b");
        // TODO: Implement this
        return false;
    }

    /**
     * (**) Returns the longest string that every word starts with (case-sensitive):
     * {"flower", "flow", "flight"} -> "fl", {"dog", "car"} -> "", {"alone"} -> "alone".
     * An empty array has the empty prefix "".
     *
     * @throws IllegalArgumentException if words is null or contains null
     */
    public static String longestCommonPrefix(String[] words) {
        requireNoNulls(words);
        // TODO: Implement this
        return null;
    }

    /**
     * (**) Caesar cipher: moves every letter shift places along the alphabet, wrapping around from
     * z to a. Upper-case letters stay upper case, lower-case stay lower case, and all other
     * characters are copied unchanged: ("abc", 1) -> "bcd", ("xyz", 3) -> "abc",
     * ("Hello, World!", 3) -> "Khoor, Zruog!". A negative shift moves backwards, so
     * ("Khoor", -3) -> "Hello".
     * <p>
     * shift can be ANY int, even 1 000 000 or {@code Integer.MIN_VALUE} - shifting by 26 changes
     * nothing, so first make the shift small. And remember that {@code %} keeps the sign:
     * {@code -3 % 26 == -3}.
     *
     * @throws IllegalArgumentException if text is null
     */
    public static String caesarShift(String text, int shift) {
        requireNonNull(text, "text");
        // TODO: Implement this
        return null;
    }

    /**
     * (**) Run-length encoding: replaces every run of equal neighbouring characters by the
     * character followed by the length of the run: "aaabccdddd" -> "a3b1c2d4", "abc" -> "a1b1c1",
     * "zzzzzzzzzzzz" (12 times) -> "z12", "" -> "". Upper and lower case are different characters
     * and spaces are characters too.
     * <p>
     * A text containing a digit '0'-'9' cannot be encoded like this (the digits of the counts
     * would get mixed up with it), so it is rejected. Writing that check is part of the exercise.
     *
     * @throws IllegalArgumentException if text is null or contains a digit
     */
    public static String compress(String text) {
        requireNonNull(text, "text");
        // TODO: Implement this
        return null;
    }

    /**
     * (**) Returns true if the brackets (), [] and {} in code are balanced: every opening bracket
     * is closed by a bracket of the same kind, in the right order. All other characters are
     * ignored: "([]{})" -> true, "if (a[0] > 1) { x(); }" -> true, "" -> true, "([)]" -> false,
     * "((" -> false, ")(" -> false.
     * <p>
     * Hint: a closing bracket must match the most recently opened bracket that is still open.
     * Keep the open brackets in a {@code char[]} used as a stack (plus a variable saying how many
     * are in it): push when a bracket opens, pop when one closes.
     *
     * @throws IllegalArgumentException if code is null
     */
    public static boolean isBalanced(String code) {
        requireNonNull(code, "code");
        // TODO: Implement this
        return false;
    }

    /**
     * (***) The opposite of {@link #compress(String)}: every character is followed by a count of one
     * or more digits saying how many times it repeats: "a3b1c2" -> "aaabcc", "z12" -> twelve z's,
     * "a10b2" -> "aaaaaaaaaabb", "" -> "".
     * <p>
     * Detecting broken input is part of the exercise: a code that starts with a digit ("3a"), or a
     * character that is not followed by a count ("a", "ab2", "a3b"), is rejected.
     *
     * @throws IllegalArgumentException if code is null or not in the format described above
     */
    public static String decompress(String code) {
        requireNonNull(code, "code");
        // TODO: Implement this
        return null;
    }

    /**
     * (***) Adds two non-negative whole numbers given as strings of digits, however long they are:
     * ("123", "456") -> "579", ("999", "1") -> "1000",
     * ("12345678901234567890", "98765432109876543210") -> "111111111011111111100".
     * The result has no leading zeros (only the number zero itself is "0"), even when the inputs
     * have some: ("007", "3") -> "10".
     * <p>
     * These numbers do not fit into a {@code long}, so do it the way you learned at school: add the
     * columns from the right and carry the tens. The input checks are already written for you.
     *
     * @throws IllegalArgumentException if a or b is null, empty or contains a non-digit
     */
    public static String addBigNumbers(String a, String b) {
        requireDigits(a, "a");
        requireDigits(b, "b");
        // TODO: Implement this
        return null;
    }

    /**
     * (***) Returns the longest substring of text that is a palindrome (reads the same from both
     * ends, case-sensitive). If there are several of that length, return the leftmost one:
     * "babad" -> "bab" (not "aba"), "cbbd" -> "bb", "abc" -> "a", "" -> "".
     * <p>
     * Hint: every palindrome has a centre - one character ("aba") or the gap between two
     * characters ("abba"). Try every possible centre and grow outwards while both ends match.
     *
     * @throws IllegalArgumentException if text is null
     */
    public static String longestPalindrome(String text) {
        requireNonNull(text, "text");
        // TODO: Implement this
        return null;
    }

    /**
     * (***) Breaks text into lines of at most width characters. Words (separated by one or more
     * spaces in the input) are put on a line one after another, separated by single spaces, as long
     * as they fit; the next word that does not fit starts a new line. Lines are joined with
     * {@code "\n"}, there are no spaces at the start or end of a line and no empty lines.
     * A word longer than width gets a line of its own (words are never split).
     * <p>
     * ("the quick brown fox jumps over the lazy dog", 10) ->
     * "the quick\nbrown fox\njumps over\nthe lazy\ndog". A blank text gives "".
     *
     * @throws IllegalArgumentException if text is null or width is smaller than 1
     */
    public static String wordWrap(String text, int width) {
        requireNonNull(text, "text");
        if (width < 1) {
            throw new IllegalArgumentException("width must be at least 1, but was " + width);
        }
        // TODO: Implement this
        return null;
    }

    // ---------------------------------------------------------------- input checks (complete)

    private static void requireNonNull(String value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
    }

    private static void requireNoNulls(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("words must not be null");
        }
        for (int i = 0; i < words.length; i++) {
            requireNonNull(words[i], "words[" + i + "]");
        }
    }

    private static void requireDigits(String number, String name) {
        requireNonNull(number, name);
        if (number.isEmpty()) {
            throw new IllegalArgumentException(name + " must not be empty");
        }
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException(name + " must contain only digits, but has '" + c + "' at index " + i);
            }
        }
    }
}
