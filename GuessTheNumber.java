import java.util.Random;
import java.util.Scanner;
public class GuessTheNumber 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalAttempts = 0;
        int totalRounds = 0;

        boolean playAgain = true;
        while (playAgain) {
            totalRounds++;
            int secretNumber = random.nextInt(100) + 1;
            int attempts = 0;
            final int maxAttempts = 5;

            System.out.println("Welcome to the Guess the Number Game!");
            System.out.println("I have chosen a number between 1 and 100. Can you guess what it is?");

            boolean guessedCorrectly = false;
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == secretNumber) {
                    System.out.println("Congratulations! You guessed the correct number " + secretNumber + " in " + attempts + " attempts!");
                    guessedCorrectly = true;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + secretNumber + ".");
            }

            totalAttempts += attempts;

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            if (!playAgainInput.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total attempts taken: " + totalAttempts);

        scanner.close();
    }
}
