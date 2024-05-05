import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountID;
    private String accountHolder;
    private double accountBalance;

    public BankAccount(String accountID, String accountHolder, double accountBalance) {
        this.accountID = accountID;
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds!");
            return false;
        }
    }
}

public class OnlineBankingSystem {
    private static Map<String, BankAccount> bankAccounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
            
            if (option == 1) {
                createAccount();
            } else if (option == 2) {
                depositFunds();
            } else if (option == 3) {
                withdrawFunds();
            } else if (option == 4) {
                System.out.println("Exiting...");
                System.exit(0);
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    private static void createAccount() {
        System.out.print("Enter account ID: ");
        String accountID = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double accountBalance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline character

        BankAccount bankAccount = new BankAccount(accountID, accountHolder, accountBalance);
        bankAccounts.put(accountID, bankAccount);
        System.out.println("Account created successfully!");
    }

    private static void depositFunds() {
        System.out.print("Enter account ID: ");
        String accountID = scanner.nextLine();
        BankAccount bankAccount = bankAccounts.get(accountID);
        if (bankAccount != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            bankAccount.deposit(amount);
            System.out.println("Deposit successful. New balance: " + bankAccount.getAccountBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void withdrawFunds() {
        System.out.print("Enter account ID: ");
        String accountID = scanner.nextLine();
        BankAccount bankAccount = bankAccounts.get(accountID);
        if (bankAccount != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (bankAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: " + bankAccount.getAccountBalance());
            }
        } else {
            System.out.println("Account not found!");
        }
    }
}