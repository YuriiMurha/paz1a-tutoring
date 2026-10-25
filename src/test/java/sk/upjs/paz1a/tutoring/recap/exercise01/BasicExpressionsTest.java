package sk.upjs.paz1a.tutoring.recap.exercise01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicExpressionsTest {

    private static final double DELTA = 1e-9;

    // ---------- fahrenheitToCelsius ----------

    @Test
    void freezingPointOfWater() {
        assertEquals(0.0, BasicExpressions.fahrenheitToCelsius(32), DELTA);
    }

    @Test
    void boilingPointOfWater() {
        assertEquals(100.0, BasicExpressions.fahrenheitToCelsius(212), DELTA,
                "Is 5 / 9 computed as an integer division (which gives 0)?");
    }

    @Test
    void minusFortyIsTheSameInBothScales() {
        assertEquals(-40.0, BasicExpressions.fahrenheitToCelsius(-40), DELTA);
    }

    @Test
    void humanBodyTemperature() {
        assertEquals(37.0, BasicExpressions.fahrenheitToCelsius(98.6), DELTA);
    }

    @Test
    void zeroFahrenheitIsBelowZeroCelsius() {
        assertEquals(-160.0 / 9, BasicExpressions.fahrenheitToCelsius(0), DELTA);
    }

    @Test
    void absoluteZeroIsStillAllowed() {
        assertEquals(-273.15, BasicExpressions.fahrenheitToCelsius(-459.67), DELTA);
    }

    @Test
    void temperatureBelowAbsoluteZeroIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> BasicExpressions.fahrenheitToCelsius(-459.68));
        assertThrows(IllegalArgumentException.class, () -> BasicExpressions.fahrenheitToCelsius(-1000));
    }

    // ---------- calculateHypotenuseSquared ----------

    @Test
    void classicThreeFourFiveTriangle() {
        assertEquals(25, BasicExpressions.calculateHypotenuseSquared(3, 4));
    }

    @Test
    void anotherRightTriangle() {
        assertEquals(169, BasicExpressions.calculateHypotenuseSquared(5, 12));
    }

    @Test
    void oneLegOfLengthZero() {
        assertEquals(49, BasicExpressions.calculateHypotenuseSquared(0, 7));
        assertEquals(49, BasicExpressions.calculateHypotenuseSquared(7, 0));
    }

    @Test
    void bothLegsZero() {
        assertEquals(0, BasicExpressions.calculateHypotenuseSquared(0, 0));
    }

    @Test
    void negativeLengthsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> BasicExpressions.calculateHypotenuseSquared(-3, 4));
        assertThrows(IllegalArgumentException.class, () -> BasicExpressions.calculateHypotenuseSquared(3, -4));
    }

    // ---------- isEven ----------

    @Test
    void positiveEvenNumber() {
        assertTrue(BasicExpressions.isEven(4));
    }

    @Test
    void positiveOddNumber() {
        assertFalse(BasicExpressions.isEven(7));
    }

    @Test
    void zeroIsEven() {
        assertTrue(BasicExpressions.isEven(0));
    }

    @Test
    void negativeEvenNumber() {
        assertTrue(BasicExpressions.isEven(-6));
    }

    @Test
    void negativeOddNumber() {
        assertFalse(BasicExpressions.isEven(-3), "In Java -3 % 2 is -1, not 1");
    }

    @Test
    void extremeValues() {
        assertFalse(BasicExpressions.isEven(Integer.MAX_VALUE));
        assertTrue(BasicExpressions.isEven(Integer.MIN_VALUE));
    }

    // ---------- extractLastDigit ----------

    @Test
    void lastDigitOfPositiveNumber() {
        assertEquals(3, BasicExpressions.extractLastDigit(123));
    }

    @Test
    void singleDigitNumber() {
        assertEquals(7, BasicExpressions.extractLastDigit(7));
    }

    @Test
    void zeroEndsWithZero() {
        assertEquals(0, BasicExpressions.extractLastDigit(0));
    }

    @Test
    void multipleOfTenEndsWithZero() {
        assertEquals(0, BasicExpressions.extractLastDigit(1230));
    }

    @Test
    void negativeNumberGivesPositiveDigit() {
        assertEquals(3, BasicExpressions.extractLastDigit(-123), "In Java -123 % 10 is -3");
    }

    @Test
    void smallestIntEndsWithEight() {
        assertEquals(8, BasicExpressions.extractLastDigit(Integer.MIN_VALUE),
                "Integer.MIN_VALUE is -2147483648 - and Math.abs() of it is still negative");
    }
}
