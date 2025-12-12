// File: Transaction.java
// Represents a single money transfer between two accounts.

import java.time.LocalDateTime;

public class Transaction {

    private final int id;
    private final String fromAccountId;
    private final String toAccountId;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(int id, String fromAccountId, String toAccountId,
                       double amount, LocalDateTime timestamp) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{id=" + id +
                ", from='" + fromAccountId + '\'' +
                ", to='" + toAccountId + '\'' +
                ", amount=" + amount +
                ", time=" + timestamp +
                '}';
    }
}
