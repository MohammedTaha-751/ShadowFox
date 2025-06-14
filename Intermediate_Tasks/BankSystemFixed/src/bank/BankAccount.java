package bank;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited ₹" + amount);
        } else {
            transactionHistory.add("Failed deposit attempt: ₹" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew ₹" + amount);
            return true;
        } else {
            transactionHistory.add("Failed withdrawal attempt: ₹" + amount);
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
