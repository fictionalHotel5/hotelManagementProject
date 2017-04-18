package hotelManagementProject;

import java.sql.*;
import java.util.ArrayList;

public class Database_handling {

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

	public static void load_admindb(ArrayList<Admin> list_of_admins,
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

	public static void load_guestdb(ArrayList<Guest> list_of_guests,
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

	public static void update_guestdb(ArrayList<Guest> list_of_guests,
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

	public static void load_roomdb(ArrayList<Room> list_of_rooms,
			Statement statement) throws SQLException {
		
		
		//METHOD FOR LOADING DATA FROM DATABASE INTO LIST
		String query = "SELECT * FROM rooms;";
		ResultSet set = statement.executeQuery(query);

		while (set.next()) {
			Room room = new Room(set.getInt("room_nmb"),
					set.getBoolean("is_booked"));
		}

	}

	public static void update_roomdb(ArrayList<Room> list_of_rooms,
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
}
