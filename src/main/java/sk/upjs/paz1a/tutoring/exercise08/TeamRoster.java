package sk.upjs.paz1a.tutoring.exercise08;

/**
 * A team with a fixed number of places, stored in a plain array.
 * <p>
 * Only the first {@code size} cells of {@code players} hold players; the rest are {@code null}.
 * Remember: the array stores REFERENCES to Player objects, not the players themselves.
 */
public class TeamRoster {

    private final Player[] players;
    private final int capacity;
    private int size;

    /**
     * Creates an empty roster with room for {@code capacity} players.
     *
     * @throws IllegalArgumentException if capacity is zero or negative
     */
    public TeamRoster(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive, but was " + capacity);
        }
        this.capacity = capacity;
        this.players = new Player[capacity];
        this.size = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    /**
     * Returns the player at {@code index} (0 = the first one added). This is the very same object
     * that was added, not a copy.
     *
     * @throws IllegalArgumentException if index is not between 0 and size - 1
     */
    public Player getPlayer(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index " + index + " is invalid for a roster of size " + size);
        }
        // TODO: Implement this
        return null;
    }

    /**
     * Puts the player (the reference itself, NOT a copy) into the first free place.
     *
     * @return true if the player was added, false if the roster is already full
     * @throws IllegalArgumentException if player is null
     */
    public boolean addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Cannot add a null player");
        }
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the player with the highest score, or null if the roster is empty. When several
     * players share the highest score, returns the one that was added first. Returns the stored
     * object itself, not a copy.
     */
    public Player findBestPlayer() {
        // TODO: Implement this
        return null;
    }

    /**
     * Returns a NEW roster with the same capacity whose players are COPIES of this roster's players
     * (same names and scores, same order). Afterwards the two rosters are completely independent:
     * changing a player in one of them, or adding a player to one of them, must not affect the other.
     * <p>
     * Hint: use the Player copy constructor.
     */
    public TeamRoster createDeepCopyRoster() {
        // TODO: Implement this
        return null;
    }
}
