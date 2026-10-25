package sk.upjs.paz1a.tutoring.recap.exercise03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoopPracticeTest {

    // ---------- sumNumbersUpTo ----------

    @Test
    void sumUpToOne() {
        assertEquals(1, LoopPractice.sumNumbersUpTo(1));
    }

    @Test
    void sumUpToFive() {
        assertEquals(15, LoopPractice.sumNumbersUpTo(5));
    }

    @Test
    void sumUpToHundred() {
        assertEquals(5050, LoopPractice.sumNumbersUpTo(100));
    }

    @Test
    void sumUpToZeroIsZero() {
        assertEquals(0, LoopPractice.sumNumbersUpTo(0));
    }

    @Test
    void negativeLimitIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> LoopPractice.sumNumbersUpTo(-1));
    }

    // ---------- countDigits ----------

    @Test
    void zeroHasOneDigit() {
        assertEquals(1, LoopPractice.countDigits(0));
    }

    @Test
    void singleDigit() {
        assertEquals(1, LoopPractice.countDigits(7));
    }

    @Test
    void nineHasOneDigitAndTenHasTwo() {
        assertEquals(1, LoopPractice.countDigits(9));
        assertEquals(2, LoopPractice.countDigits(10));
    }

    @Test
    void severalDigits() {
        assertEquals(5, LoopPractice.countDigits(12345));
    }

    @Test
    void minusSignIsNotADigit() {
        assertEquals(3, LoopPractice.countDigits(-123), "Loop while the number is != 0, not while it is > 0");
    }

    @Test
    void extremeValues() {
        assertEquals(10, LoopPractice.countDigits(Integer.MAX_VALUE));
        assertEquals(10, LoopPractice.countDigits(Integer.MIN_VALUE));
    }

    // ---------- isPrime ----------

    @Test
    void smallPrimes() {
        assertTrue(LoopPractice.isPrime(2));
        assertTrue(LoopPractice.isPrime(3));
        assertTrue(LoopPractice.isPrime(5));
        assertTrue(LoopPractice.isPrime(7));
    }

    @Test
    void evenNumbersAboveTwoAreNotPrime() {
        assertFalse(LoopPractice.isPrime(4));
        assertFalse(LoopPractice.isPrime(100));
    }

    @Test
    void squaresOfPrimesAreNotPrime() {
        assertFalse(LoopPractice.isPrime(9));
        assertFalse(LoopPractice.isPrime(25));
        assertFalse(LoopPractice.isPrime(49), "Did you try divisors up to AND including the square root?");
    }

    @Test
    void largerPrimeAndLargerComposite() {
        assertTrue(LoopPractice.isPrime(7919));
        assertFalse(LoopPractice.isPrime(7917)); // 3 * 7 * 13 * 29
    }

    @Test
    void zeroAndOneAreNotPrime() {
        assertFalse(LoopPractice.isPrime(0));
        assertFalse(LoopPractice.isPrime(1));
    }

    @Test
    void negativeNumbersAreNotPrime() {
        assertFalse(LoopPractice.isPrime(-7));
        assertFalse(LoopPractice.isPrime(-2));
    }

    // ---------- generatePattern ----------

    @Test
    void threeRows() {
        assertEquals("*\n**\n***", LoopPractice.generatePattern(3));
    }

    @Test
    void oneRow() {
        assertEquals("*", LoopPractice.generatePattern(1));
    }

    @Test
    void fiveRows() {
        String expected = """
                *
                **
                ***
                ****
                *****""";
        assertEquals(expected, LoopPractice.generatePattern(5));
    }

    @Test
    void zeroRowsGiveEmptyString() {
        assertEquals("", LoopPractice.generatePattern(0));
    }

    @Test
    void negativeRowsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> LoopPractice.generatePattern(-1));
    }
}
