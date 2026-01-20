import java.math.BigDecimal;
import java.util.Scanner;
import lib.Bank;
import lib.Account;
public class ATM {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Java ATM System");
        System.out.println("1. Login to Existing Account");
        System.out.println("2. Create New Account");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 2) {
            // --- CREATE ACCOUNT FLOW ---
            System.out.print("Enter your Name (User ID): ");
            String newUserId = scanner.nextLine();
            
            System.out.print("Set a 4-digit PIN: ");
            String newPin = scanner.nextLine();
            
            System.out.print("Enter Initial Deposit Amount: ");
            double initialDeposit = scanner.nextDouble();

            bank.createAccount(newUserId, newPin, initialDeposit);
            
            // After creating, we can just exit or let them login. 
            // For simplicity, let's ask them to restart to login.
            System.out.println("Please restart the application to login with your new account.");
            
        } else if (choice == 1) {
            // --- EXISTING LOGIN FLOW ---
            System.out.print("Enter Account Number: ");
            String accNum = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account userAccount = bank.getAccount(accNum, pin);

            if (userAccount != null) {
                System.out.println("Authentication successful. Your balance is: " + userAccount.getBalance());
                // ... (Here you can call your transaction menu method if you separated it, 
                //      or just paste the rest of your transaction loop code here)
                
                // --- START TRANSACTION LOOP ---
                while (true) {
                    System.out.println("\n1. Check Balance  2. Deposit  3. Withdraw  4. Exit");
                    System.out.print("Choose an option: ");
                    int service = scanner.nextInt();
                    
                    if (service == 4) break;
                    
                    if (service == 2) {
                        System.out.print("Enter amount to deposit: ");
                        double amt = scanner.nextDouble();
                        bank.updateBalance(accNum, amt, true); // true for deposit
                    } else if (service == 3) {
                        System.out.print("Enter amount to withdraw: ");
                        double amt = scanner.nextDouble();
                        bank.updateBalance(accNum, amt, false); // false for withdraw
                    } else if (service == 1) {
                        // Refresh account info to see new balance
                        userAccount = bank.getAccount(accNum, pin); 
                        System.out.println("Current Balance: " + userAccount.getBalance());
                    }
                }
                // --- END TRANSACTION LOOP ---
                
            } else {
                System.out.println("Invalid Account Number or PIN.");
            }
        }
    }
}