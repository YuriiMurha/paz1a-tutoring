package sk.upjs.paz1a.tutoring.exercise10;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveUtilsTest {

    // ---------- factorial ----------

    @Test
    void factorialOfZeroIsOne() {
        assertEquals(1L, RecursiveUtils.factorial(0));
    }

    @Test
    void factorialOfOneIsOne() {
        assertEquals(1L, RecursiveUtils.factorial(1));
    }

    @Test
    void factorialOfFive() {
        assertEquals(120L, RecursiveUtils.factorial(5));
    }

    @Test
    void factorialOfTwentyStillFitsIntoLong() {
        assertEquals(2_432_902_008_176_640_000L, RecursiveUtils.factorial(20));
    }

    @Test
    void factorialRejectsNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> RecursiveUtils.factorial(-1));
    }

    @Test
    void factorialRejectsNumbersAboveTwenty() {
        assertThrows(IllegalArgumentException.class, () -> RecursiveUtils.factorial(21));
    }

    // ---------- reverseString ----------

    @Test
    void reverseOfEmptyStringIsEmpty() {
        assertEquals("", RecursiveUtils.reverseString(""));
    }

    @Test
    void reverseOfSingleCharacterIsTheSame() {
        assertEquals("a", RecursiveUtils.reverseString("a"));
    }

    @Test
    void reverseOfTwoCharacters() {
        assertEquals("ba", RecursiveUtils.reverseString("ab"));
    }

    @Test
    void reverseOfWord() {
        assertEquals("olleh", RecursiveUtils.reverseString("hello"));
    }

    @Test
    void reverseKeepsSpacesAndCase() {
        assertEquals("71 avaJ", RecursiveUtils.reverseString("Java 17"));
    }

    @Test
    void reverseRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> RecursiveUtils.reverseString(null));
    }

    // ---------- countDigits ----------

    @Test
    void zeroHasOneDigit() {
        assertEquals(1, RecursiveUtils.countDigits(0));
    }

    @Test
    void nineHasOneDigitAndTenHasTwo() {
        assertEquals(1, RecursiveUtils.countDigits(9));
        assertEquals(2, RecursiveUtils.countDigits(10));
    }

    @Test
    void countDigitsOfLargerNumber() {
        assertEquals(5, RecursiveUtils.countDigits(12345));
    }

    @Test
    void countDigitsOfLargestInt() {
        assertEquals(10, RecursiveUtils.countDigits(Integer.MAX_VALUE));
    }

    @Test
    void countDigitsRejectsNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> RecursiveUtils.countDigits(-1));
        assertThrows(IllegalArgumentException.class, () -> RecursiveUtils.countDigits(Integer.MIN_VALUE));
    }

    // ---------- isPalindrome ----------

    @Test
    void emptyStringIsPalindrome() {
        assertTrue(RecursiveUtils.isPalindrome(""));
    }

    @Test
    void singleCharacterIsPalindrome() {
        assertTrue(RecursiveUtils.isPalindrome("x"));
    }

    @Test
    void oddLengthPalindrome() {
        assertTrue(RecursiveUtils.isPalindrome("racecar"));
    }

    @Test
    void evenLengthPalindrome() {
        assertTrue(RecursiveUtils.isPalindrome("abba"));
    }

    @Test
    void twoDifferentCharactersAreNotPalindrome() {
        assertFalse(RecursiveUtils.isPalindrome("ab"));
    }

    @Test
    void mismatchInTheMiddleIsFound() {
        assertFalse(RecursiveUtils.isPalindrome("abcxba"));
    }

    @Test
    void comparisonIsCaseSensitive() {
        assertFalse(RecursiveUtils.isPalindrome("Anna"));
    }

    @Test
    void isPalindromeRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> RecursiveUtils.isPalindrome(null));
    }

    // ---------- the rule of this exercise ----------

    @Test
    void solvedWithoutLoops() throws IOException {
        Path source = Path.of("src/main/java/sk/upjs/paz1a/tutoring/exercise10/RecursiveUtils.java");
        assertTrue(Files.exists(source),
                "Cannot find " + source.toAbsolutePath() + " - run the tests from the project folder");

        String code = Files.readString(source)
                .replaceAll("(?s)/\\*.*?\\*/", "")             // block and Javadoc comments
                .replaceAll("//[^\\n]*", "")                   // line comments
                .replaceAll("\"(\\\\.|[^\"\\\\])*\"", "\"\""); // string literals

        Matcher forbidden = Pattern.compile("\\b(for|while|do)\\b|forEach|Stream|\\.reverse\\(").matcher(code);
        if (forbidden.find()) {
            fail("Solve RecursiveUtils with recursion only - found '" + forbidden.group() + "' in the code");
        }
    }
}
