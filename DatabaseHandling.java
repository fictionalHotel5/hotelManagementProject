

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandling {

	/*
	 * @author Dzeno
	 */

	public static Connection getConnection(Connection connection) {
		// Method for establishing connection to database

		try {
			// Connecting to db hotel
			return connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hotel", "root", "");

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public static void loadAdminDB(ArrayList<Admin> list_of_admins,
			Statement statement) throws SQLException {

		// METHOD FOR COPYING DATA FROM DATABASE, CREATING ADMIN OBJECTS AND
		// PLACING THEM INTO ARRAYLIST
		String query = "SELECT * FROM admin_users;";
		ResultSet result = statement.executeQuery(query);
		try {
			// creating objects
			while (result.next()) {
				Admin admin = new Admin(result.getString("admin_username"),
						result.getString("admin_password"));
				list_of_admins.add(admin);

			}
		} catch (Exception ex) {
			System.out.println("Exception");
		}

	}

	public static void loadGuestDB(ArrayList<Guest> list_of_guests,
			Statement statement) throws SQLException {

		// METHOD FOR COPYING DATA FROM GUEST DATABASE AND CREATING GUEST
		// OBJECTS AND PLACING THEM INTO LIST

		String query = "SELECT * FROM guests;";
		ResultSet result = statement.executeQuery(query);

		try {
			while (result.next()) {
				Guest guest = new Guest(result.getString("first_name"),
						result.getString("last_name"),
						result.getString("gender"),
						result.getString("id_number"), result.getInt("age"),
						result.getInt("room_number"),
						result.getString("room_type"),
						result.getString("timeOfCheckIn"),
						result.getString("user_name"),
						result.getInt("user_password"),
						result.getDouble("balance"),
						result.getInt("number_of_days"),
						result.getInt("times_gym_used"),
						result.getInt("times_pool_used"),
						result.getInt("times_restaurant_used"),
						result.getInt("times_sauna_used"),
						result.getInt("times_cinema_used"),
						result.getBoolean("guest_is_checked_in"));
				list_of_guests.add(guest);
			}

		} catch (Exception ex) {
			System.out.println("Exception");
		}

	}

	public static void updateGuestDB(ArrayList<Guest> list_of_guests,
			Statement statement) throws SQLException {

		// METHOD FOR UPDATING DATABASE

		for (int i = 0; i < list_of_guests.size(); i++) {
			Guest guest = list_of_guests.get(i);

			String query = "INSERT IGNORE INTO guests VALUES(\""
					+ guest.getName() + "\", \"" + guest.getSurname()
					+ "\", \"" + guest.getGender() + "\", \""
					+ guest.getIDnumber() + "\", \"" + guest.getAge()
					+ "\", \"" + guest.getRoomNumber() + "\", \""
					+ guest.getRoomType() + "\", \"" + guest.getTimeCheckedin()
					+ "\", \"" + guest.getUsername() + "\", \""
					+ guest.getPassword() + "\", \"" + guest.getBalance()
					+ "\", \"" + guest.getNumOfDays() + "\", \""
					+ guest.getTimesGymUsed() + "\", \""
					+ guest.getTimesPoolUsed() + "\", \""
					+ guest.getTimesRestaurantUsed() + "\", \""
					+ guest.getTimesSaunaUsed() + "\", \""
					+ guest.getTimesCinemaUsed() + "\", \""
					+ guest.isCheckedIn() + "\");";

			statement.execute(query);

		}

	}

	public static void loadRoomDB(ArrayList<Room> list_of_rooms,
			Statement statement) throws SQLException {
		
		
		//METHOD FOR LOADING DATA FROM DATABASE INTO LIST
		String query = "SELECT * FROM rooms;";
		ResultSet set = statement.executeQuery(query);

		while (set.next()) {
			Room room = new Room(set.getInt("room_nmb"),
					set.getBoolean("is_booked"));
		}

	}

	public static void updateRoomDB(ArrayList<Room> list_of_rooms,
			Statement statement) throws SQLException {
		for (int i = 0; i < list_of_rooms.size(); i++) {
			
			//METHOD FOR UPDATING DATABASE FOR ROOM
			Room room = list_of_rooms.get(i);
			String query = "INSERT IGNORE INTO rooms VALUES("
					+ room.getRoomNumber() + ", \"" + room.getRoomType()
					+ "\", " + room.isAvailable() + ", " + room.getCostPerDay()
					+ ");";

			statement.execute(query);
		}
	}
			/**
	 * @author Vojo
	 * */
	// METHOD FOR COPYING DATA FROM ROOMS WHERE is_booked=1(available), AND
	// PUT NUBER ROOM IN LIST
	public static void loadAllAvailableRooms(ArrayList<Room> availableRooms,
			java.sql.Statement statement) throws SQLException {

		String query = "SELECT * " + "FROM hotel.rooms " + "WHERE is_booked=1;";
		ResultSet result = ((java.sql.Statement) statement).executeQuery(query);

		try {
			while (result.next()) {

				availableRooms.add(new Room(result.getInt("room_nmb"), result
						.getBoolean("is_booked")));
			}

		} catch (Exception ex) {
			System.out.println("Exception");
		}

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
