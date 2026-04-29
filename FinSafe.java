import java.util.ArrayList;
import java.util.Scanner;

class InSufficientFundsException extends Exception{
    public InSufficientFundsException(String message){
        super(message);
    }
}
class Account {
    private double balance;
    private String accountHolder;
    private ArrayList<String> transactions;

    Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }
    void deposit(double amount) {
        if(transactions.size() >= 5){
            transactions.remove(0);}
        transactions.add("Deposit "+ amount);
        this.balance += amount;
    }
    void processTransaction(double amount) throws InSufficientFundsException,IllegalArgumentException{
        if (amount < 0) { throw new IllegalArgumentException("Amount cannot be negative");}
        if (amount > balance) { throw new InSufficientFundsException("Insufficient balance");}
        if(transactions.size() >= 5){transactions.remove(0);}
        transactions.add("Withdraw "+amount);
        this.balance -= amount;}

    void printMiniStatement() {
        System.out.println("Last 5 transactions:");
        for (String i : transactions) {
            System.out.println(i);}
    }
    double getBalance() {
        return this.balance;
    }
}
public class FinSafe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account holder: ");
        String accountHolder = sc.nextLine();
        System.out.println("Enter balance: ");
        double balance = sc.nextDouble();
        Account acc = new Account(accountHolder, balance);
        while (true) {
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.View Balance");
            System.out.println("4.Mini Statement");
            System.out.println("5.Exit ");
            System.out.println("Enter Your Choice ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter amount ");
                    double amount = sc.nextDouble();
                    acc.deposit(amount);
                    System.out.println("Deposit successful");
                    break;
                case 2:
                    System.out.println("Enter amount ");
                    double amount2 = sc.nextDouble();
                    try {
                        acc.processTransaction(amount2);
                    } catch (InSufficientFundsException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e1) {
                        System.out.println(e1.getMessage());}
                    break;
                case 3:
                    System.out.println(acc.getBalance());
                    break;
                case 4:
                    acc.printMiniStatement();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
