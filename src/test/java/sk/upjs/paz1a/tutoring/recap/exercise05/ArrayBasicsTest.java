package sk.upjs.paz1a.tutoring.recap.exercise05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBasicsTest {

    // ---------- sumArray ----------

    @Test
    void sumOfSeveralNumbers() {
        assertEquals(10, ArrayBasics.sumArray(new int[]{1, 2, 3, 4}));
    }

    @Test
    void sumWithNegativeNumbers() {
        assertEquals(-2, ArrayBasics.sumArray(new int[]{5, -8, 1}));
    }

    @Test
    void sumOfSingleElement() {
        assertEquals(42, ArrayBasics.sumArray(new int[]{42}));
    }

    @Test
    void sumOfEmptyArrayIsZero() {
        assertEquals(0, ArrayBasics.sumArray(new int[0]));
    }

    @Test
    void sumRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayBasics.sumArray(null));
    }

    // ---------- containsValue ----------

    @Test
    void findsValueInTheMiddle() {
        assertTrue(ArrayBasics.containsValue(new int[]{4, 8, 15}, 8));
    }

    @Test
    void findsFirstAndLastValue() {
        int[] numbers = {4, 8, 15};
        assertTrue(ArrayBasics.containsValue(numbers, 4));
        assertTrue(ArrayBasics.containsValue(numbers, 15), "Does your loop reach the last element?");
    }

    @Test
    void missingValueIsNotFound() {
        assertFalse(ArrayBasics.containsValue(new int[]{4, 8, 15}, 16));
    }

    @Test
    void emptyArrayContainsNothing() {
        assertFalse(ArrayBasics.containsValue(new int[0], 0));
    }

    @Test
    void findsZeroAndNegativeValues() {
        int[] numbers = {-1, 0, 1};
        assertTrue(ArrayBasics.containsValue(numbers, 0));
        assertTrue(ArrayBasics.containsValue(numbers, -1));
    }

    @Test
    void containsRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayBasics.containsValue(null, 1));
    }

    // ---------- reverseArray ----------

    @Test
    void reversesArrayOfEvenLength() {
        assertArrayEquals(new int[]{4, 3, 2, 1}, ArrayBasics.reverseArray(new int[]{1, 2, 3, 4}));
    }

    @Test
    void reversesArrayOfOddLength() {
        assertArrayEquals(new int[]{3, 2, 1}, ArrayBasics.reverseArray(new int[]{1, 2, 3}));
    }

    @Test
    void singleElementStaysTheSame() {
        assertArrayEquals(new int[]{7}, ArrayBasics.reverseArray(new int[]{7}));
    }

    @Test
    void emptyArrayGivesEmptyArray() {
        int[] result = ArrayBasics.reverseArray(new int[0]);
        assertNotNull(result, "Return an empty array, not null");
        assertEquals(0, result.length);
    }

    @Test
    void returnsNewArrayAndKeepsInputUnchanged() {
        int[] numbers = {1, 2, 3};
        int[] result = ArrayBasics.reverseArray(numbers);
        assertNotNull(result);
        assertNotSame(numbers, result, "Create a new array instead of changing the input");
        assertArrayEquals(new int[]{1, 2, 3}, numbers);
    }

    @Test
    void reverseRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayBasics.reverseArray(null));
    }

    // ---------- countOccurrences ----------

    @Test
    void countsRepeatedValue() {
        assertEquals(3, ArrayBasics.countOccurrences(new int[]{1, 2, 2, 3, 2}, 2));
    }

    @Test
    void missingValueCountsZero() {
        assertEquals(0, ArrayBasics.countOccurrences(new int[]{1, 2, 3}, 7));
    }

    @Test
    void everyElementMatches() {
        assertEquals(3, ArrayBasics.countOccurrences(new int[]{5, 5, 5}, 5));
    }

    @Test
    void countsMatchesAtBothEnds() {
        assertEquals(2, ArrayBasics.countOccurrences(new int[]{9, 1, 9}, 9));
    }

    @Test
    void emptyArrayCountsZero() {
        assertEquals(0, ArrayBasics.countOccurrences(new int[0], 5));
    }

    @Test
    void countRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayBasics.countOccurrences(null, 1));
    }
}
