import java.util.*;

class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolder, String accountNumber, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: ₹" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null;

        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (account != null) {
                        System.out.println("Account already exists.");
                        break;
                    }
                    System.out.print("Enter account holder name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter account number: ");
                    String accNo = sc.nextLine();
                    System.out.print("Enter initial deposit: ₹");
                    double deposit = sc.nextDouble();
                    sc.nextLine();
                    account = new BankAccount(name, accNo, deposit);
                    System.out.println("Account created for " + name);
                    break;

                case 2:
                    if (account == null) {
                        System.out.println("No account exists. Please create one first.");
                        break;
                    }
                    System.out.print("Enter deposit amount: ₹");
                    double depAmt = sc.nextDouble();
                    sc.nextLine();
                    account.deposit(depAmt);
                    break;

                case 3:
                    if (account == null) {
                        System.out.println("No account exists. Please create one first.");
                        break;
                    }
                    System.out.print("Enter withdrawal amount: ₹");
                    double withAmt = sc.nextDouble();
                    sc.nextLine();
                    account.withdraw(withAmt);
                    break;

                case 4:
                    if (account == null) {
                        System.out.println("No account exists. Please create one first.");
                        break;
                    }
                    account.checkBalance();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
