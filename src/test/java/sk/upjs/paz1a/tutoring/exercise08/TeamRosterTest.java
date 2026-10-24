package sk.upjs.paz1a.tutoring.exercise08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamRosterTest {

    private static TeamRoster rosterOf(int capacity, Player... players) {
        TeamRoster roster = new TeamRoster(capacity);
        for (Player player : players) {
            roster.addPlayer(player);
        }
        return roster;
    }

    // ---------- references: read this test first - it already passes ----------

    @Test
    void assignmentCopiesTheReferenceNotTheObject() {
        Player anna = new Player("Anna", 12);
        Player sameAnna = anna; // no new object: two variables pointing to ONE Player

        sameAnna.setScore(50);

        assertEquals(50, anna.getScore());
        assertSame(anna, sameAnna);
    }

    // ---------- Player copy constructor ----------

    @Test
    void copyConstructorCreatesAnIndependentPlayer() {
        Player original = new Player("Anna", 12);
        Player copy = new Player(original);

        assertEquals("Anna", copy.getName());
        assertEquals(12, copy.getScore());

        copy.setScore(99);
        assertEquals(12, original.getScore(), "Changing the copy must not change the original");
    }

    @Test
    void copyConstructorRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Player((Player) null));
    }

    // ---------- adding and reading players ----------

    @Test
    void newRosterIsEmpty() {
        TeamRoster roster = new TeamRoster(3);
        assertEquals(0, roster.getSize());
        assertEquals(3, roster.getCapacity());
    }

    @Test
    void constructorRejectsZeroOrNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new TeamRoster(0));
        assertThrows(IllegalArgumentException.class, () -> new TeamRoster(-1));
    }

    @Test
    void addPlayerStoresPlayersInOrder() {
        TeamRoster roster = new TeamRoster(3);
        Player anna = new Player("Anna", 12);
        Player boris = new Player("Boris", 8);

        assertTrue(roster.addPlayer(anna));
        assertTrue(roster.addPlayer(boris));

        assertEquals(2, roster.getSize());
        assertSame(anna, roster.getPlayer(0));
        assertSame(boris, roster.getPlayer(1));
    }

    @Test
    void addPlayerReturnsFalseWhenRosterIsFull() {
        Player anna = new Player("Anna", 12);
        Player boris = new Player("Boris", 8);
        TeamRoster roster = rosterOf(2, anna, boris);

        assertFalse(roster.addPlayer(new Player("Cyril", 5)));
        assertEquals(2, roster.getSize());
        assertSame(boris, roster.getPlayer(1));
    }

    @Test
    void addPlayerRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> new TeamRoster(2).addPlayer(null));
    }

    @Test
    void getPlayerRejectsInvalidIndex() {
        TeamRoster roster = rosterOf(3, new Player("Anna", 12));
        assertThrows(IllegalArgumentException.class, () -> roster.getPlayer(-1));
        assertThrows(IllegalArgumentException.class, () -> roster.getPlayer(1));
    }

    @Test
    void rosterStoresReferencesNotCopies() {
        Player anna = new Player("Anna", 12);
        TeamRoster roster = rosterOf(3, anna);

        anna.setScore(40); // change the player OUTSIDE the roster...

        assertEquals(40, roster.getPlayer(0).getScore(), // ...and the roster sees the change
                "addPlayer must store the reference itself, not a copy");
    }

    // ---------- findBestPlayer ----------

    @Test
    void findBestPlayerReturnsHighestScore() {
        Player boris = new Player("Boris", 30);
        TeamRoster roster = rosterOf(5, new Player("Anna", 10), boris, new Player("Cyril", 20));
        assertSame(boris, roster.findBestPlayer());
    }

    @Test
    void findBestPlayerWhenBestIsLast() {
        Player cyril = new Player("Cyril", 3);
        TeamRoster roster = rosterOf(3, new Player("Anna", 1), new Player("Boris", 2), cyril);
        assertSame(cyril, roster.findBestPlayer());
    }

    @Test
    void findBestPlayerWithNegativeScores() {
        Player boris = new Player("Boris", -2);
        TeamRoster roster = rosterOf(2, new Player("Anna", -5), boris);
        assertSame(boris, roster.findBestPlayer());
    }

    @Test
    void findBestPlayerPrefersFirstAddedOnTie() {
        Player anna = new Player("Anna", 7);
        TeamRoster roster = rosterOf(2, anna, new Player("Boris", 7));
        assertSame(anna, roster.findBestPlayer());
    }

    @Test
    void findBestPlayerIgnoresEmptyPlaces() {
        Player anna = new Player("Anna", 7);
        TeamRoster roster = rosterOf(10, anna); // 9 of the 10 places are still null
        assertSame(anna, roster.findBestPlayer());
    }

    @Test
    void emptyRosterHasNoBestPlayer() {
        assertNull(new TeamRoster(3).findBestPlayer());
    }

    // ---------- createDeepCopyRoster ----------

    @Test
    void deepCopyHasSameCapacitySizeAndValues() {
        TeamRoster roster = rosterOf(4, new Player("Anna", 12), new Player("Boris", 8));

        TeamRoster copy = roster.createDeepCopyRoster();

        assertNotNull(copy);
        assertNotSame(roster, copy);
        assertEquals(4, copy.getCapacity());
        assertEquals(2, copy.getSize());
        assertEquals("Anna", copy.getPlayer(0).getName());
        assertEquals(12, copy.getPlayer(0).getScore());
        assertEquals("Boris", copy.getPlayer(1).getName());
        assertEquals(8, copy.getPlayer(1).getScore());
    }

    @Test
    void deepCopyContainsNewPlayerObjects() {
        TeamRoster roster = rosterOf(4, new Player("Anna", 12), new Player("Boris", 8));

        TeamRoster copy = roster.createDeepCopyRoster();

        assertNotNull(copy);
        assertEquals(2, copy.getSize());
        for (int i = 0; i < 2; i++) {
            assertNotSame(roster.getPlayer(i), copy.getPlayer(i),
                    "Player " + i + " is shared by both rosters - use the Player copy constructor");
        }
    }

    @Test
    void changingOriginalPlayerDoesNotChangeTheCopy() {
        Player anna = new Player("Anna", 12);
        TeamRoster copy = rosterOf(3, anna).createDeepCopyRoster();

        anna.setScore(100);

        assertEquals(12, copy.getPlayer(0).getScore());
    }

    @Test
    void changingCopiedPlayerDoesNotChangeTheOriginal() {
        Player anna = new Player("Anna", 12);
        TeamRoster copy = rosterOf(3, anna).createDeepCopyRoster();

        copy.getPlayer(0).setScore(0);

        assertEquals(12, anna.getScore());
    }

    @Test
    void copyAndOriginalHaveSeparateArrays() {
        TeamRoster roster = rosterOf(3, new Player("Anna", 12));
        TeamRoster copy = roster.createDeepCopyRoster();
        Player eva = new Player("Eva", 5);
        Player boris = new Player("Boris", 8);

        copy.addPlayer(eva);
        roster.addPlayer(boris);

        assertSame(eva, copy.getPlayer(1), "The copy needs its own array, not the original's");
        assertSame(boris, roster.getPlayer(1));
    }

    @Test
    void deepCopyOfEmptyRoster() {
        TeamRoster copy = new TeamRoster(2).createDeepCopyRoster();
        assertNotNull(copy);
        assertEquals(0, copy.getSize());
        assertEquals(2, copy.getCapacity());
    }
}
