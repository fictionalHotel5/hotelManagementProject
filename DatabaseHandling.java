package hotelManagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseHandling {

	// Input connector za povezivanje na bazu podataka
	private static final String USERNAME = "root";
	private static final String PASSWORD = "antigona";
	private static final String DB = "hotel";

	boolean zauzeto = false;

	public static Connection connectToDB() throws SQLException {
		Connection connection = null;
		try {
			// SQL Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + DB, USERNAME, PASSWORD);
			System.out.println("Successfully connected to database!\n");
		} catch (Exception e) {
			System.err.println("Error! Check program!");
		}
		return connection;
	}

	// Admin username and password

	public Admin getAdminData(String username) {
		Admin admin = new Admin();
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM admin_users WHERE admin_username = '"
							+ username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				admin = new Admin(result.getString("admin_username"),
						result.getString("admin_password"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return admin;
	}

	// Guest username and password

	public Builder getGuestData(String username) {

		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement(
							"SELECT * FROM guest WHERE user_name = '"
									+ username + "';");
			ResultSet result = statement.executeQuery();

			return new Guest.Builder().userName(result.getString("user_name"))
					.password(result.getInt("user_password"));

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;

	}

	public void checkOut(String username) {
		// Method for moving guest from active to archive
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"REPLACE INTO archive"
							+ "SELECT * FROM guests WHERE user_name = \""
							+ username + "\";\n"
							+ "DELETE * FROM guest WHERE user_name = \""
							+ username + "\";"

			);
			statement.executeUpdate();

		} catch (Exception ex) {
			System.err.println(ex.toString());
		}
	}

	// read all guests

	public ArrayList<Builder> readAll() {
		ArrayList<Builder> listOfGuests = new ArrayList<>();
		int counter = 0;
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM guest;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Builder guest = new Guest.Builder()
						.firstName(result.getString("first_name"))
						.lastName(result.getString("last_name"))
						.gender(result.getString("gender"))
						.idNumber(result.getString("id_number"))
						.age(result.getInt("age"))
						.roomNumber(result.getInt("room_number"))
						.roomType(result.getString("room_type"))
						.userName(result.getString("user_name"))
						.password(result.getInt("user_password"))
						.balance(result.getDouble("balance"))
						.numOfDays(result.getInt("number_of_days"))
						.timesGymUsed(result.getInt("times_gym_used"))
						.timesPoolUsed(result.getInt("times_pool_used"))
						.timesRestaurantUsed(
								result.getInt("times_restaurant_used"))
						.timesSaunaUsed(result.getInt("times_sauna_used"))
						.timesCinemaUsed(result.getInt("times_cinema_used"))
						.isCheckedIn(result.getBoolean("guest_is_checked_in	"));

				listOfGuests.add(guest);

				counter++;
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return listOfGuests;

	}

	// guest username i password ako je u bazi by idnumber

	public Builder archiveCheck(String idnumber) {

		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM archive WHERE id_number = '" + idnumber
							+ "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				return new Guest.Builder().userName(
						result.getString("user_name")).password(
						result.getInt("user_password"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}

	// guest check in
	/*
	 * Added room & username checking before adding guest
	 */
	public void addGuest(Guest guest) {
		try {
			String userName = guest.getUserName();
			PreparedStatement statement2 = connectToDB()
					.prepareStatement(
							"SELECT * FROM guest WHERE user_name = '"
									+ userName + "';");
			ResultSet result1 = statement2.executeQuery();
			while (result1.next()) {
				String usernameDB = result1.getString("user_name");
				if (usernameDB.equals(userName)) {
					zauzeto = true;
					System.out.println("Username already taken!");
				}
			}
			int roomNumber = guest.getRoomNumber();
			PreparedStatement statement1 = connectToDB().prepareStatement(
					"SELECT * FROM guest WHERE room_number = '" + roomNumber
							+ "';");
			ResultSet result = statement1.executeQuery();
			while (result.next()) {
				int brojSobeDB = result.getInt("room_number");
				if (brojSobeDB == roomNumber) {
					zauzeto = true;
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "Room occupied!");
				}
			}
			if (zauzeto != true) {
				PreparedStatement statement = connectToDB()
						.prepareStatement(
								"INSERT INTO guest("
										+ "first_name, last_name, gender, id_number, age, room_number, room_type, time_checked_in,user_name,user_password, num_of_days, times_gym_used, times_pool_used, "
										+ "times_restaurant_used, times_sauna_used, times_cinema_used) VALUES('"
										+ guest.getUserName()
										+ "','"
										+ guest.getPassword()
										+ "','"
										+ guest.getFirstName()
										+ "','"
										+ guest.getLastName()
										+ "','"
										+ guest.getGender()
										+ "','"
										+ guest.getIdNumber()
										+ "',"
										+ guest.getAge()
										+ ","
										+ guest.getRoomNumber()
										+ ",'"
										+ guest.getRoomType()
										+ "','"
										+ guest.getTimeCheckedIn()
										+ "',"
										+ guest.getNumOfDays()
										+ ",'"
										+ guest.getTimesGymUsed()
										+ "','"
										+ guest.getTimesPoolUsed()
										+ "','"
										+ guest.getTimesRestaurantUsed()
										+ "','"
										+ guest.getTimesSaunaUsed()
										+ "','"
										+ guest.getTimesCinemaUsed()
										+ "')");
				statement.executeUpdate();
			}
			;
		} catch (Exception e) {
			System.err.println("Ne radi addGuest check.");
		}
	}

	// setting username for room number guest checked in
	public void inRoom(String username, int roomnumber) {
		try {
			if (zauzeto != true) {
				PreparedStatement statement = connectToDB().prepareStatement(
						"UPDATE room SET username = '" + username
								+ "' WHERE number = " + roomnumber + ";");
				statement.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// update servisa hotela
	public void checkServices(String username, String service) {
		try {
			PreparedStatement statement1a = connectToDB().prepareStatement(
					"UPDATE guest SET times_gym_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1b = connectToDB().prepareStatement(
					"UPDATE guest SET times_pool_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1c = connectToDB().prepareStatement(
					"UPDATE guest SET times_restaurant_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1d = connectToDB().prepareStatement(
					"UPDATE guest SET times_sauna_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1e = connectToDB().prepareStatement(
					"UPDATE guest SET times_cinema_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			statement1a.executeUpdate();
			statement1b.executeUpdate();
			statement1c.executeUpdate();
			statement1d.executeUpdate();
			statement1e.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// guest updates

	public void updateRoomNumber(String username, int roomnumber) {
		try {
			PreparedStatement statement1 = connectToDB().prepareStatement(
					"UPDATE room SET is_booked='0' WHERE room_nmb = '"
							+ roomnumber + "';");
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET room_number = " + roomnumber
							+ " WHERE user_name = '" + username + "';");
			statement1.executeUpdate();
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void updateRoomType(String username, String roomtype, int roomnumber) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET room_type = '" + roomtype
							+ "' WHERE user_name = '" + username + "';");

			PreparedStatement statement2 = connectToDB().prepareStatement(
					"UPDATE room SET user_name = '" + username
							+ "' WHERE room_number = " + roomnumber + ";");
			statement.executeUpdate();

			statement2.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void updateDays(String username, int numofdays) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET number_of_days = " + numofdays
							+ " WHERE user_name = '" + username + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// guest bill

	public Builder guestBill(String username) {
		Builder guest = null;
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement(
							"SELECT * FROM guest WHERE user_name = '"
									+ username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest.Builder()
						.firstName(result.getString("first_name"))
						.lastName(result.getString("last_name"))
						.gender(result.getString("gender"))
						.idNumber(result.getString("id_number"))
						.age(result.getInt("age"))
						.roomNumber(result.getInt("room_number"))
						.roomType(result.getString("room_type"))
						.userName(result.getString("user_name"))
						.password(result.getInt("user_password"))
						.balance(result.getDouble("balance"))
						.numOfDays(result.getInt("number_of_days"))
						.timesGymUsed(result.getInt("times_gym_used"))
						.timesPoolUsed(result.getInt("times_pool_used"))
						.timesRestaurantUsed(
								result.getInt("times_restaurant_used"))
						.timesSaunaUsed(result.getInt("times_sauna_used"))
						.timesCinemaUsed(result.getInt("times_cinema_used"))
						.isCheckedIn(result.getBoolean("guest_is_checked_in	"));

			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return guest;
	}

	// guset check out

	public void guestCheckOut(int roomNumber, String username) {
		try {
			PreparedStatement statement1 = connectToDB().prepareStatement(
					"UPDATE room SET is_booked = '0' WHERE room_nmb = '"
							+ roomNumber + "';");
			PreparedStatement statement = connectToDB().prepareStatement(
					"DELETE FROM guest WHERE user_name = '" + username + "' ;");
			statement.executeUpdate();
			statement1.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// arhiviranje gosta
	public void Archive(String idnumber, String username, int password) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"INSERT INTO archive(id_number, user_name, user_password) VALUES('"
							+ idnumber + "','" + username + "','" + password
							+ "')");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// notifikacija
	public void notify(String username) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"INSERT INTO notifications(view) VALUES('" + username
							+ "')");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void notifyClear(String username) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"DELETE FROM notifications WHERE view = '" + username
							+ "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public HashSet<String> viewNot() {
		HashSet<String> notifications = new HashSet<String>();
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM notifications WHERE view IS NOT NULL ;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				notifications.add((result.getString("view")));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return notifications;
	}

	// Guest login / Logoff
	/** sets guest Status to 1 or 0, true or false */
	public void setStatus(String username, boolean status) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET active = " + status
							+ " WHERE user_name = '" + username + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public ArrayList<Guest> bindTable() {

		// creating a new list of type Guest
		ArrayList<Guest> list = new ArrayList<Guest>();
		// declaring a PreparedStatement and ResultSet
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement(
							"SELECT user_name, first_name, last_name, gender,"
									+ " id_number, age, room_number, room_type FROM guest WHERE active = 1 ");
			ResultSet result = statement.executeQuery();
			Guest guest = null;
			while (result.next()) {
				new Guest.Builder().firstName(result.getString("first_name"))
						.lastName(result.getString("last_name"))
						.gender(result.getString("gender"))
						.idNumber(result.getString("id_number"))
						.age(result.getInt("age"))
						.roomNumber(result.getInt("room_number"))
						.roomType(result.getString("room_type"))
						.userName(result.getString("user_name"));

				list.add(guest);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		// return list
		return list;
	}

	/**
	 * @author Vojo
	 * */
	// metoda update podatke kod gosta prilikom promjene sobe
	public void updateGuest(String username, String roomType, int roomNumber,
			double balanc, int newNumberOfDays) {
		try {
			PreparedStatement statement1 = connectToDB().prepareStatement(
					"UPDATE guests SET room_type = '" + roomType
							+ "' WHERE user_name = '" + username + "';");

			PreparedStatement statement2 = connectToDB().prepareStatement(
					"UPDATE guests SET room_number = '" + roomNumber
							+ "' WHERE user_name = '" + username + "';");

			PreparedStatement statement3 = connectToDB().prepareStatement(
					"UPDATE guests SET balance = '" + balanc
							+ "' WHERE user_name = '" + username + "';");
			PreparedStatement statement4 = connectToDB().prepareStatement(
					"UPDATE guests SET number_of_days = '" + newNumberOfDays
							+ "' WHERE user_name = '" + username + "';");

			statement1.executeUpdate();
			statement2.executeUpdate();
			statement3.executeUpdate();
			statement4.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}

	}

	/**
	 * @author Vojo
	 * */
	// metoda update podatke kod Sobe, prilikom prelaska gosta iz jedne sobe u
	// drugu
	public void updateRoom(int oldRoomNumber, int newRoomNumber) {
		try {
			PreparedStatement statement1 = connectToDB().prepareStatement(
					"UPDATE rooms " + "SET is_booked = '1' "
							+ "WHERE room_nmb = '" + oldRoomNumber + "';");

			PreparedStatement statement2 = connectToDB().prepareStatement(
					"UPDATE rooms " + "SET is_booked = '0' "
							+ "WHERE room_nmb  = '" + newRoomNumber + "';");

			statement1.executeUpdate();
			statement2.executeUpdate();

		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
