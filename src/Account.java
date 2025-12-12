// File: Account.java
// Represents a simple bank account in our system.

public class Account {

    private final String id;       // e.g. "A1", "A2"
    private final String ownerName;
    private double balance;

    public Account(String id, String ownerName, double balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{id='" + id + "', owner='" + ownerName + "', balance=" + balance + "}";
    }
}
