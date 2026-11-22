package sk.upjs.paz1a.tutoring.exercise11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class StringPuzzlesTest {

    // ---------- (*) reverseWords ----------

    @Test
    void reversesFourWords() {
        assertEquals("fox brown quick the", StringPuzzles.reverseWords("the quick brown fox"));
    }

    @Test
    void reversesTwoWords() {
        assertEquals("world hello", StringPuzzles.reverseWords("hello world"));
    }

    @Test
    void singleWordStaysTheSame() {
        assertEquals("single", StringPuzzles.reverseWords("single"));
    }

    @Test
    void extraSpacesDisappear() {
        assertEquals("world big hello", StringPuzzles.reverseWords("  hello   big  world "),
                "Single spaces between the words, none at the start or end");
    }

    @Test
    void wordsKeepTheirCharacters() {
        assertEquals("World! Hello,", StringPuzzles.reverseWords("Hello, World!"),
                "Reverse the ORDER of the words, not the letters");
    }

    @Test
    void emptyAndBlankSentencesGiveEmptyString() {
        assertEquals("", StringPuzzles.reverseWords(""));
        assertEquals("", StringPuzzles.reverseWords("    "));
    }

    @Test
    void reverseWordsRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.reverseWords(null));
    }

    // ---------- (*) isAnagram ----------

    @Test
    void classicAnagrams() {
        assertTrue(StringPuzzles.isAnagram("listen", "silent"));
        assertTrue(StringPuzzles.isAnagram("evil", "vile"));
    }

    @Test
    void anagramIgnoresCaseSpacesAndPunctuation() {
        assertTrue(StringPuzzles.isAnagram("Dormitory", "Dirty room!"));
        assertTrue(StringPuzzles.isAnagram("The eyes", "They see"));
        assertTrue(StringPuzzles.isAnagram("a.b,c", "C B A"));
    }

    @Test
    void differentLettersAreNotAnagrams() {
        assertFalse(StringPuzzles.isAnagram("hello", "world"));
    }

    @Test
    void letterCountsMustMatch() {
        assertFalse(StringPuzzles.isAnagram("aab", "abb"), "Same letters, but not the same number of each");
    }

    @Test
    void oneExtraLetterIsNotAnAnagram() {
        assertFalse(StringPuzzles.isAnagram("abc", "ab"));
        assertFalse(StringPuzzles.isAnagram("ab", "abc"));
    }

    @Test
    void stringsWithoutLettersAreAnagrams() {
        assertTrue(StringPuzzles.isAnagram("", ""));
        assertTrue(StringPuzzles.isAnagram("!!! 42", ""));
    }

    @Test
    void wordIsAnAnagramOfItself() {
        assertTrue(StringPuzzles.isAnagram("Java", "java"));
    }

    @Test
    void isAnagramRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.isAnagram(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.isAnagram("a", null));
    }

    // ---------- (**) longestCommonPrefix ----------

    @Test
    void prefixOfThreeWords() {
        assertEquals("fl", StringPuzzles.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    @Test
    void noCommonPrefix() {
        assertEquals("", StringPuzzles.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    void shortestWordLimitsThePrefix() {
        assertEquals("inter", StringPuzzles.longestCommonPrefix(new String[]{"interview", "inter", "internet"}),
                "Careful with charAt past the end of a shorter word");
        assertEquals("inter", StringPuzzles.longestCommonPrefix(new String[]{"inter", "interview"}));
        assertEquals("inter", StringPuzzles.longestCommonPrefix(new String[]{"interview", "inter"}));
    }

    @Test
    void identicalWordsAreTheirOwnPrefix() {
        assertEquals("same", StringPuzzles.longestCommonPrefix(new String[]{"same", "same", "same"}));
    }

    @Test
    void singleWordIsItsOwnPrefix() {
        assertEquals("alone", StringPuzzles.longestCommonPrefix(new String[]{"alone"}));
    }

    @Test
    void emptyWordMeansEmptyPrefix() {
        assertEquals("", StringPuzzles.longestCommonPrefix(new String[]{"abc", ""}));
    }

    @Test
    void emptyArrayHasEmptyPrefix() {
        assertEquals("", StringPuzzles.longestCommonPrefix(new String[0]));
    }

    @Test
    void prefixIsCaseSensitive() {
        assertEquals("", StringPuzzles.longestCommonPrefix(new String[]{"Apple", "apple"}));
    }

    @Test
    void longestCommonPrefixRejectsNulls() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.longestCommonPrefix(null));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.longestCommonPrefix(new String[]{"a", null}));
    }

    // ---------- (**) caesarShift ----------

    @Test
    void shiftByOne() {
        assertEquals("bcd", StringPuzzles.caesarShift("abc", 1));
    }

    @Test
    void wrapsAroundFromZToA() {
        assertEquals("abc", StringPuzzles.caesarShift("xyz", 3));
        assertEquals("ABC", StringPuzzles.caesarShift("XYZ", 3));
    }

    @Test
    void keepsCaseAndOtherCharacters() {
        assertEquals("Khoor, Zruog!", StringPuzzles.caesarShift("Hello, World!", 3));
    }

    @Test
    void negativeShiftMovesBackwards() {
        assertEquals("Hello, World!", StringPuzzles.caesarShift("Khoor, Zruog!", -3));
        assertEquals("zab", StringPuzzles.caesarShift("abc", -1), "a shifted back by one wraps around to z");
    }

    @Test
    void shiftsLargerThanTheAlphabet() {
        assertEquals("abc", StringPuzzles.caesarShift("abc", 26));
        assertEquals("bcd", StringPuzzles.caesarShift("abc", 27));
        assertEquals("zab", StringPuzzles.caesarShift("abc", -27));
    }

    @Test
    void hugeNegativeShift() {
        // -1 000 000 = -38 462 * 26 + 12, so it is the same as a shift of 12
        assertEquals("mM", StringPuzzles.caesarShift("aA", -1_000_000), "% keeps the sign: -1000000 % 26 == -14");
    }

    @Test
    void extremeShiftsDoNotOverflow() {
        assertEquals("w", StringPuzzles.caesarShift("z", Integer.MAX_VALUE),
                "'z' - 'a' + Integer.MAX_VALUE overflows int - make the shift small first");
        assertEquals("c", StringPuzzles.caesarShift("a", Integer.MIN_VALUE));
    }

    @Test
    void zeroShiftChangesNothing() {
        assertEquals("Java 17!", StringPuzzles.caesarShift("Java 17!", 0));
    }

    @Test
    void shiftingBackUndoesTheShift() {
        String text = "The Quick Brown Fox Jumps Over The Lazy Dog.";
        for (int shift = -60; shift <= 60; shift++) {
            String secret = StringPuzzles.caesarShift(text, shift);
            assertEquals(text, StringPuzzles.caesarShift(secret, -shift), "shift " + shift);
        }
    }

    @Test
    void caesarOfEmptyTextIsEmpty() {
        assertEquals("", StringPuzzles.caesarShift("", 5));
    }

    @Test
    void caesarShiftRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.caesarShift(null, 1));
    }

    // ---------- (**) compress ----------

    @Test
    void compressesRuns() {
        assertEquals("a3b1c2d4", StringPuzzles.compress("aaabccdddd"));
    }

    @Test
    void everyCharacterAlone() {
        assertEquals("a1b1c1", StringPuzzles.compress("abc"));
        assertEquals("a1", StringPuzzles.compress("a"));
    }

    @Test
    void longRunHasACountWithSeveralDigits() {
        assertEquals("z12", StringPuzzles.compress("zzzzzzzzzzzz"));
    }

    @Test
    void sameCharacterLaterStartsANewRun() {
        assertEquals("a2b1a2", StringPuzzles.compress("aabaa"), "Only NEIGHBOURING equal characters form a run");
    }

    @Test
    void compressIsCaseSensitive() {
        assertEquals("a1A1a1", StringPuzzles.compress("aAa"));
    }

    @Test
    void spacesAndSymbolsAreCharactersToo() {
        assertEquals(" 2x1!3", StringPuzzles.compress("  x!!!"));
    }

    @Test
    void compressOfEmptyTextIsEmpty() {
        assertEquals("", StringPuzzles.compress(""));
    }

    @Test
    void compressRejectsDigits() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.compress("abc1"));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.compress("7"));
    }

    @Test
    void compressRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.compress(null));
    }

    // ---------- (**) isBalanced ----------

    @Test
    void simplePairs() {
        assertTrue(StringPuzzles.isBalanced("()"));
        assertTrue(StringPuzzles.isBalanced("[]"));
        assertTrue(StringPuzzles.isBalanced("{}"));
    }

    @Test
    void nestedAndSideBySide() {
        assertTrue(StringPuzzles.isBalanced("([]{})"));
        assertTrue(StringPuzzles.isBalanced("{[()()]}[]"));
    }

    @Test
    void crossedPairsAreNotBalanced() {
        assertFalse(StringPuzzles.isBalanced("([)]"),
                "Counting is not enough: a closing bracket must match the MOST RECENT open one");
    }

    @Test
    void wrongKindOfBracket() {
        assertFalse(StringPuzzles.isBalanced("(]"));
        assertFalse(StringPuzzles.isBalanced("{)"));
    }

    @Test
    void bracketLeftOpen() {
        assertFalse(StringPuzzles.isBalanced("(("), "Something is still open at the end");
        assertFalse(StringPuzzles.isBalanced("{[]"));
    }

    @Test
    void closingBeforeOpening() {
        assertFalse(StringPuzzles.isBalanced(")("), "Nothing is open when ')' comes");
        assertFalse(StringPuzzles.isBalanced("}"));
        assertFalse(StringPuzzles.isBalanced("())("));
    }

    @Test
    void otherCharactersAreIgnored() {
        assertTrue(StringPuzzles.isBalanced("if (a[0] > 1) { x(); }"));
        assertTrue(StringPuzzles.isBalanced("no brackets at all"));
        assertFalse(StringPuzzles.isBalanced("print(list[0)]"));
    }

    @Test
    void emptyCodeIsBalanced() {
        assertTrue(StringPuzzles.isBalanced(""));
    }

    @Test
    void deepNesting() {
        assertTrue(StringPuzzles.isBalanced("(".repeat(1000) + ")".repeat(1000)));
        assertFalse(StringPuzzles.isBalanced("(".repeat(1000) + ")".repeat(999)));
    }

    @Test
    void isBalancedRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.isBalanced(null));
    }

    // ---------- (***) decompress ----------

    @Test
    void expandsRuns() {
        assertEquals("aaabcc", StringPuzzles.decompress("a3b1c2"));
    }

    @Test
    void countsCanHaveSeveralDigits() {
        assertEquals("zzzzzzzzzzzz", StringPuzzles.decompress("z12"), "A count can have more than one digit");
        assertEquals("aaaaaaaaaabb", StringPuzzles.decompress("a10b2"));
        assertEquals(100, StringPuzzles.decompress("x100").length());
    }

    @Test
    void spacesAndSymbolsCanBeRepeated() {
        assertEquals("   !!", StringPuzzles.decompress(" 3!2"));
    }

    @Test
    void decompressOfEmptyCodeIsEmpty() {
        assertEquals("", StringPuzzles.decompress(""));
    }

    @Test
    void characterWithoutCountIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.decompress("a"));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.decompress("ab2"));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.decompress("a3b"));
    }

    @Test
    void codeStartingWithDigitIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.decompress("3a"));
    }

    @Test
    void decompressRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.decompress(null));
    }

    // ---------- (***) addBigNumbers ----------

    @Test
    void addsSmallNumbers() {
        assertEquals("579", StringPuzzles.addBigNumbers("123", "456"));
        assertEquals("5", StringPuzzles.addBigNumbers("2", "3"));
    }

    @Test
    void carryTravelsAllTheWay() {
        assertEquals("1000", StringPuzzles.addBigNumbers("999", "1"), "Don't forget the last carry");
        assertEquals("1000", StringPuzzles.addBigNumbers("1", "999"));
    }

    @Test
    void numbersOfDifferentLengths() {
        assertEquals("12412", StringPuzzles.addBigNumbers("12345", "67"));
        assertEquals("12412", StringPuzzles.addBigNumbers("67", "12345"));
    }

    @Test
    void addingZero() {
        assertEquals("0", StringPuzzles.addBigNumbers("0", "0"));
        assertEquals("42", StringPuzzles.addBigNumbers("0", "42"));
    }

    @Test
    void numbersTooBigForLong() {
        assertEquals("111111111011111111100",
                StringPuzzles.addBigNumbers("12345678901234567890", "98765432109876543210"));
        assertEquals("9223372036854775808", StringPuzzles.addBigNumbers("9223372036854775807", "1"),
                "Long.MAX_VALUE + 1 must not overflow here");
        assertEquals("1" + "0".repeat(50), StringPuzzles.addBigNumbers("9".repeat(50), "1"));
    }

    @Test
    void leadingZerosAreRemovedFromTheResult() {
        assertEquals("10", StringPuzzles.addBigNumbers("007", "3"));
        assertEquals("10", StringPuzzles.addBigNumbers("0005", "0005"));
        assertEquals("0", StringPuzzles.addBigNumbers("000", "0"), "Zero itself is written \"0\"");
    }

    @Test
    void addBigNumbersRejectsInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.addBigNumbers(null, "1"));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.addBigNumbers("1", ""));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.addBigNumbers("12a", "1"));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.addBigNumbers("-5", "1"));
    }

    // ---------- (***) longestPalindrome ----------

    @Test
    void oddLengthPalindrome() {
        assertEquals("bab", StringPuzzles.longestPalindrome("babad"), "\"bab\" and \"aba\" are equally long - take the leftmost");
    }

    @Test
    void evenLengthPalindrome() {
        assertEquals("bb", StringPuzzles.longestPalindrome("cbbd"), "Palindromes of even length have their centre between two characters");
    }

    @Test
    void wholeTextIsAPalindrome() {
        assertEquals("racecar", StringPuzzles.longestPalindrome("racecar"));
        assertEquals("aaaa", StringPuzzles.longestPalindrome("aaaa"));
    }

    @Test
    void palindromeHiddenInside() {
        assertEquals("geeksskeeg", StringPuzzles.longestPalindrome("forgeeksskeegfor"));
    }

    @Test
    void leftmostOfEquallyLongPalindromes() {
        assertEquals("aba", StringPuzzles.longestPalindrome("abacdfgdcaba"));
        assertEquals("aba", StringPuzzles.longestPalindrome("abaxcdc"));
    }

    @Test
    void withoutRepeatsTheFirstCharacterWins() {
        assertEquals("a", StringPuzzles.longestPalindrome("abc"));
    }

    @Test
    void palindromeIsCaseSensitive() {
        assertEquals("bb", StringPuzzles.longestPalindrome("Abba"));
    }

    @Test
    void shortTexts() {
        assertEquals("", StringPuzzles.longestPalindrome(""));
        assertEquals("x", StringPuzzles.longestPalindrome("x"));
    }

    @Test
    void longestPalindromeRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.longestPalindrome(null));
    }

    // ---------- (***) wordWrap ----------

    @Test
    void wrapsAtTheWidth() {
        assertEquals("the quick\nbrown fox\njumps over\nthe lazy\ndog",
                StringPuzzles.wordWrap("the quick brown fox jumps over the lazy dog", 10));
    }

    @Test
    void exactFitStaysOnOneLine() {
        assertEquals("one two", StringPuzzles.wordWrap("one two", 7), "A line of exactly width characters is fine");
    }

    @Test
    void oneCharacterTooLongStartsANewLine() {
        assertEquals("one\ntwo", StringPuzzles.wordWrap("one two", 6));
    }

    @Test
    void tooLongWordGetsALineOfItsOwn() {
        assertEquals("a\nverylongword\nb", StringPuzzles.wordWrap("a verylongword b", 5));
    }

    @Test
    void everythingFitsOnOneLine() {
        assertEquals("short text", StringPuzzles.wordWrap("short text", 100));
    }

    @Test
    void extraSpacesDisappearWhenWrapping() {
        assertEquals("lots of space", StringPuzzles.wordWrap("  lots   of    space  ", 20));
    }

    @Test
    void widthOnePutsEveryWordOnItsOwnLine() {
        assertEquals("a\nb\nc", StringPuzzles.wordWrap("a b c", 1));
    }

    @Test
    void blankTextGivesEmptyString() {
        assertEquals("", StringPuzzles.wordWrap("", 5));
        assertEquals("", StringPuzzles.wordWrap("    ", 5));
    }

    @Test
    void everyLineFollowsTheRules() {
        String text = "It was the best of times, it was the worst of times, it was the age of wisdom, "
                + "it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity";
        for (int width = 1; width <= 40; width++) {
            String wrapped = StringPuzzles.wordWrap(text, width);
            assertNotNull(wrapped);
            String[] lines = wrapped.split("\n", -1);
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                assertFalse(line.isEmpty(), "width " + width + ": empty line " + i);
                assertEquals(line.trim(), line, "width " + width + ": spaces at the start or end of line " + i);
                assertFalse(line.contains("  "), "width " + width + ": double space in line " + i);
                assertTrue(line.length() <= width || !line.contains(" "),
                        "width " + width + ": line " + i + " is too long: \"" + line + "\"");
                if (i + 1 < lines.length) {
                    String nextWord = lines[i + 1].split(" ")[0];
                    assertTrue(line.length() + 1 + nextWord.length() > width,
                            "width " + width + ": \"" + nextWord + "\" would still fit on line " + i);
                }
            }
            assertEquals(text, wrapped.replace('\n', ' '), "width " + width + ": the words must stay the same");
        }
    }

    @Test
    void wordWrapRejectsNullAndBadWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.wordWrap(null, 5));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.wordWrap("abc", 0));
        assertThrows(IllegalArgumentException.class, () -> StringPuzzles.wordWrap("abc", -3));
    }

    // ---------- rules ----------

    @Test
    void solvedWithStringsCharsAndArraysOnly() throws IOException {
        Path source = Path.of("src/main/java/sk/upjs/paz1a/tutoring/exercise11/StringPuzzles.java");
        assertTrue(Files.exists(source),
                "Cannot find " + source.toAbsolutePath() + " - run the tests from the project folder");

        String code = Files.readString(source)
                .replaceAll("(?s)/\\*.*?\\*/", "")             // block and Javadoc comments
                .replaceAll("//[^\\n]*", "")                   // line comments
                .replaceAll("\"(\\\\.|[^\"\\\\])*\"", "\"\""); // string literals

        Matcher forbidden = Pattern.compile("java\\.util|BigInteger|BigDecimal|Stream\\b|\\.stream\\(|\\.chars\\(|\\.codePoints\\(")
                .matcher(code);
        if (forbidden.find()) {
            fail("StringPuzzles may use only String, StringBuilder, Character, Integer, Math and arrays - found '"
                    + forbidden.group() + "' in the code");
        }
    }
}
