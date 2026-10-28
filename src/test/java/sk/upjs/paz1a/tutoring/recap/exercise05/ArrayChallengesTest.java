package sk.upjs.paz1a.tutoring.recap.exercise05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayChallengesTest {

    // ---------- (*) indexOf ----------

    @Test
    void indexOfFindsFirstOccurrence() {
        assertEquals(0, ArrayChallenges.indexOf(new int[]{4, 8, 4}, 4));
        assertEquals(1, ArrayChallenges.indexOf(new int[]{4, 8, 4}, 8));
    }

    @Test
    void indexOfFindsLastElement() {
        assertEquals(2, ArrayChallenges.indexOf(new int[]{1, 2, 3}, 3));
    }

    @Test
    void indexOfMissingValueIsMinusOne() {
        assertEquals(-1, ArrayChallenges.indexOf(new int[]{4, 8, 4}, 5));
    }

    @Test
    void indexOfInEmptyArrayIsMinusOne() {
        assertEquals(-1, ArrayChallenges.indexOf(new int[0], 0));
    }

    @Test
    void indexOfRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.indexOf(null, 1));
    }

    // ---------- (*) runningSum ----------

    @Test
    void runningSumOfSeveralNumbers() {
        assertArrayEquals(new int[]{1, 3, 6, 10}, ArrayChallenges.runningSum(new int[]{1, 2, 3, 4}));
    }

    @Test
    void runningSumWithNegativeNumbers() {
        assertArrayEquals(new int[]{5, 3, 0}, ArrayChallenges.runningSum(new int[]{5, -2, -3}));
    }

    @Test
    void runningSumOfSingleElement() {
        assertArrayEquals(new int[]{7}, ArrayChallenges.runningSum(new int[]{7}));
    }

    @Test
    void runningSumOfEmptyArrayIsEmpty() {
        int[] result = ArrayChallenges.runningSum(new int[0]);
        assertNotNull(result, "Return an empty array, not null");
        assertEquals(0, result.length);
    }

    @Test
    void runningSumReturnsNewArrayAndKeepsInput() {
        int[] numbers = {1, 2, 3};
        int[] result = ArrayChallenges.runningSum(numbers);
        assertNotNull(result);
        assertNotSame(numbers, result, "Create a new array instead of changing the input");
        assertArrayEquals(new int[]{1, 2, 3}, numbers);
    }

    @Test
    void runningSumRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.runningSum(null));
    }

    // ---------- (*) isPalindrome ----------

    @Test
    void oddLengthPalindrome() {
        assertTrue(ArrayChallenges.isPalindrome(new int[]{1, 2, 1}));
    }

    @Test
    void evenLengthPalindrome() {
        assertTrue(ArrayChallenges.isPalindrome(new int[]{4, 7, 7, 4}));
    }

    @Test
    void twoDifferentValuesAreNotAPalindrome() {
        assertFalse(ArrayChallenges.isPalindrome(new int[]{1, 2}));
    }

    @Test
    void mismatchInTheMiddleIsFound() {
        assertFalse(ArrayChallenges.isPalindrome(new int[]{1, 2, 3, 4, 2, 1}));
    }

    @Test
    void emptyAndSingleElementArraysArePalindromes() {
        assertTrue(ArrayChallenges.isPalindrome(new int[0]));
        assertTrue(ArrayChallenges.isPalindrome(new int[]{5}));
    }

    @Test
    void isPalindromeRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.isPalindrome(null));
    }

    // ---------- (**) rotateLeft ----------

    @Test
    void rotateLeftByTwo() {
        assertArrayEquals(new int[]{3, 4, 5, 1, 2}, ArrayChallenges.rotateLeft(new int[]{1, 2, 3, 4, 5}, 2));
    }

    @Test
    void rotatingByZeroKeepsTheOrder() {
        assertArrayEquals(new int[]{1, 2, 3}, ArrayChallenges.rotateLeft(new int[]{1, 2, 3}, 0));
    }

    @Test
    void rotatingByTheLengthKeepsTheOrder() {
        assertArrayEquals(new int[]{1, 2, 3}, ArrayChallenges.rotateLeft(new int[]{1, 2, 3}, 3));
    }

    @Test
    void stepsLargerThanTheLengthWrapAround() {
        assertArrayEquals(new int[]{2, 3, 1}, ArrayChallenges.rotateLeft(new int[]{1, 2, 3}, 4));
    }

    @Test
    void rotatingAnEmptyArrayGivesAnEmptyArray() {
        int[] result = ArrayChallenges.rotateLeft(new int[0], 3);
        assertNotNull(result, "Careful: x % 0 throws an ArithmeticException");
        assertEquals(0, result.length);
    }

    @Test
    void rotateReturnsNewArrayAndKeepsInput() {
        int[] numbers = {1, 2, 3};
        int[] result = ArrayChallenges.rotateLeft(numbers, 1);
        assertNotNull(result);
        assertNotSame(numbers, result, "Create a new array instead of changing the input");
        assertArrayEquals(new int[]{1, 2, 3}, numbers);
    }

    @Test
    void rotateRejectsNullAndNegativeSteps() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.rotateLeft(null, 1));
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.rotateLeft(new int[]{1, 2}, -1));
    }

    // ---------- (**) secondLargest ----------

    @Test
    void secondLargestOfDifferentValues() {
        assertEquals(7, ArrayChallenges.secondLargest(new int[]{4, 9, 2, 7}));
    }

    @Test
    void duplicatesOfTheMaximumCountOnce() {
        assertEquals(3, ArrayChallenges.secondLargest(new int[]{5, 1, 5, 3}));
    }

    @Test
    void secondLargestOfNegativeNumbers() {
        assertEquals(-5, ArrayChallenges.secondLargest(new int[]{-2, -7, -5}));
    }

    @Test
    void maximumAtTheStartOrTheEnd() {
        assertEquals(2, ArrayChallenges.secondLargest(new int[]{9, 1, 2}));
        assertEquals(2, ArrayChallenges.secondLargest(new int[]{1, 2, 9}));
    }

    @Test
    void twoDifferentValues() {
        assertEquals(3, ArrayChallenges.secondLargest(new int[]{8, 3}));
        assertEquals(3, ArrayChallenges.secondLargest(new int[]{3, 8}));
    }

    @Test
    void arrayWithoutSecondLargestIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.secondLargest(new int[0]));
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.secondLargest(new int[]{4}));
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.secondLargest(new int[]{7, 7, 7}));
    }

    @Test
    void secondLargestRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.secondLargest(null));
    }

    // ---------- (**) removeDuplicates ----------

    @Test
    void removeDuplicatesKeepsFirstOccurrencesInOrder() {
        assertArrayEquals(new int[]{3, 1, 2}, ArrayChallenges.removeDuplicates(new int[]{3, 1, 3, 2, 1}));
    }

    @Test
    void arrayWithoutDuplicatesIsCopied() {
        int[] numbers = {1, 2, 3};
        int[] result = ArrayChallenges.removeDuplicates(numbers);
        assertArrayEquals(new int[]{1, 2, 3}, result);
        assertNotSame(numbers, result, "Return a new array, not the input array");
    }

    @Test
    void allEqualValuesLeaveOne() {
        assertArrayEquals(new int[]{5}, ArrayChallenges.removeDuplicates(new int[]{5, 5, 5}));
    }

    @Test
    void removeDuplicatesOfEmptyArrayIsEmpty() {
        int[] result = ArrayChallenges.removeDuplicates(new int[0]);
        assertNotNull(result, "Return an empty array, not null");
        assertEquals(0, result.length);
    }

    @Test
    void removeDuplicatesKeepsInputUnchanged() {
        int[] numbers = {2, 2, 1};
        assertArrayEquals(new int[]{2, 1}, ArrayChallenges.removeDuplicates(numbers));
        assertArrayEquals(new int[]{2, 2, 1}, numbers);
    }

    @Test
    void removeDuplicatesRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.removeDuplicates(null));
    }

    // ---------- (***) longestRun ----------

    @Test
    void longestRunInTheMiddle() {
        assertEquals(3, ArrayChallenges.longestRun(new int[]{1, 2, 2, 2, 3, 3}));
    }

    @Test
    void longestRunAtTheEnd() {
        assertEquals(3, ArrayChallenges.longestRun(new int[]{1, 2, 2, 2}),
                "Did you also count the run that reaches the last element?");
    }

    @Test
    void longestRunAtTheStart() {
        assertEquals(2, ArrayChallenges.longestRun(new int[]{4, 4, 1, 2}));
    }

    @Test
    void noRepeatedNeighbors() {
        assertEquals(1, ArrayChallenges.longestRun(new int[]{1, 2, 3}));
    }

    @Test
    void allValuesEqual() {
        assertEquals(4, ArrayChallenges.longestRun(new int[]{7, 7, 7, 7}));
    }

    @Test
    void singleElementIsARunOfOne() {
        assertEquals(1, ArrayChallenges.longestRun(new int[]{5}));
    }

    @Test
    void emptyArrayHasNoRun() {
        assertEquals(0, ArrayChallenges.longestRun(new int[0]));
    }

    @Test
    void longestRunRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.longestRun(null));
    }

    // ---------- (***) mergeSorted ----------

    @Test
    void mergesTwoSortedArrays() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 7, 8, 9},
                ArrayChallenges.mergeSorted(new int[]{1, 4, 7}, new int[]{2, 3, 8, 9}));
    }

    @Test
    void mergeKeepsDuplicates() {
        assertArrayEquals(new int[]{1, 4, 4, 4, 5}, ArrayChallenges.mergeSorted(new int[]{1, 4, 4}, new int[]{4, 5}));
    }

    @Test
    void mergeWithOneEmptyArray() {
        assertArrayEquals(new int[]{1, 2}, ArrayChallenges.mergeSorted(new int[0], new int[]{1, 2}));
        assertArrayEquals(new int[]{3}, ArrayChallenges.mergeSorted(new int[]{3}, new int[0]));
    }

    @Test
    void mergeOfTwoEmptyArraysIsEmpty() {
        assertArrayEquals(new int[0], ArrayChallenges.mergeSorted(new int[0], new int[0]));
    }

    @Test
    void oneArrayEntirelyBeforeTheOther() {
        assertArrayEquals(new int[]{1, 2, 5, 6}, ArrayChallenges.mergeSorted(new int[]{1, 2}, new int[]{5, 6}),
                "When one array runs out, copy what is left of the other one");
        assertArrayEquals(new int[]{1, 2, 5, 6}, ArrayChallenges.mergeSorted(new int[]{5, 6}, new int[]{1, 2}));
    }

    @Test
    void mergeWithNegativeNumbers() {
        assertArrayEquals(new int[]{-5, -3, 0, 2}, ArrayChallenges.mergeSorted(new int[]{-5, 0}, new int[]{-3, 2}));
    }

    @Test
    void mergeRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.mergeSorted(null, new int[]{1}));
        assertThrows(IllegalArgumentException.class, () -> ArrayChallenges.mergeSorted(new int[]{1}, null));
    }
}
