import java.math.BigDecimal;
import java.util.Scanner;
import lib.Bank;
import lib.Account;
public class ATM {
    public static void main(String[] args) throws Exception {
       Bank bank = new Bank();
       
       Scanner scanner = new Scanner(System.in);
        
        System.out.println("ATM is initialized and ready for transactions.");
        while (true) {
            System.out.println("Welcome to the ATM. Please enter your account number:");
            
            String accountNumber = scanner.nextLine();
            Account account = bank.getAccount(accountNumber);

            if (account != null){
                System.out.println("Please enter your PIN:");    
                String pin = scanner.nextLine();
                if (account.getPin().equals(pin)){
                    System.out.println("Authentication successful. Your balance is: " + account.getBalance());
                    boolean sessionActive = true;
                    while (sessionActive) {  
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1. Deposit (d)");
                        System.out.println("2. Withdraw (w)");
                        System.out.println("3. Check Balance (b)");
                        System.out.println("4. Exit (exit)");
                        String choice = scanner.nextLine();
                        
                        if(choice.equalsIgnoreCase("d")){
                            System.out.println("Enter amount to deposit:");
                            BigDecimal amount = new BigDecimal(scanner.nextLine());
                            BigDecimal newBalance = account.getBalance().add(amount);
                            account.setBalance(newBalance);
                            bank.updateBalance(account.getAccountNumber(), newBalance);
                            System.out.println("Deposit successful. New balance is: " + newBalance);
                        } else if (choice.equalsIgnoreCase("w")){
                            System.out.println("Enter amount to withdraw:");
                            BigDecimal amount = new BigDecimal(scanner.nextLine());
                            if (account.getBalance().compareTo(amount) >= 0){
                                BigDecimal newBalance = account.getBalance().subtract(amount);
                                account.setBalance(newBalance);
                                bank.updateBalance(account.getAccountNumber(), newBalance);
                                System.out.println("Withdrawal successful. New balance is: " + newBalance);
                            } else {
                                System.out.println("Insufficient funds. Transaction cancelled.");
                            }
                        } else if (choice.equalsIgnoreCase("b")){
                            System.out.println("Your current balance is: " + account.getBalance());
                        } else if (choice.equalsIgnoreCase("exit")){
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            sessionActive = false;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid PIN. Access denied.");
                }
            } else {
                System.out.println("Account not found. Please try again.");
            }
        }
    }
}
