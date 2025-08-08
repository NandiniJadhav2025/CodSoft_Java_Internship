import java.util.Scanner;
import java.util.Random;
public class NumberGuessGame {

	public static void main(String[] args) 
	{
		Random rand = new Random();
		
		int attempt = 0;
		int randomNumber = rand.nextInt(100) + 1;
		
		System.out.println("Random Number is"+ randomNumber);
		
		System.out.println("Enter Your Guess Game(1-100) :");
		
		Scanner scanner = new Scanner(System.in);
		
		int playerGuess = scanner.nextInt();
		
		attempt++;
		
		if (playerGuess == randomNumber)
		{
			System.out.println("Correct! you  win");
		}
		else if (randomNumber > playerGuess)
		{
			System.out.println("The Number is Higher. Guess again");
		}
		else
		{
			System.out.println("The Number is Lower. Guess again"+ attempt + "attempt");
		}

	}

}
