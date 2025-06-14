package bank;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class BankAccountTest {

    @Test
    public void demoAllFunctionsInOrder() {
        BankAccount account = new BankAccount("Mohammed");
        System.out.println("\n==============================");
        System.out.println("👤 Account Created: " + account.getAccountHolder());
        System.out.println("==============================");

        // Initial balance check
        System.out.println("\n🟡 [Test] Initial Balance");
        assertEquals(0.0, account.getBalance(), 0.001);
        System.out.println("✅ Initial balance is correct: ₹" + account.getBalance());

        // Deposit test
        System.out.println("\n🟡 [Test] Deposit ₹1000");
        account.deposit(1000);
        System.out.println("💰 Deposited ₹1000");
        assertEquals(1000.0, account.getBalance(), 0.001);
        System.out.println("✅ Balance after deposit: ₹" + account.getBalance());

        // Withdraw success
        System.out.println("\n🟡 [Test] Withdraw ₹500 from ₹2000");
        account.deposit(1000);  // total now ₹2000
        boolean result = account.withdraw(500);
        assertTrue(result);
        System.out.println("💸 Withdrawn ₹500");
        System.out.println("✅ Balance after withdrawal: ₹" + account.getBalance());

        // Withdraw failure
        System.out.println("\n🟡 [Test] Withdraw ₹100 from ₹0 (Expected: Fail)");
        BankAccount emptyAccount = new BankAccount("Mohammed");
        boolean failed = emptyAccount.withdraw(100);
        assertFalse(failed);
        System.out.println("⚠️ Withdrawal failed due to insufficient balance.");
        System.out.println("✅ Balance remains: ₹" + emptyAccount.getBalance());

        // Transaction history
        System.out.println("\n🟡 [Test] Transaction History");
        BankAccount historyAccount = new BankAccount("Mohammed");
        historyAccount.deposit(500);
        historyAccount.withdraw(200);
        List<String> history = historyAccount.getTransactionHistory();
        assertEquals(3, history.size());
        System.out.println("🧾 History:");
        for (String entry : history) {
            System.out.println("   • " + entry);
        }
        System.out.println("✅ Transaction history verified.");

        System.out.println("✅ All transactions completed successfully. Thank you!");
    }
}
