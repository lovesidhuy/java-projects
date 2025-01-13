package com.bankapp.model;

import com.bankapp.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SavingsAccount extends CheckingAccount {
    private double savingBalance;
    private static final double WITHDRAWAL_FEE = 2.0;

    public SavingsAccount(String firstName, String lastName, Integer sin) {
        super(firstName, lastName, sin);
    }

    @Override
    public void deposit(double depositSavingAmount) {
        if (depositSavingAmount > 0) {
            savingBalance += depositSavingAmount;
        }
    }

    @Override
    public void withdrawal(double withdrawalSavingAmount) {
        if (withdrawalSavingAmount > 0 && savingBalance >= (withdrawalSavingAmount + WITHDRAWAL_FEE)) {
            savingBalance -= (withdrawalSavingAmount + WITHDRAWAL_FEE);
        } else {
            System.out.println("Withdrawal failed: Invalid amount or insufficient funds.");
        }
    }

    @Override
    public void displayBalance() {
        System.out.println("Savings Account Balance: $" + savingBalance);
    }

    public double getSavingBalance() { return savingBalance; }

    @Override
    public void saveToDatabase() {
        String sql = "INSERT INTO customers (first_name, last_name, sin, account_type, balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getFirstName());
            stmt.setString(2, getLastName());
            stmt.setInt(3, getSin());
            stmt.setString(4, "Savings");
            stmt.setDouble(5, savingBalance);
            stmt.executeUpdate();
            System.out.println("Savings account saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
