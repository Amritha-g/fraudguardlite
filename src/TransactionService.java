// File: TransactionService.java
// Stores and manages transactions.

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private final List<Transaction> transactions = new ArrayList<>();
    private int nextId = 1;

    public Transaction recordTransaction(String fromAccountId, String toAccountId, double amount) {
        int id = nextId;
        nextId++;

        Transaction tx = new Transaction(id, fromAccountId, toAccountId, amount, LocalDateTime.now());
        transactions.add(tx);
        return tx;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void printAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }

        System.out.println("Transactions:");
        for (Transaction tx : transactions) {
            System.out.println(" - " + tx);
        }
    }
}
