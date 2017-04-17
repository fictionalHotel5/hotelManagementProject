package grupni_projekat_prvi_dio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest {

	static Scanner input = new Scanner(System.in);

	/**Jasmina*/
	public static void displayMenu() {

		System.out.println("Please choose from the following menu options:\n");
		System.out.println("1.Check balance");
		System.out.println("2.New service order");
		System.out.println("3.New room");
		System.out.println("4.Check out");
		System.out.println("5.Log out");

		int user = unosInt(input);

		switch (user) {
		case 1:
			checkBalance();
			break;
		case 2:
			newServiceOrder();
			break;
		case 3:
			newRoom();
			break;
		case 4:
			checkOut();
			break;
		case 5:
			logOut();
			break;
		default:
			System.out.println("Something went wrong.");
			displayMenu();
		}

	}

	public static void checkBalance() {
			
	}

	public static void newServiceOrder() {

	}

	public static void newRoom() {
		
	}

	public static void checkOut() {
	
	}

	public static void logOut() {
		System.out.println("You're now logged out! ");
	}

	/** for user input */
	public static int unosInt(Scanner input) {

		int user = 0;
		boolean correct = true;

		do {
			try {
				user = input.nextInt();
				correct = false;
				input.nextLine();
			} catch (InputMismatchException ex) {
				System.out.println("Wrong input.Try again:");
				input.nextLine();
			}
		} while (correct);
		return user;
	}
}
