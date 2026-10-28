package sk.upjs.paz1a.tutoring.recap.exercise10;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class RecursionBasicsTest {

    /**
     * Builds the list values[0] -> values[1] -> ... -> null. (A loop is fine here in the test;
     * only RecursionBasics itself must be loop-free.)
     */
    private static Node listOf(int... values) {
        Node head = null;
        for (int i = values.length - 1; i >= 0; i--) {
            head = new Node(values[i], head);
        }
        return head;
    }

    // ---------- power ----------

    @Test
    void powerOfTwo() {
        assertEquals(1024L, RecursionBasics.power(2, 10));
    }

    @Test
    void anythingToThePowerOfZeroIsOne() {
        assertEquals(1L, RecursionBasics.power(5, 0));
        assertEquals(1L, RecursionBasics.power(0, 0));
        assertEquals(1L, RecursionBasics.power(-3, 0));
    }

    @Test
    void zeroToAPositivePowerIsZero() {
        assertEquals(0L, RecursionBasics.power(0, 5));
    }

    @Test
    void negativeBase() {
        assertEquals(-8L, RecursionBasics.power(-2, 3));
        assertEquals(16L, RecursionBasics.power(-2, 4));
    }

    @Test
    void largePowerNeedsALong() {
        assertEquals(1_099_511_627_776L, RecursionBasics.power(2, 40),
                "2^40 does not fit into an int - multiply as long");
    }

    @Test
    void powerRejectsNegativeExponent() {
        assertThrows(IllegalArgumentException.class, () -> RecursionBasics.power(2, -1));
    }

    // ---------- sumDigits ----------

    @Test
    void sumOfSeveralDigits() {
        assertEquals(10, RecursionBasics.sumDigits(1234));
    }

    @Test
    void digitSumOfZeroIsZero() {
        assertEquals(0, RecursionBasics.sumDigits(0));
    }

    @Test
    void digitSumOfSingleDigit() {
        assertEquals(7, RecursionBasics.sumDigits(7));
    }

    @Test
    void zerosInsideTheNumber() {
        assertEquals(6, RecursionBasics.sumDigits(1005));
    }

    @Test
    void digitSumOfLargestInt() {
        assertEquals(46, RecursionBasics.sumDigits(Integer.MAX_VALUE)); // 2+1+4+7+4+8+3+6+4+7
    }

    @Test
    void sumDigitsRejectsNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> RecursionBasics.sumDigits(-1));
    }

    // ---------- length ----------

    @Test
    void lengthOfThreeNodes() {
        assertEquals(3, RecursionBasics.length(listOf(3, 1, 4)));
    }

    @Test
    void emptyListHasLengthZero() {
        assertEquals(0, RecursionBasics.length(null));
    }

    @Test
    void singleNodeHasLengthOne() {
        assertEquals(1, RecursionBasics.length(listOf(7)));
    }

    // ---------- sum ----------

    @Test
    void sumOfList() {
        assertEquals(8, RecursionBasics.sum(listOf(3, 1, 4)));
    }

    @Test
    void sumWithNegativeValues() {
        assertEquals(-4, RecursionBasics.sum(listOf(-2, 5, -7)));
    }

    @Test
    void emptyListSumsToZero() {
        assertEquals(0, RecursionBasics.sum(null));
    }

    // ---------- contains ----------

    @Test
    void containsFindsFirstAndLastValue() {
        Node list = listOf(3, 1, 4);
        assertTrue(RecursionBasics.contains(list, 3));
        assertTrue(RecursionBasics.contains(list, 4));
    }

    @Test
    void containsMissingValue() {
        assertFalse(RecursionBasics.contains(listOf(3, 1, 4), 9));
    }

    @Test
    void emptyListContainsNothing() {
        assertFalse(RecursionBasics.contains(null, 0));
    }

    // ---------- max ----------

    @Test
    void maxInTheMiddle() {
        assertEquals(9, RecursionBasics.max(listOf(3, 9, 4)));
    }

    @Test
    void maxAtTheEnd() {
        assertEquals(3, RecursionBasics.max(listOf(1, 2, 3)));
    }

    @Test
    void maxOfNegativeValues() {
        assertEquals(-2, RecursionBasics.max(listOf(-5, -2, -9)));
    }

    @Test
    void maxOfSingleNode() {
        assertEquals(7, RecursionBasics.max(listOf(7)));
    }

    @Test
    void maxOfEmptyListIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> RecursionBasics.max(null));
    }

    // ---------- the rule of this exercise ----------

    @Test
    void solvedWithoutLoops() throws IOException {
        Path source = Path.of("src/main/java/sk/upjs/paz1a/tutoring/recap/exercise10/RecursionBasics.java");
        assertTrue(Files.exists(source),
                "Cannot find " + source.toAbsolutePath() + " - run the tests from the project folder");

        String code = Files.readString(source)
                .replaceAll("(?s)/\\*.*?\\*/", "")             // block and Javadoc comments
                .replaceAll("//[^\\n]*", "")                   // line comments
                .replaceAll("\"(\\\\.|[^\"\\\\])*\"", "\"\""); // string literals

        Matcher forbidden = Pattern.compile("\\b(for|while|do)\\b|forEach|Stream").matcher(code);
        if (forbidden.find()) {
            fail("Solve RecursionBasics with recursion only - found '" + forbidden.group() + "' in the code");
        }
    }
}
