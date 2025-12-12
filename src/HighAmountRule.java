// File: HighAmountRule.java
// Flags transactions over a certain amount as suspicious.

public class HighAmountRule implements FraudRule {

    private static final double LIMIT = 50000.0;
    private static final int RISK_SCORE = 60;

    @Override
    public FraudAlert check(Transaction tx) {
        if (tx.getAmount() > LIMIT) {
            String reason = "High transaction amount above " + LIMIT;
            return new FraudAlert(tx.getId(), reason, RISK_SCORE);
        }
        return null;
    }
}
