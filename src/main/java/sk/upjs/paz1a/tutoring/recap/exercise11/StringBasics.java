package sk.upjs.paz1a.tutoring.recap.exercise11;

/**
 * Recap 11 - strings, ordered from easier (*) to harder (**).
 * <p>
 * Every method practises a few {@link String} methods or a loop over the characters with
 * {@code charAt}. You may use any method of {@link String}, {@link StringBuilder} and
 * {@link Character}. A {@code null} argument is rejected (already written for you), but the empty
 * string {@code ""} is a valid input with a natural answer.
 * <p>
 * Remember: a String never changes. Methods like {@code toUpperCase()} return a NEW string, so you
 * must use what they return. The lecture is in {@code lectures/topic-11-strings.md}.
 */
public class StringBasics {

    private StringBasics() {
        // only static methods here, so nobody needs to create a StringBasics object
    }

    /**
     * (*) Returns how many times c occurs in text. Upper and lower case are different characters:
     * ("banana", 'a') -> 3, ("Banana", 'b') -> 0, ("", 'x') -> 0.
     *
     * @throws IllegalArgumentException if text is null
     */
    public static int countChar(String text, char c) {
        requireNonNull(text);
        // TODO: Implement this
        return 0;
    }

    /**
     * (*) Returns the middle of word: the middle character if the length is odd, the middle two
     * characters if it is even: "abc" -> "b", "abcd" -> "bc", "kayak" -> "y", "" -> "".
     * Hint: {@code substring(begin, end)} includes begin but NOT end.
     *
     * @throws IllegalArgumentException if word is null
     */
    public static String middle(String word) {
        requireNonNull(word);
        // TODO: Implement this
        return null;
    }

    /**
     * (*) Returns word with its first character in upper case and all the others in lower case:
     * "jAVA" -> "Java", "x" -> "X", "" -> "". Characters that are not letters stay as they are.
     *
     * @throws IllegalArgumentException if word is null
     */
    public static String capitalize(String word) {
        requireNonNull(word);
        // TODO: Implement this
        return null;
    }

    /**
     * (*) Returns true if input is one of the commands "start", "stop" or "pause". Upper/lower case
     * and spaces before or after the word do not matter: "  Start " -> true, "STOP" -> true,
     * "starts" -> false, "st op" -> false, "" -> false.
     * <p>
     * Careful: compare the CONTENT of two strings with {@code equals}, never with {@code ==}.
     *
     * @throws IllegalArgumentException if input is null
     */
    public static boolean isCommand(String input) {
        requireNonNull(input);
        // TODO: Implement this
        return false;
    }

    /**
     * (**) Returns the extension of a file name: everything after the LAST dot.
     * "photo.png" -> "png", "archive.tar.gz" -> "gz", ".gitignore" -> "gitignore".
     * Without a dot, or with nothing after the last dot, the extension is empty:
     * "README" -> "", "notes." -> "".
     *
     * @throws IllegalArgumentException if fileName is null
     */
    public static String fileExtension(String fileName) {
        requireNonNull(fileName);
        // TODO: Implement this
        return null;
    }

    /**
     * (**) Adds up the values of all digits '0'-'9' in text and ignores every other character:
     * "a1b2c3" -> 6, "2024" -> 8, "no digits" -> 0.
     * Hint: a char is a number, and {@code '7' - '0'} is 7.
     *
     * @throws IllegalArgumentException if text is null
     */
    public static int sumOfDigits(String text) {
        requireNonNull(text);
        // TODO: Implement this
        return 0;
    }

    /**
     * (**) Returns the number of words in sentence. Words are separated by one or more spaces, and
     * there may be spaces at the start and at the end: "the quick fox" -> 3,
     * "  many   spaces  " -> 2, "" -> 0, "   " -> 0. Punctuation belongs to the word next to it.
     *
     * @throws IllegalArgumentException if sentence is null
     */
    public static int countWords(String sentence) {
        requireNonNull(sentence);
        // TODO: Implement this
        return 0;
    }

    /**
     * (**) Returns the word that comes first in dictionary order, ignoring upper/lower case:
     * {"pear", "Apple", "banana"} -> "Apple", {"cart", "car"} -> "car". If several words are equal
     * when the case is ignored, return the one that comes first in the array.
     * The array is not changed.
     *
     * @throws IllegalArgumentException if words is null or empty
     */
    public static String firstAlphabetically(String[] words) {
        if (words == null || words.length == 0) {
            throw new IllegalArgumentException("There must be at least one word");
        }
        // TODO: Implement this
        return null;
    }

    /**
     * (**) Returns the numbers 1 to n separated by a comma and a space: 5 -> "1, 2, 3, 4, 5",
     * 1 -> "1", 0 -> "". There is no separator after the last number.
     * <p>
     * A test calls this with n = 100 000 and expects the answer within 2 seconds. Gluing strings
     * together with {@code +} in a loop is far too slow for that - find out why in the lecture.
     *
     * @throws IllegalArgumentException if n is negative
     */
    public static String numberList(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative, but was " + n);
        }
        // TODO: Implement this
        return null;
    }

    private static void requireNonNull(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The string must not be null");
        }
    }
}
