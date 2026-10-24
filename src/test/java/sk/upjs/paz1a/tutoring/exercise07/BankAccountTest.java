package sk.upjs.paz1a.tutoring.exercise07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    // a double cannot store most decimal numbers exactly, so money is compared with a small tolerance
    private static final double DELTA = 1e-9;

    private BankAccount account;

    @BeforeEach
    void createAccount() {
        account = new BankAccount("SK001", "Jana Novakova", 100.0);
    }

    // ---------- constructor and getters ----------

    @Test
    void constructorStoresAllValues() {
        assertEquals("SK001", account.getAccountNumber());
        assertEquals("Jana Novakova", account.getOwnerName());
        assertEquals(100.0, account.getBalance(), DELTA);
    }

    @Test
    void accountCanStartWithZeroBalance() {
        assertEquals(0.0, new BankAccount("SK002", "Peter", 0.0).getBalance(), DELTA);
    }

    @Test
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("SK002", "Peter", -0.01));
    }

    @Test
    void constructorRejectsMissingAccountNumber() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null, "Peter", 10.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("   ", "Peter", 10.0));
    }

    @Test
    void constructorRejectsMissingOwnerName() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("SK002", null, 10.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("SK002", "", 10.0));
    }

    // ---------- deposit ----------

    @Test
    void depositIncreasesBalance() {
        account.deposit(50.5);
        assertEquals(150.5, account.getBalance(), DELTA);
    }

    @Test
    void depositsAddUp() {
        account.deposit(10.0);
        account.deposit(20.25);
        assertEquals(130.25, account.getBalance(), DELTA);
    }

    @Test
    void depositOfSmallDecimalAmounts() {
        BankAccount empty = new BankAccount("SK002", "Peter", 0.0);
        empty.deposit(0.1);
        empty.deposit(0.2);
        // with doubles 0.1 + 0.2 is 0.30000000000000004 - DELTA makes the test tolerate that
        assertEquals(0.3, empty.getBalance(), DELTA);
    }

    @Test
    void depositRejectsNegativeAmountAndKeepsBalance() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));
        assertEquals(100.0, account.getBalance(), DELTA);
    }

    @Test
    void depositRejectsZero() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
    }

    // ---------- withdraw ----------

    @Test
    void withdrawWithEnoughMoneySucceeds() {
        assertTrue(account.withdraw(30.0));
        assertEquals(70.0, account.getBalance(), DELTA);
    }

    @Test
    void withdrawWholeBalanceSucceeds() {
        assertTrue(account.withdraw(100.0));
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    void withdrawMoreThanBalanceFailsAndKeepsBalance() {
        assertFalse(account.withdraw(100.01));
        assertEquals(100.0, account.getBalance(), DELTA);
    }

    @Test
    void withdrawRejectsNegativeAmountAndKeepsBalance() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5.0));
        assertEquals(100.0, account.getBalance(), DELTA);
    }

    @Test
    void withdrawRejectsZero() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0));
    }

    // ---------- toString ----------

    @Test
    void toStringShowsAllFields() {
        assertEquals("BankAccount{accountNumber='SK001', ownerName='Jana Novakova', balance=100.0}",
                account.toString());
    }

    @Test
    void toStringShowsCurrentBalance() {
        account.deposit(50.0);
        assertEquals("BankAccount{accountNumber='SK001', ownerName='Jana Novakova', balance=150.0}",
                account.toString());
    }

    // ---------- encapsulation ----------

    @Test
    void allInstanceFieldsArePrivate() {
        for (Field field : BankAccount.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue; // constants and tool-generated fields are not part of an account's state
            }
            assertTrue(Modifier.isPrivate(field.getModifiers()),
                    "Field '" + field.getName() + "' must be private");
        }
    }

    @Test
    void thereIsNoSetterForBalance() {
        assertThrows(NoSuchMethodException.class,
                () -> BankAccount.class.getMethod("setBalance", double.class),
                "A public setBalance() would let anyone skip the checks in deposit() and withdraw()");
    }
}
