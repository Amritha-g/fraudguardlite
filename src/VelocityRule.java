// File: VelocityRule.java
// Flags if too many transactions are made in a short time from the same account.

import java.time.LocalDateTime;
import java.util.List;

public class VelocityRule implements FraudRule {

    private static final int MAX_TXN_IN_WINDOW = 3;   // how many before we worry
    private static final int WINDOW_SECONDS = 60;     // time window in seconds
    private static final int RISK_SCORE = 40;

    private final TransactionService transactionService;

    public VelocityRule(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public FraudAlert check(Transaction tx) {
        List<Transaction> allTx = transactionService.getAllTransactions();
        LocalDateTime now = tx.getTimestamp();

        int recentCount = 0;

        for (Transaction t : allTx) {
            if (t.getFromAccountId().equals(tx.getFromAccountId())) {
                // t is within [now - WINDOW_SECONDS, now]
                if (t.getTimestamp().plusSeconds(WINDOW_SECONDS).isAfter(now)) {
                    recentCount++;
                }
            }
        }

        if (recentCount >= MAX_TXN_IN_WINDOW) {
            String reason = "Too many quick transactions from account " + tx.getFromAccountId();
            return new FraudAlert(tx.getId(), reason, RISK_SCORE);
        }

        return null;
    }
}
