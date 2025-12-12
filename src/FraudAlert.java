// File: FraudAlert.java
// Represents a flagged/suspicious aspect of a transaction.

public class FraudAlert {

    private final int transactionId;
    private final String reason;
    private final int riskScore;

    public FraudAlert(int transactionId, String reason, int riskScore) {
        this.transactionId = transactionId;
        this.reason = reason;
        this.riskScore = riskScore;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getReason() {
        return reason;
    }

    public int getRiskScore() {
        return riskScore;
    }

    @Override
    public String toString() {
        return "FraudAlert{txId=" + transactionId +
                ", riskScore=" + riskScore +
                ", reason='" + reason + "'}";
    }
}
