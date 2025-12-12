// File: FraudRule.java
// A rule that checks a transaction for fraud indicators

public interface FraudRule {
    FraudAlert check(Transaction tx);
}
