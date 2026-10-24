package sk.upjs.paz1a.tutoring.exercise07;

/**
 * Exercise 07 - classes and encapsulation.
 * <p>
 * The fields are private, so the only way to change an account is through its methods. Those
 * methods protect the class invariants: the account number and owner name are never blank and the
 * balance is never negative. The checks are already written - implement the rest.
 * <p>
 * Real banking software stores money as {@code BigDecimal} or as whole cents, because a
 * {@code double} cannot represent values like 0.1 exactly. We use {@code double} to keep the
 * exercise simple, which is why the tests compare balances with a small tolerance.
 */
public class BankAccount {

    private String accountNumber;
    private String ownerName;
    private double balance;

    /**
     * Creates an account with the given starting balance.
     *
     * @throws IllegalArgumentException if accountNumber or ownerName is null or blank,
     *                                  or if initialBalance is negative
     */
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        requireText(accountNumber, "Account number");
        requireText(ownerName, "Owner name");
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance must not be negative, but was " + initialBalance);
        }
        // TODO: Implement this (store the three values in the fields)
    }

    public String getAccountNumber() {
        // TODO: Implement this
        return null;
    }

    public String getOwnerName() {
        // TODO: Implement this
        return null;
    }

    public double getBalance() {
        // TODO: Implement this
        return 0.0;
    }

    /**
     * Adds {@code amount} to the balance.
     *
     * @throws IllegalArgumentException if amount is zero or negative
     */
    public void deposit(double amount) {
        requirePositive(amount);
        // TODO: Implement this
    }

    /**
     * Takes {@code amount} from the balance, but only if there is enough money.
     * Withdrawing exactly the whole balance is allowed.
     *
     * @return true if the money was withdrawn; false if the balance is too low
     *         (the balance then stays unchanged)
     * @throws IllegalArgumentException if amount is zero or negative
     */
    public boolean withdraw(double amount) {
        requirePositive(amount);
        // TODO: Implement this
        return false;
    }

    /**
     * Returns the account in exactly this format (it is what IntelliJ's
     * Code -> Generate -> toString() produces with its default template):
     * <pre>BankAccount{accountNumber='SK001', ownerName='Jana Novakova', balance=150.0}</pre>
     */
    @Override
    public String toString() {
        // TODO: Implement this
        return "";
    }

    private static void requireText(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(what + " must not be null or blank");
        }
    }

    private static void requirePositive(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive, but was " + amount);
        }
    }
}
