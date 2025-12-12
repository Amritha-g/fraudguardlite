// File: AccountService.java
// Manages accounts: create, store, and list them.

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private final Map<String, Account> accounts = new HashMap<>();
    private int nextId = 1;   // used to generate IDs like A1, A2, ...

    public Account createAccount(String ownerName, double initialBalance) {
        String id = "A" + nextId;
        nextId++;

        Account account = new Account(id, ownerName, initialBalance);
        accounts.put(id, account);
        return account;
    }

    public Account getAccount(String id) {
        return accounts.get(id);
    }

    public void printAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts created yet.");
            return;
        }

        System.out.println("Accounts:");
        for (Account acc : accounts.values()) {
            System.out.println(" - " + acc);
        }
    }
}
