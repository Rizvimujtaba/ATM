package lib;
import java.util.HashMap;
import java.util.Map;
public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    public void addAccount(Account account){
        accounts.put(account.getAccountNumber(), account);
    }

    // retrieve account by account number
    public Account getAccount(String accountNumber){
        return accounts.get(accountNumber);
    }
}
