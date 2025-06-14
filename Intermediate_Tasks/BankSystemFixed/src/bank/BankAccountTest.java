package bank;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class BankAccountTest {

    private BankAccount account;

    @Before
    public void setUp() {
        account = new BankAccount("Mohammed Taha Ahamed");
        System.out.println("\n================================================");
        System.out.println("👤 Account Created: " + account.getAccountHolder());
        System.out.println("================================================");
    }

    @Test
    public void testInitialBalance() {
        System.out.println("\n🟡 [Test] Initial Balance");
        assertEquals(0.0, account.getBalance(), 0.001);
        System.out.println("✅ Initial balance is correct: ₹" + account.getBalance());
    }

    @Test
    public void testWithdrawFail_InsufficientBalance() {
        System.out.println("\n🟡 [Test] Withdraw ₹100 from ₹0 (Expected: Fail)");
        boolean result = account.withdraw(100);
        assertFalse(result);
        System.out.println("⚠️ Withdrawal failed due to insufficient balance.");
        System.out.println("✅ Balance remains: ₹" + account.getBalance());
    }

    @Test
    public void testTransactionHistory() {
        System.out.println("\n🟡 [Test] Transaction History");
        account.deposit(500);
        account.withdraw(200);
        List<String> history = account.getTransactionHistory();
        assertEquals(2, history.size());
        System.out.println("🧾 History:");
        for (String entry : history) {
            System.out.println("   • " + entry);
        }
        System.out.println("✅ Transaction history verified.");
    }
}
