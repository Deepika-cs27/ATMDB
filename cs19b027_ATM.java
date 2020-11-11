package cs19b027;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*; 

public class Customer {
    private String accountNo;
    private String PIN;
    private double balance;
    private int attemptNo;

public Customer(String accountNo, String PIN, double balance, int attemptNo) { // constructor
    
    this.accountNo = accountNo;
    this.PIN = PIN;
    this.balance = balance;
    this.attemptNo = attemptNo;
}
public int getAttempt() { // getting info of customer
    return attemptNo;
}
public void setAttempt(int attemptNo) { // (if required) to change info of customer
    this.attemptNo = attemptNo;
}
public String getAccountNo() {
    return accountNo;
}
public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
}
public String getPIN() {
    return PIN;
}
public void setPIN(String PIN) {
    this.PIN = PIN;
}
public double getBalance() {
    return balance;
}
public void setBalance(double balance) {
    this.balance = balance;
}




public class DepositCash extends MainBranch { 

    private Customer customer;
    private double balance;

    public DepositCash (Customer customer) //constructor
        {
        
        this.customer = customer;
        this.balance = balance;
        }


    public void depositCash(int amount) 
    {
        
        if (amount > 0)
        { 

            customer.setBalance(customer.getBalance()+amount); //set the new balance
            System.out.println("Dear Customer your card with account number is credited with Rs"+ customer.getBalance());

        }
        else 

            System.out.println("Transaction cancelled.Try again"); 


    }

}


public class MainBranch { 

    private Customer customer;
    List<Customer> cust = new ArrayList<>(); //declaration of arraylist for customer
    Boolean accessed = false;



        public MainBranch (Customer customer) {//constructor
            this.customer = customer;
        }


        public void accessaccount(String accountNo, String PIN)  
        { 
            if (customer.getAccountNo().equals(accountNo) && customer.getPIN().equals(PIN)) 
                {
                System.out.println("Welcome to ATM");

                } 

            else 
            {   
                for (int attemptNo=1; attemptNo<=3;attemptNo++)  
                {
                    if (attemptNo == 3 && customer.getAccountNo().equals(accountNo) != customer.getPIN().equals(PIN))
                    
                    System.out.println("Invalid Account Number & PIN! " + "Attempt: "+attemptNo);
                }

                
            }
        }


 

        public class OTP 
        { 
            static char[] OTP(int len) 
            { 
                System.out.println("Generating your OTP"); 
                System.out.print("You OTP is : "); 
                

                
                Random rndm_method = new Random(); 

                char[] otp = new char[len]; 

                for (int i = 0; i < len; i++) 
                { 
                    otp[i] = 
                    numbers.charAt(rndm_method.nextInt(numbers.length())); 
                } 
                return otp; 

            } 
            
                System.out.println(OTP(length)); 
    
        } 


    }
}

public class WithdrawCash extends MainBranch { 

    private Customer customer;
    private double balance;

    public WithdrawCash(Customer customer) //constructor
        {
        
        this.customer = customer;
        this.balance = balance;
        }


    public void withdrawCash(int amount) 
    { 
        if (accessed=true) 
        { 
            if (amount > 0 && amount < customer.getBalance ) 
            { 
                customer.setBalance(customer.getBalance()-amount); //set the new balance
                System.out.println("Rs. "+amount"is deducted from your account.");
                System.out.println("Avaliable balance: "+customer.getBalance()-amount);
            } 
            else 
            {
                if (accessed = false) 
                System.out.println("Error : Transaction cancelled"); 

            }
        }
    }
        
} 



public class Database {
    private static final String DBCLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/cs19b027";
    private static final String user = "Deepika";
    private static final String password = "1234";

    public static java.sql.Connection connection() throws Exception{
        Class.forName(DBCLASSNAME);
        return DriverManager.getConnection(url, user, password);
    }
}


public class Operation {
    private String cardnumber;
    private String pincode;
    private int balance;

    Operation(String cardnumber,String pincode){
        this.cardnumber = cardnumber;
        this.pincode = pincode;
    }

    public Integer showbalance(String cardnumber){
        try {
            Connection c = DataBase.connection();
            Statement stmt = c.createStatement();
            Statement stmt5 = c.createStatement();

            String sql5 = "SELECT FROM Balance WHERE cardnumber = '"+cardnumber+"' ";
            ResultSet rs5 = stmt5.executeQuery(sql5);

            while (rs5.next()) {
                this.balance = rs5.getInt(2);

            }

            System.out.println("Your current balance is Rs."+balance+);
        } catch (Exception e) {
            System.out.println(e);
        }

        return this.balance;
    }

 

    public void sendMoneyToOther(Integer amount_other , String number_other , String cardnumber){
        try {
            Connection c = DataBase.connection();
            Statement stmt8 = c.createStatement();
            String sql8 = "UPDATE Balance SET Balance = Balance '"+amount_other+"' WHERE card number  '"+number_other+"' ";
            stmt8.executeUpdate(sql8);
            

            Statement stmt9 = c.createStatement();
            String sql9 = "UPDATE Balance SET Balance = Balance '"+amount_other+"' WHERE card number  '"+cardnumber+"' ";
            stmt9.executeUpdate(sql9);
            System.out.println("You sent Rs." + amount_other +"to"+number_other+);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}




public class ATM {

    public static void main(String[] args) {

        System.out.println("Hello Customer");
        System.out.println("Welcome to ATM");
        System.out.println("Please insert your card");

        Scanner s = new Scanner(System.in);

        System.out.println("Cash Deposit");

        System.out.println("Transfer Money");

        System.out.println("Withdrawal");

        System.out.println("Check Balance");

        System.out.print("Choose the operation you want to perform:");

        int n = s.nextInt();

        Customer customer = new Customer(String accountNo, String PIN, double balance, int attemptNo); // New Customer object
    
        MainBranch mainbranch = new MainBranch(customer);//created a main branch object and passed customer in it
    
        DepositCash cash = new DepositCash(customer); // created a cash object and passed customer through it
        
        WithdrawCash withdraw = new Withdraw(customer); // created a withdraw object and passed customer in it 

        Operation.sendMoneyToOther(amount_other,number_other,cardnumber);

        Operation.showbalance(cardnumber);
    }
} 