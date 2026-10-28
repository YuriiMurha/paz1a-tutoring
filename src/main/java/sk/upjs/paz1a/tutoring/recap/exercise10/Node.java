package sk.upjs.paz1a.tutoring.recap.exercise10;

/**
 * One element of a singly linked list: a value and a reference to the next node.
 * <pre>
 * [3] -> [1] -> [4] -> null
 * </pre>
 * A list is just a reference to its first node (the "head"), and the empty list is {@code null}.
 * This class is complete - you only use it.
 */
public class Node {

    private final int value;
    private final Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    /**
     * Returns the next node, or null if this is the last node of the list.
     */
    public Node getNext() {
        return next;
    }
}
