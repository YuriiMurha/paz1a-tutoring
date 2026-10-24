package sk.upjs.paz1a.tutoring.exercise05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatsTest {

    private static final double DELTA = 1e-9;

    // ---------- calculateAverage ----------

    @Test
    void averageOfSeveralNumbers() {
        assertEquals(2.5, ArrayStats.calculateAverage(new int[]{1, 2, 3, 4}), DELTA);
    }

    @Test
    void averageIsNotCutByIntegerDivision() {
        assertEquals(1.5, ArrayStats.calculateAverage(new int[]{1, 2}), DELTA);
    }

    @Test
    void averageOfNegativeNumbers() {
        assertEquals(-1.0, ArrayStats.calculateAverage(new int[]{-4, 2, -1}), DELTA);
    }

    @Test
    void averageOfSingleElementIsThatElement() {
        assertEquals(7.0, ArrayStats.calculateAverage(new int[]{7}), DELTA);
    }

    @Test
    void averageOfZerosIsZero() {
        assertEquals(0.0, ArrayStats.calculateAverage(new int[]{0, 0, 0}), DELTA);
    }

    @Test
    void averageDoesNotOverflowForHugeValues() {
        int max = Integer.MAX_VALUE;
        assertEquals(max, ArrayStats.calculateAverage(new int[]{max, max}), DELTA,
                "The sum of two Integer.MAX_VALUE values does not fit into an int - use a long");
    }

    @Test
    void averageRejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.calculateAverage(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.calculateAverage(new int[0]));
    }

    // ---------- findMin ----------

    @Test
    void findMinInMixedArray() {
        assertEquals(-2, ArrayStats.findMin(new int[]{4, -2, 7, 0}));
    }

    @Test
    void findMinOfPositiveNumbersIsNotZero() {
        assertEquals(5, ArrayStats.findMin(new int[]{8, 5, 7}),
                "Start with the first element, not with 0");
    }

    @Test
    void findMinWhenSmallestIsLast() {
        assertEquals(1, ArrayStats.findMin(new int[]{3, 2, 1}));
    }

    @Test
    void findMinOfSingleElement() {
        assertEquals(42, ArrayStats.findMin(new int[]{42}));
    }

    @Test
    void findMinWithExtremeValues() {
        assertEquals(Integer.MIN_VALUE, ArrayStats.findMin(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }

    @Test
    void findMinRejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.findMin(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.findMin(new int[0]));
    }

    // ---------- isSortedAscending ----------

    @Test
    void sortedArrayIsSorted() {
        assertTrue(ArrayStats.isSortedAscending(new int[]{1, 2, 5, 9}));
    }

    @Test
    void equalNeighboursAreAllowed() {
        assertTrue(ArrayStats.isSortedAscending(new int[]{1, 2, 2, 5}));
    }

    @Test
    void allEqualValuesAreSorted() {
        assertTrue(ArrayStats.isSortedAscending(new int[]{4, 4, 4}));
    }

    @Test
    void singleElementIsSorted() {
        assertTrue(ArrayStats.isSortedAscending(new int[]{5}));
    }

    @Test
    void unsortedInTheMiddle() {
        assertFalse(ArrayStats.isSortedAscending(new int[]{1, 3, 2, 4}));
    }

    @Test
    void unsortedOnlyAtTheEnd() {
        assertFalse(ArrayStats.isSortedAscending(new int[]{1, 2, 3, 0}),
                "Did your loop compare the last pair?");
    }

    @Test
    void unsortedOnlyAtTheStart() {
        assertFalse(ArrayStats.isSortedAscending(new int[]{2, 1, 3, 4}));
    }

    @Test
    void descendingArrayIsNotSorted() {
        assertFalse(ArrayStats.isSortedAscending(new int[]{3, 2, 1}));
    }

    @Test
    void isSortedRejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.isSortedAscending(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.isSortedAscending(new int[0]));
    }

    // ---------- filterPositive ----------

    @Test
    void filterKeepsPositiveValuesInOrder() {
        assertArrayEquals(new int[]{3, 7, 2}, ArrayStats.filterPositive(new int[]{3, -1, 0, 7, -5, 2}));
    }

    @Test
    void zeroIsNotPositive() {
        assertArrayEquals(new int[]{}, ArrayStats.filterPositive(new int[]{0, 0}));
    }

    @Test
    void noPositiveValuesGiveEmptyArrayNotNull() {
        int[] result = ArrayStats.filterPositive(new int[]{-3, -1});
        assertNotNull(result, "Return an empty array, not null");
        assertEquals(0, result.length);
    }

    @Test
    void allPositiveValuesAreCopiedIntoNewArray() {
        int[] numbers = {1, 2, 3};
        int[] result = ArrayStats.filterPositive(numbers);
        assertArrayEquals(new int[]{1, 2, 3}, result);
        assertNotSame(numbers, result, "Return a new array, not the input array");
    }

    @Test
    void filterOfSinglePositiveElement() {
        assertArrayEquals(new int[]{5}, ArrayStats.filterPositive(new int[]{5}));
    }

    @Test
    void filterDoesNotChangeTheInput() {
        int[] numbers = {3, -1, 0};
        ArrayStats.filterPositive(numbers);
        assertArrayEquals(new int[]{3, -1, 0}, numbers);
    }

    @Test
    void filterRejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.filterPositive(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.filterPositive(new int[0]));
    }
}
