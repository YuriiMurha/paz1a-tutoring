package sk.upjs.paz1a.tutoring.exercise08;

/**
 * Exercise 08 - references and objects.
 * <p>
 * A player with a name and a score. The usual constructor, getters and setters are already done
 * (you practised those in exercise 07); your job here is the copy constructor.
 */
public class Player {

    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Copy constructor: creates a NEW player with the same name and score as {@code other}.
     * Afterwards the two players are independent - changing one must not change the other.
     * <p>
     * Hint: inside the Player class you may read {@code other.name} directly, because
     * {@code private} means "private to the class", not "private to one object".
     *
     * @throws IllegalArgumentException if other is null
     */
    public Player(Player other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy a null player");
        }
        // TODO: Implement this
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " (" + score + ")";
    }
}
