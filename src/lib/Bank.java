package lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;

public class Bank {

    public Account getAccount(String accountNumber, String pin) {
        String query = "SELECT * FROM accounts WHERE account_number = ? AND pin = ?";
        try (java.sql.Connection conn = DatabaseConnection.getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, accountNumber);
            pstmt.setString(2, pin);
            java.sql.ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getString("account_number"),
                    rs.getString("pin"),
                    rs.getString("user_id"),
                    rs.getBigDecimal("balance"),
                    rs.getString("ifsc")
                );
            }
        } catch (Exception e) {
            System.out.println("Error retrieving account: " + e.getMessage());
        }
        return null;
    }
    // save account balance updates to database 

    public void updateBalance(String accountNumber, double amount, boolean isDeposit) {
        // 1. Get current balance first
        String selectQuery = "SELECT balance FROM accounts WHERE account_number = ?";
        String updateQuery = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (java.sql.Connection conn = DatabaseConnection.getConnection();
             java.sql.PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             java.sql.PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Step A: Find current balance
            selectStmt.setString(1, accountNumber);
            java.sql.ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                java.math.BigDecimal currentBalance = rs.getBigDecimal("balance");
                java.math.BigDecimal amountBD = new java.math.BigDecimal(amount);
                java.math.BigDecimal newBalance;

                // Step B: Calculate new balance
                if (isDeposit) {
                    newBalance = currentBalance.add(amountBD);
                } else {
                    if (currentBalance.compareTo(amountBD) < 0) {
                        System.out.println("❌ Insufficient Funds!");
                        return;
                    }
                    newBalance = currentBalance.subtract(amountBD);
                }

                // Step C: Update Database
                updateStmt.setBigDecimal(1, newBalance);
                updateStmt.setString(2, accountNumber);
                int rows = updateStmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("✅ Transaction Successful. New Balance: " + newBalance);
                }
            }
        } catch (Exception e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }

    // method to create a new account in the database
    public void createAccount(String userID,String pin,double initialBalance){

    // 1. generate a random account number
    String accNum = String.valueOf(1000000 + (int)(Math.random() * 9000000));
    String defaultifsc = "BANK0001";
    String query = "INSERT INTO accounts (account_number, pin, user_id, balance, ifsc) VALUES (?, ?, ?, ?, ?)";
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
    java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, accNum);
        pstmt.setString(2, pin);
        pstmt.setString(3, userID);
        pstmt.setBigDecimal(4, BigDecimal.valueOf(initialBalance));
        pstmt.setString(5, defaultifsc);
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Account created successfully. Your account number is: " + accNum);
        }
    } catch (Exception e) {
        System.out.println("Error creating account: " + e.getMessage());
    }
}
}