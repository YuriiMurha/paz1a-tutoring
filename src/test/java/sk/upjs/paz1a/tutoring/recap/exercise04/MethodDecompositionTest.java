package sk.upjs.paz1a.tutoring.recap.exercise04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MethodDecompositionTest {

    private static final double DELTA = 1e-9;

    // ---------- isValidPassword ----------

    @Test
    void validPassword() {
        assertTrue(MethodDecomposition.isValidPassword("Secret123"));
    }

    @Test
    void exactlyEightCharactersAreEnough() {
        assertTrue(MethodDecomposition.isValidPassword("Abcdef12"));
    }

    @Test
    void sevenCharactersAreTooFew() {
        assertFalse(MethodDecomposition.isValidPassword("Abcde12"));
    }

    @Test
    void passwordWithoutDigitIsInvalid() {
        assertFalse(MethodDecomposition.isValidPassword("SecretPassword"));
    }

    @Test
    void passwordWithoutUppercaseIsInvalid() {
        assertFalse(MethodDecomposition.isValidPassword("secret123"));
    }

    @Test
    void digitsOnlyIsInvalid() {
        assertFalse(MethodDecomposition.isValidPassword("12345678"));
    }

    @Test
    void emptyPasswordIsInvalid() {
        assertFalse(MethodDecomposition.isValidPassword(""));
    }

    @Test
    void digitAndUppercaseMayBeAnywhere() {
        assertTrue(MethodDecomposition.isValidPassword("abcdefg1X"),
                "Check every character, not just the first one");
    }

    @Test
    void spacesAndSymbolsAreAllowed() {
        assertTrue(MethodDecomposition.isValidPassword("My pass #1"));
    }

    @Test
    void nullPasswordIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.isValidPassword(null));
    }

    // ---------- greatestCommonDivisor ----------

    @Test
    void gcdOfTwelveAndEighteen() {
        assertEquals(6, MethodDecomposition.greatestCommonDivisor(12, 18));
    }

    @Test
    void orderOfArgumentsDoesNotMatter() {
        assertEquals(6, MethodDecomposition.greatestCommonDivisor(18, 12));
    }

    @Test
    void coprimeNumbers() {
        assertEquals(1, MethodDecomposition.greatestCommonDivisor(17, 5));
    }

    @Test
    void sameNumbers() {
        assertEquals(7, MethodDecomposition.greatestCommonDivisor(7, 7));
    }

    @Test
    void oneNumberDividesTheOther() {
        assertEquals(25, MethodDecomposition.greatestCommonDivisor(100, 25));
    }

    @Test
    void zeroAndANumber() {
        assertEquals(5, MethodDecomposition.greatestCommonDivisor(0, 5));
        assertEquals(5, MethodDecomposition.greatestCommonDivisor(5, 0));
    }

    @Test
    void negativeNumbersAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.greatestCommonDivisor(-4, 6));
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.greatestCommonDivisor(4, -6));
    }

    @Test
    void twoZerosAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.greatestCommonDivisor(0, 0));
    }

    // ---------- calculateBMI ----------

    @Test
    void typicalBmi() {
        assertEquals(70.0 / (1.75 * 1.75), MethodDecomposition.calculateBMI(70.0, 1.75), DELTA);
    }

    @Test
    void exactValues() {
        assertEquals(12.5, MethodDecomposition.calculateBMI(50.0, 2.0), DELTA);
        assertEquals(25.0, MethodDecomposition.calculateBMI(100.0, 2.0), DELTA);
    }

    @Test
    void zeroOrNegativeWeightIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.calculateBMI(0.0, 1.75));
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.calculateBMI(-70.0, 1.75));
    }

    @Test
    void zeroOrNegativeHeightIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.calculateBMI(70.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> MethodDecomposition.calculateBMI(70.0, -1.75));
    }
}
