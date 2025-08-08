import java.util.Scanner;

	class BankAccount{
	    private double balance;

	    public BankAccount(double initialBalance) {
	        this.balance = initialBalance;
	    }
	    public double getBalance() {
	        return balance;
	    }
	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println(" Deposited ₹" + amount);
	        } else {
	            System.out.println(" Invalid deposit amount.");
	        }
	    }
	    public void withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            System.out.println(" Withdrawn ₹" + amount);
	        } else {
	            System.out.println(" Insufficient balance or invalid amount.");
	        }
	    }
	    public void checkBalance() {
	        System.out.println(" Current Balance: ₹" + balance);
	    }
	}
	class ATM {
	    private BankAccount account;

	    public ATM(BankAccount account) {
	        this.account = account;
	    }

	    public void showMenu() {
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\n======= ATM Interface =======");
	            System.out.println("1. Withdraw");
	            System.out.println("2. Deposit");
	            System.out.println("3. Check Balance");
	            System.out.println("4. Exit");
	            System.out.print("👉 Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter amount to withdraw: ₹");
	                    double withdrawAmount = scanner.nextDouble();
	                    account.withdraw(withdrawAmount);
	                    break;
	                case 2:
	                    System.out.print("Enter amount to deposit: ₹");
	                    double depositAmount = scanner.nextDouble();
	                    account.deposit(depositAmount);
	                    break;
	                case 3:
	                    account.checkBalance();
	                    break;
	                case 4:
	                    System.out.println(" Thank you for using the ATM.");
	                    break;
	                default:
	                    System.out.println(" Invalid choice. Try again.");
	            }
	        } while (choice != 4);

	        scanner.close();
	    }
	}

public class ATMInterface
{
	  public static void main(String[] args)
	  {
	      BankAccount userAccount = new BankAccount(2000.0); // Initial balance ₹2000
	      ATM atm = new ATM(userAccount);
	      atm.showMenu();
	  }
}
