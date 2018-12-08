/*
 *   A program that plays and scores a round of the game Poker Dice.  In this game,
 *   five dice are rolled.  The player is allowed to select a number of those five dice
 *   to re-roll. The dice are re-rolled and then scored as if they were a poker hand.
 *   
 * @author Brendon Baughn
 * @version 10172018
 *
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PokerDice {

	/**
	 * Prompts the user with a message passed into method and returns true if
	 * the user enters a 'Y' and false if the user enters an 'N'. The method
	 * should work if the user uses upper or lower case inputs. If the user
	 * enters any other value, the method should display an error message and
	 * continue prompting until a valid value is entered.
	 *
	 * @param inScanner
	 *            Scanner to provide in put from user
	 * @param msg
	 *            message to display to prompt the user to enter a value
	 * @return
	 */
	public static boolean promptToPlay(Scanner inScanner, String msg) {
		boolean answer = true;
		System.out.print(msg);
		String userInput = inScanner.nextLine().toUpperCase();
		while (userInput.length() >= 2 || !(userInput.equals("Y") || userInput.equals("N"))){
			System.out.println("ERROR! Only 'Y' and 'N' allowed as input!");
			System.out.print(msg);
			userInput = inScanner.nextLine().toUpperCase();

			if (userInput.equals("Y")) {
				answer = true;
			} else if (userInput.equals("N")) {
				answer = false;
			}
		}
		if (userInput.equals("Y")) {
			answer = true;
		} else if (userInput.equals("N")) {
			answer = false;
		}
		return answer;
	}

	/**
	 * Replaces the zeroes in the array dice with random values between 1 and 6
	 * chosen from the Random generator rnd. Then sorts the array so that the
	 * values are in sorted order. 
	 *
	 * @param rnd
	 *            generator to pull values from
	 * @param dice
	 *            array to fill with values.
	 */
	public static void rollDice(Random rnd, int[] dice) {

		for (int i = 0; i < dice.length; ++i) {
			if (dice[i] == 0) {
				int x = rnd.nextInt(6) + 1;
				dice[i] = x;
			}
		}
		Arrays.sort(dice);
	}

	/**
	 * Displays the array dice as the user's current set of die rolls and
	 * prompts them to indicate the indices of dice to be re-rolled, one index
	 * at a time. If the user enters a -1, the loop ends. If the user enters an
	 * invalid index other than -1, the method displays an error message and
	 * continues prompting until a valid index or -1 is entered.
	 * 
	 * Because we are using an array, each die is selected directly by its index,
	 * [0, 1, 2, 3, 4], for the 5 die.
	 *
	 * @param dice
	 *            array to set dice to be rerolled
	 * @param inScanner
	 *            Scanner to provide input from user
	 */
	public static void promptForReroll(int[] dice, Scanner inScanner) {
		String dices = Arrays.toString(dice);
		System.out.println("Your current roll: " + dices);
		System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
		int reroll = Integer.parseInt(inScanner.nextLine());

		while (reroll >= 5 || reroll <= -2) {
			System.out.println("Error: Index must be between 0 and 4");
			System.out.println("Your current roll: " + dices);
			System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
			reroll = Integer.parseInt(inScanner.nextLine());
		}
		while (reroll != -1) {
			dice[reroll] = 0;
			dices = Arrays.toString(dice);
			System.out.println("Your current roll: " + dices);
			System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
			reroll = Integer.parseInt(inScanner.nextLine());
		}

	}

	/**
	 * Returns an array where each position represents the counts of a value in
	 * the array dice. For example, if dice is [1, 2, 3, 3, 5], then this method
	 * would return [1, 1, 2, 0, 1, 0] - where index 0 holds the number of 1s,
	 * index 1 holds the number of 2s, index 2 holds the number of 3s, etc.
	 * (1 one, 1 two, 2 threes, 0 fours, 1 five, 0 six)
	 * @param dice
	 *            die rolls to count
	 * @return array holding counts of each value in the array dice
	 */
	public static int[] getCounts(int[] dice) {
		int[] newArray = new int[6];
		for (int i = 0; i < dice.length; ++i) {
			if (dice[i] == 1) {
				newArray[0] = newArray[0] + 1;
			} else if (dice[i] == 2) {
				newArray[1] = newArray[1] + 1;
			} else if (dice[i] == 3) {
				newArray[2] = newArray[2] + 1;
			} else if (dice[i] == 4) {
				newArray[3] = newArray[3] + 1;
			} else if (dice[i] == 5) {
				newArray[4] = newArray[4] + 1;
			} else if (dice[i] == 6) {
				newArray[5] = newArray[5] + 1;
			}
		}
		return newArray;
	}

	/**
	 * Given a SORTED array in increasing order, returns true if the numbers
	 * form an unbroken sequence, false otherwise. For example, [1,2,3,4,5]
	 * would be true, but [1,2,3,4,6] would be false.
	 *
	 * @param dice
	 *            array sorted in increasing order
	 * @return true if values in dice are in an unbroken sequence, false
	 *         otherwise
	 */
	public static boolean inSequence(int[] dice) {
		Arrays.sort(dice);
		boolean isBoolean = true;
		for (int i = 0; i < dice.length - 1; ++i) { // Return false if array is broken
			if (dice[i] > dice[i + 1]) {
				isBoolean = false;
			}
			if (dice[i + 1] != dice[i] + 1) {
				isBoolean = false;
			}
		}
		return isBoolean;
	}

	/**
	 * Returns true if value is somewhere in the array, false otherwise
	 *
	 * @param array
	 *            array to search for value
	 * @param value
	 *            item to look for in the array
	 * @return true if value is in array, otherwise false
	 */
	public static boolean contains(int[] array, int value) {
		boolean isBoolean = false;
		for (int i = 0; i < array.length; ++i) {
			if (array[i] == value) {
				isBoolean = true;
			}
		}
		return isBoolean;
	}

	/**
	 * Scores the value of the array of dice as a poker hand. Returns a String
	 * giving the rank of the hand. See the assignment write-up for details on
	 * how the rank string should be formatted.
	 *
	 * @param dice
	 *            die rolls in sorted order
	 * @return String holding the rank of the roll in the array dice
	 */
	public static String scoreDice(int[] dice) {
		String str = "";
		Arrays.sort(dice);
		// Find a straight
		int[] straight = {1, 2, 3, 4, 5};
		int[] straight2 = {2, 3, 4, 5, 6};
		if (Arrays.equals(dice, straight) == true || Arrays.equals(dice, straight2) == true) { // Straight
			str = "Straight";
		}
		int max = dice[0];
		for (int i = 0; i < dice.length; ++i) {
			while (dice[i] > max) {
				max = dice[i];
			}
		}
		if ((dice[0] == dice[1]) && (dice[1] == dice[2]) && 
				dice[2] != dice[3] || // If the first three are pairs
				(dice[1] == dice[2]) && (dice[2] == dice[3]) && 
				dice[3] != dice[4] || // If the second element and up are pairs
				(dice[2] == dice[3]) && (dice[3] == dice[4]) && (dice[1] != dice[2])) { // Three of a kind
			str = "Three of a kind";
		}
		if (dice[0] != dice[1] && dice[1] != dice[2] && dice[2] != dice[3] && dice[3] != dice[4] &&
				(Arrays.equals(dice, straight) == false && Arrays.equals(dice, straight2) == false)) { // Highest value
			str = "Highest value " + max;
		}
		if (dice[0] == dice[1] && dice[1] == dice[2] && dice[2] == dice[3] && dice[3] != dice[4] || 
				dice[0] != dice[1] && dice[1] == dice[2] && dice[2] == dice[3] && dice[3] == dice[4]) { // Four of a kind
			str = "Four of a kind";
		}
		if (dice[0] == dice[1] && dice[1] != dice[2] && dice[2] != dice[3] && dice[3] != dice[4] || 
				dice[1] == dice[2] && dice[2] != dice[3] && dice[0] != dice[1] && dice[3] != dice[4] ||
				dice[2] == dice[3] && dice[3] != dice[4] && dice[1] != dice[2] && dice[0] != dice[1] ||
				dice[3] == dice[4] && dice[2] != dice[3] && dice[1] != dice[2] && dice[0] != dice[1]) { // One pair
			str = "One pair";
		}
		if ((dice[0] == dice[1] && dice[2] == dice[3]) && (dice[1] != dice[2]) && dice[3] != dice[4] ||
				dice[0] == dice[1] && dice[3] == dice[4] && dice[1] != dice[2] && dice[2] != dice[3] || 
				dice[1] == dice[2] && dice[3] == dice[4] && dice[0] != dice[1] && dice[1] != dice[3]) { // Two pair
			str = "Two pair";
		}
		if ((dice[0] == dice[1]) && (dice[1] == dice[2]) && (dice[2] == dice[3])&& (dice[3] == dice[4])) { // Five of a kind
			str = "Five of a kind";
		}

		if ((((dice[0] == dice[1]) && (dice[1] == dice[2]))
				&& (dice[3] == dice[4]) &&
				(dice[2] != dice[3])) || ((dice[0] == dice[1]) && 
						((dice[2] == dice[3]) && (dice[3] == dice[4]))
						&& (dice[1] != dice[2]))) { // Full house
			str = "Full house";
		}
		return str;
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter a random seed: ");
		int seed = Integer.parseInt(in.nextLine());
		Random rnd = new Random(seed);

		System.out.println("Welcome to the Poker Dice game!");
		System.out.println("Roll 5 dice and try to assemble the best poker hand.");

		String msg = "Would you like to play [Y/N]? ";
		boolean prompt = promptToPlay(in, msg);
		while (prompt == true) {
			System.out.println();

			int[] myArray = new int[5];

			rollDice(rnd, myArray);
			promptForReroll(myArray, in);
			System.out.println("Keeping remaining dice...");
			System.out.println("Rerolling...");
			rollDice(rnd, myArray);

			String dices = Arrays.toString(myArray);
			System.out.println("Your final roll: " + dices);
			getCounts(myArray);
			Arrays.toString(getCounts(myArray));
			inSequence(myArray);
			contains(myArray, 1);
			System.out.println(scoreDice(myArray));

			msg = "Would you like to play again [Y/N]? ";
			System.out.print(msg);
			String userInput = in.nextLine().toUpperCase();
			while (userInput.length() > 1 || !(userInput.equals("Y") || userInput.equals("N"))){
				System.out.println("ERROR! Only 'Y' and 'N' allowed as input!");
				System.out.print(msg);
				userInput = in.nextLine().toUpperCase();

				if (userInput.equals("Y")) {
					prompt = true;
				} else {
					prompt = false;
				}
			}
			if (userInput.equals("Y")) {
				prompt = true;
			} else {
				prompt = false;
			}
		}
		System.out.println();
		System.out.println("Goodbye!");
		in.close();

	}

}