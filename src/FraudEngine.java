// File: FraudEngine.java
// Runs all fraud rules on a transaction and returns any alerts.

import java.util.ArrayList;
import java.util.List;

public class FraudEngine {

    private final List<FraudRule> rules = new ArrayList<>();

    public FraudEngine(TransactionService transactionService) {
        rules.add(new HighAmountRule());
        rules.add(new VelocityRule(transactionService));
        rules.add(new BlacklistRule());
    }

    public List<FraudAlert> evaluate(Transaction tx) {
        List<FraudAlert> alerts = new ArrayList<>();

        for (FraudRule rule : rules) {
            FraudAlert alert = rule.check(tx);
            if (alert != null) {
                alerts.add(alert);
            }
        }

        return alerts;
    }
}
