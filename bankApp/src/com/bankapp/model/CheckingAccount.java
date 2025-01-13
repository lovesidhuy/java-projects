package com.bankapp.model;

import com.bankapp.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckingAccount extends AbstractCustomer {
    private double balance;

    public CheckingAccount(String firstName, String lastName, Integer sin) {
        super(firstName, lastName, sin);
    }

    public void deposit(double depositAmount) {
        if (depositAmount > 0) {
            balance += depositAmount;
        }
    }

    public void withdrawal(double withdrawalAmount) {
        if (withdrawalAmount > 0) {
            balance -= withdrawalAmount;
        }
    }

    public void displayBalance() {
        System.out.println("Checking Account Balance: $" + balance);
    }

    public void transferTo(SavingsAccount savingsAccount, double transferAmount) {
        if (balance >= transferAmount && transferAmount > 0) {
            withdrawal(transferAmount);
            savingsAccount.deposit(transferAmount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed: Invalid transfer amount or insufficient funds.");
        }
    }

    public double getBalance() { return balance; }

    @Override
    public void saveToDatabase() {
        String sql = "INSERT INTO customers (first_name, last_name, sin, account_type, balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getFirstName());
            stmt.setString(2, getLastName());
            stmt.setInt(3, getSin());
            stmt.setString(4, "Checking");
            stmt.setDouble(5, balance);
            stmt.executeUpdate();
            System.out.println("Checking account saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
