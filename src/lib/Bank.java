package lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;

public class Bank {

    public Account getAccount(String accountNumber){
        String query = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
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

    public void updateBalance(String accountNumber, BigDecimal newBalance){
        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBigDecimal(1, newBalance);
            stmt.setString(2, accountNumber);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("DEBUG: Balance updated successfully in database.");
            }
        } catch (Exception e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }

    }
}
