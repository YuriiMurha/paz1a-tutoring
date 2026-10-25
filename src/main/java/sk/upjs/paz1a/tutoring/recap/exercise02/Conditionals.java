package sk.upjs.paz1a.tutoring.recap.exercise02;

/**
 * Recap 02 - conditionals and branching.
 * <p>
 * Practise {@code if / else if / else} chains and the boolean operators {@code &&}, {@code ||} and
 * {@code !}. Most bugs here sit exactly on a boundary (89 vs 90, age 64 vs 65), and that is where
 * the tests look.
 */
public class Conditionals {

    public static final int CHILD_AGE_LIMIT = 6; // younger than this: free
    public static final int SENIOR_AGE = 65;     // this age or older: senior discount
    public static final double SENIOR_DISCOUNT = 0.50;
    public static final double STUDENT_DISCOUNT = 0.25;

    private Conditionals() {
        // only static methods here, so nobody needs to create a Conditionals object
    }

    /**
     * Converts a percentage score to a grade on the Slovak university scale:
     * 90-100 -> "A", 80-89 -> "B", 70-79 -> "C", 60-69 -> "D", 50-59 -> "E", 0-49 -> "FX".
     *
     * @throws IllegalArgumentException if percentage is below 0 or above 100
     */
    public static String classifyGrade(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100, but was " + percentage);
        }
        // TODO: Implement this
        return null;
    }

    /**
     * Returns true for leap years of the Gregorian calendar: a year divisible by 4 is a leap year,
     * except years divisible by 100, which are leap years only when they are also divisible by 400.
     * Examples: 2024 -> true, 2023 -> false, 1900 -> false, 2000 -> true.
     *
     * @throws IllegalArgumentException if year is 0 or negative (there is no year 0)
     */
    public static boolean isLeapYear(int year) {
        if (year < 1) {
            throw new IllegalArgumentException("Year must be 1 or later, but was " + year);
        }
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the largest of the three numbers. Write it with if-statements - no {@code Math.max}.
     */
    public static int maxOfThree(int a, int b, int c) {
        // TODO: Implement this
        return 0;
    }

    /**
     * Returns the ticket price after the discount the customer is entitled to. Discounts are NOT
     * combined: go through the rules in this order and apply only the first one that matches.
     * <ol>
     *     <li>younger than 6: free (0.0)</li>
     *     <li>65 or older: 50 % off</li>
     *     <li>has a student card: 25 % off</li>
     *     <li>everybody else: full price</li>
     * </ol>
     * Example: a 70-year-old with a student card pays 50.0 of 100.0 (only the senior discount).
     *
     * @throws IllegalArgumentException if originalPrice or age is negative
     */
    public static double calculateDiscountedPrice(double originalPrice, boolean hasStudentCard, int age) {
        if (originalPrice < 0 || age < 0) {
            throw new IllegalArgumentException("Price and age must not be negative, but got "
                    + originalPrice + " and " + age);
        }
        // TODO: Implement this
        return 0.0;
    }
}
