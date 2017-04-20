
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest {

	static Scanner input = new Scanner(System.in);

	/**
	 * @author Sipo
	 */
	private String name;
	private String surname;
	private String gender;
	private String IDnumber;
	private int age;
	private int roomNumber;
	private String roomType;
	private String timeCheckedin;
	private String username;
	private int password;
	private double balance;
	private int numOfDays;
	private int timesGymUsed;
	private int timesPoolUsed;
	private int timesRestaurantUsed;
	private int timesSaunaUsed;
	private int timesCinemaUsed;
	private boolean isCheckedIn;

	/** constructor to return list of active users */
	public Guest(String name, String surname, String gender, String idnumber, int age, int roomnumber,
			String roomtype) {

		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.IDnumber = idnumber;
		this.age = age;
		this.roomNumber = roomnumber;
		this.roomType = roomtype;
		this.timeCheckedin = new java.util.Date() + "";
		this.balance = 0;
		this.isCheckedIn = true;

		this.username = name.toLowerCase() + (int) (Math.random() * 89 + 10);
		this.password = (int) (Math.random() * 8999 + 1000);

		this.numOfDays = 0;
		this.timesGymUsed = 0;
		this.timesPoolUsed = 0;
		this.timesCinemaUsed = 0;
		this.timesRestaurantUsed = 0;
		this.timesSaunaUsed = 0;

	}

	public Guest(String name, String surname, String gender, String idnumber, int age, int roomnumber, String roomtype,
			String time_check, String username, int password, double balance, int numDay, int gym, int pool,
			int restourant, int sauna, int cinema, boolean isCheckedIn) {

		// Constructor for loading from database
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.IDnumber = idnumber;
		this.age = age;
		this.roomNumber = roomnumber;
		this.roomType = roomtype;
		this.timeCheckedin = time_check;
		this.password = password;
		this.balance = balance;
		this.numOfDays = numDay;
		this.timesGymUsed = gym;
		this.timesPoolUsed = pool;
		this.timesCinemaUsed = cinema;
		this.timesRestaurantUsed = restourant;
		this.timesSaunaUsed = sauna;
		this.isCheckedIn = isCheckedIn;

	}

	
	/**
	 * @ author vojo @ param isCheckedIn
	 */
	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getTimeCheckedin() {
		return timeCheckedin;
	}

	public void setTimeCheckedin(String timeCheckedin) {
		this.timeCheckedin = timeCheckedin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addBalance(double balance) {
		this.balance += balance;
	}

	public int getNumOfDays() {
		return numOfDays;
	}

	public void setNumOfDays(int numOfDays) {
		this.numOfDays = numOfDays;
	}

	public int getTimesGymUsed() {
		return timesGymUsed;
	}

	public void setTimesGymUsed(int timesGymUsed) {
		this.timesGymUsed = timesGymUsed;
	}

	public int getTimesPoolUsed() {
		return timesPoolUsed;
	}

	public void setTimesPoolUsed(int timesPoolUsed) {
		this.timesPoolUsed = timesPoolUsed;
	}

	public int getTimesRestaurantUsed() {
		return timesRestaurantUsed;
	}

	public void setTimesRestaurantUsed(int timesRestaurantUsed) {
		this.timesRestaurantUsed = timesRestaurantUsed;
	}

	public int getTimesSaunaUsed() {
		return timesSaunaUsed;
	}

	public void setTimesSaunaUsed(int timesSaunaUsed) {
		this.timesSaunaUsed = timesSaunaUsed;
	}

	public int getTimesCinemaUsed() {
		return timesCinemaUsed;
	}

	public void setTimesCinemaUsed(int timesCinemaUsed) {
		this.timesCinemaUsed = timesCinemaUsed;
	}

	public String getIDnumber() {
		return IDnumber;
	}

	public String getUsername() {
		return username;
	}

	public int getPassword() {
		return password;
	}

	public int isCheckedIn() {
		if (isCheckedIn)
			return 1;
		else
			return 0;
	}

	/** constructor to change status of user in database */
	public Guest(String username, boolean status) {
		this.username = username;
		this.isCheckedIn = status;
	}

	/** return boolean status */
	public boolean getStatus() {
		return isCheckedIn;
	}

	/** sets status tp true or false */
	public void setActive(boolean status) {
		this.isCheckedIn = status;
	}

	/** no-arg construcator */
	public Guest() {
	}

	public Guest(String username, int password) {
		this.username = username;
		this.password = password;
	}

	public Guest(int gym, int pool, int restaurant, int sauna, int cinema) {
		this.timesGymUsed = gym;
		this.timesPoolUsed = pool;
		this.timesRestaurantUsed = restaurant;
		this.timesSaunaUsed = sauna;
		this.timesCinemaUsed = cinema;
	}

	public Guest(String IDnumber) {
		this.IDnumber = IDnumber;
	}

	/** @Jasmina */
	public void displayMenu(ArrayList<Room> allRooms) throws SQLException {

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
			newServiceOrder(allRooms);
			break;
		case 3:
			newRoom(allRooms);
			break;
		case 4:
			checkOut();
			break;
		case 5:
			logOut();
			break;
		default:
			System.out.println("Something went wrong.");
			System.exit(0);
		}

	}

	/** @Jasmina */
	/** method for user's check of current balance and all used services */
	public void checkBalance() throws SQLException {

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String formatedPrice = nf.format(getBalance());

		System.out.println("Your balans for : " + getNumOfDays() + " days is: " + formatedPrice + getBalance());

		if ((getTimesGymUsed() + getTimesPoolUsed() + getTimesRestaurantUsed() + getTimesSaunaUsed()
				+ getTimesCinemaUsed()) > 0) {
			System.out.println("Services you are using :");
		}
		if (getTimesGymUsed() > 0) {
			System.out.println("Gym - 10 KM per day.");
		}
		if (getTimesPoolUsed() > 0) {
			System.out.println("Pool - 10 KM per day.");
		}
		if (getTimesRestaurantUsed() > 0) {
			System.out.println("Restaurant - 20 KM per day. ");
		}
		if (getTimesSaunaUsed() > 0) {
			System.out.println("Sauna - 10 KM per day.");
		}
		if (getTimesCinemaUsed() > 0) {
			System.out.println("Cinema - 10 KM per day.");
		}
		System.out.println();
		System.exit(0);

	}

	/** @Jasmina method for ordering new services */
	public void newServiceOrder(ArrayList<Room>allRooms) throws SQLException {

		System.out.println("Please select which service you want to order: ");
		System.out.println("1.Sauna");
		System.out.println("2.Pool");
		System.out.println("3.Restaurant");
		System.out.println("4.Gym");
		System.out.println("5.Cinema");
		System.out.println("6.Exit menu");

		int user = unosInt(input);

		/** add 1 to the current value of the chosen service
		 * add cost of the service to the balance 
		 * */
		switch (user) {
		case 1:
			setTimesSaunaUsed(getTimesSaunaUsed() + 1);
			addBalance(10);
			System.out.println("Your reservation for sauna is complete!");
			break;
		case 2:
			setTimesPoolUsed(getTimesPoolUsed() + 1);
			addBalance(10);
			System.out.println("Your reservation for pool is complete!");
			break;
		case 3:
			setTimesRestaurantUsed(getTimesRestaurantUsed() + 1);
			addBalance(20);
			System.out.println("Your reservation for restaurant is complete!");
			break;
		case 4:
			setTimesGymUsed(getTimesGymUsed() + 1);
			addBalance(10);
			System.out.println("Your reservation for gym is complete!");
			break;
		case 5:
			setTimesCinemaUsed(getTimesCinemaUsed() + 1);
			addBalance(10);
			System.out.println("Your reservation for cinema is complete!");
			break;
		case 6:
			System.out.println("Exiting the service menu.......");
			System.exit(0);
			break;
		default:
			System.out.println("Something went wrong.");
			newServiceOrder(allRooms);
		}

	}

		/**
	 * @author vojo
	 * 
	 * */
	public void newRoom(ArrayList<Room> allRooms) throws SQLException {

		System.out.println("Please select which room you want to order: ");
		System.out.println("1.SINGLE ROOM");
		System.out.println("2.DOUBLE ROOM");
		System.out.println("3.APARTMENT");
		System.out.println("4.Exit menu");

		int user = unosInt(input);

		switch (user) {

		case 1:
			System.out.println("Available single rooms:");

			ArrayList<Room> availableSingleRooms = new Room()
					.allAvailableRooms(allRooms);

			if (availableSingleRooms.isEmpty())
				System.out
						.println("Currently we do not have an empty SINGLE ROOM.");
			else {
				System.out.println("Enter the number of room, which you want:");

				int newRoom = unosInt(input);
				boolean check = false;

				for (Room room : availableSingleRooms) {
					if (room.getRoomNumber() == newRoom) {
						System.out.println("Enter the number of days:");
						this.addBalance(20 * unosInt(input));

						this.setRoomNumber(newRoom);
						System.out
								.println("Guest has just changed the room number in: "
										+ newRoom);
						check = true;

						room.setAvailable(false);
					}
				}
				if (!check)
					System.out.println("You did not select an available room!");
			}

			break;
		case 2:
			System.out.println("Available double rooms:");

			ArrayList<Room> availableDoubleRooms = new Room()
					.allAvailableRooms(allRooms);

			if (availableDoubleRooms.isEmpty())
				System.out
						.println("Currently we do not have an empty double room.");
			else {
				System.out.println("Enter the number of room, which you want:");

				int newRoom = unosInt(input);
				boolean check = false;

				for (Room room1 : availableDoubleRooms) {
					if (room1.getRoomNumber() == newRoom) {
						
						System.out.println("Enter the number of days:");
						this.addBalance(40 * unosInt(input));

						this.setRoomNumber(newRoom);
						System.out
								.println("Guest has just changed the room number in: "
										+ newRoom);
						check = true;

						room1.setAvailable(false);
					}
				}
				if (!check)
					System.out.println("You did not select an available room!");
			}

			break;
		case 3:
			System.out.println("Available apartment:");

			ArrayList<Room> availableApartment = new Room().allAvailableRooms(
					allRooms);

			if (availableApartment.isEmpty())
				System.out
						.println("Currently we do not have an empty apartment.");
			else {
				System.out.println("Enter the number of room, which you want:");

				int newRoom = unosInt(input);
				boolean check = false;

				for (Room room : availableApartment) {
					if (room.getRoomNumber() == newRoom) {
						System.out.println("Enter the number of days:");
						this.addBalance(60 * unosInt(input));

						this.setRoomNumber(newRoom);
						System.out
								.println("Guest has just changed the room number in: "
										+ newRoom);
						check = true;
						room.setAvailable(false);
					}
				}
				if (!check)
					System.out
							.println("You did not select an available apartment!");
			}

			break;

		case 4:
			System.out.println("Exiting the service menu.......");
			displayMenu(allRooms);
			break;
		default:
			System.out.println("Something went wrong.");
			newServiceOrder(allRooms);
		}

	}

	/** @Vojo */
	public void checkOut() {
		System.out.println("Your account is currently " + this.balance + " KM");
		this.setCheckedIn(false);
		System.exit(0);
	}

	/** @Vojo */
	public void logOut() {
		System.out.println("You're now logged out! ");
		System.exit(0);
	}

	/** @Jasmina method for user's input */
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
