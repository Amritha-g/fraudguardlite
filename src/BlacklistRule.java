// File: BlacklistRule.java
// Flags any transaction where the receiver account is blacklisted.

import java.util.HashSet;
import java.util.Set;

public class BlacklistRule implements FraudRule {

    private final Set<String> blacklistedAccounts = new HashSet<>();
    private static final int RISK_SCORE = 80;

    public BlacklistRule() {
        // Hard-coded for now; could be loaded from a file later
        blacklistedAccounts.add("BL1");
        blacklistedAccounts.add("SCAM1");
    }

    @Override
    public FraudAlert check(Transaction tx) {
        if (blacklistedAccounts.contains(tx.getToAccountId())) {
            String reason = "Receiver account " + tx.getToAccountId() + " is blacklisted";
            return new FraudAlert(tx.getId(), reason, RISK_SCORE);
        }
        return null;
    }
}
