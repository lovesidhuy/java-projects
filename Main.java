import java.util.Scanner;

// Abstract class representing a customer
abstract class AbstractCustomer {
    // Attributes for customer
    private String firstName;
    private String lastName;
    private Integer sin;

    // Constructor to initialize customer attributes
    public AbstractCustomer(String firstName, String lastName, Integer sin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
    }

    // Method to display customer account details
    public void accountDetails() {
        System.out.println("Account Details: " + firstName + " " + lastName + " SIN: " + sin);
    }

    // Getters and Setters for customer attributes
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSin() {
        return sin;
    }

    public void setSin(Integer sin) {
        this.sin = sin;
    }
}
interface AccountOperations {
    void deposit(double amount);
    void withdrawal(double amount);
    void displayBalance();
}

// Checking account class extending AbstractCustomer implements AccountOperations
class CheckingAccount extends AbstractCustomer implements AccountOperations {
    private double balance;

    // Constructor to initialize checking account
    public CheckingAccount(String firstName, String lastName, Integer sin) {
        super(firstName, lastName, sin);
    }

    // Method to deposit money into checking account
    public void deposit(double depositAmount) {
        if (depositAmount > 0) {
            balance += depositAmount;
        }
    }

    // Method to withdraw money from checking account
    public void withdrawal(double withdrawalAmount) {
        if (withdrawalAmount > 0 && balance >= withdrawalAmount) {
            balance -= withdrawalAmount;
        } else {
            System.out.println("Withdrawal failed");
        }
    }


    // Method to display checking account balance
    public void displayBalance() {
        System.out.println("Checking Account Balance: $" + balance);
    }

    // Method to transfer money from checking to savings account
    public void transferTo(SavingsAccount savingsAccount, double transferAmount) {
        try {
            if (balance >= transferAmount && transferAmount > 0) {
                withdrawal(transferAmount);
                savingsAccount.deposit(transferAmount);
                System.out.println("Transfer successful!");
            } else {
                throw new IllegalArgumentException("Invalid transfer amount or insufficient funds in checking account.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    // Getter for checking account balance
    public double getBalance() {
        return balance;
    }
    @Override
    public void accountDetails() {
        System.out.println("Checking Account Details: " + getFirstName() + " " + getLastName() + " SIN: " + getSin());
        System.out.println("Checking Account Balance: $" + getBalance());
    }

}

// Savings account class extending AbstractCustomer implements AccountOperations
class SavingsAccount extends AbstractCustomer implements AccountOperations{
    private double savingBalance;
    private static final double WITHDRAWAL_FEE = 2;

    // Constructor to initialize savings account
    public SavingsAccount(String firstName, String lastName, Integer sin) {
        super(firstName, lastName, sin);
    }

    // Method to deposit money into savings account
    @Override
    public void deposit(double depositSavingAmount) {
        if (depositSavingAmount > 0) {
            savingBalance += depositSavingAmount;
        }
    }

    // Method to withdraw money from savings account
    @Override
    public void withdrawal(double withdrawalSavingAmount) {
        try {
            if (withdrawalSavingAmount > 0 && savingBalance >= (withdrawalSavingAmount + WITHDRAWAL_FEE)) {
                savingBalance -= (withdrawalSavingAmount + WITHDRAWAL_FEE);
            } else {
                throw new IllegalArgumentException("Invalid withdrawal mount or insufficient funds in savings account.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    // Method to display savings account balance
    @Override
    public void displayBalance() {
        System.out.println("Savings   Account Balance: $" + savingBalance);
    }

    // Method to transfer money from savings to checking account
    public void transferTo(CheckingAccount checkingAccount, double transferAmount) {
        try {
            if (savingBalance >= transferAmount && transferAmount > 0) {
                savingBalance -= transferAmount;
                checkingAccount.deposit(transferAmount);
                System.out.println("Transfer successful!");
            } else {
                throw new IllegalArgumentException("Invalidtransfer amount or insufficient funds in savings account.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    // Getter for savings account balance
    public double getSavingBalance() {
        return savingBalance;
    }
    @Override
    public void accountDetails() {
        // You can add any additional information specific to SavingsAccount
        System.out.println("Savings Account Details: " + getFirstName() + " " + getLastName() + " SIN: " + getSin());
        System.out.println("Savings Account Ba lance: $" + getSavingBalance());
    }
}

// Main class to test the program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for details to create a Checking Account
        System.out.println("Enter details for Checking Account:");
        System.out.print("Enter first name: ");
        String firstNameChecking = scanner.next();

        System.out.print("Enter last name: ");
        String lastNameChecking = scanner.next();

        System.out.print("Enter SIN: ");
        int sinChecking = scanner.nextInt();

        // Create a checking account
        CheckingAccount c1 = new CheckingAccount(firstNameChecking, lastNameChecking, sinChecking);

        // Ask user for initial deposit amount
        System.out.print("Enter initial deposit amount for Checking Account: ");
        double initialDepositChecking = scanner.nextDouble();
        c1.deposit(initialDepositChecking);

        // Display Checking Account details and balance
        c1.accountDetails();
        c1.displayBalance();

        // Ask user for details to create a Savings Account
        System.out.println("\nEnter details for Savings Account:");
        System.out.print("Enter first name: ");
        String firstNameSavings = scanner.next();

        System.out.print("Enter last name: ");
        String lastNameSavings = scanner.next();

        System.out.print("Enter SIN: ");
        int sinSavings = scanner.nextInt();

        // Create a savings account
        SavingsAccount s1 = new SavingsAccount(firstNameSavings, lastNameSavings, sinSavings);

        // Ask user for initial deposit amount
        System.out.print("Enter initial deposit amount for Savings Account: ");
        double initialDepositSavings = scanner.nextDouble();
        s1.deposit(initialDepositSavings);

        // Display Savings Account details and balance
        s1.accountDetails();
        s1.displayBalance();

        // Transfer money from savings to checking account
        System.out.print("\nEnter transfer amount from savings to checking: ");
        double transferAmount = scanner.nextDouble();
        s1.transferTo(c1, transferAmount);

        // Display updated balances
        c1.displayBalance();
        s1.displayBalance();

        // Alert about withdrawal fee and note about interest feature
        System.out.println("\nAlert: Savings Account has a withdrawal fee of $2");

        // Withdraw money from savings account
        System.out.print("\nEnter withdrawal amount from savings: ");
        double withdrawalAmount = scanner.nextDouble();
        s1.withdrawal(withdrawalAmount);

        // Display updated balance after withdrawal
        s1.displayBalance();
    }

}
