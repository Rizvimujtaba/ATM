package lib;
import java.math.BigDecimal;
public class Account {
    // fields
    private String accountNumber;
    private String pin;
    private BigDecimal balance;
    private String customerID;
    private String IFSCCODE;

    // constructor
    public Account(String accountNumber,String pin,String customerID,BigDecimal balance,String IFSCCODE){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.customerID = customerID;
        this.balance = balance;
        this.IFSCCODE = IFSCCODE;     
    }

    // getter
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getPin(){
        return pin;
    }
    public BigDecimal getBalance(){
        return balance;
    }
    public String getCustomerID(){
        return customerID;
    }
    public String getIFSCCODE(){
        return IFSCCODE;
    }

    // setter
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
}
