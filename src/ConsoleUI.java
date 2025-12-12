import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final FraudEngine fraudEngine;
    private final AlertService alertService;

    private static final int RISK_THRESHOLD = 50; // minimum risk to treat as real fraud

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.accountService = new AccountService();
        this.transactionService = new TransactionService();
        this.fraudEngine = new FraudEngine(transactionService);
        this.alertService = new AlertService();
    }

    public void start() {
        System.out.println("=================================");
        System.out.println("   Welcome to FraudGuard Lite");
        System.out.println("=================================");

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleCreateAccount();
                    break;
                case 2:
                    handleMakeTransaction();
                    break;
                case 3:
                    alertService.printAllAlerts();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                case 5:
                    accountService.printAllAccounts();
                    break;
                case 6:
                    transactionService.printAllTransactions();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // blank line for readability
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("--------------- MENU ---------------");
        System.out.println("1. Create account");
        System.out.println("2. Make transaction");
        System.out.println("3. View fraud alerts");
        System.out.println("4. Exit");
        System.out.println("5. [Debug] List all accounts");
        System.out.println("6. [Debug] List all transactions");
        System.out.println("------------------------------------");
    }

    // === Option 1: Create account ===
    private void handleCreateAccount() {
        System.out.println("---- Create New Account ----");
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine().trim();

        double initialBalance = readDouble("Enter initial balance: ");

        Account account = accountService.createAccount(name, initialBalance);

        System.out.println("Account created successfully!");
        System.out.println("Account ID: " + account.getId());
        System.out.println("Owner    : " + account.getOwnerName());
        System.out.println("Balance  : " + account.getBalance());
    }

    // === Option 2: Make transaction ===
    private void handleMakeTransaction() {
        System.out.println("---- Make Transaction ----");

        System.out.print("From account ID: ");
        String fromId = scanner.nextLine().trim();

        System.out.print("To account ID  : ");
        String toId = scanner.nextLine().trim();

        double amount = readDouble("Amount to transfer: ");

        Account fromAcc = accountService.getAccount(fromId);
        Account toAcc = accountService.getAccount(toId);

        if (fromAcc == null) {
            System.out.println("From account not found: " + fromId);
            return;
        }

        if (toAcc == null) {
            System.out.println("To account not found: " + toId);
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }

        if (fromAcc.getBalance() < amount) {
            System.out.println("Insufficient balance in account " + fromId);
            return;
        }

        // Perform transfer
        fromAcc.setBalance(fromAcc.getBalance() - amount);
        toAcc.setBalance(toAcc.getBalance() + amount);

        // Record transaction
        Transaction tx = transactionService.recordTransaction(fromId, toId, amount);

        System.out.println("Transaction successful!");
        System.out.println("Transaction ID: " + tx.getId());
        System.out.println("From: " + fromId + "  To: " + toId + "  Amount: " + amount);
        System.out.println("New balance of " + fromId + ": " + fromAcc.getBalance());
        System.out.println("New balance of " + toId + ": " + toAcc.getBalance());

        // === Fraud check with risk scoring ===
        List<FraudAlert> alerts = fraudEngine.evaluate(tx);

        if (!alerts.isEmpty()) {
            int totalRisk = 0;
            for (FraudAlert alert : alerts) {
                totalRisk += alert.getRiskScore();
            }

            if (totalRisk >= RISK_THRESHOLD) {
                System.out.println("!! Fraud detected! Total risk score = " + totalRisk);
                alertService.addAlerts(alerts);
            } else {
                System.out.println("Warnings detected (low risk, total score = " + totalRisk + "):");
                for (FraudAlert alert : alerts) {
                    System.out.println(" - " + alert.getReason() + " (score " + alert.getRiskScore() + ")");
                }
            }
        }
    }

    // === Input helpers ===

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (like 1000 or 99.5).");
            }
        }
    }
}
