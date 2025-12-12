// File: AlertService.java
// Stores and displays fraud alerts.

import java.util.ArrayList;
import java.util.List;

public class AlertService {

    private final List<FraudAlert> alerts = new ArrayList<>();

    public void addAlerts(List<FraudAlert> newAlerts) {
        alerts.addAll(newAlerts);
    }

    public void printAllAlerts() {
        if (alerts.isEmpty()) {
            System.out.println("No fraud alerts detected.");
            return;
        }

        System.out.println("Fraud Alerts:");
        for (FraudAlert alert : alerts) {
            System.out.println(" - " + alert);
        }
    }
}
