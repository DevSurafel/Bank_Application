import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Name and CustomerId to access your bank application");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter Your Customer ID: ");
        String customerId = sc.nextLine();
        BankAccount obj1 = new BankAccount(name, customerId);
        System.out.println("\n");

        obj1.menu(sc);

        sc.close(); // Close the scanner
    }
}

class BankAccount {
    private double balance;
    private double prevTrans;
    private String customerName;
    private String customerId;

    BankAccount(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    void deposit(double amount) {
        if (amount != 0) {
            balance += amount;
            prevTrans = amount;
            System.out.println("\n");
            System.out.println(amount + " has been successfully deposited.");
            System.out.println("\n");
        }
    }

    void withdraw(double amt) {
        if (amt != 0 && balance >= amt) {
            balance -= amt;
            prevTrans = -amt;
            System.out.println("\n");
            System.out.println(amt + " has been successfully withdrawn.");
            System.out.println("\n");
        } else if (balance < amt) {
            System.out.println("\n");
            System.out.println("Insufficient Balance.");
            System.out.println("\n");
        }
    }

    void transfer(BankAccount recipient, double amount) {
        if (amount != 0 && balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            prevTrans = -amount;
            System.out.println("\n");
            System.out.println(amount + " has been successfully transferred to " + recipient.customerName + ".");
            System.out.println("\n");
        } else if (balance < amount) {
            System.out.println("\n");
            System.out.println("Insufficient Balance for transfer.");
            System.out.println("\n");
        }
    }

    void getPreviousTrans() {
        if (prevTrans > 0) {
            System.out.println("Deposited: " + prevTrans);
            System.out.println("\n");
        } else if (prevTrans < 0) {
            System.out.println("Withdrawn: " + Math.abs(prevTrans));
            System.out.println("\n");
        } else {
            System.out.println("No transaction occurred.");
            System.out.println("\n");
        }
    }

    void checkAccountDetails() {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Current Balance: " + balance);
        System.out.println("\n");
    }

    void menu(Scanner sc) {
        int option;

        System.out.println("Welcome " + customerName);
        System.out.println("Your ID: " + customerId);
        System.out.println("\n");

        do {
            System.out.println("---------------------------------");
            System.out.println("1) Check Balance");
            System.out.println("2) Deposit Amount");
            System.out.println("3) Withdraw Amount");
            System.out.println("4) Transfer Funds");
            System.out.println("5) Previous Transaction");
            System.out.println("6) Account Details");
            System.out.println("7) Exit");
            System.out.println("---------------------------------");
            System.out.print("Choose an option: ");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\n");
                    System.out.println("Balance: " + balance);
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("\n");
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;

                case 3:
                    System.out.println("\n");
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = sc.nextDouble();
                    withdraw(withdrawalAmount);
                    break;

                case 4:
                    System.out.println("\n");
                    System.out.print("Enter recipient's name: ");
                    sc.nextLine();
                    String recipientName = sc.nextLine();
                    System.out.print("Enter recipient's customer ID: ");
                    String recipientId = sc.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = sc.nextDouble();
                    BankAccount recipient = new BankAccount(recipientName, recipientId);
                    transfer(recipient, transferAmount);
                    break;

                case 5:
                    System.out.println("\n");
                    System.out.print("Previous Transaction: ");
                    getPreviousTrans();
                    break;

                case 6:
                    System.out.println("\n");
                    System.out.println("Account Details:");
                    checkAccountDetails();
                    break;

                case 7:
                    System.out.println("\n");
                    System.out.println("Thank you for using our bank.");
                    break;

                default:
                    System.out.println("\n");
                    System.out.println("Incorrect option.");
                    break;
            }
        } while (option != 7);
    }
}
