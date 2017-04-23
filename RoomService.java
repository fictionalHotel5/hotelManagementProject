import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RoomService {

	static Scanner input = new Scanner(System.in);

	/**
	 * @Jasmina method for displaying menu for user
	 */
	public void displayMenu(ArrayList<Room> allRooms, Guest guest)
			throws SQLException {

		System.out.println("Please choose from the following menu options:\n");
		System.out.println("1.Check balance");
		System.out.println("2.New service order");
		System.out.println("3.New room");
		System.out.println("4.Check out");
		System.out.println("5.Log out");

		int user = unosInt(input);

		switch (user) {
		case 1:
			checkBalance(guest);
			break;
		case 2:
			newServiceOrder(allRooms, guest);
			break;
		case 3:
			newRoom(allRooms, guest);
			break;
		case 4:
			checkOut(guest);
			break;
		case 5:
			logOut();
			break;
		default:
			System.out.println("Something went wrong.");
			System.exit(0);
		}

	}

	/**
	 * @Jasmina method for user's check of current balance and all used services
	 */
	public void checkBalance() throws SQLException {

		NumberFormat nf = NumberFormat.getCurrencyInstance();

		String formatedPrice = nf.format(guest.getBalance());

		System.out.println("Your balans for : " + guest.getNumOfDays()
				+ " days is: " + formatedPrice + guest.getBalance());

		if ((guest.getTimesGymUsed() + guest.getTimesPoolUsed()
				+ guest.getTimesRestaurantUsed() + guest.getTimesSaunaUsed() + guest
					.getTimesCinemaUsed()) > 0) {
			System.out.println("Services you are using :");
		}
		if (guest.getTimesGymUsed() > 0) {
			System.out.println("Gym - 10 KM per day.");
		}
		if (guest.getTimesPoolUsed() > 0) {
			System.out.println("Pool - 10 KM per day.");
		}
		if (guest.getTimesRestaurantUsed() > 0) {
			System.out.println("Restaurant - 20 KM per day. ");
		}
		if (guest.getTimesSaunaUsed() > 0) {
			System.out.println("Sauna - 10 KM per day.");
		}
		if (guest.getTimesCinemaUsed() > 0) {
			System.out.println("Cinema - 10 KM per day.");
		}
		System.out.println();
		System.exit(0);

	}

	/**
	 * @Jasmina method for ordering new services
	 */
	public void newServiceOrder(ArrayList<Room> allRooms, Guest guest)
			throws SQLException {

		System.out.println("Please select which service you want to order: ");
		System.out.println("1.Sauna");
		System.out.println("2.Pool");
		System.out.println("3.Restaurant");
		System.out.println("4.Gym");
		System.out.println("5.Cinema");
		System.out.println("6.Exit menu");

		int user = unosInt(input);

		/**
		 * add 1 to the current value of the chosen service add cost of the
		 * service to the balance
		 */
		switch (user) {
		case 1:
			guest.setTimesSaunaUsed(guest.getTimesSaunaUsed() + 1);
			guest.getBalance(10);
			System.out.println("Your reservation for sauna is complete!");
			break;
		case 2:
			guest.setTimesPoolUsed(guest.getTimesPoolUsed() + 1);
			guest.getBalance(10);
			System.out.println("Your reservation for pool is complete!");
			break;
		case 3:
			guest.setTimesRestaurantUsed(guest.getTimesRestaurantUsed() + 1);
			guest.getBalance(20);
			System.out.println("Your reservation for restaurant is complete!");
			break;
		case 4:
			guest.setTimesGymUsed(guest.getTimesGymUsed() + 1);
			guest.getBalance(10);
			System.out.println("Your reservation for gym is complete!");
			break;
		case 5:
			guest.setTimesCinemaUsed(guest.getTimesCinemaUsed() + 1);
			guest.getBalance(10);
			System.out.println("Your reservation for cinema is complete!");
			break;
		case 6:
			System.out.println("Exiting the service menu.......");
			System.exit(0);
			break;
		default:
			System.out.println("Something went wrong.");
			newServiceOrder(allRooms, guest);
		}

	}

		/**
	 * @author vojo
	 * 
	 */
	public void newRoom(ArrayList<Room> allAvailableRooms, Guest guest)
			throws SQLException {

		printNewRoomMeni();
		int user = unosInt(input);

		while (user > 4 || user < 1) {
			System.out.println("You only have the options[1-4]. Try again!");
			printNewRoomMeni();
			user = unosInt(input);

		}

		switch (user) {

		case 1:
			newSingleRoom(allAvailableRooms, guest);
			break;
		case 2:
			newDoubleleRoom(allAvailableRooms, guest);
			break;
		case 3:
			newAparment(allAvailableRooms, guest);

			break;

		case 4:
			System.out.println("Exiting the service menu.......");
			
			break;

		}

	}

	/**
	 * @author vojo
	 * @throws SQLException
	 * 
	 */
	private void newAparment(ArrayList<Room> allAvailableRooms, Guest guest)
			throws SQLException {
		if (allAvailableRooms.isEmpty())
			System.out.println("Currently we do not have an empty APARTMENTS.");
		else {
			printAvailableRoom(allAvailableRooms, 3);
			System.out.println("Enter the number of room :");

			int numberNewRoom = unosInt(input);

			while (numberNewRoom > 100 || numberNewRoom < 90
					|| isOcupated(allAvailableRooms, numberNewRoom)) {
				System.out.println("Enter the number of room which are free:");
				numberNewRoom = unosInt(input);
			}

			System.out.println("Enter the number of days:");
			// Gost update roomNumber, rommType, balanc.
			int numberOfDays = unosInt(input);
			double newBalance = (guest.getBalance() + (numberOfDays * 60));

			new Database().updateGuest(guest.getUserName(), "Single room",
					numberNewRoom, newBalance, numberOfDays);
			System.out.println("Guest has just changed the room number in: "
					+ numberNewRoom);

			new Database().updateRoom(guest.getRoomNumber(), numberNewRoom);
			new Database().updateGuest(guest.getUserName(), "Single room",
					numberNewRoom, newBalance, numberOfDays);

		}
	}

	/**
	 * @author vojo
	 * @throws SQLException
	 * 
	 */
	private void newSingleRoom(ArrayList<Room> allAvailableRooms, Guest guest)
			throws SQLException {

		if (allAvailableRooms.isEmpty())

			System.out
					.println("Currently we do not have an empty SINGLE ROOM.");
		else {
			printAvailableRoom(allAvailableRooms, 1);
			System.out.println("Enter the number of room :");
			int numberNewRoom = unosInt(input);
			while (numberNewRoom > 45 || numberNewRoom < 1
					|| (isOcupated(allAvailableRooms, numberNewRoom))) {
				System.out.println("Enter the number of room which are free:");
				numberNewRoom = unosInt(input);
			}

			System.out.println("Enter the number of days:");
			// Gost update roomNumber, rommType, balanc.
			int numberOfDays = unosInt(input);
			double newBalance = (guest.getBalance() + (numberOfDays * 20));

			new Database().updateGuest(guest.getUserName(), "Single room",
					numberNewRoom, newBalance, numberOfDays);
			System.out.println("Guest has just changed the room number in: "
					+ numberNewRoom);
			 
			new Database().updateRoom(guest.getRoomNumber(), numberNewRoom);
			new Database().updateGuest(guest.getUserName(), "Single room",
					numberNewRoom, newBalance, numberOfDays);
			 

		}
	}

	/**
	 * @author vojo
	 * 
	 */
	private void newDoubleleRoom(ArrayList<Room> allAvailableRooms, Guest guest)
			throws SQLException {
		if (allAvailableRooms.isEmpty())
			System.out
					.println("Currently we do not have an empty DOUBLE ROOM.");
		else {
			printAvailableRoom(allAvailableRooms, 2);
			System.out.println("Enter the number of room :");
			int numberNewRoom = unosInt(input);
			while (numberNewRoom > 89 || numberNewRoom <= 45
					|| (isOcupated(allAvailableRooms, numberNewRoom))) {
				System.out.println("Enter the number of room which are free:");
				numberNewRoom = unosInt(input);
			}

			System.out.println("Enter the number of days:");
			// Gost update roomNumber, rommType, balanc.
			int numberOfDays = unosInt(input);
			double newBalance = (guest.getBalance() + (numberOfDays * 40));

			new Database().updateGuest(guest.getUserName(), "Single room",
					numberNewRoom, newBalance, numberOfDays);
			System.out.println("Guest has just changed the room number in: "
					+ numberNewRoom);

			new Database().updateRoom(guest.getRoomNumber(), numberNewRoom);
			new Database().updateGuest(guest.getUserName(), "Double room",
					numberNewRoom, newBalance, numberOfDays);

		}
	}

	private void printNewRoomMeni() {
		System.out.println("Please select which room you want to order: ");
		System.out.println("1.SINGLE ROOM");
		System.out.println("2.DOUBLE ROOM");
		System.out.println("3.APARTMENT");
		System.out.println("4.Exit menu");
	}

	/**
	 * @Vojo
	 * */
	private boolean isOcupated(ArrayList<Room> all, int numberRoom) {
		ArrayList<Integer> list = new ArrayList<>();
		for (Room room : all) {
			list.add(room.getRoomNumber());

		}

		return !list.contains(numberRoom);
	}

	/**
	 * @Vojo
	 * */

	private void printAvailableRoom(ArrayList<Room> listRoms, int numberRoom) {
		switch (numberRoom) {
		case 1:
			System.out.println("Available single rooms:");
			for (int i = 0; i < listRoms.size(); i++) {
				if (listRoms.get(i).getRoomNumber() <= 45)
					System.out.print(listRoms.get(i).getRoomNumber() + " ");

			}

			System.out.println();

			break;
		case 2:
			System.out.println("Available double rooms:");

			for (Room room : listRoms) {

				if (room.getRoomNumber() > 45 && room.getRoomNumber() < 91)
					System.out.print(room.getRoomNumber() + " ");
			}
			System.out.println();

			break;
		case 3:
			System.out.println("Available APARTMENTS:");

			for (Room room : listRoms) {
				if (room.getRoomNumber() > 89)
					System.out.print(room.getRoomNumber() + " ");
			}
			System.out.println();

			break;

		}
	}

	/** @Vojo */
	private void checkOut(Guest guest) {
		System.out.println("Your account is currently " + guest.getBalance()
				+ " KM");
		new Database().guestCheckOut(guest.getUserName());
	}

	/** @Vojo */
	private void logOut() {
		System.out.println("You're now logged out! ");

	}


	/**
	 * @Jasmina method for storing guest data into archive
	 */
	public String[] guestForArchive(Guest guest) {

		/** converting age data to String type so it could be stored in array */
		String[] zaArhiv = { guest.getName(), guest.getSurname(),
				guest.getGender(), guest.getIDnumber(),
				String.valueOf(guest.getAge()) };

		return zaArhiv;
	}

	/**
	 * @Jasmina method for user's input
	 */
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
