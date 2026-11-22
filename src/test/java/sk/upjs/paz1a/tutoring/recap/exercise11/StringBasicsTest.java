package sk.upjs.paz1a.tutoring.recap.exercise11;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StringBasicsTest {

    // ---------- (*) countChar ----------

    @Test
    void countsRepeatedCharacter() {
        assertEquals(3, StringBasics.countChar("banana", 'a'));
        assertEquals(2, StringBasics.countChar("banana", 'n'));
    }

    @Test
    void missingCharacterCountsZero() {
        assertEquals(0, StringBasics.countChar("banana", 'z'));
    }

    @Test
    void countIsCaseSensitive() {
        assertEquals(0, StringBasics.countChar("Banana", 'b'));
        assertEquals(1, StringBasics.countChar("Banana", 'B'));
    }

    @Test
    void countsFirstAndLastCharacter() {
        assertEquals(2, StringBasics.countChar("abca", 'a'), "Does your loop reach the last character?");
    }

    @Test
    void spacesAreCharactersToo() {
        assertEquals(2, StringBasics.countChar("a b c", ' '));
    }

    @Test
    void emptyStringCountsZero() {
        assertEquals(0, StringBasics.countChar("", 'x'));
    }

    @Test
    void countCharRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.countChar(null, 'a'));
    }

    // ---------- (*) middle ----------

    @Test
    void middleOfOddLength() {
        assertEquals("b", StringBasics.middle("abc"));
        assertEquals("y", StringBasics.middle("kayak"));
    }

    @Test
    void middleOfEvenLength() {
        assertEquals("bc", StringBasics.middle("abcd"));
        assertEquals("av", StringBasics.middle("Java"));
    }

    @Test
    void singleCharacterIsItsOwnMiddle() {
        assertEquals("x", StringBasics.middle("x"));
    }

    @Test
    void twoCharactersAreBothTheMiddle() {
        assertEquals("ab", StringBasics.middle("ab"));
    }

    @Test
    void middleOfEmptyStringIsEmpty() {
        assertEquals("", StringBasics.middle(""), "An empty word has an empty middle - check it before substring");
    }

    @Test
    void middleRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.middle(null));
    }

    // ---------- (*) capitalize ----------

    @Test
    void capitalizesLowerCaseWord() {
        assertEquals("Java", StringBasics.capitalize("java"));
    }

    @Test
    void fixesMixedCase() {
        assertEquals("Java", StringBasics.capitalize("jAVA"), "The rest of the word must be lower case");
        assertEquals("Hello", StringBasics.capitalize("HELLO"));
    }

    @Test
    void capitalizesSingleLetter() {
        assertEquals("X", StringBasics.capitalize("x"));
    }

    @Test
    void capitalizedWordStaysTheSame() {
        assertEquals("Java", StringBasics.capitalize("Java"));
    }

    @Test
    void nonLettersStayAsTheyAre() {
        assertEquals("3d", StringBasics.capitalize("3D"));
        assertEquals("O'neil", StringBasics.capitalize("o'NEIL"));
    }

    @Test
    void capitalizeOfEmptyStringIsEmpty() {
        assertEquals("", StringBasics.capitalize(""));
    }

    @Test
    void capitalizeRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.capitalize(null));
    }

    // ---------- (*) isCommand ----------

    @Test
    void recognisesAllThreeCommands() {
        assertTrue(StringBasics.isCommand("start"));
        assertTrue(StringBasics.isCommand("stop"));
        assertTrue(StringBasics.isCommand("pause"));
    }

    @Test
    void comparesContentNotReferences() {
        // These strings are created while the program runs, so they are NOT the same objects as the
        // literals "stop" and "pause" in your code. == compares the arrows and says false.
        String typed = new String("stop");
        String built = String.valueOf(new char[]{'p', 'a', 'u', 's', 'e'});
        assertTrue(StringBasics.isCommand(typed), "Compare Strings with equals(), not == (lecture, section 3)");
        assertTrue(StringBasics.isCommand(built), "Compare Strings with equals(), not == (lecture, section 3)");
    }

    @Test
    void commandIgnoresUpperAndLowerCase() {
        assertTrue(StringBasics.isCommand("STOP"));
        assertTrue(StringBasics.isCommand("Pause"));
        assertTrue(StringBasics.isCommand("sTaRt"));
    }

    @Test
    void ignoresSpacesAroundTheCommand() {
        assertTrue(StringBasics.isCommand("  start  "));
        assertTrue(StringBasics.isCommand("\tpause\n"));
        assertTrue(StringBasics.isCommand("  Stop"));
    }

    @Test
    void otherWordsAreNotCommands() {
        assertFalse(StringBasics.isCommand("starts"));
        assertFalse(StringBasics.isCommand("go"));
        assertFalse(StringBasics.isCommand("st op"), "Only spaces around the word are ignored, not inside it");
        assertFalse(StringBasics.isCommand(""));
        assertFalse(StringBasics.isCommand("   "));
    }

    @Test
    void isCommandRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.isCommand(null));
    }

    // ---------- (**) fileExtension ----------

    @Test
    void simpleExtension() {
        assertEquals("png", StringBasics.fileExtension("photo.png"));
    }

    @Test
    void onlyTheLastDotCounts() {
        assertEquals("gz", StringBasics.fileExtension("archive.tar.gz"));
    }

    @Test
    void noDotMeansNoExtension() {
        assertEquals("", StringBasics.fileExtension("README"), "indexOf/lastIndexOf return -1 when nothing is found");
    }

    @Test
    void dotAtTheEndGivesEmptyExtension() {
        assertEquals("", StringBasics.fileExtension("notes."));
    }

    @Test
    void nameStartingWithDot() {
        assertEquals("gitignore", StringBasics.fileExtension(".gitignore"));
    }

    @Test
    void extensionKeepsItsCase() {
        assertEquals("PDF", StringBasics.fileExtension("Report.PDF"));
    }

    @Test
    void fileExtensionRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.fileExtension(null));
    }

    // ---------- (**) sumOfDigits ----------

    @Test
    void sumsDigitsBetweenLetters() {
        assertEquals(6, StringBasics.sumOfDigits("a1b2c3"));
    }

    @Test
    void sumsAStringOfDigits() {
        assertEquals(8, StringBasics.sumOfDigits("2024"));
    }

    @Test
    void usesTheDigitValueNotTheCharacterCode() {
        assertEquals(7, StringBasics.sumOfDigits("ab7"), "'7' has the code 55, but '7' - '0' is 7");
    }

    @Test
    void zeroAndNineCount() {
        assertEquals(9, StringBasics.sumOfDigits("0 9 0"));
    }

    @Test
    void noDigitsGivesZero() {
        assertEquals(0, StringBasics.sumOfDigits("no digits here!"));
        assertEquals(0, StringBasics.sumOfDigits(""));
    }

    @Test
    void sumOfDigitsRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.sumOfDigits(null));
    }

    // ---------- (**) countWords ----------

    @Test
    void countsWordsOfSimpleSentence() {
        assertEquals(4, StringBasics.countWords("the quick brown fox"));
    }

    @Test
    void singleWord() {
        assertEquals(1, StringBasics.countWords("hello"));
    }

    @Test
    void severalSpacesBetweenWords() {
        assertEquals(3, StringBasics.countWords("many   spaces    here"));
    }

    @Test
    void spacesAtStartAndEndAreNotWords() {
        assertEquals(2, StringBasics.countWords("   hello world   "),
                "Spaces at the start or end must not create empty words");
    }

    @Test
    void emptyStringHasNoWords() {
        assertEquals(0, StringBasics.countWords(""), "Careful: \"\".split(\" \") is an array with ONE empty string");
    }

    @Test
    void onlySpacesHasNoWords() {
        assertEquals(0, StringBasics.countWords("     "));
    }

    @Test
    void punctuationBelongsToTheWord() {
        assertEquals(2, StringBasics.countWords("Hello, world!"));
    }

    @Test
    void countWordsRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.countWords(null));
    }

    // ---------- (**) firstAlphabetically ----------

    @Test
    void findsTheAlphabeticallyFirstWord() {
        assertEquals("banana", StringBasics.firstAlphabetically(new String[]{"pear", "banana", "cherry"}));
    }

    @Test
    void firstWordCanBeAnywhere() {
        assertEquals("apple", StringBasics.firstAlphabetically(new String[]{"apple", "pear", "fig"}));
        assertEquals("fig", StringBasics.firstAlphabetically(new String[]{"pear", "kiwi", "fig"}));
    }

    @Test
    void alphabeticalOrderIgnoresCase() {
        assertEquals("Apple", StringBasics.firstAlphabetically(new String[]{"pear", "Apple", "banana"}));
        assertEquals("apple", StringBasics.firstAlphabetically(new String[]{"Zebra", "apple"}),
                "compareTo puts every upper-case letter before every lower-case one ('Z' is 90, 'a' is 97) - ignore the case");
    }

    @Test
    void shorterPrefixComesFirst() {
        assertEquals("car", StringBasics.firstAlphabetically(new String[]{"cart", "car", "carton"}));
    }

    @Test
    void equalWordsKeepTheFirstOne() {
        assertEquals("apple", StringBasics.firstAlphabetically(new String[]{"apple", "APPLE", "Apple"}));
        assertEquals("APPLE", StringBasics.firstAlphabetically(new String[]{"APPLE", "apple"}));
    }

    @Test
    void singleWordIsFirst() {
        assertEquals("solo", StringBasics.firstAlphabetically(new String[]{"solo"}));
    }

    @Test
    void arrayIsNotChanged() {
        String[] words = {"pear", "apple", "fig"};
        StringBasics.firstAlphabetically(words);
        assertArrayEquals(new String[]{"pear", "apple", "fig"}, words);
    }

    @Test
    void firstAlphabeticallyRejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.firstAlphabetically(null));
        assertThrows(IllegalArgumentException.class, () -> StringBasics.firstAlphabetically(new String[0]));
    }

    // ---------- (**) numberList ----------

    @Test
    void listUpToFive() {
        assertEquals("1, 2, 3, 4, 5", StringBasics.numberList(5));
    }

    @Test
    void listOfOneHasNoSeparator() {
        assertEquals("1", StringBasics.numberList(1));
    }

    @Test
    void noSeparatorAfterTheLastNumber() {
        assertEquals("1, 2, 3", StringBasics.numberList(3), "No \", \" after the last number");
    }

    @Test
    void numbersWithSeveralDigits() {
        assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12", StringBasics.numberList(12));
    }

    @Test
    void listOfZeroIsEmpty() {
        assertEquals("", StringBasics.numberList(0));
    }

    @Test
    void fastEvenForAHugeList() {
        String list = assertTimeoutPreemptively(Duration.ofSeconds(2), () -> StringBasics.numberList(100_000),
                "Too slow: are you gluing Strings with + in a loop? Use a StringBuilder (lecture, section 5)");
        assertNotNull(list);
        assertTrue(list.startsWith("1, 2, 3, "));
        assertTrue(list.endsWith(", 99999, 100000"));
        assertEquals(688_893, list.length());
    }

    @Test
    void numberListRejectsNegativeN() {
        assertThrows(IllegalArgumentException.class, () -> StringBasics.numberList(-1));
    }
}
