package hotelMenagment;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest {
 /**
  * @author Sipo
  */
	private String username;
	private String password;
	private String name;
	private String surname;
	private String gender;
	private String IDnumber;
	private int age;
	private int roomNumber;
	private String roomType;
	private String timeCheckedin;
	private int numofdays;
	private String gym;
	private String pool;
	private String restaurant;
	private String sauna;
	private String cinema;

	
	private boolean status;

	// constructor to return list of active users
	public Guest(String username, String name, String surname, String gender, String idnumber, int age, int roomnumber,
			String roomtype) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.IDnumber = idnumber;
		this.age = age;
		this.roomNumber = roomnumber;
		this.roomType = roomtype;

	}

	// constructor to change status of user in databse
	public Guest(String username, boolean status) {
		this.username = username;
		this.status = status;
	}

	/** return boolean status */
	public boolean getStatus() {
		return status;
	}

	/** sets status tp true or false */
	public void setActive(boolean status) {
		this.status = status;
	}

	// no-arg construcator
	public Guest() {
	}

	public Guest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Guest(String gym, String pool, String restaurant, String sauna, String cinema) {
		this.gym = gym;
		this.pool = pool;
		this.restaurant = restaurant;
		this.sauna = sauna;
		this.cinema = cinema;
	}

	public Guest(String IDnumber) {
		this.IDnumber = IDnumber;
	}
	//Vojo nastavi ;)
	
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
