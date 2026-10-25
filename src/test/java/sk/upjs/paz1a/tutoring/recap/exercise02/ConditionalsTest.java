package sk.upjs.paz1a.tutoring.recap.exercise02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionalsTest {

    private static final double DELTA = 1e-9;

    // ---------- classifyGrade ----------

    @Test
    void gradeA() {
        assertEquals("A", Conditionals.classifyGrade(100));
        assertEquals("A", Conditionals.classifyGrade(95));
        assertEquals("A", Conditionals.classifyGrade(90));
    }

    @Test
    void gradeB() {
        assertEquals("B", Conditionals.classifyGrade(89));
        assertEquals("B", Conditionals.classifyGrade(80));
    }

    @Test
    void gradeC() {
        assertEquals("C", Conditionals.classifyGrade(79));
        assertEquals("C", Conditionals.classifyGrade(70));
    }

    @Test
    void gradeD() {
        assertEquals("D", Conditionals.classifyGrade(69));
        assertEquals("D", Conditionals.classifyGrade(60));
    }

    @Test
    void gradeE() {
        assertEquals("E", Conditionals.classifyGrade(59));
        assertEquals("E", Conditionals.classifyGrade(50));
    }

    @Test
    void gradeFX() {
        assertEquals("FX", Conditionals.classifyGrade(49));
        assertEquals("FX", Conditionals.classifyGrade(0));
    }

    @Test
    void percentageOutOfRangeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> Conditionals.classifyGrade(-1));
        assertThrows(IllegalArgumentException.class, () -> Conditionals.classifyGrade(101));
    }

    // ---------- isLeapYear ----------

    @Test
    void yearDivisibleByFourIsLeap() {
        assertTrue(Conditionals.isLeapYear(2024));
        assertTrue(Conditionals.isLeapYear(1996));
    }

    @Test
    void ordinaryYearIsNotLeap() {
        assertFalse(Conditionals.isLeapYear(2023));
        assertFalse(Conditionals.isLeapYear(2025));
    }

    @Test
    void centuryYearIsNotLeap() {
        assertFalse(Conditionals.isLeapYear(1900));
        assertFalse(Conditionals.isLeapYear(2100));
    }

    @Test
    void everyFourHundredthYearIsLeap() {
        assertTrue(Conditionals.isLeapYear(2000));
        assertTrue(Conditionals.isLeapYear(2400));
    }

    @Test
    void yearOneIsNotLeap() {
        assertFalse(Conditionals.isLeapYear(1));
    }

    @Test
    void yearZeroAndNegativeYearsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> Conditionals.isLeapYear(0));
        assertThrows(IllegalArgumentException.class, () -> Conditionals.isLeapYear(-4));
    }

    // ---------- maxOfThree ----------

    @Test
    void maxIsFirst() {
        assertEquals(9, Conditionals.maxOfThree(9, 2, 5));
    }

    @Test
    void maxIsSecond() {
        assertEquals(9, Conditionals.maxOfThree(2, 9, 5));
    }

    @Test
    void maxIsThird() {
        assertEquals(9, Conditionals.maxOfThree(2, 5, 9));
    }

    @Test
    void allNumbersEqual() {
        assertEquals(4, Conditionals.maxOfThree(4, 4, 4));
    }

    @Test
    void twoNumbersShareTheMax() {
        assertEquals(7, Conditionals.maxOfThree(7, 7, 3));
        assertEquals(7, Conditionals.maxOfThree(3, 7, 7));
        assertEquals(7, Conditionals.maxOfThree(7, 3, 7));
    }

    @Test
    void negativeNumbers() {
        assertEquals(-2, Conditionals.maxOfThree(-5, -2, -9), "Don't start from 0 - all numbers may be negative");
    }

    @Test
    void extremeValues() {
        assertEquals(Integer.MAX_VALUE, Conditionals.maxOfThree(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, Conditionals.maxOfThree(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    // ---------- calculateDiscountedPrice ----------

    @Test
    void adultWithoutStudentCardPaysFullPrice() {
        assertEquals(100.0, Conditionals.calculateDiscountedPrice(100.0, false, 30), DELTA);
    }

    @Test
    void studentGetsQuarterOff() {
        assertEquals(75.0, Conditionals.calculateDiscountedPrice(100.0, true, 20), DELTA);
    }

    @Test
    void childUnderSixIsFree() {
        assertEquals(0.0, Conditionals.calculateDiscountedPrice(100.0, false, 5), DELTA);
        assertEquals(0.0, Conditionals.calculateDiscountedPrice(100.0, false, 0), DELTA);
    }

    @Test
    void sixYearOldIsNoLongerFree() {
        assertEquals(100.0, Conditionals.calculateDiscountedPrice(100.0, false, 6), DELTA);
    }

    @Test
    void seniorDiscountStartsAtSixtyFive() {
        assertEquals(50.0, Conditionals.calculateDiscountedPrice(100.0, false, 65), DELTA);
        assertEquals(100.0, Conditionals.calculateDiscountedPrice(100.0, false, 64), DELTA);
    }

    @Test
    void discountsAreNotCombined() {
        assertEquals(50.0, Conditionals.calculateDiscountedPrice(100.0, true, 70), DELTA,
                "A senior student gets only the senior discount");
        assertEquals(0.0, Conditionals.calculateDiscountedPrice(100.0, true, 4), DELTA);
    }

    @Test
    void freeItemStaysFree() {
        assertEquals(0.0, Conditionals.calculateDiscountedPrice(0.0, true, 20), DELTA);
    }

    @Test
    void negativePriceOrAgeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> Conditionals.calculateDiscountedPrice(-1.0, false, 30));
        assertThrows(IllegalArgumentException.class, () -> Conditionals.calculateDiscountedPrice(10.0, false, -1));
    }
}
