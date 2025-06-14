package bank;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {

    private BankAccount account;

    @Before
    public void setUp() {
        account = new BankAccount("Mohammed");
    }

    @Test
    public void testInitialBalance() {
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDeposit() {
        account.deposit(1000);
        assertEquals(1000.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawSuccess() {
        account.deposit(2000);
        boolean result = account.withdraw(500);
        assertTrue(result);
        assertEquals(1500.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawFail_InsufficientBalance() {
        boolean result = account.withdraw(100);
        assertFalse(result);
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    public void testTransactionHistory() {
        account.deposit(500);
        account.withdraw(200);
        List<String> history = account.getTransactionHistory();
        assertEquals(2, history.size());
        assertTrue(history.get(0).contains("Deposited ₹500"));
        assertTrue(history.get(1).contains("Withdrew ₹200"));
    }
}