package com.bankapp;

import com.bankapp.model.CheckingAccount;
import com.bankapp.model.SavingsAccount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create Checking Account
        System.out.println("Enter details for Checking Account:");
        System.out.print("First Name: ");
        String firstNameChecking = scanner.next();
        System.out.print("Last Name: ");
        String lastNameChecking = scanner.next();
        System.out.print("SIN: ");
        int sinChecking = scanner.nextInt();
        CheckingAccount checkingAccount = new CheckingAccount(firstNameChecking, lastNameChecking, sinChecking);
        System.out.print("Initial Deposit: ");
        double initialDepositChecking = scanner.nextDouble();
        checkingAccount.deposit(initialDepositChecking);
        checkingAccount.saveToDatabase();

        // Create Savings Account
        System.out.println("Enter details for Savings Account:");
        System.out.print("First Name: ");
        String firstNameSavings = scanner.next();
        System.out.print("Last Name: ");
        String lastNameSavings = scanner.next();
        System.out.print("SIN: ");
        int sinSavings = scanner.nextInt();
        SavingsAccount savingsAccount = new SavingsAccount(firstNameSavings, lastNameSavings, sinSavings);
        System.out.print("Initial Deposit: ");
        double initialDepositSavings = scanner.nextDouble();
        savingsAccount.deposit(initialDepositSavings);
        savingsAccount.saveToDatabase();

        // Transfer from Savings to Checking
        System.out.print("Transfer amount from Savings to Checking: ");
        double transferAmount = scanner.nextDouble();
        savingsAccount.transferTo((SavingsAccount) checkingAccount, transferAmount);

        // Display Balances
        checkingAccount.displayBalance();
        savingsAccount.displayBalance();
    }
}
