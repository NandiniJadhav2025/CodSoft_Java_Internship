import java.util.Scanner;
public class CurrencyConverter
{
	    // Method to get exchange rate (static data for simplicity)
	    public static double getRate(String base, String target) {
	        // Example rates (base currency to target currency)
	        if (base.equals("USD") && target.equals("INR")) return 83.0;
	        if (base.equals("INR") && target.equals("USD")) return 0.012;
	        if (base.equals("USD") && target.equals("EUR")) return 0.91;
	        if (base.equals("EUR") && target.equals("USD")) return 1.10;
	        if (base.equals("INR") && target.equals("EUR")) return 0.011;
	        if (base.equals("EUR") && target.equals("INR")) return 90.0;

	        return -1; // Rate not found
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        // Step 1: Select currencies once
	        System.out.print("Enter base currency (USD, INR, EUR): ");
	        String baseCurrency = sc.next().toUpperCase();

	        System.out.print("Enter target currency (USD, INR, EUR): ");
	        String targetCurrency = sc.next().toUpperCase();

	        double rate = getRate(baseCurrency, targetCurrency);

	        if (rate == -1) {
	            System.out.println("Currency rate not available. Program exiting.");
	            return;
	        }

	        System.out.println("Exchange Rate: 1 " + baseCurrency + " = " + rate + " " + targetCurrency);

	        // Step 2: Keep converting amounts without restarting
	        while (true) {
	            System.out.print("\nEnter amount in " + baseCurrency + " (type 'exit' to quit): ");
	            String input = sc.next();

	            if (input.equalsIgnoreCase("exit")) {
	                System.out.println("Thank you for using Currency Converter!");
	                break;
	            }

	            try {
	                double amount = Double.parseDouble(input);
	                double converted = amount * rate;

	                System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, converted, targetCurrency);
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input! Please enter a number.");
	            }
	        }

	        sc.close();
	    }
	}
