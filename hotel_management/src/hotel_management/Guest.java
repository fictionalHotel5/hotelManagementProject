package hotel_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest {

	
	
	
			static Connection connection;
			static Scanner input = new Scanner(System.in);
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
		private int numofdays;
		private int gym;
		private int pool;
		private int restaurant;
		private int sauna;
		private int cinema;
		private java.util.Date timecheckedin;
		private boolean status;

		
		// constructor to return list of active users
		public Guest(String name, String surname, String gender, String idnumber, int age, int roomnumber,
				String roomtype) {
			
			this.username = name.toLowerCase()+(10+(int)(Math.random()*99));
			this.password=""+(1000+(int)(Math.random()*9000));
			this.name = name;
			this.surname = surname;
			this.gender = gender;
			this.IDnumber = idnumber;
			this.age = age;
			this.roomNumber = roomnumber;
			this.roomType = roomtype;	
			timecheckedin= new java.util.Date();

		}
		
		// no-arg construcator
		public Guest() {
		}

		// constructor to change status of user in databse
		public Guest(String username, boolean status) {
			this.username = username;
			this.status = status;
		}
		
		public Guest(String username, String password) {
			this.username = username;
			this.password = password;
		}
		

		private String getUsername() {
			return username;
		}

		private String getPassword() {
			return password;
		}

		public Guest(int gym, int pool, int restaurant, int sauna, int cinema) {
			this.gym = gym;
			this.pool = pool;
			this.restaurant = restaurant;
			this.sauna = sauna;
			this.cinema = cinema;
		}

		public Guest(String IDnumber) {
			this.IDnumber = IDnumber;
		}

		/** return boolean status */
		public boolean getStatus() {
			return status;
		}

		/** sets status tp true or false */
		public void setActive(boolean status) {
			this.status = status;
		}

		
		
		//Vojo nastavi ;)
	
	
	
		/**@Jasmina*/
	public  void displayMenu() throws SQLException {

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
	
	/**@Jasmina*/
	public  void checkBalance() throws SQLException {
		
		/**prepravit kad dzeno zavrsi tabelu*/
		String query="SELECT numofdays,gym,pool,restaurant,cinema,sauna FROM hotel WHERE username LIKE '" + getUsername() +"'";
		connection = connect();
		PreparedStatement stmt = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = stmt.executeQuery(query);
		
		NumberFormat nf=NumberFormat.getCurrencyInstance();
		
		//kad dzeno uradi tabelu
		int balans=0;
		String formatedPrice=nf.format(balans);
		
		System.out.println("Your balans : " + formatedPrice + balans );
		
		//kad dzeno uradi tabelu
		System.out.println();
	
	}

	/**@Jasmina*/
	@SuppressWarnings("resource")
	public  void newServiceOrder() throws SQLException {

		String query="SELECT gym,pool,restaurant,cinema,sauna FROM hotel WHERE username LIKE '" + getUsername() +"'";
		connection = connect();
		PreparedStatement stmt = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet result = stmt.executeQuery(query);
		
		System.out.println("Please select which service you want to order: ");
		System.out.println("1.Sauna");
		System.out.println("2.Pool");
		System.out.println("3.Restaurant");
		System.out.println("4.Gym");
		System.out.println("5.Cinema");
		System.out.println("6.Exit menu");
		
		connection = connect();
		
		int user = unosInt(input);

		/**add 1 to the current value of the chosen service*/
		switch (user) {
		case 1:
			
			int saunaServise=result.getInt("sauna") + 1;
			result = stmt.executeQuery("UPDATE sauna = '" + saunaServise + "' WHERE username LIKE '" + getUsername() +"'");
			System.out.println("Your reservation for sauna is complete!");
			break;
		case 2:
			int poolServise=result.getInt("pool") + 1;
			result = stmt.executeQuery("UPDATE pool = '" + poolServise + "' WHERE username LIKE '" + getUsername() +"'");
			System.out.println("Your reservation for pool is complete!");
			break;
		case 3:
			int restaurantServise=result.getInt("restaurant") + 1;
			result = stmt.executeQuery("UPDATE restaurant = '" + restaurantServise + "' WHERE username LIKE '" + getUsername() +"'");
			System.out.println("Your reservation for restaurant is complete!");
			break;
		case 4:
			int gymServise=result.getInt("gym") + 1;
			result = stmt.executeQuery("UPDATE gym = '" + gymServise + "' WHERE username LIKE '" + getUsername() +"'");
			System.out.println("Your reservation forgym is complete!");
			break;
		case 5:
			int cinemaServise=result.getInt("cinema") + 1;
			result = stmt.executeQuery("UPDATE cinema = '" + cinemaServise + "' WHERE username LIKE '" + getUsername() +"'");
			System.out.println("Your reservation for cinema is complete!");
			break;
		case 6:
			System.out.println("Exiting the service menu.......");
			displayMenu();
			break;
		default:
			System.out.println("Something went wrong.");
			newServiceOrder();
		}
		
	}

	public void newRoom() {
		
	}

	public void checkOut() {
	
	}

	public  void logOut() {
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
	/**dok dzenanovo ne bude gotovo*/
	public static Connection connect() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql11169375?autoReconnect=true&useSSL=false","root","root");
		return connection;

	}
	
}
